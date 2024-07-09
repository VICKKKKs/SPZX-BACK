package com.atguigu.spzx.product;

import com.atguigu.common.anno.EnableUserLoginAuthInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.atguigu.spzx.product.mapper")
@EnableCaching
@EnableUserLoginAuthInterceptor
public class ProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }
}
