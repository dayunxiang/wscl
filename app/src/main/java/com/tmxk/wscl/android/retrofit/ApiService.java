package com.tmxk.wscl.android.retrofit;

import com.tmxk.wscl.android.mvp.model.HttpReturnBean;
import com.tmxk.wscl.android.mvp.model.UserBean;
import com.tmxk.wscl.android.util.Route;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ApiService {
    @POST(Route.IP_URL + Route.LOGIN_URL)
    Observable<UserBean> login(@Body RequestBody body);

    @PUT(Route.IP_URL + Route.USER_SYS_URL)
    Observable<UserBean> updateSysUser(@Query("id") int id, @Body RequestBody body);

    @PUT(Route.IP_URL + Route.USER_SYS_PWD_URL)
    Observable<ResponseBody> updateSysUserPwd(@Query("id") int id, @Body RequestBody body);
}