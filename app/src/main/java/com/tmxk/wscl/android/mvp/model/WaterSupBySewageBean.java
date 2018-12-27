package com.tmxk.wscl.android.mvp.model;

import java.util.List;
//站点超水量分析
public class WaterSupBySewageBean {

    /**
     * normal : {"metaInfo":{"code":200,"offset":1,"limit":100,"totalPage":1,"totalSize":10},"object":[{"date":1543204825000,"dailyData":30.2,"designData":40,"totalData":null,"diffData":-24.499998,"status":"正常"},{"date":1543118425000,"dailyData":35.2,"designData":40,"totalData":null,"diffData":-11.999998,"status":"正常"},{"date":1543032025000,"dailyData":35.2,"designData":40,"totalData":null,"diffData":-11.999998,"status":"正常"},{"date":1542945625000,"dailyData":35.2,"designData":40,"totalData":null,"diffData":-11.999998,"status":"正常"},{"date":1542772825000,"dailyData":35.2,"designData":40,"totalData":null,"diffData":-11.999998,"status":"正常"},{"date":1542600025000,"dailyData":35.2,"designData":40,"totalData":null,"diffData":-11.999998,"status":"正常"},{"date":1542686425000,"dailyData":35.2,"designData":40,"totalData":null,"diffData":-11.999998,"status":"正常"},{"date":1543291225000,"dailyData":35.2,"designData":40,"totalData":null,"diffData":-11.999998,"status":"正常"},{"date":1542081625000,"dailyData":35.2,"designData":40,"totalData":null,"diffData":-11.999998,"status":"正常"},{"date":1542340825000,"dailyData":35.2,"designData":40,"totalData":null,"diffData":-11.999998,"status":"正常"}]}
     * uper : {"metaInfo":{"code":404,"offset":0,"limit":0,"totalPage":0,"totalSize":0},"object":[]}
     * lower : {"metaInfo":{"code":404,"offset":0,"limit":0,"totalPage":0,"totalSize":0},"object":[]}
     * totalSize : 10
     */

    private NormalBean normal;
    private NormalBean uper;
    private NormalBean lower;
    private int totalSize;

    public NormalBean getNormal() {
        return normal;
    }

    public void setNormal(NormalBean normal) {
        this.normal = normal;
    }

    public NormalBean getUper() {
        return uper;
    }

    public void setUper(NormalBean uper) {
        this.uper = uper;
    }

    public NormalBean getLower() {
        return lower;
    }

    public void setLower(NormalBean lower) {
        this.lower = lower;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public static class NormalBean {
        /**
         * metaInfo : {"code":200,"offset":1,"limit":100,"totalPage":1,"totalSize":10}
         * object : [{"date":1543204825000,"dailyData":30.2,"designData":40,"totalData":null,"diffData":-24.499998,"status":"正常"},{"date":1543118425000,"dailyData":35.2,"designData":40,"totalData":null,"diffData":-11.999998,"status":"正常"},{"date":1543032025000,"dailyData":35.2,"designData":40,"totalData":null,"diffData":-11.999998,"status":"正常"},{"date":1542945625000,"dailyData":35.2,"designData":40,"totalData":null,"diffData":-11.999998,"status":"正常"},{"date":1542772825000,"dailyData":35.2,"designData":40,"totalData":null,"diffData":-11.999998,"status":"正常"},{"date":1542600025000,"dailyData":35.2,"designData":40,"totalData":null,"diffData":-11.999998,"status":"正常"},{"date":1542686425000,"dailyData":35.2,"designData":40,"totalData":null,"diffData":-11.999998,"status":"正常"},{"date":1543291225000,"dailyData":35.2,"designData":40,"totalData":null,"diffData":-11.999998,"status":"正常"},{"date":1542081625000,"dailyData":35.2,"designData":40,"totalData":null,"diffData":-11.999998,"status":"正常"},{"date":1542340825000,"dailyData":35.2,"designData":40,"totalData":null,"diffData":-11.999998,"status":"正常"}]
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
             * diffData : -24.499998
             * status : 正常
             */

            private long date;
            private double dailyData;
            private double designData;
            private double totalData;
            private double diffData;
            private String status;

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

            public void setDesignData(double designData) {
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

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }
    }
}
