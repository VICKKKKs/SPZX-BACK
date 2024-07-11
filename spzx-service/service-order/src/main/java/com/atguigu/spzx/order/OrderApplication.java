package com.atguigu.spzx.order;

import com.atguigu.common.anno.EnableGlobalExceptionHandler;
import com.atguigu.common.anno.EnableUserLoginAuthInterceptor;
import com.atguigu.common.anno.EnableUserTokenFeignInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableUserLoginAuthInterceptor
@EnableGlobalExceptionHandler
@EnableUserTokenFeignInterceptor
@EnableFeignClients({"com.atguigu.spzx.feign.cart","com.atguigu.spzx.feign.user","com.atguigu.spzx.feign.product"})
@MapperScan("com.atguigu.spzx.order.mapper")
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
