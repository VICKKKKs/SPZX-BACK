package com.atguigu.spzx.manager.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.spzx.manager.mapper.CategoryMapper;
import com.atguigu.spzx.model.entity.product.Category;
import com.atguigu.spzx.model.vo.product.CategoryExcelVo;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class CategoryExcelListener extends AnalysisEventListener<CategoryExcelVo> {

    List<CategoryExcelVo> list = new ArrayList<CategoryExcelVo>();

    CategoryMapper categoryMapper;

    public CategoryExcelListener(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public void invoke(CategoryExcelVo categoryExcelVo, AnalysisContext analysisContext) {
        list.add(categoryExcelVo);
        if (list.size() >= 10) {
            System.out.println("每10条写入数据库");
            insertDb();

            System.out.println(list);
            list.clear();
        }


    }

    private void insertDb() {
        List<Category> categories = list.stream().map(categoryExcelVo -> {
            Category category = new Category();
            BeanUtils.copyProperties(categoryExcelVo, category);
            return category;
        }).collect(Collectors.toList());
        categoryMapper.batchInsert(categories);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("结束处理");
        System.out.println(list);
        insertDb();
    }
}
