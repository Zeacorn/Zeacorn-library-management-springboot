package com.zea.springboot.mapper;

import com.zea.springboot.controller.request.BaseRequest;
import com.zea.springboot.entity.Borrow;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BorrowMapper {

    List<Borrow> list();

    void save(Borrow borrow);

    List<Borrow> listByCondition(BaseRequest baseRequest);

    Borrow getById(Integer id);

    void update(Borrow borrow);

    void deleteById(Integer id);

}
