package com.tmxk.wscl.android.mvp.view;

import com.tmxk.wscl.android.mvp.model.UserBean;

public interface BaseView {
    void showLoading(String loadInfo);

    void hideLoading();

    void getDataSuccess(UserBean model);

    void getDataFail(String msg);

    void toastShow(String info);
}