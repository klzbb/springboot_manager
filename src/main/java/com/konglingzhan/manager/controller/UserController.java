package com.konglingzhan.manager.controller;

import com.konglingzhan.manager.bean.User;
import com.konglingzhan.manager.exception.ParamException;
import com.konglingzhan.manager.param.TestVo;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class UserController {
    @Resource
    private  UserService userService;

    @PostMapping("/register")
    public Result register(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {
        List<User> person = userService.selectUserByUsername(username);

        if (person.size() == 0) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            int result = userService.insertUser(user);
            if (result == 1) {
                return Result.success("注册用户成功");
            } else {
                return Result.error();
            }
        } else {
            return Result.error("用户名重复");
        }
    }

    @PostMapping("/userAll")
    public Result userAll(){
        List<User> userList = userService.selectAllUser();
        return Result.success(userList);
    }

    @PostMapping("/test1")
    public Result validate(TestVo vo) throws ParamException {
        log.info("validate");
        BeanValidator.check(vo);
//        try{
//            Map<String,String> map = BeanValidator.validateObject(vo);
//            if(MapUtils.isNotEmpty(map)){
//                for (Map.Entry<String,String> entry: map.entrySet()){
//                    log.info("{}=>{}",entry.getKey(),entry.getValue());
//                }
//            }
//        } catch (Exception e){
//
//        }
        return Result.success();
    }
}
