package com.konglingzhan.manager.controller;

import com.konglingzhan.manager.dto.UserRoleDto;
import com.konglingzhan.manager.entity.UserRole;
import com.konglingzhan.manager.param.RoleUserParam;
import com.konglingzhan.manager.service.UserRoleService;
import com.konglingzhan.manager.vo.Result;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Validated
public class UserRoleController {

    @Resource
    private UserRoleService userRoleService;

    @PostMapping("/roleuser/add")
    public Result roleUserAdd (RoleUserParam param) {
        userRoleService.insert(param);
        return Result.success();
    }

    @PostMapping("/roleuser/all")
    public Result roleUserAll () {
        List<UserRoleDto> list = userRoleService.all();
        return Result.success(list);
    }

    @PostMapping("/roleuser/del")
    public Result roleUserDel (int id) {
        userRoleService.del(id);
        return Result.success();
    }
}
