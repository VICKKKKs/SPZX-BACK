package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.dto.system.AssginRoleDto;
import com.atguigu.spzx.model.entity.system.SysRole;

import java.util.List;

public interface SysUserRoleMapper {
    void deleteRoleIdByUserId(Long currentUserId);

    void insertRoleIdsByUserId(AssginRoleDto assginRoleDto);


    List<Long> selectCurrentRoleIds(Long currentUserId);
}
