package com.atguigu.spzx.product.controller;

import com.atguigu.spzx.model.entity.product.Category;
import com.atguigu.spzx.model.entity.product.ProductSku;
import com.atguigu.spzx.model.entity.user.UserInfo;
import com.atguigu.spzx.model.entity.user.UserInfoAuthContextUtil;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.product.IndexVo;
import com.atguigu.spzx.product.service.CategoryService;
import com.atguigu.spzx.product.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
//@CrossOrigin(allowCredentials = "true", originPatterns = "*", allowedHeaders = "*")
public class IndexController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;


    @GetMapping("index")
    public Result<IndexVo> findData(HttpServletRequest request) {
        System.out.println("经过了网关的鉴权，如果方法中需要鉴权的结果呢？");
//        String userInfoJson = request.getHeader("userInfoJson");
//        System.out.println(userInfoJson);
//        UserInfo userInfo = UserInfoAuthContextUtil.get();
//        System.out.println("userInfo = " + userInfo);
        List<Category> categoryList = categoryService.findOneCategory();
        List<ProductSku> productSkuList = productService.findProductSkuBySale();
        IndexVo indexVo = new IndexVo();
        indexVo.setCategoryList(categoryList);
        indexVo.setProductSkuList(productSkuList);
        return Result.ok(indexVo);
    }

    @GetMapping("test/{id}")
    public Result<IndexVo> testCache(@PathVariable Long id) {
        List<Category> categoryList = categoryService.findOneCategory(id);
        List<ProductSku> productSkuList = productService.findProductSkuById(id);
        IndexVo indexVo = new IndexVo();
        indexVo.setCategoryList(categoryList);
        indexVo.setProductSkuList(productSkuList);
        return Result.ok(indexVo);
    }

}
