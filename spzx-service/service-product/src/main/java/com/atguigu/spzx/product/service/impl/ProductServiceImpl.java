package com.atguigu.spzx.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.atguigu.spzx.model.dto.product.ProductSkuDto;
import com.atguigu.spzx.model.entity.product.Product;
import com.atguigu.spzx.model.entity.product.ProductDetails;
import com.atguigu.spzx.model.entity.product.ProductSku;
import com.atguigu.spzx.model.vo.product.ProductItemVo;
import com.atguigu.spzx.product.mapper.ProductDetailsMapper;
import com.atguigu.spzx.product.mapper.ProductMapper;
import com.atguigu.spzx.product.mapper.ProductSkuMapper;
import com.atguigu.spzx.product.service.ProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductDetailsMapper productDetailsMapper;

    @Autowired
    private ProductSkuMapper productSkuMapper;

    @Override
    public List<ProductSku> findProductSkuBySale() {
        List<ProductSku> productSkuList = productMapper.selectProductSkuBySale();
        return productSkuList;
    }

    @Override
    public List<ProductSku> findProductSkuById(Long id) {
        List<ProductSku> productSkuList = productMapper.selectProductSkuById(id);
        return productSkuList;
    }

    @Override
    public PageInfo<ProductSku> findByPage(Integer page, Integer limit, ProductSkuDto productSkuDto) {
        PageHelper.startPage(page, limit);
        List<ProductSku> productSkuList = productMapper.selectProductSkuByPage(productSkuDto);
        return new PageInfo<>(productSkuList);
    }

    @Override
    public ProductItemVo findItem(Long skuId) {
        // sku
        ProductSku productSku  = productSkuMapper.selectBySkuId(skuId);

        // spu
        Long productId = productSku.getProductId();
        Product product = productMapper.selectBySkuId(productId);

        // details
        ProductDetails productDetails = productDetailsMapper.selectDetailsByPId(productId);

        // 封装vo
        //同一个商品下面的sku信息列表

        List<ProductSku> productSkuList = productMapper.selectProductSkuById(productId);

        HashMap<String,Object> map = new HashMap<>();
        for(ProductSku sku : productSkuList){
            map.put(sku.getSkuSpec(),sku.getId());
        }

        ProductItemVo productItemVo = new ProductItemVo();
        productItemVo.setProduct(product);
        productItemVo.setProductSku(productSku);
        productItemVo.setSliderUrlList(Arrays.asList(product.getSliderUrls().split(",")));
        productItemVo.setDetailsImageUrlList(Arrays.asList(productDetails.getImageUrls().split(",")));
        productItemVo.setSpecValueList(JSON.parseArray(product.getSpecValue()));
        productItemVo.setSkuSpecValueMap(map); // 页面用户选择的规格对应sku的map
        return productItemVo;
    }
}
