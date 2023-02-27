package com.zea.springboot.service;

import com.github.pagehelper.PageInfo;
import com.zea.springboot.controller.request.BaseRequest;
import com.zea.springboot.controller.request.LoginRequest;
import com.zea.springboot.controller.request.PassWordRequest;
import com.zea.springboot.controller.request.UserPageRequest;
import com.zea.springboot.dto.LoginDTO;
import com.zea.springboot.entity.Admin;
import com.zea.springboot.entity.User;

import java.util.List;

public interface IAdminService {
    List<Admin> list();

    PageInfo<Admin> page(BaseRequest baseRequest);

    void save(Admin admin);

    Admin getById(Integer id);

    void update(Admin admin);

    void deleteById(Integer id);

    LoginDTO login(LoginRequest loginRequest);

    void changePassWord(PassWordRequest passWordRequest);

}
