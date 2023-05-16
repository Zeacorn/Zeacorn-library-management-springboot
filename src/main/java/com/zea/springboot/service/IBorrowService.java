package com.zea.springboot.service;

import com.github.pagehelper.PageInfo;
import com.zea.springboot.controller.request.BaseRequest;
import com.zea.springboot.entity.Borrow;

import java.util.List;

public interface IBorrowService {
    List<Borrow> list();

    void save(Borrow borrow);

    PageInfo<Borrow> page(BaseRequest baseRequest);

    Borrow getById(Integer id);

    void update(Borrow borrow);

    void deleteById(Integer id);

}
