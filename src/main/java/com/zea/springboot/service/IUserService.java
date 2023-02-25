package com.zea.springboot.service;

import com.github.pagehelper.PageInfo;
import com.zea.springboot.controller.request.UserPageRequest;
import com.zea.springboot.entity.User;

import java.util.List;

public interface IUserService {
    List<User> list();

    PageInfo<User> page(UserPageRequest userPageRequest);

    void save(User user);

    User getById(Integer id);

    void update(User user);

    void deleteById(Integer id);
}
