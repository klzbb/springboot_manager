package com.konglingzhan.manager.controller;

import com.konglingzhan.manager.bean.User;
import com.konglingzhan.manager.exception.ParamException;
import com.konglingzhan.manager.param.TestVo;
import com.konglingzhan.manager.param.UserParam;
import com.konglingzhan.manager.service.UserService;
import com.konglingzhan.manager.util.BeanValidator;
import com.konglingzhan.manager.vo.CodeMsg;
import com.konglingzhan.manager.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class UserController {
    @Resource
    private  UserService userService;

    @PostMapping("/register")
    public Result register(@RequestBody UserParam param) {
        userService.insertUser(param);
        return Result.success("注册用户成功");
    }

    @PostMapping("/userAll")
    public Result userAll(){
        List<User> userList = userService.selectAllUser();
        return Result.success(userList);
    }

    @PostMapping("/test1")
    public Result validate(TestVo vo) throws ParamException {
        ArrayList list = new ArrayList();
        list.add("stu1");
        list.add("stu2");
        list.add("stu3");
        list.add("stu4");
        list.add("stu1");
        System.out.println(list.size());
        System.out.println(list.get(5));
        BeanValidator.check(vo);
        return Result.success();
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
            System.out.println("login success");


        }
        return Result.success();
    }

    @PostMapping("/user/session")
    public Result session(HttpServletRequest request){
        HttpSession session = request.getSession();
        Object name =  session.getAttribute("userInfo");
        return Result.success(name);
    }
}
