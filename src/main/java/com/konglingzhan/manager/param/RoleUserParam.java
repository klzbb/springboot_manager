package com.konglingzhan.manager.param;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RoleUserParam {

    private Integer id;

    @NotNull(message = "角色id不能为空")
    private Integer roleId;

    @NotNull(message = "用户id不能为空")
    private Integer userId;
}
