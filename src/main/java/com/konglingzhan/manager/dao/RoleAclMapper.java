package com.konglingzhan.manager.dao;

import com.konglingzhan.manager.bean.RoleAcl;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoleAclMapper {

    @Select({
            "<script>",
                "select acl_id from sys_role_acl where role_id in ",
                "<foreach collection='roleIdList' item='roleId' open='(' separator=',' close=')'> #{roleId} </foreach>",
            "</script>"
    })
    List<Integer> getAclIdListByRoleIdList(@Param("roleIdList") List<Integer> roleIdList);

    @Delete({
            "<script>",
                "delete from sys_role_acl where role_id = #{roleId}",
            "</script>"
    })
    void deleteByRoleId(@Param("roleId") int roleId);

    @Insert({
            "<script>",
                "insert into sys_role_acl (role_id,acl_id,operator,operate_time,operate_ip)",
                    "values",
                "<foreach collection='roleAclList' item='roleAcl'  separator=','>",
                    "(#{roleAcl.role_id},#{roleAcl.acl_id},#{roleAcl.operator},#{roleAcl.operate_time},#{roleAcl.operate_ip})",
                "</foreach>",
            "</script>"
    })
    void batchInsert(@Param("roleAclList") List<RoleAcl> roleAclList);
}
