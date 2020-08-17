package com.konglingzhan.manager.dao;

import com.konglingzhan.manager.bean.Acl;
import com.konglingzhan.manager.bean.AclModule;
import com.konglingzhan.manager.bean.User;
import com.konglingzhan.manager.param.PageQuery;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AclMapper {

    @Insert("insert into sys_acl(code,name,acl_module_id,url,type,status,seq,remark,operator,operate_time,operate_ip) values(#{code},#{name},#{acl_module_id},#{url},#{type},#{status},#{seq},#{remark},#{operator},#{operate_time},#{operate_ip})")
    void insert(Acl acl);

    @Select("select * from sys_acl")
    List<Acl> selectAll();

    @Select({
            "<script>",
            "select count(1)",
            "from sys_acl",
            "where acl_module_id = #{aclModuleId}",
            "and name = #{name}",
            "<if test='id != null'> and id != #{id} </if>",
            "</script>"
    })
    int countByNameAndParentId(int aclModuleId, String name, Integer id);

    @Select("select * from sys_acl where id = #{id}")
    Acl selectByPrimaryKey(Integer id);

    @Update({
            "<script>",
                "update sys_acl set",
                    "code = #{code},",
                    "name = #{name},",
                    "acl_module_id = #{acl_module_id},",
                    "url = #{url},",
                    "type = #{type},",
                    "status = #{status},",
                    "seq = #{seq},",
                    "remark = #{remark},",
                    "operator = #{operator},",
                    "operate_time = #{operate_time},",
                    "operate_ip = #{operate_ip}",
                "where id = #{id}",
            "</script>"
    })
    void updateByPrimaryKeySelective(Acl acl);

    @Select("select count(1) from sys_acl where acl_module_id = #{aclModuleId}")
    int countByAclModuleId(@Param("aclModuleId") int aclModuleId);

    @Select("select * from sys_acl where acl_module_id = #{aclModuleId} order by seq ASC, name ASC limit  #{page.offset}, #{page.pageSize}")
    List<Acl> pageList(@Param("aclModuleId") int aclModuleId, @Param("page") PageQuery page);

    @Delete("delete from sys_acl where id = #{id}")
    void del(@Param("id") int id);
}
