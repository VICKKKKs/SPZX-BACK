package com.atguigu.spzx.product.mapper;

import com.atguigu.spzx.model.entity.product.ProductSku;

public interface ProductSkuMapper {
    ProductSku selectBySkuId(Long skuId);
}
