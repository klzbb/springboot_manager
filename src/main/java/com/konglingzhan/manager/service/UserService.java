package com.konglingzhan.manager.service;

import com.konglingzhan.manager.bean.User;
import com.konglingzhan.manager.param.UserParam;
import org.springframework.stereotype.Service;
import java.util.List;

public interface UserService {

    int insertUser(UserParam param);

    List<User> selectAllUser();

    List<User> selectUserByUsername(String username);

    void updateById(UserParam param);

    User findByKeyWord (String keyWord);
}
