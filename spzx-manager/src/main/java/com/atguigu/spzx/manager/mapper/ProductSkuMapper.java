package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.entity.product.ProductSku;

import java.util.List;

public interface ProductSkuMapper {
    void save(ProductSku productSku);

    List<ProductSku> selectById(Long id);

    void updateById(ProductSku productSku);

    void deleteById(Long id);
}
