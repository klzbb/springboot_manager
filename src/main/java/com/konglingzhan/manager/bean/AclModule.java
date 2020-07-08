package com.konglingzhan.manager.bean;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AclModule {
    /**
     * 权限模块名称
     */
    @NotBlank(message="权限模块名称不能为空")
    private String name;

    private Integer parent_id;
    private String level;
    private Integer status;
    private Integer seq;
    private String operator;
}
