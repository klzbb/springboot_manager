package com.konglingzhan.manager.bean;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "t_test")
@Data
public class Test {

    @Column(name = "dept_name")
    private String deptName;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dept_no")
    private Integer deptNo;
}
