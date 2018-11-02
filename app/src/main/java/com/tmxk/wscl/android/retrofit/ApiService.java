package com.tmxk.wscl.android.retrofit;

import com.tmxk.wscl.android.mvp.model.UserBean;
import com.tmxk.wscl.android.mvp.model.UserListBean;
import com.tmxk.wscl.android.mvp.model.UserLoginLogListBean;
import com.tmxk.wscl.android.util.Route;

import java.util.Date;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiService {
    @POST(Route.IP_URL + Route.LOGIN_URL)
    Observable<UserBean> login(@Body RequestBody body);

    @PUT(Route.IP_URL + Route.USER_SYS_URL)
    Observable<UserBean> updateSysUser(@Query("id") int id, @Body RequestBody body);

    @PUT(Route.IP_URL + Route.USER_SYS_PWD_URL)
    Observable<ResponseBody> updateSysUserPwd(@Query("id") int id, @Body RequestBody body);

    @GET(Route.IP_URL + Route.USER_SYS_URL)
    Observable<UserListBean> getSysUsers(@Query("offset") int offset, @Query("limit") int limit);

    @POST(Route.IP_URL + Route.USER_SYS_URL)
    Observable<ResponseBody> addSysUser(@Body RequestBody body);

    @DELETE(Route.IP_URL + Route.USER_SYS_DEL_URL)
    Observable<ResponseBody> delSysUser(@Query("loginName") String loginName);

    @GET(Route.IP_URL + Route.USER_SYS_LOGIN_LOG_URL)
    Observable<UserLoginLogListBean> getSysUserLoginLogs(@Query("offset") int offset, @Query("limit") int limit,@QueryMap Map<String, String> options);
}