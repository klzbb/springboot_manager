package com.konglingzhan.manager.controller;

import com.konglingzhan.manager.bean.Menu;
import com.konglingzhan.manager.dto.AclModuleLevelDto;
import com.konglingzhan.manager.param.AclModuleParam;
import com.konglingzhan.manager.service.MenuService;
import com.konglingzhan.manager.service.SysTreeService;
import com.konglingzhan.manager.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class MenuController {
    @Resource
    private MenuService menuService;

    @Resource
    private SysTreeService sysTreeService;

    @PostMapping("/aclmodule/add")
    public Result insert(AclModuleParam param){
        menuService.insert(param);
        return Result.success();
    }

    @PostMapping("/aclmodule/update")
    public Result update(AclModuleParam param){
        menuService.update(param);
        return Result.success();
    }

    @PostMapping("/aclmodule/all")
    public Result queryAll(){
        List<Menu> list = menuService.selectAll();
        return Result.success(list);
    }

    @PostMapping("/aclmodule/del")
    public Result aclmoduleDel(@RequestParam("id") int id){
        menuService.delById(id);
        return Result.success();
    }

    @PostMapping("/aclmodule/tree")
    public Result aclModuleTree(){
        List<AclModuleLevelDto> list = sysTreeService.aclModuleTree();
        return Result.success(list);
    }

    @PostMapping("/aclmodule/findLevelById")
    public Result findLevelById(@RequestParam("aclModuleId") int aclModuleId){
        Menu menu = menuService.findLevelById(aclModuleId);
        return Result.success(menu);
    }
}
