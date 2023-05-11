package com.zea.springboot.controller;

import com.zea.springboot.common.Result;
import com.zea.springboot.controller.request.BookPageRequest;
import com.zea.springboot.controller.request.CategoryPageRequest;
import com.zea.springboot.entity.Book;
import com.zea.springboot.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    IBookService iBookService;

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        Book book = iBookService.getById(id);
        return Result.success(book);
    }

    @PutMapping("/update")
    public Result update(@RequestBody Book book){
        iBookService.update(book);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        iBookService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/list")
    public Result list(){
        List<Book> categories = iBookService.list();
        return Result.success(categories);
    }

    @PostMapping("/save")
    public Result save(@RequestBody Book book){
        iBookService.save(book);
        return Result.success();
    }

    @GetMapping("/page")
    public Result page(BookPageRequest pageRequest){
        return Result.success(iBookService.page(pageRequest));
    }
}
