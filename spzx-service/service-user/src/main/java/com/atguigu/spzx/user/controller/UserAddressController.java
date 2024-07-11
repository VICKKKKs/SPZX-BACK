package com.atguigu.spzx.user.controller;

import com.atguigu.spzx.model.entity.user.UserAddress;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.user.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user/userAddress")
public class UserAddressController {

    @Autowired
    private UserAddressService userAddressService;


    @GetMapping("auth/findUserAddressList")
    public Result<List<UserAddress>> findUserAddressList(){
        List<UserAddress> userAddressList = userAddressService.findUserAddress();
        return Result.ok(userAddressList);
    }

    @GetMapping("getUserAddress/{id}")
    public UserAddress getUserAddress(@PathVariable Long id) {
        return userAddressService.getById(id);
    }


}
