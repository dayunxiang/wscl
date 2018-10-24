package com.tmxk.wscl.android.mvp.model;

import java.io.Serializable;

public class UserInfoBean implements Serializable {

    /**
     * loginPwd : 111111
     * loginName : wy
     * userEmail : 123456@qq.com
     * telephone : 18896724553
     * id : 3
     * userName : 王尧
     * department : 大数据
     */
    private String loginPwd;
    private String loginName;
    private String userEmail;
    private String telephone;
    private int id;
    private String userName;
    private String department;

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public String getLoginName() {
        return loginName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getTelephone() {
        return telephone;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getDepartment() {
        return department;
    }
}
