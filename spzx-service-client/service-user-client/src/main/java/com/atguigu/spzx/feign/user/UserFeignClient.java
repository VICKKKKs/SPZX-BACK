package com.atguigu.spzx.feign.user;

import com.atguigu.spzx.model.entity.user.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-user")
public interface UserFeignClient {

    @GetMapping("/api/user/userInfo/inner/checkToken/{token}")
    public UserInfo checkToken(@PathVariable("token") String token);
}
