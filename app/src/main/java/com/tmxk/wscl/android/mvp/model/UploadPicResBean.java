package com.tmxk.wscl.android.mvp.model;

//上传文件返回
public class UploadPicResBean {

    /**
     * msg : tdf.jpg 上传成功.
     * size : 1385984
     * downloadUrl : http://10.254.4.29:8186/tmxk/file-upload/files/1/fileName/tdf.jpg
     */

    private String msg;
    private String size;
    private String downloadUrl;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}
