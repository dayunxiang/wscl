package com.tmxk.wscl.android.mvp.model;

public class RepairmentBean {
    /**
     * handlman : string
     * handltime : 2019-04-16T15:05:30.368Z
     * id : 0
     * repairContent : string
     * repairTime : 2019-04-16T15:05:30.368Z
     * repairman : string
     * repairmanTel : string
     * sewage :
     * status : string
     */

    private String handlman;
    private String handltime;
    private int id;
    private String repairContent;
    private String repairTime;
    private String repairman;
    private String repairmanTel;
    private AssignmentOrderListBean.ObjectBean.SewageBean sewage;
    private String status;

    public RepairmentBean() {
    }

    public RepairmentBean(int id) {
        this.id = id;
    }

    public String getHandlman() {
        return handlman;
    }

    public void setHandlman(String handlman) {
        this.handlman = handlman;
    }

    public String getHandltime() {
        return handltime;
    }

    public void setHandltime(String handltime) {
        this.handltime = handltime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRepairContent() {
        return repairContent;
    }

    public void setRepairContent(String repairContent) {
        this.repairContent = repairContent;
    }

    public String getRepairTime() {
        return repairTime;
    }

    public void setRepairTime(String repairTime) {
        this.repairTime = repairTime;
    }

    public String getRepairman() {
        return repairman;
    }

    public void setRepairman(String repairman) {
        this.repairman = repairman;
    }

    public String getRepairmanTel() {
        return repairmanTel;
    }

    public void setRepairmanTel(String repairmanTel) {
        this.repairmanTel = repairmanTel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AssignmentOrderListBean.ObjectBean.SewageBean getSewage() {
        return sewage;
    }

    public void setSewage(AssignmentOrderListBean.ObjectBean.SewageBean sewage) {
        this.sewage = sewage;
    }
}
