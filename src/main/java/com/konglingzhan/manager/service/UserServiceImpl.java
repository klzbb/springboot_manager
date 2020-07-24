package com.konglingzhan.manager.service;

import com.konglingzhan.manager.bean.User;
import com.konglingzhan.manager.dao.UserMapper;
import com.konglingzhan.manager.exception.ParamException;
import com.konglingzhan.manager.param.UserParam;
import com.konglingzhan.manager.util.BeanValidator;
import org.assertj.core.util.Preconditions;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public int insertUser(UserParam param) {
        BeanValidator.check(param);
        if(checkTelephoneExist(param.getTelephone(),param.getId())){
            throw new ParamException("电话已经被占用");
        }

        if(checkEmailExist(param.getMail(),param.getId())){
            throw new ParamException("邮箱已经被占用");
        }

        String password = "123456";
        User user = User.builder().username(param.getUsername()).telephone(param.getTelephone()).mail(param.getMail()).password(password).deptId(param.getDeptId()).status(param.getStatus()).remark(param.getRemark()).build();
        user.setOperator("system");
        user.setOperateIp("127.0.0.1");
        user.setOperateTime(new Date());

        return userMapper.insert(user);
    }


    @Override
    public void updateById(UserParam param) {
        BeanValidator.check(param);
        if(checkTelephoneExist(param.getTelephone(),param.getId())){
            throw new ParamException("电话已经被占用");
        }

        if(checkEmailExist(param.getMail(),param.getId())){
            throw new ParamException("邮箱已经被占用");
        }

        User before = userMapper.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull(before,"待更新的用户不存在");

        User after = User.builder().id(param.getId()).username(param.getUsername()).telephone(param.getTelephone()).mail(param.getMail()).password(before.getPassword()).deptId(param.getDeptId()).status(param.getStatus()).remark(param.getRemark()).build();
        after.setOperator("system");
        after.setOperateIp("127.0.0.1");
        after.setOperateTime(new Date());
        userMapper.updateById(after);
    }


    public boolean checkEmailExist(String email, Integer userId){
        return false;
    }

    public boolean checkTelephoneExist(String tel,Integer userId){
        return false;
    }
    @Override
    public List<User> selectAllUser() {
        return userMapper.selectAllUser();
    }

    @Override
    public List<User> selectUserByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }

}
