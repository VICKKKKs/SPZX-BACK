package com.atguigu.spzx.manager;

import com.atguigu.common.anno.EnableGlobalExceptionHandler;
import com.atguigu.spzx.common.log.anno.EnableLogAspect;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.atguigu.spzx.manager.mapper")
@EnableGlobalExceptionHandler
@EnableLogAspect
@EnableScheduling
public class ManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class, args);
    }
}
