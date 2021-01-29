package com.konglingzhan.manager.controller;

import com.konglingzhan.manager.vo.CodeMsg;
import com.konglingzhan.manager.vo.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping("/login/timeout")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Result loginTimeout(){
        return new Result(CodeMsg.CODE_NO_LOGIN,"登录态失效");
    }
}
