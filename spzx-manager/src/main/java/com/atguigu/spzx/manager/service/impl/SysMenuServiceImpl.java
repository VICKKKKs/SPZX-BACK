package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.manager.mapper.SysMenuMapper;
import com.atguigu.spzx.manager.service.SysMenuService;
import com.atguigu.spzx.model.entity.system.SysMenu;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.entity.system.SysUserAuthContextUtil;
import com.atguigu.spzx.model.vo.system.SysMenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public List<SysMenuVo> findUserMenuList() {
        SysUser sysUser = SysUserAuthContextUtil.get();
        Long userId = sysUser.getId();
        List<SysMenu> sysMenuList = sysMenuMapper.selectSysMenuListByUserId(userId);
        List<SysMenu> sysMenusTree = MenuHelper.buildTree(sysMenuList);
        List<SysMenuVo> sysMenuVoTree = new ArrayList<>();
        sysMenuVoTree =  MenuVoHelper.buildTree(sysMenusTree);
        return sysMenuVoTree;
    }
}
