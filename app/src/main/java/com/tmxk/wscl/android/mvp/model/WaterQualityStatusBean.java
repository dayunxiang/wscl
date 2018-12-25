package com.tmxk.wscl.android.mvp.model;

import java.util.List;

public class WaterQualityStatusBean {

    /**
     * metaInfo : {"code":200,"offset":1,"limit":10,"totalPage":1,"totalSize":3}
     * object : [{"shortTitle":"长浜河","operationNum":"XB10SD30T-041","testingTime":1542792120000,"sewageID":23,"qualityInfo":[{"name":"T","val":60},{"name":"DO","val":1},{"name":"COD","val":1},{"name":"NH3N","val":11},{"name":"P","val":11},{"name":"SS","val":1}]}]
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
         * testingTime : 1542792120000
         * sewageID : 23
         * qualityInfo : [{"name":"T","val":60},{"name":"DO","val":1},{"name":"COD","val":1},{"name":"NH3N","val":11},{"name":"P","val":11},{"name":"SS","val":1}]
         */

        private String shortTitle;
        private String operationNum;
        private long testingTime;
        private int sewageID;
        private List<QualityInfoBean> qualityInfo;

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

        public List<QualityInfoBean> getQualityInfo() {
            return qualityInfo;
        }

        public void setQualityInfo(List<QualityInfoBean> qualityInfo) {
            this.qualityInfo = qualityInfo;
        }

        public static class QualityInfoBean {
            /**
             * name : T
             * val : 60
             */

            private String name;
            private Float val;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Float getVal() {
                return val;
            }

            public void setVal(Float val) {
                this.val = val;
            }
        }
    }
}
