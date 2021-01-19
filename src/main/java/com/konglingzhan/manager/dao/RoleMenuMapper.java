package com.konglingzhan.manager.dao;

import com.konglingzhan.manager.entity.RoleMenu;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoleMenuMapper {

    @Select({
            "<script>",
                "select menu_id from sys_role_menu where role_id in ",
                "<foreach collection='roleIdList' item='roleId' open='(' separator=',' close=')'> #{roleId} </foreach>",
            "</script>"
    })
    List<Integer> getMenuIdListByRoleIdList(@Param("roleIdList") List<Integer> roleIdList);

    @Delete({
            "<script>",
                "delete from sys_role_menu where role_id = #{roleId}",
            "</script>"
    })
    void deleteByRoleId(@Param("roleId") int roleId);

    @Insert({
            "<script>",
                "insert into sys_role_menu (role_id,menu_id,operator,operate_time,operate_ip)",
                    "values",
                "<foreach collection='roleMenuList' item='roleMenuItem'  separator=','>",
                    "(#{roleMenuItem.role_id},#{roleMenuItem.menu_id},#{roleMenuItem.operator},#{roleMenuItem.operate_time},#{roleMenuItem.operate_ip})",
                "</foreach>",
            "</script>"
    })
    void batchInsert(@Param("roleMenuList") List<RoleMenu> roleMenuList);


    @Delete("delete from sys_role_menu where #{roleId}")
    void delByUserId(@Param("roleId") int roleId);

    @Select("select count(1) from sys_role_menu where menu_id = #{menuId}")
    int countByMenuId(@Param("menuId") int menuId);
}
