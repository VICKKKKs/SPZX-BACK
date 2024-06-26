package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.manager.mapper.CategoryMapper;
import com.atguigu.spzx.manager.service.CategoryService;
import com.atguigu.spzx.model.entity.product.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> findByParentId(Long parentId) {
        List<Category> categories = categoryMapper.selectByParentId(parentId);
        for (Category category : categories) {
            Long categoryId = category.getId();
            Long parentIdSize = categoryMapper.countParentId(categoryId);
            if(parentIdSize <= 0){
                category.setHasChildren(false);
            }
        }
        return categories;
    }
}
