package com.tmxk.wscl.android.mvp.presenter;

import com.tmxk.wscl.android.mvp.model.AreaListBean;
import com.tmxk.wscl.android.mvp.model.SewageListBean;
import com.tmxk.wscl.android.mvp.model.SiteDeviceDocBean;
import com.tmxk.wscl.android.mvp.model.SiteDeviceDocListBean;
import com.tmxk.wscl.android.mvp.view.SewageArchiveView;
import com.tmxk.wscl.android.retrofit.ApiCallback;

import okhttp3.ResponseBody;

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

    public void createDeviceDoc(SiteDeviceDocBean siteDeviceDocBean){
//        设备名称、设备编号、设备类型、设备功率、安装时间
        if(siteDeviceDocBean==null){
            mvpView.toastShow("请填写表单信息");
        }else if(siteDeviceDocBean.getDeviceName().isEmpty()){
            mvpView.toastShow("请填写设备名称");
        }else if(siteDeviceDocBean.getDeviceNo().isEmpty()){
            mvpView.toastShow("请填写设备编号");
        }else if(siteDeviceDocBean.getDeviceType().isEmpty()){
            mvpView.toastShow("请填写设备类型");
        }else if(siteDeviceDocBean.getDevicePower()<=0){
            mvpView.toastShow("请填写设备功率");
        }else if(siteDeviceDocBean.getSetupTime()==null){
            mvpView.toastShow("请填写安装时间");
        }else if(siteDeviceDocBean.getSewage()==null){
            mvpView.toastShow("请选择站点信息");
        }else {
            addSubscription(apiService.createDeviceDoc(siteDeviceDocBean),
                    new ApiCallback<ResponseBody>() {
                        @Override
                        public void onSuccess(ResponseBody responseBody) {
                            mvpView.getDataSuccess(null, null);
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

    public void delDeviceDocById(int id) {
        addSubscription(apiService.deleteDeviceDocById(id),
                new ApiCallback<ResponseBody>() {
                    @Override
                    public void onSuccess(ResponseBody responseBody) {
                        mvpView.toastShow("设备档案删除成功");
                        mvpView.onRefresh();
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