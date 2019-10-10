package com.newer.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private static final long serialVersionUID= 1L;

    //用户id
    private Integer u_id;

    //用户名
    private String userName;
    //密码
    private String password;



}
