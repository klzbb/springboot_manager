package com.konglingzhan.manager.controller;

import com.konglingzhan.manager.bean.Role;
import com.konglingzhan.manager.service.RoleService;
import com.konglingzhan.manager.vo.CodeMsg;
import com.konglingzhan.manager.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Controller
public class RoleController {
    @Resource
    private RoleService roleService;

    @PostMapping("/role/add")
    public Result roleAdd(@RequestParam(value = "name") String name,@RequestParam(value = "type") Integer type){
        List<Role> roleList = roleService.selectRoleByname(name);
        if(roleList.size() == 0){
            Role role = new Role();
            role.setName(name);
            role.setType(type);
            int result = roleService.insertRole(role);
            if(result == 1){
                return Result.success("新建角色成功");
            } else {
                return Result.error(CodeMsg.SERVER_EXCEPTION);
            }
        } else {
            return Result.error(CodeMsg.ROLE_EXIST);
        }
    }

    @PostMapping("/role/all")
    public Result roleAll(){
        List<Role> roleList = roleService.selectAllRole();
        return Result.success(roleList);
    }
}
