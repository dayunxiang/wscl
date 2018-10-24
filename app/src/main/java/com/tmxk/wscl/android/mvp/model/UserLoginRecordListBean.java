package com.tmxk.wscl.android.mvp.model;

import java.io.Serializable;
import java.util.List;

public class UserLoginRecordListBean implements Serializable {

    /**
     * metaInfo : {"code":200,"totalSize":106,"offset":1,"totalPage":22,"limit":5}
     * object : [{"userlogintime":1537101242597,"sysuser":{"loginPwd":"111111","loginName":"wy","userEmail":"123456@qq.com","telephone":"18896724553","id":3,"userName":"王尧","department":"大数据"},"id":106},{"userlogintime":1537089617060,"sysuser":{"loginPwd":"111111","loginName":"wy","userEmail":"123456@qq.com","telephone":"18896724553","id":3,"userName":"王尧","department":"大数据"},"id":105},{"userlogintime":1537089340877,"sysuser":{"loginPwd":"111111","loginName":"wy","userEmail":"123456@qq.com","telephone":"18896724553","id":3,"userName":"王尧","department":"大数据"},"id":104},{"userlogintime":1537089303067,"sysuser":{"loginPwd":"111111","loginName":"wy","userEmail":"123456@qq.com","telephone":"18896724553","id":3,"userName":"王尧","department":"大数据"},"id":103},{"userlogintime":1537089294707,"sysuser":{"loginPwd":"111111","loginName":"wy","userEmail":"123456@qq.com","telephone":"18896724553","id":3,"userName":"王尧","department":"大数据"},"id":102}]
     */
    private MetaInfoEntity metaInfo;
    private List<UserLoginRecordBean> object;

    public void setMetaInfo(MetaInfoEntity metaInfo) {
        this.metaInfo = metaInfo;
    }

    public void setObject(List<UserLoginRecordBean> object) {
        this.object = object;
    }

    public MetaInfoEntity getMetaInfo() {
        return metaInfo;
    }

    public List<UserLoginRecordBean> getObject() {
        return object;
    }

    public static class MetaInfoEntity implements Serializable {
        /**
         * code : 200
         * totalSize : 106
         * offset : 1
         * totalPage : 22
         * limit : 5
         */
        private int code;
        private int totalSize;
        private int offset;
        private int totalPage;
        private int limit;

        public void setCode(int code) {
            this.code = code;
        }

        public void setTotalSize(int totalSize) {
            this.totalSize = totalSize;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public int getCode() {
            return code;
        }

        public int getTotalSize() {
            return totalSize;
        }

        public int getOffset() {
            return offset;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public int getLimit() {
            return limit;
        }
    }
}
