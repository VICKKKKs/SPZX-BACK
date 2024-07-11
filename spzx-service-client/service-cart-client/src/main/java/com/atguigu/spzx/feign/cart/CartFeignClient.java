package com.atguigu.spzx.feign.cart;

import com.atguigu.spzx.model.entity.cart.CartInfo;
import com.atguigu.spzx.model.vo.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("service-cart")
public interface CartFeignClient {

    // /api/order/cart/auth/getAllCkecked
    @GetMapping("/api/order/cart/auth/getAllCkecked")
    public Result<List<CartInfo>> getAllCkecked();


    @GetMapping("/api/order/cart/auth/deleteChecked")
    public Result deleteChecked();
}
