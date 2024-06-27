package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.entity.product.Category;

import java.util.List;

public interface CategoryMapper {
    List<Category> selectByParentId(Long parentId);

    Long countParentId(Long categoryId);

    void batchInsert(List<Category> categories);

    List<Category> selectAll();
}
