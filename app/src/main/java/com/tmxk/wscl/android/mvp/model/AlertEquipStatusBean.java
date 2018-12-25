package com.tmxk.wscl.android.mvp.model;

import java.util.List;

public class AlertEquipStatusBean {

    /**
     * metaInfo : {"code":200,"offset":1,"limit":10,"totalPage":1,"totalSize":3}
     * object : [{"shortTitle":"长浜河","operationNum":"XB10SD30T-041","testingTime":1542599688000,"sewageID":23,"equipName":["提升泵 开","污水泵 开","回流泵 开","排水泵 关","加药泵 关","曝气泵 故障"]}]
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
         * limit : 10
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
         * shortTitle : 长浜河
         * operationNum : XB10SD30T-041
         * testingTime : 1542599688000
         * sewageID : 23
         * equipName : ["提升泵 开","污水泵 开","回流泵 开","排水泵 关","加药泵 关","曝气泵 故障"]
         */

        private String shortTitle;
        private String operationNum;
        private long testingTime;
        private int sewageID;
        private int controlId;
        private List<String> equipName;

        public int getControlId() {
            return controlId;
        }

        public void setControlId(int controlId) {
            this.controlId = controlId;
        }

        public String getShortTitle() {
            return shortTitle;
        }

        public void setShortTitle(String shortTitle) {
            this.shortTitle = shortTitle;
        }

        public String getOperationNum() {
            return operationNum;
        }

        public void setOperationNum(String operationNum) {
            this.operationNum = operationNum;
        }

        public long getTestingTime() {
            return testingTime;
        }

        public void setTestingTime(long testingTime) {
            this.testingTime = testingTime;
        }

        public int getSewageID() {
            return sewageID;
        }

        public void setSewageID(int sewageID) {
            this.sewageID = sewageID;
        }

        public List<String> getEquipName() {
            return equipName;
        }

        public void setEquipName(List<String> equipName) {
            this.equipName = equipName;
        }
    }
}
