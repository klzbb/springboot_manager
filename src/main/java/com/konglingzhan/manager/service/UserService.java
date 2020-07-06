package com.konglingzhan.manager.service;

import com.konglingzhan.manager.bean.User;
import org.springframework.stereotype.Service;
import java.util.List;

public interface UserService {
    int insertUser(User user);

    List<User> selectAllUser();

    List<User> selectUserByUsername(String username);
}
