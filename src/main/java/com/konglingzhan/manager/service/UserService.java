package com.konglingzhan.manager.service;

import com.konglingzhan.manager.dto.LoginUserInfo;
import com.konglingzhan.manager.dto.PageResult;
import com.konglingzhan.manager.dto.UserDto;
import com.konglingzhan.manager.entity.User;
import com.konglingzhan.manager.param.PageQuery;
import com.konglingzhan.manager.param.UserAddParam;
import com.konglingzhan.manager.param.UserUpdateParam;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.List;

public interface UserService extends UserDetailsService {

    void insertUser(UserAddParam param);

    User selectUserByUsername(String username);

    void updateById(UserUpdateParam param);

    User findByKeyWord (String keyWord);

    @Override
    UserDetails loadUserByUsername(String s);

    List<User> selectByDeptId(String deptId);

    PageResult<User> userList(int deptId, PageQuery pageQuery);

    void delUserById(int id);

    LoginUserInfo getUserInfo(int id);

    PageResult<UserDto> userAll(PageQuery pageQuery);
}
