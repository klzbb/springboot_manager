package com.konglingzhan.manager.controller;

import com.konglingzhan.manager.bean.AclModule;
import com.konglingzhan.manager.service.AclModuleService;
import com.konglingzhan.manager.vo.CodeMsg;
import com.konglingzhan.manager.vo.Result;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
public class AclModuleController {
    @Resource
    private AclModuleService aclModuleService;

    @PostMapping("/aclmodule/add")
    public Result insert(@Validated AclModule aclModule){
        String name = aclModule.getName();
        List<AclModule> list = aclModuleService.selectByName(name);
        if(list.size() == 0){
            int result = aclModuleService.insert(aclModule);
            if(result == 1){
                return Result.success();
            } else {
                return Result.error();
            }
        } else {
            return Result.error("权限模块名称重复");
        }
    }

    @PostMapping("/aclmodule/all")
    public Result queryAll(){
        List<AclModule> list = aclModuleService.selectAll();
        return Result.success(list);
    }
}
