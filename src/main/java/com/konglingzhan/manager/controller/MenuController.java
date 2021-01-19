package com.konglingzhan.manager.controller;

import com.konglingzhan.manager.common.domain.router.VueRouter;
import com.konglingzhan.manager.entity.Menu;
import com.konglingzhan.manager.dto.AclModuleLevelDto;
import com.konglingzhan.manager.param.MenuParam;
import com.konglingzhan.manager.routes.RoutesService;
import com.konglingzhan.manager.service.MenuService;
import com.konglingzhan.manager.service.RoleService;
import com.konglingzhan.manager.service.impl.SysTreeService;
import com.konglingzhan.manager.util.UserUtil;
import com.konglingzhan.manager.vo.Result;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Validated
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Resource
    private MenuService menuService;

    @Resource
    private RoleService roleService;
    @Resource
    private SysTreeService sysTreeService;

    @Resource
    private RoutesService routesService;

    /**
     *  前端动态路由
     **/
    @PostMapping("/getPermissionMenusByUid")
    public Result getPermissionMenusByUid(){
        int uid = UserUtil.getLoginUser().getId();
        ArrayList<VueRouter<Menu>> permissionMenus = routesService.getPermissionMenusByUid(uid);
        return Result.success(permissionMenus);
    }

    @PostMapping("/add")
    public Result insert(@Valid MenuParam param){
        menuService.insert(param);
        return Result.success();
    }

    @PostMapping("/update")
    public Result update(MenuParam param){
        menuService.update(param);
        return Result.success();
    }

    @PostMapping("/all")
    public Result all(){
        List<Menu> list = menuService.selectAll();
        return Result.success(list);
    }
    /*
    * 删除菜单
    * */
    @PostMapping("/del")
    public Result del(@RequestParam("id") int id){
        menuService.delById(id);
        return Result.success();
    }
    /*
    * 树状结构
    * */
    @PostMapping("/tree")
    public Result tree(){
        List<AclModuleLevelDto> list = sysTreeService.menuTree();
        return Result.success(list);
    }

    @PostMapping("/findLevelById")
    public Result findLevelById(@RequestParam("aclModuleId") int aclModuleId){
        Menu menu = menuService.findLevelById(aclModuleId);
        return Result.success(menu);
    }

    /*
     * 根据level模糊查询
     **/
    @PostMapping("/findListByLevel")
    public Result findListByLevel(@RequestParam("level") String level){
        List<Menu> menuList = menuService.getMenuListByLevel(level);
        return Result.success(menuList);
    }

    /*
     * 删除菜单
     * @param {String}
     **/
    @PostMapping("/delByIds")
    public Result delByIds(String ids){
        menuService.getMenuListByLevel(ids);
        return Result.success();
    }


}
