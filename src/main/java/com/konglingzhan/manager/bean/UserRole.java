package com.konglingzhan.manager.bean;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class UserRole {
    private Integer id;
    private Integer roleId;
    private Integer userId;
    private String operator;
    private Date operateTime;
    private String operateIp;
}
