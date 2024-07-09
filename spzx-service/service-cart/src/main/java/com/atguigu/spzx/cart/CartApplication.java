package com.atguigu.spzx.cart;

import com.atguigu.common.anno.EnableGlobalExceptionHandler;
import com.atguigu.common.anno.EnableRedisConfig;
import com.atguigu.common.anno.EnableUserLoginAuthInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

// 排除数据库的自动化配置，Cart微服务不需要访问数据库
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableGlobalExceptionHandler
@EnableUserLoginAuthInterceptor
@EnableCaching
@EnableRedisConfig
@EnableFeignClients(basePackages = "com.atguigu.spzx.feign.product")
public class CartApplication {

    public static void main(String[] args) {
        SpringApplication.run(CartApplication.class, args);
    }
}
