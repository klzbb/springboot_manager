package com.konglingzhan.manager.common.authentication;

import com.konglingzhan.manager.common.filter.JwtAuthenticationFilter;
import com.konglingzhan.manager.service.UserService;
import com.konglingzhan.manager.util.JsonUtil;
import com.konglingzhan.manager.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.session.InvalidSessionStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@Configuration
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;
    /**
     * 登录失败的处理
     */
    @Autowired
    private LoginFailureHandler loginFailureHandler;
    /**
     * 登录成功的处理
     */
    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    /**
     * 配置认证方式等
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }
    /**
     * BCrypt加密
     */
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
        return new JwtAuthenticationFilter();
    }
    /**
     * http相关的配置，包括登入登出、异常处理、会话管理等
     *
     * @param
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception{

        // Add our custom JWT security filter
        httpSecurity.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        httpSecurity.cors().and().csrf().disable();

        httpSecurity
                // 放行接口
                .authorizeRequests()
                .antMatchers("/register","/login/timeout").permitAll()
                .anyRequest()
                .authenticated()

                // 异常处理（权限拒绝，登录失效）
                .and().exceptionHandling()
                .authenticationEntryPoint(new AuthenticationEntryPoint() {
                    @Override
                    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                        System.out.println("authenticationEntryPoint");
                    }
                })
                .accessDeniedHandler(new AccessDeniedHandler() {
                    @Override
                    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
                        System.out.println("accessDeniedHandler");
                    }
                })

                // 会话管理（登录超时）
                .and()
                .sessionManagement()
                .invalidSessionUrl("/login/timeout")

                // 登录
                .and()
                .formLogin()
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailureHandler)
                .permitAll()

                // 登出
                .and()
                .logout()
                .logoutUrl("/logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .logoutSuccessHandler((req,res,authention) -> {
                    res.setContentType("application/json;charset=utf-8");
                    PrintWriter out = res.getWriter();
                    out.write("{\"data\":\"success\",\"msg\":\"注销登录\",\"code\":0}");
                    out.flush();
                    out.close();
                });

    }

}
