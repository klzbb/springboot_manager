package com.konglingzhan.manager.common;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 拦截器实现方案之: interceptor（spring框架提供的拦截机制）
 * Filter -> Interceptor -> Aspect
 **/
@Component
@Slf4j
public class HttpInterceptor extends HandlerInterceptorAdapter {
    private static final String START_TIME = "requestStartTime";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle = {}",handler);
        request.setAttribute("startTime",new Date().getTime());
        return true;
    }

    // 正常处理请求之后调用
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle");
    }

    // 请求异常或正常都会调用
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion");
        Long endTime = new Date().getTime();
        Long startTime = (Long) request.getAttribute("startTime");
        Long time = endTime - startTime;
        log.info("{}-{}-{}",request.getServletPath(),"调用耗时",time);
    }
}
