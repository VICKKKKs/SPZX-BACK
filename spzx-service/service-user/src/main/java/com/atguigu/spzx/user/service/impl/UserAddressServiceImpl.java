package com.atguigu.spzx.user.service.impl;

import com.atguigu.spzx.model.entity.user.UserAddress;
import com.atguigu.spzx.model.entity.user.UserInfo;
import com.atguigu.spzx.model.entity.user.UserInfoAuthContextUtil;
import com.atguigu.spzx.user.mapper.UserAddressMapper;
import com.atguigu.spzx.user.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAddressServiceImpl implements UserAddressService {

    @Autowired
    private UserAddressMapper userAddressMapper;

    @Override
    public List<UserAddress> findUserAddress() {
        Long id = UserInfoAuthContextUtil.get().getId();
        List<UserAddress> userAddressList = userAddressMapper.selectUserAddress(id);
        return userAddressList;
    }

    @Override
    public UserAddress getById(Long id) {
        UserAddress userAddress = userAddressMapper.selectById(id);
        return userAddress;
    }
}
