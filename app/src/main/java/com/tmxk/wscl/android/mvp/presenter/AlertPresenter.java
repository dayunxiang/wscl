package com.tmxk.wscl.android.mvp.presenter;

import com.tmxk.wscl.android.mvp.model.AlertEquipStatusBean;
import com.tmxk.wscl.android.mvp.model.AreaListBean;
import com.tmxk.wscl.android.mvp.model.DataTransferBean;
import com.tmxk.wscl.android.mvp.model.PowerOffBean;
import com.tmxk.wscl.android.mvp.model.SewageListBean;
import com.tmxk.wscl.android.mvp.model.SewageMonitorBean;
import com.tmxk.wscl.android.mvp.model.WaterQualityStatusBean;
import com.tmxk.wscl.android.mvp.view.SewageArchiveView;
import com.tmxk.wscl.android.retrofit.ApiCallback;

import java.util.Date;

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

    public void getAlertWaterQualityStatus() {
        addSubscription(apiService.getAlertWaterQualityStatus(1,1000),
                new ApiCallback<WaterQualityStatusBean>() {
                    @Override
                    public void onSuccess(WaterQualityStatusBean waterQualityStatusBean) {
                        mvpView.getDataSuccess(waterQualityStatusBean, null);
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

    public void getAlertPowerOff() {
        addSubscription(apiService.getAlertPowerOff(1,1000),
                new ApiCallback<PowerOffBean>() {
                    @Override
                    public void onSuccess(PowerOffBean powerOffBean) {
                        mvpView.getDataSuccess(powerOffBean, null);
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

    public void getAlertDataTransfer(String startTime) {
        addSubscription(apiService.getAlertDataTransfer(startTime,1,1000),
                new ApiCallback<DataTransferBean>() {
                    @Override
                    public void onSuccess(DataTransferBean dataTransferBean) {
                        mvpView.getDataSuccess(dataTransferBean, null);
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