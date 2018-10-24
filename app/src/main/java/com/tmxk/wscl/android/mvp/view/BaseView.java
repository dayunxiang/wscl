package com.tmxk.wscl.android.mvp.view;

import com.tmxk.wscl.android.mvp.model.LoginModel;

public interface BaseView {
    void showLoading(String loadInfo);

    void hideLoading();

    void getDataSuccess(LoginModel model);

    void getDataFail(String msg);

    void toastShow(String info);
}