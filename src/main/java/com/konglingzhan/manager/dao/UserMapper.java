package com.konglingzhan.manager.dao;

import com.konglingzhan.manager.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Insert("insert into sys_user(username,password) values(#{username},#{password})")
    int insert(User user);

    @Select("select * from sys_user")
    List<User> selectAllUser();

    @Select("select * from sys_user where username = #{username}")
    List<User> selectUserByUsername(@Param("username") String username);
}
