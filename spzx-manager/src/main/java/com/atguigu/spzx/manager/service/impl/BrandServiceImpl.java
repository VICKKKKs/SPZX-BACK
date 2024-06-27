package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.manager.mapper.BrandMapper;
import com.atguigu.spzx.manager.service.BrandService;
import com.atguigu.spzx.model.entity.product.Brand;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public PageInfo<Brand> findBrandPageList(int page, int limit) {
        PageHelper.startPage(page, limit);
        List<Brand> brandList = brandMapper.selectBrandPageList();
        return new PageInfo<>(brandList);
    }

    @Override
    public void saveBrand(Brand brand) {
        brandMapper.insertBrand(brand);
    }

    @Override
    public void updateBrandById(Brand brand) {
        brandMapper.updateBrandById(brand);
    }

    @Override
    public void deleteBrandById(Long id) {
         brandMapper.deleteBrandById(id);
    }

    @Override
    public List<Brand> findAllBrand() {
        List<Brand> brandList = brandMapper.selectBrandPageList();
        return brandList;
    }
}
