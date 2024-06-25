package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.entity.system.SysMenu;

import java.util.List;

public interface SysMenuMapper {
    List<SysMenu> findNodes(Long parentId);

    List<SysMenu> findAllNodes();

    void insertSysMenu(SysMenu sysMenu);

    void updateSysMenu(SysMenu sysMenu);

    List<SysMenu> selectSysMenuListByUserId(Long userId);
}
