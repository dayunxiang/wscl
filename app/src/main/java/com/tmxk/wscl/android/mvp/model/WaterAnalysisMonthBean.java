package com.tmxk.wscl.android.mvp.model;

import java.util.List;

public class WaterAnalysisMonthBean {

    /**
     * metaInfo : {"code":200,"offset":1,"limit":100,"totalPage":1,"totalSize":10}
     * object : [{"date":1543204825000,"dailyData":30.2,"designData":40,"totalData":null,"diffData":null,"status":null},{"date":1543118425000,"dailyData":35.2,"designData":40,"totalData":null,"diffData":null,"status":null},{"date":1543032025000,"dailyData":35.2,"designData":40,"totalData":null,"diffData":null,"status":null},{"date":1542945625000,"dailyData":35.2,"designData":40,"totalData":null,"diffData":null,"status":null},{"date":1542772825000,"dailyData":35.2,"designData":40,"totalData":null,"diffData":null,"status":null},{"date":1542600025000,"dailyData":35.2,"designData":40,"totalData":null,"diffData":null,"status":null},{"date":1542686425000,"dailyData":35.2,"designData":40,"totalData":null,"diffData":null,"status":null},{"date":1543291225000,"dailyData":35.2,"designData":40,"totalData":null,"diffData":null,"status":null},{"date":1542081625000,"dailyData":35.2,"designData":40,"totalData":null,"diffData":null,"status":null},{"date":1542340825000,"dailyData":35.2,"designData":40,"totalData":null,"diffData":null,"status":null}]
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
         * totalSize : 10
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
         * date : 1543204825000
         * dailyData : 30.2
         * designData : 40
         * totalData : null
         * diffData : null
         * status : null
         */

        private long date;
        private float dailyData;
        private float designData;
        private float totalData;
        private float diffData;
        private String status;

        public long getDate() {
            return date;
        }

        public void setDate(long date) {
            this.date = date;
        }

        public float getDailyData() {
            return dailyData;
        }

        public void setDailyData(float dailyData) {
            this.dailyData = dailyData;
        }

        public float getDesignData() {
            return designData;
        }

        public void setDesignData(float designData) {
            this.designData = designData;
        }

        public float getTotalData() {
            return totalData;
        }

        public void setTotalData(float totalData) {
            this.totalData = totalData;
        }

        public float getDiffData() {
            return diffData;
        }

        public void setDiffData(float diffData) {
            this.diffData = diffData;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
