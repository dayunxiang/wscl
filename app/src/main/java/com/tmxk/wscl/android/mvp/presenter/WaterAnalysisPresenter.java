package com.tmxk.wscl.android.mvp.presenter;

import com.tmxk.wscl.android.mvp.model.AdminListBean;
import com.tmxk.wscl.android.mvp.model.AreaListBean;
import com.tmxk.wscl.android.mvp.model.SewageListBean;
import com.tmxk.wscl.android.mvp.model.SiteDeviceDocBean;
import com.tmxk.wscl.android.mvp.model.SiteDeviceDocListBean;
import com.tmxk.wscl.android.mvp.model.WaterAnalysisMonthBean;
import com.tmxk.wscl.android.mvp.model.WaterAnalysisYearBean;
import com.tmxk.wscl.android.mvp.model.WaterSupBySewageBean;
import com.tmxk.wscl.android.mvp.model.WaterUpMonthBean;
import com.tmxk.wscl.android.mvp.model.WaterUpYearBean;
import com.tmxk.wscl.android.mvp.view.SewageArchiveView;
import com.tmxk.wscl.android.retrofit.ApiCallback;
import com.tmxk.wscl.android.util.Route;

import okhttp3.ResponseBody;

public class WaterAnalysisPresenter extends BasePresenter<SewageArchiveView> {
    private int page = 1;
    public WaterAnalysisPresenter(SewageArchiveView view) {
        attachView(view);
    }

    public void getAllAreas() {
        addSubscription(apiService.getAllArea(page, 1000),
                new ApiCallback<AreaListBean>() {
                    @Override
                    public void onSuccess(AreaListBean areaListBean) {
                        mvpView.getDataSuccess(areaListBean, null);
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

    public void getSewages(boolean isRefresh, int areaId) {
        if (isRefresh) {
            page = 1;
        } else {
            page++;
        }
        addSubscription(apiService.getSewageByArea(page, 1000, areaId),
                new ApiCallback<SewageListBean>() {
                    @Override
                    public void onSuccess(SewageListBean sewageListBean) {
                        mvpView.getDataSuccess(sewageListBean, null);
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

    public void getWaterAnalysisMonth(String date, int sewageId) {
        addSubscription(apiService.getWaterAnalysisMonth(date, sewageId, 1 ,1000),
                new ApiCallback<WaterAnalysisMonthBean>() {
                    @Override
                    public void onSuccess(WaterAnalysisMonthBean waterAnalysisMonthBean) {
                        mvpView.getDataSuccess(waterAnalysisMonthBean, null);
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

    public void getWaterAnalysisYear(String date, int sewageId) {
        addSubscription(apiService.getWaterAnalysisYear(date, sewageId, 1 ,1000),
                new ApiCallback<WaterAnalysisYearBean>() {
                    @Override
                    public void onSuccess(WaterAnalysisYearBean waterAnalysisYearBean) {
                        mvpView.getDataSuccess(waterAnalysisYearBean, null);
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

    public void getWaterUpBySewage(String date, int sewageId) {
        addSubscription(apiService.getWaterUpBySewage(date, sewageId, 1 ,1000),
                new ApiCallback<WaterSupBySewageBean>() {
                    @Override
                    public void onSuccess(WaterSupBySewageBean waterSupBySewageBean) {
                        mvpView.getDataSuccess(waterSupBySewageBean, null);
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

    public void getWaterUpMonth(String date) {
        addSubscription(apiService.getWaterUpMonth(date,1 ,1000),
                new ApiCallback<WaterUpMonthBean>() {
                    @Override
                    public void onSuccess(WaterUpMonthBean waterUpMonthBean) {
                        mvpView.getDataSuccess(waterUpMonthBean, null);
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

    public void getWaterUpYear(String date) {
        addSubscription(apiService.getWaterUpYear(date,1 ,1000),
                new ApiCallback<WaterUpYearBean>() {
                    @Override
                    public void onSuccess(WaterUpYearBean waterUpYearBean) {
                        mvpView.getDataSuccess(waterUpYearBean, null);
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