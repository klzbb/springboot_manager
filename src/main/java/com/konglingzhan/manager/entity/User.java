package com.konglingzhan.manager.entity;

import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Date;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    private Boolean isEnabled = true;

}
