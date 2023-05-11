package com.zea.springboot.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zea.springboot.controller.request.BaseRequest;
import com.zea.springboot.entity.Book;
import com.zea.springboot.mapper.BookMapper;
import com.zea.springboot.service.IBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class BookService implements IBookService {
    @Autowired
    BookMapper bookMapper;

    @Override
    public List<Book> list() {
        return bookMapper.list();
    }

    @Override
    public void save(Book book) {
        List<String> categories = book.getCategories();
        StringBuilder stringBuilder = new StringBuilder();
        if (CollUtil.isNotEmpty(categories)) {
            categories.forEach(v -> stringBuilder.append(v).append(" > "));
            book.setCategory(stringBuilder.toString().substring(0, stringBuilder.lastIndexOf(" > ")));
        }
        bookMapper.save(book);
    }

    @Override
    public PageInfo<Book> page(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(),baseRequest.getPageSize());
        List<Book> books = bookMapper.listByCondition(baseRequest);
        return new PageInfo<>(books);
    }

    @Override
    public Book getById(Integer id) {
        return bookMapper.getById(id);
    }

    @Override
    public void update(Book book) {
        book.setUpdateTime(new Date());
        bookMapper.update(book);
    }

    @Override
    public void deleteById(Integer id) {
        bookMapper.deleteById(id);
    }

}
