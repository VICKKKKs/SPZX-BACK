package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.dto.product.CategoryBrandDto;
import com.atguigu.spzx.model.entity.product.Brand;
import com.atguigu.spzx.model.entity.product.CategoryBrand;

import java.util.List;

public interface CategoryBrandMapper {
    List<CategoryBrand> findByPage(CategoryBrandDto categoryBrandDto);

    void insertCategoryBrand(CategoryBrand categoryBrand);

    void updateCategoryBrand(CategoryBrand categoryBrand);

    List<Brand> selectByCategoryId(Long categoryId);
}
