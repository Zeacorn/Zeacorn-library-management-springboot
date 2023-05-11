package com.zea.springboot.controller;

import cn.hutool.core.collection.CollUtil;
import com.zea.springboot.common.Result;
import com.zea.springboot.controller.request.CategoryPageRequest;
import com.zea.springboot.entity.Category;
import com.zea.springboot.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    ICategoryService iCategoryService;

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        Category category = iCategoryService.getById(id);
        return Result.success(category);
    }

    @PutMapping("/update")
    public Result update(@RequestBody Category category){
        iCategoryService.update(category);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        iCategoryService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/list")
    public Result list(){
        List<Category> categories = iCategoryService.list();
        return Result.success(categories);
    }

    @PostMapping("/save")
    public Result save(@RequestBody Category category){
        iCategoryService.save(category);
        return Result.success();
    }

    @GetMapping("/page")
    public Result page(CategoryPageRequest pageRequest){
        return Result.success(iCategoryService.page(pageRequest));
    }

    @GetMapping("/tree")
    public Result tree() {
        List<Category> list = iCategoryService.list();
        return Result.success(createTree(null, list));
    }

    private List<Category> createTree(Integer pid, List<Category> categories) {
        List<Category> treelist = new ArrayList<>();
        for (Category category : categories) {
            if (pid == null) {
                if (category.getPid() == null) {
                    treelist.add(category);
                    category.setChildren(createTree(category.getId(), categories));
                }
            }
            else {
                if (pid.equals(category.getPid())) {
                    treelist.add(category);
                    category.setChildren(createTree(category.getId(), categories));
                }
            }
            if (CollUtil.isEmpty(category.getChildren())) {
                category.setChildren(null);
            }
        }
        return treelist;
    }
}
