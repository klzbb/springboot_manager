package com.konglingzhan.manager.controller;

import com.konglingzhan.manager.entity.Dept;
import com.konglingzhan.manager.dto.DeptLevelDto;
import com.konglingzhan.manager.common.exception.ParamException;
import com.konglingzhan.manager.param.DeptParam;
import com.konglingzhan.manager.service.DeptService;
import com.konglingzhan.manager.service.impl.SysTreeService;
import com.konglingzhan.manager.util.BeanValidator;
import com.konglingzhan.manager.vo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class DeptController {
    @Resource
    private DeptService deptService;

    @Resource
    private SysTreeService sysTreeService;

    @PostMapping("/dept/add")
    public Result deptAdd(@RequestBody DeptParam param){
        deptService.insert(param);
        return Result.success();
    }

    @PostMapping("/dept/all")
    public Result deptAll(){
        List<Dept> list = deptService.queryAll();
        return Result.success(list);
    }

    @PostMapping("/dept/search")
    public Result search(DeptParam vo) throws ParamException {
        BeanValidator.check(vo);
        List<Dept> list = deptService.search(vo);
        return Result.success(list);
    }

    @PostMapping("/dept/selectByName")
    public Result selectByName(String name) {
        List<Dept> list = deptService.selectByName(name);
        return Result.success(list);
    }

    @PostMapping("/dept/tree")
    public Result getDeptTree(){
        List<DeptLevelDto> list = sysTreeService.deptTree();
        return Result.success(list);
    }

    @PostMapping("/dept/update")
    public Result update(DeptParam param){
        deptService.updateDept(param);
        return Result.success("更新部门成功");
    }

    @PostMapping("/dept/delById")
    public Result delById(@RequestParam(value = "id") int id){
        deptService.delById(id);
        return Result.success();
    }
}
