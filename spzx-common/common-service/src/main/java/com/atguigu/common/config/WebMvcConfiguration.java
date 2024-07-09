package com.atguigu.common.config;


import com.atguigu.common.interceptor.UserLoginAuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Component
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    UserLoginAuthInterceptor userLoginAuthInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userLoginAuthInterceptor)
                .addPathPatterns("/**");
    }


}
