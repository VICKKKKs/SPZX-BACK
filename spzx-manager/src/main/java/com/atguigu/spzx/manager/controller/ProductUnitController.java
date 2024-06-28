package com.atguigu.spzx.manager.controller;

import com.atguigu.spzx.manager.service.ProductUnitService;
import com.atguigu.spzx.model.entity.product.ProductUnit;
import com.atguigu.spzx.model.vo.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/admin/product/productUnit")
@CrossOrigin(allowCredentials = "true", originPatterns = "*", allowedHeaders = "*")
public class ProductUnitController {

    @Autowired
    ProductUnitService productUnitService;

    @GetMapping(value = "findAll")
    public Result<List<ProductUnit>> findAll(){
        List<ProductUnit> productUnitList = productUnitService.findAll();
        return Result.ok(productUnitList);
    }
}
