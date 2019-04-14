package com.tmxk.wscl.android.mvp.presenter;

import com.tmxk.wscl.android.mvp.model.AreaListBean;
import com.tmxk.wscl.android.mvp.model.AssignmentOrderListBean;
import com.tmxk.wscl.android.mvp.model.SewageListBean;
import com.tmxk.wscl.android.mvp.model.UserListBean;
import com.tmxk.wscl.android.mvp.view.SewageArchiveView;
import com.tmxk.wscl.android.retrofit.ApiCallback;

import okhttp3.ResponseBody;
import retrofit2.http.Query;

public class OperatePresenter extends BasePresenter<SewageArchiveView> {
    private int page = 1;
    public OperatePresenter(SewageArchiveView view) {
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

    public void getAllAssignOrder(boolean isRefresh) {
        if (isRefresh) {
            page = 1;
        } else {
            page++;
        }
        addSubscription(apiService.getAllAssignOrder(page, 20),
                new ApiCallback<AssignmentOrderListBean>() {
                    @Override
                    public void onSuccess(AssignmentOrderListBean assignmentOrderListBean) {
                        mvpView.getDataSuccess(assignmentOrderListBean, null);
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

    public void getAssignOrderByCondition(boolean isRefresh,int sysuserId,
                                          String taskStatus,String startTime,String endTime) {
        if (isRefresh) {
            page = 1;
        } else {
            page++;
        }
        addSubscription(apiService.getAssignOrderByCondition(sysuserId, taskStatus, startTime ,endTime ,page, 20),
                new ApiCallback<AssignmentOrderListBean>() {
                    @Override
                    public void onSuccess(AssignmentOrderListBean assignmentOrderListBean) {
                        mvpView.getDataSuccess(assignmentOrderListBean, null);
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

    public void getAssignOrderByCondition(boolean isRefresh,String startTime,String endTime) {
        if (isRefresh) {
            page = 1;
        } else {
            page++;
        }
        addSubscription(apiService.getAssignOrderByCondition_3(startTime ,endTime ,page, 20),
                new ApiCallback<AssignmentOrderListBean>() {
                    @Override
                    public void onSuccess(AssignmentOrderListBean assignmentOrderListBean) {
                        mvpView.getDataSuccess(assignmentOrderListBean, null);
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

    public void getAssignOrderByCondition(boolean isRefresh,String taskStatus,String startTime,String endTime) {
        if (isRefresh) {
            page = 1;
        } else {
            page++;
        }
        addSubscription(apiService.getAssignOrderByCondition_2(taskStatus, startTime ,endTime ,page, 20),
                new ApiCallback<AssignmentOrderListBean>() {
                    @Override
                    public void onSuccess(AssignmentOrderListBean assignmentOrderListBean) {
                        mvpView.getDataSuccess(assignmentOrderListBean, null);
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

    public void getAssignOrderByCondition(boolean isRefresh,int sysuserId,String startTime,String endTime) {
        if (isRefresh) {
            page = 1;
        } else {
            page++;
        }
        addSubscription(apiService.getAssignOrderByCondition_1(sysuserId, startTime ,endTime ,page, 20),
                new ApiCallback<AssignmentOrderListBean>() {
                    @Override
                    public void onSuccess(AssignmentOrderListBean assignmentOrderListBean) {
                        mvpView.getDataSuccess(assignmentOrderListBean, null);
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

    public void getAllSysuser() {
        addSubscription(apiService.getSysUsers(1, 10000),
                new ApiCallback<UserListBean>() {
                    @Override
                    public void onSuccess(UserListBean userListBean) {
                        mvpView.getDataSuccess(userListBean, null);
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