package com.konglingzhan.manager.param;

import com.konglingzhan.manager.param.common.UserAdd;
import com.konglingzhan.manager.param.common.UserUpdate;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Getter
@Setter
public class UserParam {

    @NotNull(message = "修改必须传入id",groups = {UserUpdate.class})
    @Null(message = "新增不能指定id", groups = {UserAdd.class})
    private Integer id;

    @NotBlank(message = "(密码不能为空)",groups = {UserAdd.class})
    private String password;

    @NotBlank(message = "(用户名不能为空)")
    @Length(min = 1,max=20,message = "用户名长度需在20个字以内")
    private String username;


    @NotBlank(message = "(电话不能为空)")
    @Length(min = 1 ,max = 13, message = "电话长度需在13个字以内")
    private String telephone;

    @NotBlank(message = "(邮箱不能为空)")
    @Length(min = 5, max = 50, message = "邮箱长度需在50个字符以内")
    private String mail;

    @NotNull(message = "(必须提供用户所在部门)")
    private Integer deptId;

    @NotNull(message = "(必须指定用户状态)")
    @Min(value = 0,message = "用户状态不合法")
    @Max(value = 2,message = "用户状态不合法")
    private Integer status;

    @Length(min = 0, max = 200, message = "(备注长度需在200个字以内)")
    private String remark = "";

    private String rolesStr;
}
