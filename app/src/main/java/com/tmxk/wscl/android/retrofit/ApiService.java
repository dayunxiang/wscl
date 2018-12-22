package com.tmxk.wscl.android.retrofit;

import com.tmxk.wscl.android.mvp.model.AdminListBean;
import com.tmxk.wscl.android.mvp.model.AreaListBean;
import com.tmxk.wscl.android.mvp.model.SewageListBean;
import com.tmxk.wscl.android.mvp.model.SiteDeviceDocBean;
import com.tmxk.wscl.android.mvp.model.SiteDeviceDocListBean;
import com.tmxk.wscl.android.mvp.model.UserBean;
import com.tmxk.wscl.android.mvp.model.UserListBean;
import com.tmxk.wscl.android.mvp.model.UserLoginLogListBean;
import com.tmxk.wscl.android.util.Route;

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
    Observable<UserLoginLogListBean> getSysUserLoginLogs(@Query("offset") int offset, @Query("limit") int limit, @QueryMap Map<String, String> options);

    @GET(Route.IP_URL + Route.SITE_DEVICE_DOC_ALL_URL)
    Observable<SiteDeviceDocListBean> getDeviceDocList(@Query("offset") int offset, @Query("limit") int limit);

    @PUT(Route.IP_URL + Route.SITE_DEVICE_DOC_URL)
    Observable<ResponseBody> updateDeviceDoc(@Body SiteDeviceDocBean siteDeviceDocBean);

    @POST(Route.IP_URL + Route.SITE_DEVICE_DOC_URL)
    Observable<ResponseBody> createDeviceDoc(@Body SiteDeviceDocBean siteDeviceDocBean);

    @DELETE(Route.IP_URL + Route.SITE_DEVICE_DOC_ID_URL)
    Observable<ResponseBody> deleteDeviceDocById(@Query("id") int id);

    @DELETE(Route.IP_URL + Route.SITE_DEVICE_DOC_NAME_URL)
    Observable<ResponseBody> deleteDeviceDocByName(@Query("deviceName") String deviceName);

    @DELETE(Route.IP_URL + Route.SITE_DEVICE_DOC_SEWAGE_URL)
    Observable<ResponseBody> deleteDeviceDocBySewage(@Query("sewageId") int sewageId);

    @GET(Route.IP_URL + Route.SITE_DEVICE_DOC_GET_SEWAGE_URL)
    Observable<SiteDeviceDocListBean> getDeviceDocBySewage(@Query("offset") int offset, @Query("limit") int limit, @Query("sewageId") int sewageId);

    @GET(Route.IP_URL + Route.SITE_SEWAGE_GET_SEWAGE_BY_AREA_URL)
    Observable<SewageListBean> getSewageByArea(@Query("offset") int offset, @Query("limit") int limit, @Query("areaId") int areaId);

    @GET(Route.IP_URL + Route.SITE_SEWAGE_GET_SEWAGE_BY_ID_URL)
    Observable<SewageListBean.ObjectBean> getSewageById(@Query("id") int id);

    @GET(Route.IP_URL + Route.AREA_GET_ALL_URL)
    Observable<AreaListBean> getAllArea(@Query("offset") int offset, @Query("limit") int limit);

    @GET(Route.IP_URL + Route.ADMIN_GET_ALL_URL)
    Observable<AdminListBean> getAllAdmin(@Query("offset") int offset, @Query("limit") int limit);

    @POST(Route.IP_URL + Route.ADMIN_GET_ALL_URL)
    Observable<ResponseBody> createAdmin(@Body AdminListBean.ObjectBean adminBean);

    @DELETE(Route.IP_URL + Route.ADMIN_DEL_ALL_URL)
    Observable<ResponseBody> delAdmin(@Query("id") int adminId);
}