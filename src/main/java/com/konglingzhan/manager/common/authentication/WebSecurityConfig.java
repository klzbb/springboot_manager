package com.konglingzhan.manager.common.authentication;

import com.konglingzhan.manager.common.filter.JwtAuthenticationFilter;
import com.konglingzhan.manager.common.filter.ValidateCodeFilter;
import com.konglingzhan.manager.common.properties.SecurityConstants;
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
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


/**
 * 1.spring-security 过滤器执行顺序
 * FilterChainProxy -
 * WebAsyncManagerIntegrationFilter - 提供SecurityContext和spring Web的集成
 * ChannelProcessingFilter - 如果你访问的channel错了，那首先就会在channel之间进行跳转，如http变为https
 * SecurityContextPersistenceFilter - 加载SecurityContext对象，然后加载到SecurityContextHolder
 * HeaderWriterFilter - 主要是对响应信息的请求头添加一些配置
 * CsrfFilter - 主要防止跨站请求伪造
 * LogoutFilter - 登出操作的处理
 * UsernamePasswordAuthenticationFilter - 主要是用户名密码登录认证
 * RequestCacheAwareFilter - 用于用户登录成功后，重新恢复因为登录被打断的请求
 * SecurityContextHolderAwareRequestFilter - 填充ServletRequest实现servlet API安全方法的包装器
 * AnonymousAuthenticationFilter - 匿名用户信息的填充
 * SessionManagementFilter - 会话的管理机制
 * ExceptionTranslationFilter  - 对于任意的AccessDeniedException类型的异常和AuthenticationException类型异常的处理
 *
 *
 * 2.spring-security 核心组件
 * AuthenticationFilter - 过滤器
 * AuthenticationToken - 包装后的认证类（传递给AuthenticationManager）
 * AuthenticationManager - 认证管理器（接受authenticationToken使用特定的authenticationProvider认证）
 * AccessDecisionManager - 授权管理器
 */

@Configuration
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private UserService userService;

    @Resource
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

    @Resource
    private DataSource dataSource;



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

//    @Bean
//    public JwtAuthenticationFilter jwtAuthenticationFilter(){
//        return new JwtAuthenticationFilter();
//    }

    @Bean
    public ValidateCodeFilter validateCodeFilter(){
        return new ValidateCodeFilter();
    }


    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
//        tokenRepository.setCreateTableOnStartup(true); // 启动时创建记住我功能需要的表
        return tokenRepository;
    }

    @Override
    public void configure(WebSecurity webSecurity){
        // 不经过securityFilter过滤链
        webSecurity.ignoring().antMatchers("/code/image");
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
//        httpSecurity.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        // validateCodeFilter to validate imageCode
        httpSecurity.addFilterBefore(validateCodeFilter(),UsernamePasswordAuthenticationFilter.class);

        httpSecurity.cors().and().csrf().disable();

        httpSecurity
                // 放行接口
                .authorizeRequests()
                .antMatchers(
                        "/register",
                        SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        "/login/timeout",
                        "/test/*"
                    ).permitAll()
                .anyRequest()
                .authenticated()

                // 会话管理（登录超时）
//                .and()
//                .sessionManagement()
//                .invalidSessionUrl("/login/timeout")

                // 记住我
//                .and()
//                .rememberMe()
//                .tokenRepository(persistentTokenRepository())
//                .tokenValiditySeconds(SecurityConstants.rememberMeSeconds)
//                .userDetailsService(userService)

                // 登录
                .and()
                .formLogin()
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL)
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
                    Map logoutInfo = new HashMap();
                    logoutInfo.put("code",0);
                    logoutInfo.put("msg","注销登录");
                    out.write(JsonUtil.toJsonString(logoutInfo));
                    out.flush();
                    out.close();
                });

    }

}
