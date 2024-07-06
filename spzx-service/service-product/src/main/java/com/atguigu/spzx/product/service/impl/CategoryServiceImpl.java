package com.atguigu.spzx.product.service.impl;

import com.atguigu.spzx.model.entity.product.Category;
import com.atguigu.spzx.product.mapper.CategoryMapper;
import com.atguigu.spzx.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Cacheable(value = "userCache", key = "'ALL'")
    @Override
    public List<Category> findOneCategory() {
        System.out.println("查询redis");
        List<Category> categoryList = categoryMapper.selectOneCategory();
        return categoryList;
    }

    // key = "#id + 'USER'"
    @Cacheable(value = "userCacheID", key = "#id")
    @Override
    public List<Category> findOneCategory(Long id) {
        List<Category> testList = categoryMapper.selectOneCategoryById(id);
        return testList;
    }
}
