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

    public UserPresenter(UserView view) {
        attachView(view);
    }

    public void modifyUserInfo(UserBean userBean) {

        if (userBean.getLoginName().isEmpty()) {
            mvpView.toastShow("请输入用户名");
        } else if (userBean.getUserName().isEmpty()) {
            mvpView.toastShow("请输入姓名");
        } else if (userBean.getUserEmail().isEmpty()) {
            mvpView.toastShow("请输入邮箱");
        } else if (userBean.getDepartment().isEmpty()) {
            mvpView.toastShow("请输入部门");
        } else if (userBean.getTelephone().isEmpty()) {
            mvpView.toastShow("请输入联系方式");
        } else {
            mvpView.showLoading(Constant.DATA_PROCESS);
            JSONObject strJson = new JSONObject();
            try {
                strJson.put("id", userBean.getId());
                strJson.put("loginName", userBean.getLoginName());
                strJson.put("userName", userBean.getUserName());
                strJson.put("userEmail", userBean.getUserEmail());
                strJson.put("department", userBean.getDepartment());
                strJson.put("telephone", userBean.getTelephone());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            RequestBody body = RequestBody.create(Route.JSON, strJson.toString());
            addSubscription(apiService.updateSysUser(userBean.getId(), body),
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
        int perPage = 10;
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

    public void addUserInfo(UserBean userBean) {
        if (userBean.getLoginName().isEmpty()) {
            mvpView.toastShow("请输入用户名");
        } else if (userBean.getUserName().isEmpty()) {
            mvpView.toastShow("请输入姓名");
        } else if (userBean.getUserEmail().isEmpty()) {
            mvpView.toastShow("请输入邮箱");
        } else if (userBean.getDepartment().isEmpty()) {
            mvpView.toastShow("请输入部门");
        } else if (userBean.getTelephone().isEmpty()) {
            mvpView.toastShow("请输入联系方式");
        } else {
            mvpView.showLoading(Constant.DATA_PROCESS);
            JSONObject strJson = new JSONObject();
            try {
                strJson.put("loginName", userBean.getLoginName());
                strJson.put("userName", userBean.getUserName());
                strJson.put("userEmail", userBean.getUserEmail());
                strJson.put("department", userBean.getDepartment());
                strJson.put("telephone", userBean.getTelephone());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            RequestBody body = RequestBody.create(Route.JSON, strJson.toString());
            addSubscription(apiService.addSysUser(body),
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

    public void delUserInfo(String loginName) {
        if (loginName.isEmpty()) {
            mvpView.toastShow("用户名为空");
        } else {
            mvpView.showLoading(Constant.DATA_PROCESS);
            addSubscription(apiService.delSysUser(loginName),
                    new ApiCallback<ResponseBody>() {
                        @Override
                        public void onSuccess(ResponseBody responseBody) {
                            mvpView.toastShow("用户删除成功");
                            mvpView.autoRefresh();
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
}