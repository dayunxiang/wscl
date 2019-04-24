package com.tmxk.wscl.android.mvp.model;

public class WaterTestManageBodyBean {
    private int id;
    private AssignmentOrderListBean.ObjectBean.SewageBean sewage;
    private String testingTime;
    private Float outCod;
    private Float outNh3n;
    private Float outP;
    private Float inCod;
    private Float inNh3n;
    private Float inP;
    private String reportNo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AssignmentOrderListBean.ObjectBean.SewageBean getSewage() {
        return sewage;
    }

    public void setSewage(AssignmentOrderListBean.ObjectBean.SewageBean sewage) {
        this.sewage = sewage;
    }

    public String getTestingTime() {
        return testingTime;
    }

    public void setTestingTime(String testingTime) {
        this.testingTime = testingTime;
    }

    public Float getOutCod() {
        return outCod;
    }

    public void setOutCod(Float outCod) {
        this.outCod = outCod;
    }

    public Float getOutNh3n() {
        return outNh3n;
    }

    public void setOutNh3n(Float outNh3n) {
        this.outNh3n = outNh3n;
    }

    public Float getOutP() {
        return outP;
    }

    public void setOutP(Float outP) {
        this.outP = outP;
    }

    public Float getInCod() {
        return inCod;
    }

    public void setInCod(Float inCod) {
        this.inCod = inCod;
    }

    public Float getInNh3n() {
        return inNh3n;
    }

    public void setInNh3n(Float inNh3n) {
        this.inNh3n = inNh3n;
    }

    public Float getInP() {
        return inP;
    }

    public void setInP(Float inP) {
        this.inP = inP;
    }

    public String getReportNo() {
        return reportNo;
    }

    public void setReportNo(String reportNo) {
        this.reportNo = reportNo;
    }
}
