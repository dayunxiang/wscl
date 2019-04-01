package com.tmxk.wscl.android.mvp.model;

public class GpsRecordBean {

    /**
     * coordinateX : 0
     * coordinateY : 0
     * createTime : 2019-03-23T13:04:13.732Z
     * id : 0
     * sysuser : {"department":"string","id":0,"loginName":"string","loginPwd":"string","telephone":"string","userEmail":"string","userName":"string"}
     */

    private double coordinateX;
    private double coordinateY;
    private SysuserBean sysuser;

    public double getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(double coordinateX) {
        this.coordinateX = coordinateX;
    }

    public double getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(double coordinateY) {
        this.coordinateY = coordinateY;
    }

    public SysuserBean getSysuser() {
        return sysuser;
    }

    public void setSysuser(SysuserBean sysuser) {
        this.sysuser = sysuser;
    }

    public static class SysuserBean {

        /**
         * department : string
         * id : 0
         * loginName : string
         * loginPwd : string
         * telephone : string
         * userEmail : string
         * userName : string
         */

        private int id;

        public SysuserBean() {
        }
        public SysuserBean(int id) {
            this.id = id;
        }
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
