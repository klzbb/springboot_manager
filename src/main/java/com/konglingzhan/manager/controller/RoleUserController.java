package com.konglingzhan.manager.controller;

import com.konglingzhan.manager.param.RoleUserParam;
import com.konglingzhan.manager.service.RoleUserService;
import com.konglingzhan.manager.vo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class RoleUserController {

    @Resource
    private RoleUserService roleUserService;

    @PostMapping("/roleuser/add")
    public Result roleUserAdd (RoleUserParam param) {
        roleUserService.insert(param);
        return Result.success();
    }
}
