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

    @NotBlank(message = "(菜单名称不能为空)")
    @Length(min=2,max = 20,message = "(名称长度为2-20个字段)")
    private String name;

    @Length(min = 1,max = 100,message = "path长度需在1-100个字符之间")
    private String path;

    @NotNull(message = "必须指定菜单的类型")
    @Min(value = 1, message = "菜单类型不合法")
    @Max(value = 3, message = "菜单类型不合法")
    private Integer type;

    private Integer parentId = 0;

    private String componentName;

    private String component;

    private String icon;

    @NotNull(message = "菜单展示顺序不能为空")
    private Integer seq;

    @NotNull(message = "菜单的状态不能为空")
    @Min(value = 0,message = "菜状态不合法")
    @Max(value = 1,message = "菜单状态不合法")
    private Integer status;

    @Length(max = 200,message = "菜单的备注需在200个字段以内")
    private String remark;
}
