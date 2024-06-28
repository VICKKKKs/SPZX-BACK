package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.entity.product.ProductSpec;

import java.util.List;

public interface ProductSpecMapper {
    List<ProductSpec> selectPage();

    void insertProductSpec(ProductSpec productSpec);

    void updateById(ProductSpec productSpec);

    void deleteById(Long id);
}
