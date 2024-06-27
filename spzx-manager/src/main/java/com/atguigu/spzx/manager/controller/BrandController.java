package com.atguigu.spzx.manager.controller;

import com.atguigu.spzx.manager.service.BrandService;
import com.atguigu.spzx.model.entity.product.Brand;
import com.atguigu.spzx.model.vo.common.Result;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/admin/product/brand")
@CrossOrigin(allowCredentials = "true", originPatterns = "*", allowedHeaders = "*")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping(value = "getBrandPageList/{page}/{limit}")
    public Result<PageInfo<Brand>> getBrandPageList(@PathVariable Integer page, @PathVariable Integer limit) {
        PageInfo<Brand> pageInfo = brandService.findBrandPageList(page,limit);
        return Result.ok(pageInfo);
    }
}
