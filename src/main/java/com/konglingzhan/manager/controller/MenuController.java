package com.konglingzhan.manager.controller;

import com.konglingzhan.manager.entity.Menu;
import com.konglingzhan.manager.dto.AclModuleLevelDto;
import com.konglingzhan.manager.param.MenuParam;
import com.konglingzhan.manager.service.MenuService;
import com.konglingzhan.manager.service.impl.SysTreeService;
import com.konglingzhan.manager.vo.Result;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Resource
    private MenuService menuService;

    @Resource
    private SysTreeService sysTreeService;

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

    @PostMapping("/del")
    public Result del(@RequestParam("id") int id){
        menuService.delById(id);
        return Result.success();
    }

    @PostMapping("/tree")
    public Result tree(){
        List<AclModuleLevelDto> list = sysTreeService.aclModuleTree();
        return Result.success(list);
    }

    @PostMapping("/findLevelById")
    public Result findLevelById(@RequestParam("aclModuleId") int aclModuleId){
        Menu menu = menuService.findLevelById(aclModuleId);
        return Result.success(menu);
    }
}