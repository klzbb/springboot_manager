package com.konglingzhan.manager.param;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RoleParam {

    private Integer id;

    @NotBlank(message = "角色名称不能为空")
    @Length(min=2,max = 20,message = "角色名称需在2-20个字符串以内")
    private String name;

    @NotNull(message = "角色状态不能为空")
    @Min(value = 0,message = "角色状态不合法")
    @Max(value = 1,message = "角色状态不合法")
    private Integer status;

    @Min(value = 1,message = "角色类型不合法")
    @Max(value = 2,message = "角色类型不合法")
    private Integer type = 1;

    @NotNull(message = "角色展示顺序不能为空")
    private Integer seq = 1;

    @Length(min=0,max = 200,message = "角色备注需在200个字符串以内")
    private String remark;
}
