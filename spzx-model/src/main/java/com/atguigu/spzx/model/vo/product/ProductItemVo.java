package com.atguigu.spzx.model.vo.product;

import com.alibaba.fastjson.JSONArray;
import com.atguigu.spzx.model.entity.product.Product;
import com.atguigu.spzx.model.entity.product.ProductSku;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ProductItemVo {

   private ProductSku productSku;

   private Product product;

   private List<String> sliderUrlList;

   private List<String> detailsImageUrlList;

   private JSONArray specValueList;

   private Map<String,Object> skuSpecValueMap;

}