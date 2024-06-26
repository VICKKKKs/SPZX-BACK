package com.atguigu.spzx.manager.controller;


import com.atguigu.spzx.manager.mapper.CategoryMapper;
import com.atguigu.spzx.manager.service.CategoryService;
import com.atguigu.spzx.model.entity.product.Category;
import com.atguigu.spzx.model.vo.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/admin/product/category")
@CrossOrigin(allowCredentials = "true", originPatterns = "*", allowedHeaders = "*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/findByParentId/{parentId}")
    public Result<List<Category>> findByParentId(@PathVariable Long parentId) {
        List<Category> categories = categoryService.findByParentId(parentId);
        return Result.ok(categories);
    }
}
