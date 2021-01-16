package com.konglingzhan.manager.routes;

import com.konglingzhan.manager.dto.AclModuleLevelDto;
import com.konglingzhan.manager.entity.Menu;
import com.konglingzhan.manager.service.MenuService;
import com.konglingzhan.manager.service.RoleMenuService;
import com.konglingzhan.manager.service.UserRoleService;
import com.konglingzhan.manager.service.impl.SysTreeService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoutesService {
    @Resource
    private UserRoleService userRoleService;

    @Resource
    private RoleMenuService roleMenuService;

    @Resource
    private MenuService menuService;

    @Resource
    private SysTreeService sysTreeService;

    public List<AclModuleLevelDto> getPermissionMenusByUid(int uid){
        List<Integer> roleIds = userRoleService.getRoleIdListByUserId(uid);
        if(CollectionUtils.isEmpty(roleIds)){
            return new ArrayList<>();
        }
        List<Integer> menuIds = roleMenuService.selectMenuIdsByRoleIds(roleIds);
        List<Menu> menuList = menuService.getMenuListByMenuIds(menuIds);
        List<AclModuleLevelDto> routes = sysTreeService.routesTree(menuList);
        return routes;
    }
}
