package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.manager.mapper.SysRoleMapper;
import com.atguigu.spzx.manager.mapper.SysUserRoleMapper;
import com.atguigu.spzx.manager.service.SysUserRoleService;
import com.atguigu.spzx.model.dto.system.AssginRoleDto;
import com.atguigu.spzx.model.entity.system.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public void doAssign(AssginRoleDto AssginRoleDto) {
        Long currentUserId = AssginRoleDto.getUserId() ;


        sysUserRoleMapper.deleteRoleIdByUserId(currentUserId);

        sysUserRoleMapper.insertRoleIdsByUserId(AssginRoleDto);
    }

    @Override
    public Map<String, Object> getUserRoleIdList(Long currentUserId) {

        // 查询所有
        List<SysRole> allRoles = sysRoleMapper.selectAllSysRole();

        // 当前行用户已经拥有的roleId
        List<Long> currentRoleIds = sysUserRoleMapper.selectCurrentRoleIds(currentUserId);
        Map<String, Object> map = new HashMap<>();
        map.put("allRoles", allRoles);
        map.put("currentRoleIds", currentRoleIds);
        return map;
    }
}
