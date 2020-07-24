package com.konglingzhan.manager.bean;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private Integer id;
    private String username;
    private String telephone;
    private String mail;
    private String password;
    private Integer deptId;
    private Integer status;
    private String remark;
    private String operator;
    private String operateIp;
    private Date operateTime;
}
