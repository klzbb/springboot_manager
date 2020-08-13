package com.konglingzhan.manager.controller;

import com.konglingzhan.manager.bean.AclModule;
import com.konglingzhan.manager.param.AclModuleParam;
import com.konglingzhan.manager.service.AclModuleService;
import com.konglingzhan.manager.util.BeanValidator;
import com.konglingzhan.manager.vo.CodeMsg;
import com.konglingzhan.manager.vo.Result;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public Result insert(AclModuleParam param){
        aclModuleService.insert(param);
        return Result.success();
    }

    @PostMapping("/aclmodule/update")
    public Result update(AclModule aclModule){
        return Result.success();
    }

    @PostMapping("/aclmodule/all")
    public Result queryAll(){
        List<AclModule> list = aclModuleService.selectAll();
        return Result.success(list);
    }
}
