package com.konglingzhan.manager.dao;

import com.konglingzhan.manager.bean.RoleUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleUserMapper {
    @Insert("insert into sys_role_user (role_id,user_id,operator,operate_time,operate_ip) values (#{roleId},#{userId},#{operator},#{operateTime},#{operateIp})")
    int insert(RoleUser roleUser);

    @Select({
            "<script>",
                "select role_id",
                    "from",
                "sys_role_user",
                "where user_id = #{userId}",
            "</script>"
    })
    List<Integer> getRoleIdListByUserId(int userId);
}
