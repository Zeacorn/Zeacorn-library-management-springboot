package com.zea.springboot.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zea.springboot.controller.exception.ServiceException;
import com.zea.springboot.controller.request.BaseRequest;
import com.zea.springboot.entity.Book;
import com.zea.springboot.entity.Borrow;
import com.zea.springboot.entity.User;
import com.zea.springboot.mapper.BookMapper;
import com.zea.springboot.mapper.BorrowMapper;
import com.zea.springboot.mapper.UserMapper;
import com.zea.springboot.po.BorrowReturnCountPO;
import com.zea.springboot.service.IBorrowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
public class BorrowService implements IBorrowService {
    @Autowired
    BorrowMapper borrowMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    BookMapper bookMapper;

    @Override
    public List<Borrow> list() {
        return borrowMapper.list();
    }

    @Override
    @Transactional
    public void save(Borrow borrow) {
        // 1. 校验用户是否存在
        String userId = borrow.getUserId();
        User user = userMapper.getByUserId(userId);
        if (Objects.isNull(user)) {
            throw new ServiceException("用户不存在");
        }

        //2. 校验图书的数量是否足够
        Book book = bookMapper.getByBookNo(borrow.getBookNo());
        if (Objects.isNull(book)) {
            throw new ServiceException("所借图书不存在");
        }
        if (book.getNums() < 1) {
            throw new ServiceException("图书数量不足");
        }
        //3. 更新图书的数量
        book.setNums(book.getNums() - 1);
        bookMapper.update(book);

        //4. 获取归还时间
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, borrow.getDay());
        date = calendar.getTime();
        borrow.setReturnTime(date);

        borrow.setStatus("未归还");
        borrowMapper.save(borrow);
    }

    @Override
    public PageInfo<Borrow> page(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(),baseRequest.getPageSize());
        List<Borrow> borrows = borrowMapper.listByCondition(baseRequest);
        for (Borrow borrow : borrows) {
            if (borrow.getStatus().equals("已归还")) continue;
            Date returnTime = borrow.getReturnTime();
            Date now = new Date();
            long getTime = returnTime.getTime() - now.getTime();
            long day = getTime / (1000 * 60 * 60 * 24);
            if (day < 0) {
                borrow.setStatus("已逾期");
            }
            else if (day <= 5 ) {
                borrow.setStatus("即将逾期");
            }

        }
        return new PageInfo<>(borrows);
    }

    @Override
    public Borrow getById(Integer id) {
        return borrowMapper.getById(id);
    }

    @Override
    public void update(Borrow borrow) {
        borrowMapper.update(borrow);
    }

    @Override
    public void deleteById(Integer id) {
        borrowMapper.deleteById(id);
    }

    @Override
    public void updateStatus(Borrow borrow) {
        borrow.setReturnDate(new Date());
        borrow.setStatus("已归还");
        borrowMapper.updateStatus(borrow);
    }

    @Override
    public Map<String, Object> getCountByTimeRange(String timeRange) {
        Map<String, Object> map = new HashMap<>();
        Date today = new Date();
        List<DateTime> dateRange;
        switch (timeRange) {
            case "week":
                dateRange = DateUtil.rangeToList(DateUtil.offsetDay(today, -6), today, DateField.DAY_OF_WEEK);
                break;
            case "month":
                dateRange = DateUtil.rangeToList(DateUtil.offsetDay(today, -29), today, DateField.DAY_OF_MONTH);
                break;
            case "month1":
                dateRange = DateUtil.rangeToList(DateUtil.offsetDay(today, -59), today, DateField.DAY_OF_MONTH);
                break;
            case "month2":
                dateRange = DateUtil.rangeToList(DateUtil.offsetDay(today, -89), today, DateField.DAY_OF_MONTH);
                break;
            default:
                dateRange = new ArrayList<>();
        }
        List<String> dateStrRange = datetimeToDateStr(dateRange);
        map.put("date", dateStrRange);
        List<BorrowReturnCountPO> borrowCount = borrowMapper.getCountByTimeRange(timeRange, 1);
        List<BorrowReturnCountPO> returnCount = borrowMapper.getCountByTimeRange(timeRange, 2);
        map.put("borrow", countList(borrowCount, dateStrRange));
        map.put("retur", countList(returnCount, dateStrRange));

        return map;
    }

    private List<String> datetimeToDateStr(List<DateTime> dateTimeList) {
        List<String> list = CollUtil.newArrayList();
        if (CollUtil.isEmpty(dateTimeList)) {
            return list;
        }
        for (DateTime dateTime : dateTimeList) {
            String date = DateUtil.formatDate(dateTime);
            list.add(date);
        }
        return list;
    }

    private List<Integer> countList(List<BorrowReturnCountPO> countPOList, List<String> dateRange) {
        List<Integer> list = CollUtil.newArrayList();
        if (CollUtil.isEmpty(countPOList)) {
            return list;
        }
        for (String date : dateRange) {
            Integer count = countPOList.stream().filter(countPO -> date.equals(countPO.getDate()))
                    .map(BorrowReturnCountPO::getCount).findFirst().orElse(0);
            list.add(count);
        }
        return list;
    }
}
