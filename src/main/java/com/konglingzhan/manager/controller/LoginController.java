package com.konglingzhan.manager.controller;

import com.konglingzhan.manager.common.properties.SecurityConstants;
import com.konglingzhan.manager.vo.CodeMsg;
import com.konglingzhan.manager.vo.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
//@RequiredArgsConstructor
//@CrossOrigin(origins = "*",maxAge = 3600)
public class LoginController {

    /**
     * session 失效
     * @return
     */
    @GetMapping("/login/timeout")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Result loginTimeout(){
        return new Result(CodeMsg.CODE_NO_LOGIN,"登录态失效");
    }


    /**
     * 登出
     * @return Result
     */
    @GetMapping("/logoutSuccess")
    public Result logoutSuccess(){
        return Result.success("登出成功");
    }

    /**
     * 当需要身份认证时，跳转到这里
     * @param request
     * @param response
     * @return Result
     * @throws
     */
    @GetMapping(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
    public Result requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return Result.success("当需要身份认证时，跳转到这里");
    }
}
