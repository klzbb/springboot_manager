package com.konglingzhan.manager.controller;

import com.konglingzhan.manager.bean.Role;
import com.konglingzhan.manager.exception.CommonException;
import com.konglingzhan.manager.exception.ParamException;
import com.konglingzhan.manager.param.RoleParam;
import com.konglingzhan.manager.service.RoleAclService;
import com.konglingzhan.manager.service.RoleService;
import com.konglingzhan.manager.service.SysTreeService;
import com.konglingzhan.manager.util.StringUtil;
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
public class RoleController {
    @Resource
    private RoleService roleService;

    @Resource
    private SysTreeService sysTreeService;

    @Resource
    private RoleAclService roleAclService;

    @PostMapping("/role/add")
    public Result roleAdd(RoleParam param){
      roleService.insertRole(param);
      return Result.success();
    }

    @PostMapping("/role/update")
    public Result roleUpdate(RoleParam param){
        roleService.update(param);
        return Result.success();
    }

    @PostMapping("/role/list")
    public Result roleAll(){
        List<Role> roleList = roleService.selectAllRole();
        return Result.success(roleList);
    }

    @PostMapping("/role/tree")
    public Result roleTree(@RequestParam("roleId") int roleId){
        return Result.success(sysTreeService.roleTree(roleId));
    }

    @PostMapping("/role/del")
    public Result roleDel(int roleId){
        int num = roleService.del(roleId);
        if(num == 1){
            return Result.success();
        } else {
            throw new ParamException("删除角色Id不存在");
        }
    }

    @PostMapping("/role/changeAcl")
    public Result roleChangeAcl(@RequestParam("roleId") int roleId, @RequestParam(value = "aclIds",required = false,defaultValue = "") String aclIds){
        List<Integer> aclList = StringUtil.splitToListInt(aclIds);
        roleAclService.changeRoleAcls(roleId,aclList);
        return Result.success();
    }
}
