package com.tmxk.wscl.android.mvp.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class UserListBean implements Serializable {
    private MetaInfoEntity metaInfo;
    private List<UserBean> object;

    public class MetaInfoEntity implements Serializable {
        private int code;
        private int totalSize;
        private int offset;
        private int totalPage;
        private int limit;
    }
}
