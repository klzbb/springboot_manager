package com.konglingzhan.manager.config;

import com.konglingzhan.manager.common.HttpInterceptor;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletPath;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import javax.annotation.Resource;

public class WebConfig extends WebMvcAutoConfiguration {
    @Resource
    private HttpInterceptor httpInterceptor;

    public WebConfig(InterceptorRegistration registry) {
//        registry.add(httpInterceptor);
    }
}
