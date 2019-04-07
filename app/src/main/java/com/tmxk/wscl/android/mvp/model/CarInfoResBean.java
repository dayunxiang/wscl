package com.tmxk.wscl.android.mvp.model;

import java.util.List;

public class CarInfoResBean {
    /**
     * metaInfo : {"code":200,"offset":1,"limit":1000,"totalPage":1,"totalSize":2}
     * object : [{"id":1,"carNo":"苏BW9N60","company":"天马","driver":"无敌","telephone":"18896724553","carType":"SUV","sysuser":{"id":11,"userName":"王尧","loginPwd":"111111","loginName":"wy","userEmail":"123@163.com","department":"abc","telephone":"12345678"}},{"id":2,"carNo":"苏B88888","company":"侠客","driver":"石破天","telephone":"1888888888","carType":"MPV","sysuser":{"id":15,"userName":"王杰锋","loginPwd":"111111","loginName":"wjf","userEmail":"123","department":"dsj","telephone":"12355678"}}]
     */

    private MetaInfoBean metaInfo;
    private List<ObjectBean> object;

    public MetaInfoBean getMetaInfo() {
        return metaInfo;
    }

    public void setMetaInfo(MetaInfoBean metaInfo) {
        this.metaInfo = metaInfo;
    }

    public List<ObjectBean> getObject() {
        return object;
    }

    public void setObject(List<ObjectBean> object) {
        this.object = object;
    }

    public static class MetaInfoBean {
        /**
         * code : 200
         * offset : 1
         * limit : 1000
         * totalPage : 1
         * totalSize : 2
         */

        private int code;
        private int offset;
        private int limit;
        private int totalPage;
        private int totalSize;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getTotalSize() {
            return totalSize;
        }

        public void setTotalSize(int totalSize) {
            this.totalSize = totalSize;
        }
    }

    public static class ObjectBean {
        /**
         * id : 1
         * carNo : 苏BW9N60
         * company : 天马
         * driver : 无敌
         * telephone : 18896724553
         * carType : SUV
         * sysuser : {"id":11,"userName":"王尧","loginPwd":"111111","loginName":"wy","userEmail":"123@163.com","department":"abc","telephone":"12345678"}
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
             * id : 11
             * userName : 王尧
             * loginPwd : 111111
             * loginName : wy
             * userEmail : 123@163.com
             * department : abc
             * telephone : 12345678
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
}
