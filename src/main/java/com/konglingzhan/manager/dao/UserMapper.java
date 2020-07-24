package com.konglingzhan.manager.dao;

import com.konglingzhan.manager.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Insert("insert into sys_user (username,telephone,mail,dept_id,status,remark,operator,operate_ip,operate_time) values(#{username},#{telephone},#{mail},#{deptId},#{status},#{remark},#{operator},#{operateIp},#{operateTime})")
    int insert(User user);

    @Select("select * from sys_user")
    List<User> selectAllUser();

    @Select("select * from sys_user where username = #{username}")
    List<User> selectUserByUsername(@Param("username") String username);

    @Update({
            "<script>",
                "update sys_user set",
                    "username = #{username},",
                    "telephone = #{telephone},",
                    "mail = #{mail},",
                    "password = #{password},",
                    "dept_id = #{deptId},",
                    "status = #{status},",
                    "remark = #{remark},",
                    "operator = #{operator},",
                    "operate_ip = #{operateIp},",
                    "operate_time = #{operateTime}",
                "where id = #{id}",
            "</script>"
    })
    void updateById(User user);

    @Select("select * from sys_user where id = #{id}")
    User selectByPrimaryKey(int id);
}
