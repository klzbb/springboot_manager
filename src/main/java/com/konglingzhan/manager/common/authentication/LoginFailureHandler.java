package com.konglingzhan.manager.common.authentication;

import com.konglingzhan.manager.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {

        if (e instanceof AccountExpiredException) {
            // 账号过期
            log.info("[登录失败] - 用户[{}]账号过期");
//            return Result.error("账号过期");

        } else if (e instanceof BadCredentialsException) {
            // 密码错误
            log.info("[登录失败] - 用户[{}]密码错误");

        } else if (e instanceof CredentialsExpiredException) {
            // 密码过期
            log.info("[登录失败] - 用户[{}]密码过期");

        } else if (e instanceof DisabledException) {
            // 用户被禁用
            log.info("[登录失败] - 用户[{}]被禁用");

        } else if (e instanceof LockedException) {
            // 用户被锁定
            log.info("[登录失败] - 用户[{}]被锁定");

        } else if (e instanceof InternalAuthenticationServiceException) {
            // 内部错误
            log.error("[登录失败] - [%s]内部错误");

        } else {
            // 其他错误
            log.error("[登录失败] - [%s]其他错误");
        }

    }
}