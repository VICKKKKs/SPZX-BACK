package com.atguigu.spzx.product.service.impl;

import com.atguigu.spzx.model.dto.product.ProductSkuDto;
import com.atguigu.spzx.model.entity.product.ProductSku;
import com.atguigu.spzx.product.mapper.ProductMapper;
import com.atguigu.spzx.product.service.ProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<ProductSku> findProductSkuBySale() {
        List<ProductSku> productSkuList = productMapper.selectProductSkuBySale();
        return productSkuList;
    }

    @Override
    public List<ProductSku> findProductSkuById(Long id) {
        List<ProductSku> testList = productMapper.selectProductSkuById(id);
        return testList;
    }

    @Override
    public PageInfo<ProductSku> findByPage(Integer page, Integer limit, ProductSkuDto productSkuDto) {
        PageHelper.startPage(page, limit);
        List<ProductSku> productSkuList = productMapper.selectProductSkuByPage(productSkuDto);
        return new PageInfo<>(productSkuList);
    }
}
