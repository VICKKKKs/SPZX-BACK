package com.atguigu.spzx.manager.config;


import com.atguigu.common.interceptor.UserLoginAuthInterceptor;
import com.atguigu.spzx.manager.util.UserAuthProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Component
public class WebMvcConfiguration  implements WebMvcConfigurer {

    @Autowired
    UserLoginAuthInterceptor userLoginAuthInterceptor;

    @Autowired
    UserAuthProperties userAuthProperties;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> noAuthUrls = userAuthProperties.getNoAuthUrls();
        registry.addInterceptor(userLoginAuthInterceptor)
                .excludePathPatterns(noAuthUrls)
                .addPathPatterns("/**");
    }



}
