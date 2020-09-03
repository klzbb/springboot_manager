package com.konglingzhan.manager.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sys_user_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer roleId;

    private Integer userId;

    private String operator;

    @Column(name = "operate_ip")
    private String operateIp;

    @Column(name = "operate_time")
    private Date operateTime;

}
