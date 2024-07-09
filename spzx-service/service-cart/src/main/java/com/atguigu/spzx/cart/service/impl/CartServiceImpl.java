package com.atguigu.spzx.cart.service.impl;

import com.alibaba.fastjson.JSON;
import com.atguigu.spzx.cart.service.CartService;
import com.atguigu.spzx.feign.product.ProductFeignClient;
import com.atguigu.spzx.model.entity.cart.CartInfo;
import com.atguigu.spzx.model.entity.product.ProductSku;
import com.atguigu.spzx.model.entity.user.UserInfo;
import com.atguigu.spzx.model.entity.user.UserInfoAuthContextUtil;
import com.atguigu.spzx.model.vo.common.Result;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    private ProductFeignClient productFeignClient;

    @Override
    public List<CartInfo> cartList() {
        UserInfo userInfo = UserInfoAuthContextUtil.get();
        List<String> cartInfoStringList = redisTemplate.opsForHash().values("user:cart:" + userInfo.getId());
        return cartInfoStringList.stream().map(cartInfoString -> {
            return JSON.parseObject(cartInfoString, CartInfo.class);
        }).collect(Collectors.toList());
    }

    @Override
    public void addToCart(Long skuId, Integer skuNum) {

        // 根据skuID查询商品信息
        ProductSku productSku = productFeignClient.getBySkuId(skuId);

        // 将productSKU转为cattInfo
        CartInfo cartInfo = null;

        // 存入redis
        UserInfo userInfo = UserInfoAuthContextUtil.get();
        String cartInfoJSON = (String) redisTemplate.opsForHash().get("user:cart:" + userInfo.getId(), skuId + "");

        if (StringUtils.hasText(cartInfoJSON)) {
            cartInfo = JSON.parseObject(cartInfoJSON, CartInfo.class);
        }

        if (cartInfo != null) {
            // 用户已经添加过，修改商品数量
            cartInfo.setSkuNum(cartInfo.getSkuNum() + skuNum);
        } else {
            // 添加
            cartInfo = new CartInfo();
            // 根据skuID查询商品信息
            cartInfo.setSkuNum(skuNum);
            cartInfo.setUserId(productSku.getId());
            cartInfo.setSkuId(skuId);
            cartInfo.setCartPrice(productSku.getSalePrice());
            cartInfo.setImgUrl(productSku.getThumbImg());
            cartInfo.setSkuName(productSku.getSkuName());
            cartInfo.setIsChecked(1);
        }
//        System.out.println("cartInfo = " + cartInfo);
        redisTemplate.opsForHash().put("user:cart:" + userInfo.getId(), skuId + "", JSON.toJSONString(cartInfo));
    }

    @Override
    public void deleteCart(Long skuId) {
        UserInfo userInfo = UserInfoAuthContextUtil.get();
        redisTemplate.opsForHash().delete("user:cart:" + userInfo.getId(), skuId + "");
    }

    @Override
    public void checkCart(Long skuId, Integer isChecked) {
        UserInfo userInfo = UserInfoAuthContextUtil.get();
        String cartInfoJSON = (String) redisTemplate.opsForHash().get("user:cart:" + userInfo.getId(), skuId + "");
        Assert.hasText(cartInfoJSON, "购物车已过期");
        CartInfo cartInfo = JSON.parseObject(cartInfoJSON, CartInfo.class);
        cartInfo.setIsChecked(isChecked);
        redisTemplate.opsForHash().put("user:cart:" + userInfo.getId(), skuId + "", JSON.toJSONString(cartInfo));
    }

    @Override
    public void allCheckCart(Integer isChecked) {
        UserInfo userInfo = UserInfoAuthContextUtil.get();
        List<Object> cartObjectList = redisTemplate.opsForHash().values("user:cart:" + userInfo.getId());
        Assert.notNull(cartObjectList, "购物车为空");
        cartObjectList.stream().map(cartInfoJson -> {
            CartInfo cartInfo = JSON.parseObject(cartInfoJson.toString(), CartInfo.class);
            cartInfo.setIsChecked(isChecked);
            return cartInfo;
        }).forEach(cartInfo ->
                redisTemplate.opsForHash().put("user:cart:" + userInfo.getId(), String.valueOf(cartInfo.getSkuId()), JSON.toJSONString(cartInfo)));
    }

    @Override
    public void clearCart() {
        UserInfo userInfo = UserInfoAuthContextUtil.get();
        String cartKey = "user:cart:" + userInfo.getId();

        List<Object> cartObjectList = redisTemplate.opsForHash().values(cartKey);

        Assert.notNull(cartObjectList, "购物车为空");

//        cartObjectList.stream().map(cartInfoJson -> {
//            CartInfo cartInfo = JSON.parseObject(cartInfoJson.toString(),CartInfo.class);
//            return cartInfo;
//        }).forEach(cartInfo -> redisTemplate.opsForHash().delete("user:cart:" + userInfo.getId(), String.valueOf(cartInfo.getSkuId()), JSON.toJSONString(cartInfo)));

        // 逐条删除，不推荐
//        cartObjectList.stream()
//                .map(cartInfoJson -> JSON.parseObject(cartInfoJson.toString(), CartInfo.class))
//                .forEach(cartInfo -> redisTemplate.opsForHash().delete(cartKey, String.valueOf(cartInfo.getSkuId())));

        // 直接删除整个哈希表
        redisTemplate.delete(cartKey);
    }
}



