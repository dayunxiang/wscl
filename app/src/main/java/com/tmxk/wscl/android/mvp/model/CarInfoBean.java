package com.tmxk.wscl.android.mvp.model;

public class CarInfoBean {

    /**
     * id : 2
     * carNo : 苏B88888
     * company : 侠客
     * driver : 石破天
     * telephone : 1888888888
     * carType : MPV
     * sysuser : {"id":15,"userName":"王杰锋","loginPwd":"111111","loginName":"wjf","userEmail":"123","department":"dsj","telephone":"12355678"}
     */

    private int id;
    private String carNo;
    private String company;
    private String driver;
    private String telephone;
    private String carType;
    private SysuserBean sysuser;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public SysuserBean getSysuser() {
        return sysuser;
    }

    public void setSysuser(SysuserBean sysuser) {
        this.sysuser = sysuser;
    }

    public static class SysuserBean {
        /**
         * id : 15
         * userName : 王杰锋
         * loginPwd : 111111
         * loginName : wjf
         * userEmail : 123
         * department : dsj
         * telephone : 12355678
         */

        private int id;
        private String userName;
        private String loginPwd;
        private String loginName;
        private String userEmail;
        private String department;
        private String telephone;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getLoginPwd() {
            return loginPwd;
        }

        public void setLoginPwd(String loginPwd) {
            this.loginPwd = loginPwd;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public String getUserEmail() {
            return userEmail;
        }

        public void setUserEmail(String userEmail) {
            this.userEmail = userEmail;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }
    }
}
