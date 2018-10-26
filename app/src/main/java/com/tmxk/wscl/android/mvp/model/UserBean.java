package com.tmxk.wscl.android.mvp.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserBean implements Serializable {
    private int id;
    private String userName;
    private String loginPwd;
    private String loginName;
    private String userEmail;
    private String department;
    private String telephone;
}