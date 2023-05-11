package com.zea.springboot.mapper;

import com.zea.springboot.controller.request.BaseRequest;
import com.zea.springboot.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {

    List<Category> list();

    void save(Category category);

    List<Category> listByCondition(BaseRequest baseRequest);

    Category getById(Integer id);

    void update(Category category);

    void deleteById(Integer id);

}
