package com.tmxk.wscl.android.mvp.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class UserLoginLogListBean implements Serializable {
    private MetaInfoEntity metaInfo;
    private List<UserLoginLogBean> object;

    @Data
    public static class MetaInfoEntity implements Serializable {
        private int code;
        private int totalSize;
        private int offset;
        private int totalPage;
        private int limit;
    }
}
