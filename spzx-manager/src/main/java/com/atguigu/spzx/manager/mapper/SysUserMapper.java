package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.entity.system.SysUser;

public interface SysUserMapper {
    SysUser selectSysUserByName(String username);
}
