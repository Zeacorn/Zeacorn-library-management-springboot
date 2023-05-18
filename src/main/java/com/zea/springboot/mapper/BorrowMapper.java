package com.zea.springboot.mapper;

import com.zea.springboot.controller.request.BaseRequest;
import com.zea.springboot.entity.Borrow;
import com.zea.springboot.po.BorrowReturnCountPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BorrowMapper {

    List<Borrow> list();

    void save(Borrow borrow);

    List<Borrow> listByCondition(BaseRequest baseRequest);

    Borrow getById(Integer id);

    void update(Borrow borrow);

    void deleteById(Integer id);

    void updateStatus(Borrow borrow);

    List<BorrowReturnCountPO> getCountByTimeRange(@Param("timeRange") String timeRange, @Param("type") int type);

}
