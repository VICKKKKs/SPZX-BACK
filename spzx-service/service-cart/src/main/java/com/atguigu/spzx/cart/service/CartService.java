package com.atguigu.spzx.cart.service;

import com.atguigu.spzx.model.entity.cart.CartInfo;

import java.util.List;

public interface CartService {
    List<CartInfo> cartList();

    void addToCart(Long skuId, Integer skuNum);

    void deleteCart(Long skuId);

    void checkCart(Long skuId, Integer isChecked);

    void allCheckCart(Integer isChecked);

    void clearCart();
}
