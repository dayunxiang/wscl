package com.tmxk.wscl.android.mvp.presenter;

import android.util.Log;

import com.tmxk.wscl.android.mvp.model.UserModel;
import com.tmxk.wscl.android.mvp.view.UserView;
import com.tmxk.wscl.android.retrofit.ApiCallback;
import com.tmxk.wscl.android.util.Route;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class UserPresenter extends BasePresenter<UserView> {

    public UserPresenter(UserView view) {
        attachView(view);
    }

    public void modifyUserInfo(int id, String loginName, String userName, String email, String depart, String phone) {
        JSONObject strJson = new JSONObject();
        try {
            strJson.put("id", id);
            strJson.put("loginName", loginName);
            strJson.put("userName", userName);
            strJson.put("userEmail", email);
            strJson.put("department", depart);
            strJson.put("telephone", phone);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(Route.JSON, strJson.toString());
        addSubscription(apiService.updateSysUser(id, body),
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
    }
}
