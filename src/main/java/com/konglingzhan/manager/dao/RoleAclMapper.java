package com.konglingzhan.manager.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
}
