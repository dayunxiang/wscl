package com.tmxk.wscl.android.mvp.presenter;

import com.tmxk.wscl.android.mvp.model.UserBean;
import com.tmxk.wscl.android.mvp.model.UserListBean;
import com.tmxk.wscl.android.mvp.view.UserView;
import com.tmxk.wscl.android.retrofit.ApiCallback;
import com.tmxk.wscl.android.util.Constant;
import com.tmxk.wscl.android.util.Route;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class UserPresenter extends BasePresenter<UserView> {

    private int page = 1;
    private int perPage = 10;

    public UserPresenter(UserView view) {
        attachView(view);
    }

    public void modifyUserInfo(int userId, String loginName, String userName, String email, String depart, String phone) {
        mvpView.showLoading(Constant.DATA_PROCESS);
        JSONObject strJson = new JSONObject();
        try {
            strJson.put("id", userId);
            strJson.put("loginName", loginName);
            strJson.put("userName", userName);
            strJson.put("userEmail", email);
            strJson.put("department", depart);
            strJson.put("telephone", phone);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(Route.JSON, strJson.toString());
        addSubscription(apiService.updateSysUser(userId, body),
                new ApiCallback<UserBean>() {
                    @Override
                    public void onSuccess(UserBean model) {
                        mvpView.getDataSuccess(model);
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

    public void modifyUserPwd(int userId, String sysPassword, String oldPassword, String newPassword, String confPassword) {
        if (!sysPassword.equals(oldPassword)) {
            mvpView.toastShow(Constant.OLD_PWD_ERROR);
        } else if (!newPassword.equals(confPassword)) {
            mvpView.toastShow(Constant.NEW_PWD_ERROR);
        } else {
            mvpView.showLoading(Constant.DATA_PROCESS);
            JSONObject strJson = new JSONObject();
            try {
                strJson.put("id", userId);
                strJson.put("oldPassword", oldPassword);
                strJson.put("newPassword", newPassword);
                strJson.put("confirmPassword", confPassword);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            RequestBody body = RequestBody.create(Route.JSON, strJson.toString());
            addSubscription(apiService.updateSysUserPwd(userId, body),
                    new ApiCallback<ResponseBody>() {
                        @Override
                        public void onSuccess(ResponseBody responseBody) {
                            mvpView.getDataSuccess(null);
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

    public void getSysUsers(boolean isRefresh) {
        if (isRefresh) {
            page = 1;
        } else {
            page++;
        }
        addSubscription(apiService.getSysUsers(page, perPage),
                new ApiCallback<UserListBean>() {
                    @Override
                    public void onSuccess(UserListBean userList) {
                        if (Route.STATUS_CODE == userList.getMetaInfo().getCode()) {
                            mvpView.getDataSuccess(userList);
                        } else {
                            mvpView.toastShow("获取用户列表失败");
                        }
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
