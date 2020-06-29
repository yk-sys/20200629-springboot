package com.aaa.yk.springboot.model;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer id;

    private String username;

    private String password;

    private String perms;

    private String workno;

    private Integer phone;

    private String sex;

    private Date birth;
}