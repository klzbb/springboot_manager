package com.konglingzhan.manager.controller;

import com.konglingzhan.manager.bean.Dept;
import com.konglingzhan.manager.exception.ParamException;
import com.konglingzhan.manager.param.DeptVo;
import com.konglingzhan.manager.service.DeptService;
import com.konglingzhan.manager.util.BeanValidator;
import com.konglingzhan.manager.vo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class DeptController {
    @Resource
    private DeptService deptService;

    @PostMapping("/dept/add")
    public Result deptAdd(Dept dept){
        List<Dept> list = deptService.selectByName(dept.getName());
        if(list.size() == 0){
            int result = deptService.insert(dept);
            if(result == 1){
                return Result.success("新建部门成功");
            } else {
                return Result.error("插入错误");
            }
        } else{
            return Result.error("已存在该部门名称");
        }

    }

    @PostMapping("/dept/all")
    public Result deptAll(){
        List<Dept> list = deptService.queryAll();
        return Result.success(list);
    }

    @PostMapping("/dept/search")
    public Result search(DeptVo vo) throws ParamException {
        BeanValidator.check(vo);
        List<Dept> list = deptService.search(vo);
        return Result.success(list);
    }

    @PostMapping("/dept/selectByName")
    public Result selectByName(String name) {
        List<Dept> list = deptService.selectByName(name);
        return Result.success(list);
    }

}
