package com.zea.springboot.controller;

import com.zea.springboot.common.Result;
import com.zea.springboot.controller.request.AdminPageRequest;
import com.zea.springboot.controller.request.LoginRequest;
import com.zea.springboot.controller.request.PassWordRequest;
import com.zea.springboot.entity.Admin;
import com.zea.springboot.service.IAdminService;
import com.zea.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    IAdminService iAdminService;

    @PostMapping("/login")
    public  Result login(@RequestBody LoginRequest loginRequest){
        return Result.success(iAdminService.login(loginRequest));
    }

    @PutMapping("/password")
    public  Result password(@RequestBody PassWordRequest passWordRequest){
        iAdminService.changePassWord(passWordRequest);
        return Result.success();
    }

    @PostMapping("/save")
    public  Result save(@RequestBody Admin admin){
        iAdminService.save(admin);
        return Result.success();
    }

    @PutMapping("/update")
    public  Result update(@RequestBody Admin admin){
        iAdminService.update(admin);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        Admin admin = iAdminService.getById(id);
        return Result.success(admin);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        iAdminService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/list")
    public Result list(){
        List<Admin> users = iAdminService.list();
        return Result.success(users);
    }
    @GetMapping("/page")
    public Result page(AdminPageRequest adminPageRequest){
        return Result.success(iAdminService.page(adminPageRequest));
    }
}
