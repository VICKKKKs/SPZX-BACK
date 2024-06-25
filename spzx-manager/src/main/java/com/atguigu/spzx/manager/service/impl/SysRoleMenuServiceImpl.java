package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.manager.mapper.SysMenuMapper;
import com.atguigu.spzx.manager.mapper.SysRoleMenuMapper;
import com.atguigu.spzx.manager.service.SysMenuService;
import com.atguigu.spzx.manager.service.SysRoleMenuService;
import com.atguigu.spzx.model.entity.system.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysRoleMenuServiceImpl implements SysRoleMenuService {

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Autowired
    private SysMenuService sysMenuService;

    @Override
    public Map<String, Object> getSysRoleMenuIds(Long roleId) {
        // 所有数据 menuIds
        List<SysMenu> sysMenuList = sysMenuService.findNodes(0L);

        // roleIds
        List<Long> roleMenuIds = sysRoleMenuMapper.selectSysRoleMenuById(roleId);

        Map<String, Object> map = new HashMap<>();
        map.put("sysMenuList", sysMenuList);
        map.put("roleMenuIds", roleMenuIds);
        return map;
    }
}
