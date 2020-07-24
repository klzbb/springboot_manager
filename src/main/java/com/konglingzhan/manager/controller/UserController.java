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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
    public Result register(UserParam param) {
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
}
