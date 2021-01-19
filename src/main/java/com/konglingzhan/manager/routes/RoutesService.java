package com.konglingzhan.manager.routes;

import com.konglingzhan.manager.common.domain.router.VueRouter;
import com.konglingzhan.manager.common.exception.CommonException;
import com.konglingzhan.manager.dto.AclModuleLevelDto;
import com.konglingzhan.manager.entity.Menu;
import com.konglingzhan.manager.service.MenuService;
import com.konglingzhan.manager.service.RoleMenuService;
import com.konglingzhan.manager.service.UserRoleService;
import com.konglingzhan.manager.service.impl.SysTreeService;
import com.konglingzhan.manager.util.TreeUtil;
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

    public ArrayList<VueRouter<Menu>> getPermissionMenusByUid(int uid){

        List<Integer> roleIds = userRoleService.getRoleIdListByUserId(uid);
        if(CollectionUtils.isEmpty(roleIds)){
            throw new CommonException(-1,"该用户没有绑定任何有效的角色");
        }

        List<Integer> menuIds = roleMenuService.selectMenuIdsByRoleIds(roleIds);
        if(CollectionUtils.isEmpty(menuIds)){
            throw new CommonException(-1,"该用户没有绑定任何有效的菜单");
        }

        List<Menu> menuList = menuService.getMenuListByMenuIds(menuIds);
        List<VueRouter<Menu>> routes = new ArrayList<>();
        menuList.forEach(menu -> {
            VueRouter<Menu> route = new VueRouter<>();
            route.setId(menu.getId().toString());
            route.setParentId(menu.getParentId().toString());
            route.setIcon(menu.getIcon());
            route.setPath(menu.getPath());
            route.setComponent(menu.getComponent());
            route.setName(menu.getComponentName());
            routes.add(route);
        });

        return TreeUtil.buildVueRouter(routes);
    }
}
