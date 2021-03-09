package com.konglingzhan.manager.controller;

import com.konglingzhan.manager.common.exception.CommonException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequestMapping("/test")
public class TestController {
    @PostMapping("/cookie")
    public void test(HttpServletRequest request,HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
                 //将数据存储到session中
                 session.setAttribute("data", "孤傲苍狼");
                 //获取session的Id
        String sessionId = session.getId();
        //判断session是不是新创建的
                 if (session.isNew()) {
                     response.getWriter().print("new =："+sessionId);
                 }else {
                     response.getWriter().print("old = ："+sessionId);
                 }
        Cookie cookie = new Cookie("name","konglingzhan");
        cookie.setPath("/");
        cookie.setMaxAge(10000);
        response.addCookie(cookie);

    }
}
