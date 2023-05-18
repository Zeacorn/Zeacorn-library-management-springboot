package com.zea.springboot.controller;

import cn.hutool.core.io.FileUtil;
import com.zea.springboot.common.Result;
import com.zea.springboot.controller.request.BorrowPageRequest;
import com.zea.springboot.entity.Borrow;
import com.zea.springboot.service.IBorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @PutMapping("/updateStatus")
    public Result updateStatus(@RequestBody Borrow borrow){
        iBorrowService.updateStatus(borrow);
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

    @GetMapping("/lineCharts/{timeRange}")
    public Result lineCharts(@PathVariable String timeRange) {
        return Result.success(iBorrowService.getCountByTimeRange(timeRange));
    }
}
