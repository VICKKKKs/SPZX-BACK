package com.atguigu.spzx.product.service.impl;

import com.atguigu.spzx.model.entity.product.Category;

import java.util.ArrayList;
import java.util.List;

public class TreeHelper {

    public static List<Category> buildTree(List<Category> allCategoryList) {
        List<Category> treeList = new ArrayList<Category>();
        for (Category category : allCategoryList) {
            if(category.getParentId().longValue() == 0) {
                treeList.add(findChildren(category,allCategoryList));//顶级父亲找孩子
            }
        }
        return treeList;
    }

    private static Category findChildren(Category category, List<Category> allCategoryList) {
        for (Category child : allCategoryList) {
            if(child.getParentId().longValue() == category.getId().longValue()) {
                category.getChildren().add(findChildren(child,allCategoryList));// 孩子继续找它的孩子
            }
        }
        return category;
    }


}
