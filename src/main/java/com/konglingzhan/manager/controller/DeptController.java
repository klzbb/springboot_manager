package com.konglingzhan.manager.controller;

import com.konglingzhan.manager.bean.Dept;
import com.konglingzhan.manager.vo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeptController {
    @PostMapping("/dept/add")
    public Result deptAdd(Dept dept){

    }
}
