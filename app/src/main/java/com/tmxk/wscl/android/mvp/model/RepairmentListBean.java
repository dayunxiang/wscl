package com.tmxk.wscl.android.mvp.model;

import java.util.List;

public class RepairmentListBean {

    /**
     * metaInfo : {"code":200,"offset":1,"limit":1,"totalPage":9,"totalSize":9}
     */

    private MetaInfoBean metaInfo;
    private List<RepairmentBean> object;

    public List<RepairmentBean> getObject() {
        return object;
    }

    public void setObject(List<RepairmentBean> object) {
        this.object = object;
    }

    public MetaInfoBean getMetaInfo() {
        return metaInfo;
    }

    public void setMetaInfo(MetaInfoBean metaInfo) {
        this.metaInfo = metaInfo;
    }

    public static class MetaInfoBean {
        /**
         * code : 200
         * offset : 1
         * limit : 1
         * totalPage : 9
         * totalSize : 9
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
}
