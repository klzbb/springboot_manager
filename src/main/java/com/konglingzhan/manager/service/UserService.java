package com.konglingzhan.manager.service;

import com.konglingzhan.manager.bean.PageResult;
import com.konglingzhan.manager.bean.User;
import com.konglingzhan.manager.param.PageQuery;
import com.konglingzhan.manager.param.UserParam;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.List;
//UserDetailsService
public interface UserService  {

    int insertUser(UserParam param);

    List<User> selectAllUser();

    User selectUserByUsername(String username);

    void updateById(UserParam param);

    User findByKeyWord (String keyWord);

//    @Override
//    UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;

    List<User> selectByDeptId(String deptId);

    PageResult<User> userList(int deptId, PageQuery pageQuery);

    void delUserById(int id);

    User getUserInfo();
}
