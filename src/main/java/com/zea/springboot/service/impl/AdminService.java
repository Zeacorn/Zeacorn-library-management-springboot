package com.zea.springboot.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zea.springboot.controller.exception.ServiceException;
import com.zea.springboot.controller.request.BaseRequest;
import com.zea.springboot.controller.request.LoginRequest;
import com.zea.springboot.controller.request.UserPageRequest;
import com.zea.springboot.dto.LoginDTO;
import com.zea.springboot.entity.Admin;
import com.zea.springboot.entity.Admin;
import com.zea.springboot.mapper.AdminMapper;
import com.zea.springboot.mapper.UserMapper;
import com.zea.springboot.service.IAdminService;
import com.zea.springboot.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AdminService implements IAdminService{
    @Autowired
    AdminMapper adminMapper;

    @Override
    public List<Admin> list() {
        return adminMapper.list();
    }

    @Override
    public PageInfo<Admin> page(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(),baseRequest.getPageSize());
        List<Admin> admins = adminMapper.listByCondition(baseRequest);
        return new PageInfo<>(admins);
    }

    @Override
    public void save(Admin admin) {
        admin.setPassword(SecureUtil.md5(admin.getPassword())); //设置md5加密
        adminMapper.save(admin);
    }

    @Override
    public Admin getById(Integer id) {
        return adminMapper.getById(id);
    }

    @Override
    public void update(Admin admin) {
        admin.setUpdatetime(new Date());
        adminMapper.updateById(admin);
    }

    @Override
    public void deleteById(Integer id) {
        adminMapper.deleteById(id);
    }

    @Override
    public LoginDTO login(LoginRequest loginRequest) {
        loginRequest.setPassword(SecureUtil.md5(loginRequest.getPassword()));
        Admin admin = adminMapper.getByUsernameAndPassword(loginRequest);
        if(admin == null){
            throw new ServiceException("用户名或密码错误");
        }
        LoginDTO loginDTO = new LoginDTO();
        BeanUtils.copyProperties(admin,loginDTO);
        return loginDTO;
    }


}
