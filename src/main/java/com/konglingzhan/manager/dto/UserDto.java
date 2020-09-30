package com.konglingzhan.manager.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
public class UserDto {

    private Integer id;

    private String username;

    private String telephone;

    private String mail;

    private String deptName;

    private Integer status;

    private String remark;

}