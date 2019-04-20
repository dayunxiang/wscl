package com.tmxk.wscl.android.mvp.model;

public class DeviceReplaceCreateBody {
    /**
     * brokerage : string
     * deviceName : string
     * deviceNo : string
     * deviceType : string
     * id : 0
     * productionDate : 2019-04-16T15:05:30.287Z
     * replaceDate : 2019-04-16T15:05:30.287Z
     * sewage :
     */

    private String brokerage;
    private String deviceName;
    private String deviceNo;
    private String deviceType;
    private int id;
    private String productionDate;
    private String replaceDate;
    private AssignmentOrderListBean.ObjectBean.SewageBean sewage;

    public String getBrokerage() {
        return brokerage;
    }

    public void setBrokerage(String brokerage) {
        this.brokerage = brokerage;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    public String getReplaceDate() {
        return replaceDate;
    }

    public void setReplaceDate(String replaceDate) {
        this.replaceDate = replaceDate;
    }

    public AssignmentOrderListBean.ObjectBean.SewageBean getSewage() {
        return sewage;
    }

    public void setSewage(AssignmentOrderListBean.ObjectBean.SewageBean sewage) {
        this.sewage = sewage;
    }
}
