package com.tmxk.wscl.android.mvp.presenter;

import com.tmxk.wscl.android.mvp.model.AreaListBean;
import com.tmxk.wscl.android.mvp.model.SewageListBean;
import com.tmxk.wscl.android.mvp.model.SiteDeviceDocListBean;
import com.tmxk.wscl.android.mvp.view.SewageArchiveView;
import com.tmxk.wscl.android.retrofit.ApiCallback;

public class DeviceDocPresenter extends BasePresenter<SewageArchiveView> {
    private int page = 1;
    public DeviceDocPresenter(SewageArchiveView view) {
        attachView(view);
    }

    public void getDeviceDocs(boolean isRefresh, int sewageId) {
        if (isRefresh) {
            page = 1;
        } else {
            page++;
        }
        addSubscription(apiService.getDeviceDocBySewage(page, 10, sewageId),
                new ApiCallback<SiteDeviceDocListBean>() {
                    @Override
                    public void onSuccess(SiteDeviceDocListBean siteDeviceDocListBean) {
                        mvpView.getDataSuccess(siteDeviceDocListBean, null);
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

    public void getAllDeviceDocs(boolean isRefresh) {
        if (isRefresh) {
            page = 1;
        } else {
            page++;
        }
        addSubscription(apiService.getDeviceDocList(page, 10),
                new ApiCallback<SiteDeviceDocListBean>() {
                    @Override
                    public void onSuccess(SiteDeviceDocListBean siteDeviceDocListBean) {
                        mvpView.getDataSuccess(siteDeviceDocListBean, null);
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
}