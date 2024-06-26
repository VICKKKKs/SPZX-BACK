package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.entity.product.Brand;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BrandService {
    PageInfo<Brand> findBrandPageList(int page, int limit);

    void saveBrand(Brand brand);

    void updateBrandById(Brand brand);

    void deleteBrandById(Long id);

    List<Brand> findAllBrand();

}
