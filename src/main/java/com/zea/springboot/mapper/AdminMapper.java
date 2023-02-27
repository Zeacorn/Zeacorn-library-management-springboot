package com.zea.springboot.mapper;

import com.zea.springboot.controller.request.BaseRequest;
import com.zea.springboot.controller.request.LoginRequest;
import com.zea.springboot.controller.request.PassWordRequest;
import com.zea.springboot.entity.Admin;
import com.zea.springboot.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {

    List<Admin> list();

    List<Admin> listByCondition(BaseRequest baseRequest);

    void save(Admin admin);

    Admin getById(Integer id);

    void updateById(Admin admin);

    void deleteById(Integer id);

    Admin getByUsernameAndPassword(LoginRequest loginRequest);

    boolean changePassWord(PassWordRequest passWordRequest);

}
