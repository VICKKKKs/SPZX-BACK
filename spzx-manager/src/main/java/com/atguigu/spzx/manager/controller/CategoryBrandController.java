package com.atguigu.spzx.manager.controller;

import com.atguigu.spzx.manager.service.CategoryBrandService;
import com.atguigu.spzx.model.dto.product.CategoryBrandDto;
import com.atguigu.spzx.model.entity.product.Brand;
import com.atguigu.spzx.model.entity.product.CategoryBrand;
import com.atguigu.spzx.model.vo.common.Result;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/admin/product/categoryBrand")
@CrossOrigin(allowCredentials = "true", originPatterns = "*", allowedHeaders = "*")
public class CategoryBrandController {

    @Autowired
    private CategoryBrandService categoryBrandService;

    @GetMapping(value = "getCategoryBrandPageList/{page}/{limit}")
    public Result<PageInfo<CategoryBrand>> findByPage(@PathVariable Integer page, @PathVariable Integer limit, CategoryBrandDto categoryBrandDto) {
        PageInfo<CategoryBrand> pageInfo = categoryBrandService.findByPage(page, limit, categoryBrandDto);
        return Result.ok(pageInfo);
    }

    @PostMapping(value = "saveCategoryBrand")
    public Result saveCategoryBrand(@RequestBody CategoryBrand categoryBrand) {
        categoryBrandService.saveCategoryBrand(categoryBrand);
        return Result.ok(null);
    }

    @PutMapping(value = "updateCategoryBrand")
    public Result updateCategoryBrand(@RequestBody CategoryBrand categoryBrand) {
        categoryBrandService.updateCategoryBrand(categoryBrand);
        return Result.ok(null);
    }

    @GetMapping(value = "findBrandByCategoryId/{categoryId}")
    public Result<List<Brand>> findBrandByCategoryId(@PathVariable Long categoryId) {
        List<Brand> brandList = categoryBrandService.findByCategoryId(categoryId);
        return Result.ok(brandList);
    }


}
