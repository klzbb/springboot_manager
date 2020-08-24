package com.konglingzhan.manager.param;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class MenuParam {
    private Integer id;

    @NotBlank(message = "权限模块不能为空")
    @Length(min=2,max = 20,message = "权限模块名称长度为2-20个字段")
    private String name;

    @Length(min = 6,max = 100,message = "权限点url长度需在6-100个字符之间")
    private String url;

    @NotNull(message = "必须指定权限点的类型")
    @Min(value = 1, message = "权限点类型不合法")
    @Max(value = 3, message = "权限点类型不合法")
    private Integer type;

    private Integer parentId = 0;

    @NotNull(message = "模块展示顺序不能为空")
    private Integer seq;

    @NotNull(message = "权限模块的状态不能为空")
    @Min(value = 0,message = "权限模块状态不合法")
    @Max(value = 1,message = "权限模块状态不合法")
    private Integer status;

    @Length(max = 200,message = "权限模块的备注需在200个字段以内")
    private String remark;
}
