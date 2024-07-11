package com.atguigu.spzx.user;

import com.atguigu.common.anno.EnableUserLoginAuthInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.atguigu.spzx.user.mapper")
@EnableUserLoginAuthInterceptor
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
