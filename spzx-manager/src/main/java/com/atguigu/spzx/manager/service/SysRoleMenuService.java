package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.dto.system.AssginMenuDto;

import java.util.Map;

public interface SysRoleMenuService {
    Map<String, Object> getSysRoleMenuIds(Long roleId);

    void doAssignMenuIdToSysRole(AssginMenuDto assginMenuDto);
}
