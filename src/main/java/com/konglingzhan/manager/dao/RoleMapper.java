package com.konglingzhan.manager.dao;

import com.konglingzhan.manager.bean.Dept;
import com.konglingzhan.manager.bean.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoleMapper {
    @Insert("insert into sys_role(name,type,status,remark,operator,operate_ip,operate_time) values(#{name},#{type},#{status},#{remark},#{operator},#{operate_ip},#{operate_time})")
    int insert(Role role);

    @Update({
            "<script>",
                "update sys_role set",
                    "name = #{name},",
                    "type = #{type},",
                    "status = #{status},",
                    "remark = #{remark},",
                    "operator = #{operator},",
                    "operate_ip = #{operate_ip},",
                    "operate_time = #{operate_time}",
                "where id = #{id}",
            "</script>"
    })
    void update(Role role);

    @Select({
            "<script>",
                "select count(1) from sys_role",
                    "where name = #{name}",
                    "<if test='id != null'> and id != #{id} </if>",
            "</script>"
    })
    int countByname(String name,Integer id);

    @Select("select * from sys_role")
    List<Role> selectAllRole();

    @Select("select * from sys_role where name = #{name}")
    List<Role> selectRoleByName(@Param("name") String name);

    @Select("SELECT r.* FROM roles r,roles_user ru WHERE r.`id`=ru.`rid` AND ru.`uid`=#{uid}")
    List<Role> getRolesByUid(@Param("uid") Integer uid);

    @Select("select * from sys_role where id = #{id}")
    Role selectByPrimaryKey(Integer id);
}
