package com.atguigu.spzx.product.controller;

import com.atguigu.spzx.model.dto.product.ProductSkuDto;
import com.atguigu.spzx.model.entity.product.ProductSku;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.product.ProductItemVo;
import com.atguigu.spzx.product.service.ProductService;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{page}/{limit}")
    public Result<PageInfo<ProductSku>> findByPage(@PathVariable Integer page, @PathVariable Integer limit, @Parameter ProductSkuDto productSkuDto) {
        PageInfo<ProductSku> pageInfo = productService.findByPage(page,limit,productSkuDto);
        return Result.ok(pageInfo);
    }

    @GetMapping("item/{skuId}")
    public Result<ProductItemVo> item(@PathVariable Long skuId) {
        ProductItemVo productItemVo = productService.findItem(skuId);
        return Result.ok(productItemVo);
    }
}
