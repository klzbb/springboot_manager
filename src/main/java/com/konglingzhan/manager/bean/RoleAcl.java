package com.konglingzhan.manager.bean;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class RoleAcl {
    private Integer id;

    private Integer role_id;

    private Integer acl_id;

    private String operator;

    private Date operate_time;

    private String operate_ip;

}
