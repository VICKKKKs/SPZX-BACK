package com.atguigu.spzx.pay;

import com.atguigu.common.anno.EnableUserLoginAuthInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableUserLoginAuthInterceptor
@EnableFeignClients(basePackages = "com.atguigu.spzx.feign.order")
@MapperScan("com.atguigu.spzx.pay.mapper")
public class PayApplication {
    public static void main(String[] args) {
        SpringApplication.run(PayApplication.class, args);
    }
}
