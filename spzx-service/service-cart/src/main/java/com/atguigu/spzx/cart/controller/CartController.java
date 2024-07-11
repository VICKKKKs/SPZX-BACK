package com.atguigu.spzx.cart.controller;

import com.atguigu.spzx.cart.service.CartService;
import com.atguigu.spzx.model.entity.cart.CartInfo;
import com.atguigu.spzx.model.vo.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order/cart")
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private RedisCacheManager cacheManager;

    // /api/order/cart/auth/cartList
    @GetMapping("/auth/cartList")
    public Result<List<CartInfo>> cartList(){
        List<CartInfo> cartInfoList = cartService.cartList();
        return Result.ok(cartInfoList);
    }

    // /api/order/cart/auth/addToCart/{skuId}/{skuNum}
    @GetMapping("/auth/addToCart/{skuId}/{skuNum}")
    public Result addToCart(@PathVariable("skuId") Long skuId,@PathVariable("skuNum") Integer skuNum){
        cartService.addToCart(skuId,skuNum);
        return Result.ok(null);
    }

    // /api/order/cart/auth/deleteCart/{skuId}
    @DeleteMapping("/auth/deleteCart/{skuId}")
    public Result deleteCart(@PathVariable("skuId") Long skuId){
        cartService.deleteCart(skuId);
        return Result.ok(null);
    }

    // /api/order/cart/auth/checkCart/{skuId}/{isChecked}
    @GetMapping("/auth/checkCart/{skuId}/{isChecked}")
    public Result checkCart(@PathVariable Long skuId, @PathVariable Integer isChecked) {
        cartService.checkCart(skuId,isChecked);
        return Result.ok(null);
    }


    // /api/order/cart/auth/allCheckCart/{isChecked}
    @GetMapping("auth/allCheckCart/{isChecked}")
    public Result allCheckCart(@PathVariable Integer isChecked){
        cartService.allCheckCart(isChecked);
        return Result.ok(null);
    }

    // /api/order/cart/auth/clearCart
    @GetMapping("/auth/clearCart")
    public Result clearCart(){
        cartService.clearCart();
        return Result.ok(null);
    }


    // /api/order/cart/auth/getAllCkecked
    @GetMapping("/auth/getAllCkecked")
    public Result<List<CartInfo>> getAllCkecked(){
        List<CartInfo> cartInfoList = cartService.getAllChecked();
        return Result.ok(cartInfoList);
    }

    @GetMapping("/auth/deleteChecked")
    public Result deleteChecked(){
        cartService.deleteChecked();
        return Result.ok(null);
    }



}
