package com.zea.springboot.controller;

import com.zea.springboot.common.Result;
import com.zea.springboot.controller.request.BorrowPageRequest;
import com.zea.springboot.entity.Borrow;
import com.zea.springboot.service.IBorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borrow")
public class BorrowController {
    @Autowired
    IBorrowService iBorrowService;

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        Borrow borrow = iBorrowService.getById(id);
        return Result.success(borrow);
    }

    @PutMapping("/update")
    public Result update(@RequestBody Borrow borrow){
        iBorrowService.update(borrow);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        iBorrowService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/list")
    public Result list(){
        List<Borrow> borrows = iBorrowService.list();
        return Result.success(borrows);
    }

    @PostMapping("/save")
    public Result save(@RequestBody Borrow borrow){
        iBorrowService.save(borrow);
        return Result.success();
    }

    @GetMapping("/page")
    public Result page(BorrowPageRequest pageRequest){
        return Result.success(iBorrowService.page(pageRequest));
    }
}
