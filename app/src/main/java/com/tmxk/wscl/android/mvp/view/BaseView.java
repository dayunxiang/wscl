package com.tmxk.wscl.android.mvp.view;

public interface BaseView {
    void showLoading(String loadInfo);

    void hideLoading();

    void getDataSuccess(Object model);

    void getDataFail(String msg);

    void toastShow(String info);
}