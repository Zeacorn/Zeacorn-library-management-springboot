package com.zea.springboot.service.impl;

import cn.hutool.core.collection.CollUtil;
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
import com.zea.springboot.service.IBorrowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

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
        borrowMapper.save(borrow);
    }

    @Override
    public PageInfo<Borrow> page(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(),baseRequest.getPageSize());
        List<Borrow> borrows = borrowMapper.listByCondition(baseRequest);
        return new PageInfo<>(borrows);
    }

    @Override
    public Borrow getById(Integer id) {
        return borrowMapper.getById(id);
    }

    @Override
    public void update(Borrow borrow) {
        borrow.setUpdateTime(new Date());
        borrowMapper.update(borrow);
    }

    @Override
    public void deleteById(Integer id) {
        borrowMapper.deleteById(id);
    }

}
