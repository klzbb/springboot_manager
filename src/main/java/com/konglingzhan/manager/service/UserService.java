package com.konglingzhan.manager.service;

import com.konglingzhan.manager.bean.User;
import com.konglingzhan.manager.param.UserParam;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

public interface UserService extends UserDetailsService {

    int insertUser(UserParam param);

    List<User> selectAllUser();

    User selectUserByUsername(String username);

    void updateById(UserParam param);

    User findByKeyWord (String keyWord);

//    @Override
//    UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;
}
