package com.tmxk.wscl.android.mvp.model;

import java.util.List;

public class AreaListBean {

    /**
     * metaInfo : {"code":200,"offset":1,"limit":10,"totalPage":1,"totalSize":1}
     * object : [{"id":9,"pid":1,"name":"无锡","coordinateX":31.67727,"coordinateY":120.43651,"introduce":"江苏省","isSendArea":"true"}]
     */

    private MetaInfoBean metaInfo;
    private List<AreaBean> object;

    public MetaInfoBean getMetaInfo() {
        return metaInfo;
    }

    public void setMetaInfo(MetaInfoBean metaInfo) {
        this.metaInfo = metaInfo;
    }

    public List<AreaBean> getObject() {
        return object;
    }

    public void setObject(List<AreaBean> object) {
        this.object = object;
    }

    public static class MetaInfoBean {
        /**
         * code : 200
         * offset : 1
         * limit : 10
         * totalPage : 1
         * totalSize : 1
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

    public static class AreaBean {
        /**
         * id : 9
         * pid : 1
         * name : 无锡
         * coordinateX : 31.67727
         * coordinateY : 120.43651
         * introduce : 江苏省
         * isSendArea : true
         */

        private int id;
        private int pid;
        private String name;
        private double coordinateX;
        private double coordinateY;
        private String introduce;
        private String isSendArea;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

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

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public String getIsSendArea() {
            return isSendArea;
        }

        public void setIsSendArea(String isSendArea) {
            this.isSendArea = isSendArea;
        }
    }
}
