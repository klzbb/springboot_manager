package com.konglingzhan.manager.bean;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "sys_user")
public class User {//UserDetails

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String username;

    private String telephone;

    private String mail;

    private String password;

    @Column(name = "dept_id")
    private Integer deptId;

    private Integer status;

    private String remark;

    private String operator;

    @Column(name = "operate_ip")
    private String operateIp;

    @Column(name = "operate_time")
    private Date operateTime;

    private List<Role> roles;

    private Boolean isEnabled = true;

}
