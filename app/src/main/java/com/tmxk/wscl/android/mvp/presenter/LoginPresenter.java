package com.tmxk.wscl.android.mvp.presenter;

import android.text.TextUtils;

import com.tmxk.wscl.android.mvp.model.UserModel;
import com.tmxk.wscl.android.mvp.view.LoginView;
import com.tmxk.wscl.android.retrofit.ApiCallback;
import com.tmxk.wscl.android.util.Constant;
import com.tmxk.wscl.android.util.Route;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.RequestBody;

public class LoginPresenter extends BasePresenter<LoginView> {

    public LoginPresenter(LoginView view) {
        attachView(view);
    }

    public void login(String username, String password) {
        mvpView.showLoading(Constant.LOGIN_ON);
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
            JSONObject strJson = new JSONObject();
            try {
                strJson.put("userName", username);
                strJson.put("userPassword", password);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            RequestBody body = RequestBody.create(Route.JSON, strJson.toString());
            addSubscription(apiService.login(body),
                    new ApiCallback<UserModel>() {
                        @Override
                        public void onSuccess(UserModel model) {
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
        } else {
            mvpView.hideLoading();
            if (TextUtils.isEmpty(username)) {
                mvpView.toastShow(Constant.LOGIN_ENTER_INFO_NAME);
            } else {
                mvpView.toastShow(Constant.LOGIN_ENTER_INFO_PWD);
            }
        }
    }
}