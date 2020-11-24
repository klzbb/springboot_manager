package com.konglingzhan.manager.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class UserDto {

    private Integer id;

    private String username;

    private String telephone;

    private String mail;

    private String deptName;

    private Integer status;

    private String roleIds;

    private Integer deptId;

    private String roleNames;

    private String remark;

}
