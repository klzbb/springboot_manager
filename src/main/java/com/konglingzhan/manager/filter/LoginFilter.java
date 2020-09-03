//package com.konglingzhan.manager.filter;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.konglingzhan.manager.entity.User;
//import com.konglingzhan.manager.common.RequestHolder;
//import com.konglingzhan.manager.vo.CodeMsg;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.HashMap;
//import java.util.Map;
//
//@Slf4j
//@Order(10)
//@Component
//@WebFilter(filterName = "LoginFilter", urlPatterns = "/*")
//public class LoginFilter implements Filter {
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) servletRequest;
//        HttpServletResponse res = (HttpServletResponse) servletResponse;
//        String url = req.getRequestURI();
//        if(url.equals("/app/user/login") || url.equals("/app/user/logout") || url.equals("/app/register")){
//            filterChain.doFilter(servletRequest, servletResponse);
//            return;
//        }
//        User user = (User) req.getSession().getAttribute("user");
//        if (user == null) {
//            log.info("未登录");
//            res.setContentType("charset=UTF-8");
//            PrintWriter out = res.getWriter();
//            Map result = new HashMap();
//            result.put("code", CodeMsg.CODE_NO_LOGIN);
//            String msg = CodeMsg.getMessage(CodeMsg.CODE_NO_LOGIN);
//            result.put("msg", msg);
//            result.put("data", null);
//            ObjectMapper mapper = new ObjectMapper();
//            out.append(mapper.writeValueAsString(result));
//            return;
//        }
//        RequestHolder.add(user);
//        RequestHolder.add(req);
//        filterChain.doFilter(servletRequest, servletResponse);
//    }
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
