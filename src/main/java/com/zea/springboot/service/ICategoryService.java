package com.zea.springboot.service;

import com.github.pagehelper.PageInfo;
import com.zea.springboot.controller.request.BaseRequest;
import com.zea.springboot.controller.request.CategoryPageRequest;
import com.zea.springboot.entity.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> list();

    void save(Category category);

    PageInfo<Category> page(BaseRequest baseRequest);

    Category getById(Integer id);

    void update(Category category);

    void deleteById(Integer id);

}
