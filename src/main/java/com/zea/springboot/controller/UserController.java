package com.zea.springboot.controller;

import com.zea.springboot.common.Result;
import com.zea.springboot.controller.request.UserPageRequest;
import com.zea.springboot.entity.User;
import com.zea.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService iUserService;

    @PostMapping("/save")
    public  Result save(@RequestBody User user){
        iUserService.save(user);
        return Result.success();
    }

    @PutMapping("/update")
    public  Result update(@RequestBody User user){
        iUserService.update(user);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        User user = iUserService.getById(id);
        return Result.success(user);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        iUserService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/list")
    public Result list(){
        List<User> users = iUserService.list();
        return Result.success(users);
    }
    @GetMapping("/page")
    public Result page(UserPageRequest userPageRequest){
        return Result.success(iUserService.page(userPageRequest));
    }
}
