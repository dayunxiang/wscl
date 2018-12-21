package com.tmxk.wscl.android.mvp.view;

import com.tmxk.wscl.android.emuns.DataTypeEnum;

public interface BaseView {
    void showLoading(String loadInfo);

    void hideLoading();

    void getDataSuccess(Object model, DataTypeEnum dataTypeEnum);

    void getDataFail(String msg);

    void toastShow(String info);
}