package com.zea.springboot.service;

import com.github.pagehelper.PageInfo;
import com.zea.springboot.controller.request.BaseRequest;
import com.zea.springboot.entity.Book;

import java.util.List;

public interface IBookService {
    List<Book> list();

    void save(Book book);

    PageInfo<Book> page(BaseRequest baseRequest);

    Book getById(Integer id);

    Book getByBookNo(String bookNo);

    void update(Book book);

    void deleteById(Integer id);

}
