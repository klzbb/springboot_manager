package com.konglingzhan.manager.dao;

import com.konglingzhan.manager.dto.LoginUserInfo;
import com.konglingzhan.manager.dto.UserDto;
import com.konglingzhan.manager.entity.User;
import com.konglingzhan.manager.param.PageQuery;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    /*
    * 新增用户
    * */
    @Insert("insert into sys_user (username,telephone,password,mail,dept_id,status,remark,operator,operate_ip,operate_time) values(#{username},#{telephone},#{password},#{mail},#{deptId},#{status},#{remark},#{operator},#{operateIp},#{operateTime})")
    int insert(User user);

    @Select("select * from sys_user")
    @Results(
            value={
                    @Result(property = "deptId",column = "dept_id")
            }
    )
    List<UserDto> selectAllUser();

    @Select("select * from sys_user where username = #{username}")
    User selectUserByUsername(@Param("username") String username);

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

    @Select("select * from sys_user where telephone = #{keyWord} or mail = #{keyWord}")
    User findByKeyWord(@Param("keyWord") String keyWord);

    @Select({
            "<script>",
                "select count(1) ",
                    "from ",
                "sys_user",
                    "where mail = #{mail}",
                    "<if test='id != null'> and id != #{id} </if>",
            "</script>"
    })
    int countByMail(@Param("mail") String mail, @Param("id") Integer id);

    @Select({
            "<script>",
                "select count(1) ",
                    "from ",
                "sys_user",
                    "where telephone = #{telephone}",
                    "<if test='id != null'> and id != #{id} </if>",
            "</script>"
    })
    int countByTelephone(@Param("telephone") String telephone, @Param("id") Integer id);


    @Select("select * from sys_user where dept_id = #{deptId}")
    List<User> selectByDeptId(@Param("deptId") String deptId);

    @Select("select count(1) from sys_user where dept_id = #{deptId}")
    int countByDeptId(@Param("deptId") int deptId);

    @Select("select * from sys_user where dept_id = #{deptId} order by username ASC limit  #{page.offset}, #{page.pageSize}")
    List<User> userList(@Param("deptId") int deptId, @Param("page") PageQuery page);

    @Delete("delete from sys_user where id = #{id}")
    void delUserById(@Param("id") int id);

    @Select("select * from sys_user where id = #{id}")
    LoginUserInfo getUserInfo(@Param("id") int id);
}
