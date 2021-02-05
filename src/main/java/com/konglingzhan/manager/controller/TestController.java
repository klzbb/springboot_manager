package com.konglingzhan.manager.controller;

import com.konglingzhan.manager.common.exception.CommonException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @PostMapping("/test")
    public String test(){
        throw new CommonException(-1,"test异常");
//        return "test";
    }
}
