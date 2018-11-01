package com.tmxk.wscl.android.mvp.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserLoginLogBean implements Serializable {
    private long userlogintime;
    private SysuserEntity sysuser;
    private int id;

    @Data
    public class SysuserEntity {
        private int id;
        private String userName;
        private String loginPwd;
        private String loginName;
        private String userEmail;
        private String department;
        private String telephone;
    }
}
