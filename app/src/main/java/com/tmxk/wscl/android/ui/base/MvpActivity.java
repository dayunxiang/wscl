package com.tmxk.wscl.android.ui.base;

import android.os.Bundle;

import com.tmxk.wscl.android.mvp.presenter.BasePresenter;

public abstract class MvpActivity<P extends BasePresenter> extends BaseActivity {
    protected P mvpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mvpPresenter = createPresenter();
        super.onCreate(savedInstanceState);
    }

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
    }

    public void showLoading(String loadInfo) {
        showProgressDialog(loadInfo);
    }

    public void hideLoading() {
        dismissProgressDialog();
    }

    public void toastInfo(String resInfo) {
        toastShow(resInfo);
    }
}