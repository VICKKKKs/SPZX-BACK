package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.dto.system.AssginRoleDto;

import java.util.Map;

public interface SysUserRoleService {

    void doAssign(AssginRoleDto AssginRoleDto);

    Map<String, Object> getUserRoleIdList(Long currentUserId);
}
