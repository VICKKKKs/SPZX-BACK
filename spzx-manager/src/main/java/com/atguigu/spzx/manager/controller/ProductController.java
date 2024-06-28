package com.atguigu.spzx.manager.controller;


import com.atguigu.spzx.manager.service.ProductService;
import com.atguigu.spzx.manager.service.ProductSpecService;
import com.atguigu.spzx.model.dto.product.ProductDto;
import com.atguigu.spzx.model.entity.product.Product;
import com.atguigu.spzx.model.vo.common.Result;
import com.github.pagehelper.PageInfo;
import org.bouncycastle.jcajce.provider.symmetric.util.PBE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/admin/product/product")
@CrossOrigin(allowCredentials = "true" , originPatterns = "*" , allowedHeaders = "*")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping(value = "/{page}/{limit}")
    public Result<PageInfo<Product>> findByPage(@PathVariable Integer page, @PathVariable Integer limit, ProductDto productDto) {
        PageInfo<Product> pageInfo = productService.findBypage(page,limit,productDto);
        return Result.ok(pageInfo);
    }

    @PostMapping(value = "/save")
    public Result save(@RequestBody Product product) {
        productService.save(product);
        return Result.ok(null);
    }

    @GetMapping(value = "getById/{id}")
    public Result<Product> getById(@PathVariable Long id) {
        Product product = productService.getById(id);
        return Result.ok(product);
    }

    @PutMapping(value = "updateById")
    public Result updateById(@RequestBody Product product) {
        productService.updateById(product);
        return Result.ok(null);
    }

    @DeleteMapping(value = "deleteById/{id}")
    public Result deleteById(@PathVariable Long id) {
        productService.deleteById(id);
        return Result.ok(null);
    }

    @PutMapping("/updateAuditStatus/{id}/{auditStatus}")
    public Result updateAuditStatus(@PathVariable Long id, @PathVariable Integer auditStatus) {
        productService.updateAuditStatus(id, auditStatus);
        return Result.ok(null);
    }

    @GetMapping("updateStatus/{id}/{status}")
    public Result updateStatus(@PathVariable Long id, @PathVariable Integer status) {
        productService.updateStatus(id,status);
        return Result.ok(null);
    }


}
