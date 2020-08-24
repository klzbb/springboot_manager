package com.konglingzhan.manager.bean;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class RoleMenu {
    private Integer id;

    private Integer role_id;

    private Integer menu_id;

    private String operator;

    private Date operate_time;

    private String operate_ip;

}
