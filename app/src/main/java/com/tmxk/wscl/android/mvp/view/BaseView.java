package com.tmxk.wscl.android.mvp.view;

import com.tmxk.wscl.android.mvp.model.UserModel;

public interface BaseView {
    void showLoading(String loadInfo);

    void hideLoading();

    void getDataSuccess(UserModel model);

    void getDataFail(String msg);

    void toastShow(String info);
}