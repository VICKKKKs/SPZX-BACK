package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.manager.mapper.SysMenuMapper;
import com.atguigu.spzx.manager.service.SysMenuService;
import com.atguigu.spzx.model.entity.system.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> findNodes(Long parentId) {
//        List<SysMenu> nodes = sysMenuMapper.findNodes(parentId);

        if(parentId == null){}
        List<SysMenu> allNodes = sysMenuMapper.findAllNodes();
        List<SysMenu> sysMenuList = MenuHelper.buildTree(allNodes);
        return sysMenuList;
    }

    @Override
    public void saveSysMenu(SysMenu sysMenu) {
        sysMenuMapper.insertSysMenu(sysMenu);
    }

    @Override
    public void updateSysMenu(SysMenu sysMenu) {
        sysMenuMapper.updateSysMenu(sysMenu);
    }
}
