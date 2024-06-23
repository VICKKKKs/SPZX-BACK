package com.atguigu.spzx.manager.mapper;


import com.atguigu.spzx.model.dto.system.SysRoleDto;
import com.atguigu.spzx.model.entity.system.SysRole;

import java.util.List;

public interface SysRoleMapper {

    List<SysRole> selectByPage(SysRoleDto sysRoleDto);

    void insertSysRole(SysRole sysRole);

    void updateSysRole(SysRole sysRole);
    List<SysRole> selectAllSysRole();

}
