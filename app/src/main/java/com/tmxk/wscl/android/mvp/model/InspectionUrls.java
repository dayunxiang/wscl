package com.tmxk.wscl.android.mvp.model;

public class InspectionUrls {
    private int id;
    private InspectionInfoListBean.ObjectBean inspectionInfo;
    private String url;

    public InspectionUrls(int id) {
        this.id = id;
    }


    public InspectionUrls() {
    }

    public InspectionUrls(InspectionInfoListBean.ObjectBean inspectionInfo, String url) {
        this.inspectionInfo = inspectionInfo;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public InspectionInfoListBean.ObjectBean getInspectionInfo() {
        return inspectionInfo;
    }

    public void setInspectionInfo(InspectionInfoListBean.ObjectBean inspectionInfo) {
        this.inspectionInfo = inspectionInfo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
