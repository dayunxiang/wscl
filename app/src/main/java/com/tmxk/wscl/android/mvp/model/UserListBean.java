package com.tmxk.wscl.android.mvp.model;

import java.io.Serializable;
import java.util.List;

public class UserListBean implements Serializable {

    /**
     * metaInfo : {"code":200,"totalSize":1,"offset":1,"totalPage":1,"limit":10}
     * object : [{"loginPwd":"111111","loginName":"wy","userEmail":"123456@qq.com","telephone":"18896724553","id":3,"userName":"王尧","department":"大数据"}]
     */
    private MetaInfoEntity metaInfo;
    private List<UserInfoBean> object;

    public void setMetaInfo(MetaInfoEntity metaInfo) {
        this.metaInfo = metaInfo;
    }

    public void setObject(List<UserInfoBean> object) {
        this.object = object;
    }

    public MetaInfoEntity getMetaInfo() {
        return metaInfo;
    }

    public List<UserInfoBean> getObject() {
        return object;
    }

    public class MetaInfoEntity {
        /**
         * code : 200
         * totalSize : 1
         * offset : 1
         * totalPage : 1
         * limit : 10
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
