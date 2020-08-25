package com.konglingzhan.manager.bean;

//import org.hibernate.annotations.Table;


import javax.persistence.*;

@Entity
@Table(name = "kkk")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

}

