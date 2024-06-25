package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.entity.system.SysMenu;
import com.atguigu.spzx.model.vo.system.SysMenuVo;

import java.util.List;

public interface SysMenuService {

    List<SysMenu> findNodes(Long parentId);

    void saveSysMenu(SysMenu sysMenu);

    void updateSysMenu(SysMenu sysMenu);


    List<SysMenuVo> findUserMenuList();
}
