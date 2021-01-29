package com.konglingzhan.manager.common.authentication;

import com.konglingzhan.manager.common.filter.JwtAuthenticationFilter;
import com.konglingzhan.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;


@Configuration
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
        httpSecurity.cors().and().csrf().disable();
        httpSecurity
                .authorizeRequests()
                .antMatchers("/register",
                        "/login/timeout"
                ).permitAll()
                .anyRequest().authenticated()
                .and()
                    .sessionManagement().invalidSessionUrl("/login/timeout")
                .and()
                    .formLogin()
                    .loginProcessingUrl("/login")
                    .successHandler(loginSuccessHandler)
                    .failureHandler(loginFailureHandler)
                    .permitAll()
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .addLogoutHandler(new LogoutHandler() {
                        @Override
                        public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
                            System.out.println("LogoutHandler");
                        }
                     })
                    .logoutSuccessHandler((req,res,authention) -> {
                        res.setContentType("application/json;charset=utf-8");
                        PrintWriter out = res.getWriter();
                        out.write("{\"data\":\"success\",\"msg\":\"注销登录\",\"code\":0}");
                        out.flush();
                        out.close();
                    });
        // Add our custom JWT security filter
        httpSecurity.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

}
