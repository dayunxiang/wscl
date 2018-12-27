package com.tmxk.wscl.android.mvp.model;

import java.util.List;

public class WaterAnalysisYearBean {

    /**
     * metaInfo : {"code":200,"offset":1,"limit":100,"totalPage":1,"totalSize":12}
     * object : [{"date":1533052800000,"dailyData":35.2,"designData":40,"totalData":null,"diffData":null,"status":null}]
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
         * totalSize : 12
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
         * date : 1533052800000
         * dailyData : 35.2
         * designData : 40
         * totalData : null
         * diffData : null
         * status : null
         */

        private long date;
        private double dailyData;
        private double designData;
        private double totalData;
        private double diffData;
        private Object status;

        public long getDate() {
            return date;
        }

        public void setDate(long date) {
            this.date = date;
        }

        public double getDailyData() {
            return dailyData;
        }

        public void setDailyData(double dailyData) {
            this.dailyData = dailyData;
        }

        public double getDesignData() {
            return designData;
        }

        public void setDesignData(int designData) {
            this.designData = designData;
        }

        public double getTotalData() {
            return totalData;
        }

        public void setTotalData(double totalData) {
            this.totalData = totalData;
        }

        public double getDiffData() {
            return diffData;
        }

        public void setDiffData(double diffData) {
            this.diffData = diffData;
        }

        public Object getStatus() {
            return status;
        }

        public void setStatus(Object status) {
            this.status = status;
        }
    }
}
