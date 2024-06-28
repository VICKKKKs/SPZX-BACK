package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.entity.product.ProductDetails;
import com.atguigu.spzx.model.entity.product.ProductSku;

import java.util.List;

public interface ProductDetailsMapper {
    void save(ProductDetails productDetails);

    ProductDetails selectById(Long id);

    void updateById(ProductDetails productDetails);

    void deleteById(Long id);
}
