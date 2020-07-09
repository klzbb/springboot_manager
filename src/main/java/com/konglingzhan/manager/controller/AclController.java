package com.konglingzhan.manager.controller;

import com.konglingzhan.manager.bean.Acl;
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
    public Result aclAdd(Acl acl){
        int result = aclService.insert(acl);
        if(result == 1){
            return Result.success();
        } else {
            return Result.error("插入权限失败");
        }
    }

    @PostMapping("/acl/all")
    public Result aclAll(){
        List<Acl> list = aclService.queryAll();
        return Result.success(list);
    }
}
