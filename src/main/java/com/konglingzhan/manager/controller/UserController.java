package com.konglingzhan.manager.controller;

import com.konglingzhan.manager.bean.PageResult;
import com.konglingzhan.manager.bean.User;
import com.konglingzhan.manager.common.exception.ParamException;
import com.konglingzhan.manager.param.PageQuery;
import com.konglingzhan.manager.param.TestVo;
import com.konglingzhan.manager.param.UserParam;
import com.konglingzhan.manager.service.UserService;
import com.konglingzhan.manager.util.BeanValidator;
import com.konglingzhan.manager.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Validated
@RestController
@Slf4j
public class UserController {
    @Resource
    private  UserService userService;

    @PostMapping("/register")
    public Result register(@Valid UserParam param) {
        userService.insertUser(param);
        return Result.success("注册用户成功");
    }

    @PostMapping("/userAll")
    public Result userAll(){
        List<User> userList = userService.selectAllUser();
        return Result.success(userList);
    }

    @PostMapping("/user/updateById")
    public Result updateById(UserParam param){
        userService.updateById(param);
        return Result.success();
    }

    @PostMapping("/user/login")
    public Result login (HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        session.setAttribute("userInfo","konglingzhan");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userService.findByKeyWord(username);
        String ret = request.getParameter("ret");
        String errorMsg = "";
        if(StringUtils.isBlank(username)){
            errorMsg = "用户名不能为空";
        } else if (StringUtils.isBlank(password)){
            errorMsg = "密码不能为空";

        } else if(user == null){
            errorMsg = "查询不到指定用户";

        } else if (!user.getPassword().equals(password)){
            errorMsg = "用户名或密码错误";

        } else if(user.getStatus() != 1){
            errorMsg = "用户已被冻结，请联系管理员";

        } else {
            request.getSession().setAttribute("user",user);
            return Result.success("登录成功");
        }
        return Result.error(errorMsg);
    }
    @GetMapping("/user/logout")
    public Result userLogout(HttpServletRequest request){
        request.getSession().invalidate();
        return Result.success("登出成功");
    }

    @PostMapping("/user/session")
    public Result session(HttpServletRequest request){
        System.out.println(request.getClass());

        HttpSession session = request.getSession();
        Object name =  session.getAttribute("user");
        return Result.success(name);
    }

//    @GetMapping("/user/admin")
//    @PreAuthorize("hasRole('admin')")
//    public Result userAdmin(){
//        return Result.success("admin access");
//    }
//    @GetMapping("/user/user")
//    @PreAuthorize("hasRole('admin,normal')")
//    public Result userUser(){
//        return Result.success("access admin user");
//    }

    @PostMapping("/user/byDeptId")
    public Result userByDeptId(@RequestParam(value = "deptId") String deptId){
        List<User> userList = userService.selectByDeptId(deptId);
        return Result.success(userList);
    }

    @PostMapping("/user/list")
    public Result userList(@RequestParam("deptId") int deptId, PageQuery pageQuery){
        PageResult<User> result = userService.userList(deptId,pageQuery);
        return Result.success(result);
    }

    @PostMapping("/user/delById")
    public Result delUserById(@RequestParam("id") int id){
        userService.delUserById(id);
        return Result.success();
    }

    @PostMapping("/user/getUserInfo")
    public Result getUserInfo(){
        User userInfo = userService.getUserInfo();
        return Result.success(userInfo);
    }
}
