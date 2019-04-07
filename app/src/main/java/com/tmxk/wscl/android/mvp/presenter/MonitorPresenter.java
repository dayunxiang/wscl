package com.tmxk.wscl.android.mvp.presenter;

import com.tmxk.wscl.android.emuns.DataTypeEnum;
import com.tmxk.wscl.android.mvp.model.AreaListBean;
import com.tmxk.wscl.android.mvp.model.CarGpsRecordResBean;
import com.tmxk.wscl.android.mvp.model.CarInfoBean;
import com.tmxk.wscl.android.mvp.model.CarInfoResBean;
import com.tmxk.wscl.android.mvp.model.CreateCarGpsBySysuserBean;
import com.tmxk.wscl.android.mvp.model.GpsRecordBean;
import com.tmxk.wscl.android.mvp.model.SewageListBean;
import com.tmxk.wscl.android.mvp.model.SewageMonitorBean;
import com.tmxk.wscl.android.mvp.view.SewageArchiveView;
import com.tmxk.wscl.android.retrofit.ApiCallback;

import okhttp3.ResponseBody;

public class MonitorPresenter extends BasePresenter<SewageArchiveView> {
    private int page = 1;
    public MonitorPresenter(SewageArchiveView view) {
        attachView(view);
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

    public void getAllSewages() {
        addSubscription(apiService.getAllSewage(1, 1000),
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

    public void getSewageMonitor(int sewageId) {
        addSubscription(apiService.getSewageMonitor(sewageId),
                new ApiCallback<SewageMonitorBean>() {
                    @Override
                    public void onSuccess(SewageMonitorBean sewageMonitorBean) {
                        mvpView.getDataSuccess(sewageMonitorBean, null);
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

    public void getCarInfoBySysuserId(int sysuserId) {
        addSubscription(apiService.getCarinfoBySysuserId(sysuserId),
                new ApiCallback<CarInfoBean>() {
                    @Override
                    public void onSuccess(CarInfoBean carInfoBean) {
                        mvpView.getDataSuccess(carInfoBean, null);
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

    public void createGpsRecord(GpsRecordBean gpsRecordBean) {
        addSubscription(apiService.createGpsRecord(gpsRecordBean),
                new ApiCallback<ResponseBody>() {
                    @Override
                    public void onSuccess(ResponseBody responseBody) {
//                        mvpView.toastShow("GPS记录成功");
                        android.util.Log.d("createGpsRecord","GPS记录成功");
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

    public void createGpsRecordBySysuser(CreateCarGpsBySysuserBean createCarGpsBySysuserBean) {
        addSubscription(apiService.createGpsRecordBySysuser(createCarGpsBySysuserBean),
                new ApiCallback<ResponseBody>() {
                    @Override
                    public void onSuccess(ResponseBody responseBody) {
//                        mvpView.toastShow("GPS记录成功");
                        android.util.Log.d("GpsRecordBySysuser","GPS记录成功");
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

    public void getCarGpsByIdAndPeriod(String carIdOrName, String startTime,String endTime) {
        addSubscription(apiService.getCarGpsByIdAndPeriod(carIdOrName,1,100000, startTime, endTime),
                new ApiCallback<CarGpsRecordResBean>() {
                    @Override
                    public void onSuccess(CarGpsRecordResBean listCarGpsBean) {
                        mvpView.getDataSuccess(listCarGpsBean, DataTypeEnum.TYPE01);
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

    public void getAllCarInfo() {
        addSubscription(apiService.getAllCarInfo(1,100000),
                new ApiCallback<CarInfoResBean>() {
                    @Override
                    public void onSuccess(CarInfoResBean carInfoResBean) {
                        mvpView.getDataSuccess(carInfoResBean, null);
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

    public void getCarGpsRecentOnce() {
        addSubscription(apiService.getCarGpsRecentOnce(1,100000),
                new ApiCallback<CarGpsRecordResBean>() {
                    @Override
                    public void onSuccess(CarGpsRecordResBean listCarGpsBean) {
                        mvpView.getDataSuccess(listCarGpsBean, DataTypeEnum.TYPE02);
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