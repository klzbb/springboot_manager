package com.konglingzhan.manager.controller;

import com.konglingzhan.manager.param.RoleUserParam;
import com.konglingzhan.manager.service.UserRoleService;
import com.konglingzhan.manager.vo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserRoleController {

    @Resource
    private UserRoleService userRoleService;

    @PostMapping("/roleuser/add")
    public Result roleUserAdd (RoleUserParam param) {
        userRoleService.insert(param);
        return Result.success();
    }

//    @PostMapping("/roleuser/all")
//    public Result roleUserAdd (RoleUserParam param) {
//        userRoleService.all(param);
//        return Result.success();
//    }
}
