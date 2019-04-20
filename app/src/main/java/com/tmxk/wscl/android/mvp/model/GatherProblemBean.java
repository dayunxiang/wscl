package com.tmxk.wscl.android.mvp.model;

public class GatherProblemBean {

    /**
     * description : string
     * findTime : 2019-04-16T15:05:30.347Z
     * finder : string
     * id : 0
     * sewage :
     * title : string
     *
     * description (string, optional): 问题描述 ,
     * findTime (string, optional): 反馈时间 ,
     * finder (string, optional): 反馈者 默认当前登录者 ,
     * id (integer, optional),
     * sewage (sewage表数据, optional): 站点信息 ,
     * title (string, optional): 问题标题
     */

    private String description;
    private long findTime;
    private String finder;
    private int id;
    private AssignmentOrderListBean.ObjectBean.SewageBean sewage;
    private String title;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getFindTime() {
        return findTime;
    }

    public void setFindTime(long findTime) {
        this.findTime = findTime;
    }

    public String getFinder() {
        return finder;
    }

    public void setFinder(String finder) {
        this.finder = finder;
    }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
