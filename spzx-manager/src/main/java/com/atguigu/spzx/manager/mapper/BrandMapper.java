package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.entity.product.Brand;

import java.util.List;

public interface BrandMapper {
    List<Brand> selectBrandPageList();

    void insertBrand(Brand brand);

    void updateBrandById(Brand brand);

    void deleteBrandById(Long id);

}
