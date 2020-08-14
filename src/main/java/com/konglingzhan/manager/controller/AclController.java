package com.konglingzhan.manager.controller;

import com.konglingzhan.manager.bean.Acl;
import com.konglingzhan.manager.param.AclParam;
import com.konglingzhan.manager.service.AclService;
import com.konglingzhan.manager.vo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class AclController {

    @Resource
    private AclService aclService;

    @PostMapping("/acl/add")
    public Result aclAdd(AclParam param){
        aclService.insert(param);
        return Result.success("权限点插入成功");
    }

    @PostMapping("/acl/all")
    public Result aclAll(){
        List<Acl> list = aclService.queryAll();
        return Result.success(list);
    }
}
