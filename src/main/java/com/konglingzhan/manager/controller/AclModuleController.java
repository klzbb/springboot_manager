package com.konglingzhan.manager.controller;

import com.konglingzhan.manager.bean.AclModule;
import com.konglingzhan.manager.service.AclModuleService;
import com.konglingzhan.manager.vo.CodeMsg;
import com.konglingzhan.manager.vo.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@Controller
public class AclModuleController {
    @Resource
    private AclModuleService aclModuleService;

    @PostMapping("/aclmodule/add")
    public Result insert(AclModule aclModule){
        String name = aclModule.getName();
        List<AclModule> list = aclModuleService.selectByName(name);
        if(list.size() == 0){
            if(aclModule.getLevel() == null){
                return Result.error(new CodeMsg(-1,"权限模块层级为必填项"));
            }
            if(aclModule.getParent_id() == null){
                aclModule.setParent_id(0);
            }
            if(aclModule.getStatus() == null){
                aclModule.setStatus(1);
            }
            if(aclModule.getSeq() == null){
                aclModule.setSeq(1);
            }

            int result = aclModuleService.insert(aclModule);
            if(result == 1){
                return Result.success("权限模块创建成功");
            } else {
                return Result.error(CodeMsg.SERVER_EXCEPTION);
            }
        } else {
            return Result.error(CodeMsg.ACL_MODULE_EXIST);
        }
    }

    @PostMapping("/aclmodule/all")
    public Result queryAll(){
        List<AclModule> list = aclModuleService.selectAll();
        return Result.success(list);
    }
}
