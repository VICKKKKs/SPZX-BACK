package com.atguigu.spzx.user.mapper;

import com.atguigu.spzx.model.entity.user.UserAddress;

import java.util.List;

public interface UserAddressMapper {
    List<UserAddress> selectUserAddress(Long id);

    UserAddress selectById(Long id);
}
