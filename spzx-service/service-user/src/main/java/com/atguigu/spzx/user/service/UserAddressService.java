package com.atguigu.spzx.user.service;

import com.atguigu.spzx.model.entity.user.UserAddress;
import com.atguigu.spzx.model.entity.user.UserInfo;
import com.atguigu.spzx.model.entity.user.UserInfoAuthContextUtil;

import java.util.List;

public interface UserAddressService {
    List<UserAddress> findUserAddress();

    UserAddress getById(Long id);
}
