package com.atguigu.spzx.feign.user;

import com.atguigu.spzx.model.entity.user.UserAddress;
import com.atguigu.spzx.model.entity.user.UserInfo;
import com.atguigu.spzx.model.vo.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "service-user")
public interface UserFeignClient {

    @GetMapping("/api/user/userInfo/inner/checkToken/{token}")
    public UserInfo checkToken(@PathVariable("token") String token);

    @GetMapping("/api/user/userAddress/getUserAddress/{id}")
    public UserAddress getUserAddress(@PathVariable Long id);

    @GetMapping("/api/user/userAddress/auth/findUserAddressList")
    public Result<List<UserAddress>> findUserAddressList();
}
