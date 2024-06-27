package com.atguigu.spzx.manager.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.spzx.manager.mapper.CategoryMapper;
import com.atguigu.spzx.manager.service.CategoryService;
import com.atguigu.spzx.manager.test.MyListener;
import com.atguigu.spzx.manager.test.Student;
import com.atguigu.spzx.manager.util.CategoryExcelListener;
import com.atguigu.spzx.model.entity.product.Category;
import com.atguigu.spzx.model.vo.product.CategoryExcelVo;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @SneakyThrows
    @Override
    public void importData(MultipartFile multipartFile) {
        EasyExcel.read(multipartFile.getInputStream(), CategoryExcelVo.class,new CategoryExcelListener(categoryMapper))
                .sheet("分类数据")
                .doRead();
    }

    @SneakyThrows
    @Override
    public void exportData(HttpServletResponse response) {
        List<CategoryExcelVo> categoryExcelVos = new ArrayList<>();

        List<Category> categories = categoryMapper.selectAll();
        categoryExcelVos = categories.stream().map(category->{
            CategoryExcelVo categoryExcelVo = new CategoryExcelVo();
            BeanUtils.copyProperties(category,categoryExcelVo, CategoryExcelVo.class);
            return categoryExcelVo;
        }).collect(Collectors.toList());

        // 设置响应结果类型
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");

        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("分类数据", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");

        EasyExcel.write(response.getOutputStream(), CategoryExcelVo.class).sheet("测试数据").doWrite(categoryExcelVos);
    }
}
