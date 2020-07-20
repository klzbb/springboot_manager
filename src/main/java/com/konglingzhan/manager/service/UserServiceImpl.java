package com.konglingzhan.manager.service;

import com.konglingzhan.manager.bean.User;
import com.konglingzhan.manager.dao.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public int insertUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public List<User> selectAllUser() {
        return userMapper.selectAllUser();
    }

    @Override
    public List<User> selectUserByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }

    @Override
    public void updateById(User user) {
        userMapper.updateById(user);
    }
}
