package com.konglingzhan.manager.dao;

import com.konglingzhan.manager.entity.UserRole;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserRoleMapper {
    @Insert("insert into sys_user_role (role_id,user_id,operator,operate_time,operate_ip) values (#{roleId},#{userId},#{operator},#{operateTime},#{operateIp})")
    int insert(UserRole userRole);

    @Select({
            "<script>",
                "select role_id",
                    "from",
                "sys_user_role",
                "where user_id = #{userId}",
            "</script>"
    })
    List<Integer> getRoleIdListByUserId(int userId);
}
