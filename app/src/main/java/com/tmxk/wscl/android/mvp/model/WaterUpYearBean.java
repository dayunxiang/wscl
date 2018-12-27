package com.tmxk.wscl.android.mvp.model;

import java.util.List;

public class WaterUpYearBean {

    /**
     * metaInfo : {"code":200,"offset":1,"limit":100,"totalPage":1,"totalSize":3}
     * object : [{"sewageId":14,"sewageName":"rewr3","operateId":"ewr3","controlId":2,"upNum":0,"totalNum":0,"bit":"0%"},{"sewageId":23,"sewageName":"长浜河","operateId":"XB10SD30T-041","controlId":23,"upNum":0,"totalNum":13,"bit":"0%"},{"sewageId":145,"sewageName":"er43","operateId":"fdf32","controlId":3,"upNum":0,"totalNum":0,"bit":"0%"}]
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
         * limit : 100
         * totalPage : 1
         * totalSize : 3
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
         * sewageId : 14
         * sewageName : rewr3
         * operateId : ewr3
         * controlId : 2
         * upNum : 0
         * totalNum : 0
         * bit : 0%
         */

        private int sewageId;
        private String sewageName;
        private String operateId;
        private int controlId;
        private int upNum;
        private int totalNum;
        private String bit;

        public int getSewageId() {
            return sewageId;
        }

        public void setSewageId(int sewageId) {
            this.sewageId = sewageId;
        }

        public String getSewageName() {
            return sewageName;
        }

        public void setSewageName(String sewageName) {
            this.sewageName = sewageName;
        }

        public String getOperateId() {
            return operateId;
        }

        public void setOperateId(String operateId) {
            this.operateId = operateId;
        }

        public int getControlId() {
            return controlId;
        }

        public void setControlId(int controlId) {
            this.controlId = controlId;
        }

        public int getUpNum() {
            return upNum;
        }

        public void setUpNum(int upNum) {
            this.upNum = upNum;
        }

        public int getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(int totalNum) {
            this.totalNum = totalNum;
        }

        public String getBit() {
            return bit;
        }

        public void setBit(String bit) {
            this.bit = bit;
        }
    }
}
