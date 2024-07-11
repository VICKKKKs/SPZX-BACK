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
                // 配置了一个排除规则，表示对于匹配 **/api/user/userInfo/** 的路径，
                // 不应用 userLoginAuthInterceptor 拦截器。
                .excludePathPatterns("**/api/user/userInfo/**")
                .addPathPatterns("/**");
    }


}
