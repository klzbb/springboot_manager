package com.konglingzhan.manager.controller;

import com.konglingzhan.manager.dao.RoleMenuMapper;
import com.konglingzhan.manager.service.RoleMenuService;
import com.konglingzhan.manager.util.StringUtil;
import com.konglingzhan.manager.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.List;


@RestController
@Validated
public class RoleMenuController {

    @Resource
    private RoleMenuService roleMenuService;

    @PostMapping("/roleMenu/save")
    public Result save(@NotBlank String menuIds, int roleId){
        List<Integer> menuIdList = StringUtil.splitToListInt(menuIds);
        roleMenuService.save(menuIdList,roleId);
        return Result.success();
    }
}
