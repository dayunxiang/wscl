package com.tmxk.wscl.android.mvp.presenter;

import com.tmxk.wscl.android.mvp.model.AlertEquipStatusBean;
import com.tmxk.wscl.android.mvp.model.AreaListBean;
import com.tmxk.wscl.android.mvp.model.SewageListBean;
import com.tmxk.wscl.android.mvp.model.SewageMonitorBean;
import com.tmxk.wscl.android.mvp.view.SewageArchiveView;
import com.tmxk.wscl.android.retrofit.ApiCallback;

public class AlertPresenter extends BasePresenter<SewageArchiveView> {
    private int page = 1;
    public AlertPresenter(SewageArchiveView view) {
        attachView(view);
    }

    public void getAlertEquipmentStatus() {
        addSubscription(apiService.getAlertEquipmentStatus(1,1000),
                new ApiCallback<AlertEquipStatusBean>() {
                    @Override
                    public void onSuccess(AlertEquipStatusBean alertEquipStatusBean) {
                        mvpView.getDataSuccess(alertEquipStatusBean, null);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.getDataFail(msg);
                    }

                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }
                });
    }
}