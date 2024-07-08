package com.atguigu.spzx.product.service;

import com.atguigu.spzx.model.dto.product.ProductSkuDto;
import com.atguigu.spzx.model.entity.product.ProductSku;
import com.atguigu.spzx.model.vo.product.ProductItemVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ProductService {
    List<ProductSku> findProductSkuBySale();

    List<ProductSku> findProductSkuById(Long id);

    PageInfo<ProductSku> findByPage(Integer page, Integer limit, ProductSkuDto productSkuDto);

    ProductItemVo findItem(Long skuId);
}
