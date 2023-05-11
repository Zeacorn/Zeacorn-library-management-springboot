package com.zea.springboot.mapper;

import com.zea.springboot.controller.request.BaseRequest;
import com.zea.springboot.entity.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {

    List<Book> list();

    void save(Book book);

    List<Book> listByCondition(BaseRequest baseRequest);

    Book getById(Integer id);

    void update(Book category);

    void deleteById(Integer id);

}
