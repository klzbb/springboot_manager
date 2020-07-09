package com.konglingzhan.manager.bean;

import lombok.Data;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class AclModule {
    private Integer id;
    /**
     * 权限模块名称
     */
    @NotBlank(message ="权限模块名称不能为空")
    private String name;

    private Integer parent_id;
    private String level;
    private Integer status;
    private Integer seq;
    private String operator;
}
