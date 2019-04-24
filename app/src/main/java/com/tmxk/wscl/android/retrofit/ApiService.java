package com.tmxk.wscl.android.retrofit;

import com.tmxk.wscl.android.mvp.model.AdminListBean;
import com.tmxk.wscl.android.mvp.model.AlertEquipStatusBean;
import com.tmxk.wscl.android.mvp.model.AreaListBean;
import com.tmxk.wscl.android.mvp.model.AssignOrderPutBody;
import com.tmxk.wscl.android.mvp.model.AssignmentOrderListBean;
import com.tmxk.wscl.android.mvp.model.CarGpsRecordResBean;
import com.tmxk.wscl.android.mvp.model.CarInfoBean;
import com.tmxk.wscl.android.mvp.model.CarInfoResBean;
import com.tmxk.wscl.android.mvp.model.CreateCarGpsBySysuserBean;
import com.tmxk.wscl.android.mvp.model.DataTransferBean;
import com.tmxk.wscl.android.mvp.model.DeviceReplaceCreateBody;
import com.tmxk.wscl.android.mvp.model.DeviceReplaceListBean;
import com.tmxk.wscl.android.mvp.model.EquipRepairRecordListBean;
import com.tmxk.wscl.android.mvp.model.GatherProblemBean;
import com.tmxk.wscl.android.mvp.model.GpsRecordBean;
import com.tmxk.wscl.android.mvp.model.InspectionInfoListBean;
import com.tmxk.wscl.android.mvp.model.InspectionUrls;
import com.tmxk.wscl.android.mvp.model.PowerOffBean;
import com.tmxk.wscl.android.mvp.model.RepairmentBean;
import com.tmxk.wscl.android.mvp.model.RepairmentListBean;
import com.tmxk.wscl.android.mvp.model.SewageListBean;
import com.tmxk.wscl.android.mvp.model.SewageMonitorBean;
import com.tmxk.wscl.android.mvp.model.SiteDeviceDocBean;
import com.tmxk.wscl.android.mvp.model.SiteDeviceDocListBean;
import com.tmxk.wscl.android.mvp.model.UploadPicResBean;
import com.tmxk.wscl.android.mvp.model.UserBean;
import com.tmxk.wscl.android.mvp.model.UserListBean;
import com.tmxk.wscl.android.mvp.model.UserLoginLogListBean;
import com.tmxk.wscl.android.mvp.model.WaterAnalysisMonthBean;
import com.tmxk.wscl.android.mvp.model.WaterAnalysisYearBean;
import com.tmxk.wscl.android.mvp.model.WaterQualityStatusBean;
import com.tmxk.wscl.android.mvp.model.WaterSupBySewageBean;
import com.tmxk.wscl.android.mvp.model.WaterTestListBean;
import com.tmxk.wscl.android.mvp.model.WaterTestManageBodyBean;
import com.tmxk.wscl.android.mvp.model.WaterUpMonthBean;
import com.tmxk.wscl.android.mvp.model.WaterUpYearBean;
import com.tmxk.wscl.android.util.Route;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
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

    @GET(Route.IP_URL + Route.SITE_SEWAGE_GET_ALL_SEWAGE)
    Observable<SewageListBean> getAllSewage(@Query("offset") int offset, @Query("limit") int limit);

    @GET(Route.IP_URL + Route.SITE_SEWAGE_GET_SEWAGE_BY_ID_URL)
    Observable<SewageListBean.ObjectBean> getSewageById(@Query("id") int id);

    @POST(Route.IP_URL + Route.SITE_SEWAGE_URL)
    Observable<ResponseBody> createSewage(@Body SewageListBean.ObjectBean sewageBean);

    @DELETE(Route.IP_URL + Route.SITE_SEWAGE_DEL_SEWAGE_BY_ID_URL)
    Observable<ResponseBody> delSewageById(@Query("id") int Id);

    @GET(Route.IP_URL + Route.AREA_GET_ALL_URL)
    Observable<AreaListBean> getAllArea(@Query("offset") int offset, @Query("limit") int limit);

    @GET(Route.IP_URL + Route.ADMIN_GET_ALL_URL)
    Observable<AdminListBean> getAllAdmin(@Query("offset") int offset, @Query("limit") int limit);

    @POST(Route.IP_URL + Route.ADMIN_GET_ALL_URL)
    Observable<ResponseBody> createAdmin(@Body AdminListBean.ObjectBean adminBean);

    @DELETE(Route.IP_URL + Route.ADMIN_DEL_ALL_URL)
    Observable<ResponseBody> delAdmin(@Query("id") int adminId);

    @GET(Route.IP_URL + Route.MONITOR_SEWAGE_URL)
    Observable<SewageMonitorBean> getSewageMonitor(@Query("id") int id);

    @GET(Route.IP_URL + Route.ALERT_EQUIP_STATUS_URL)
    Observable<AlertEquipStatusBean> getAlertEquipmentStatus(@Query("offset") int offset, @Query("limit") int limit);

    @GET(Route.IP_URL + Route.ALERT_WATER_QUALITY_URL)
    Observable<WaterQualityStatusBean> getAlertWaterQualityStatus(@Query("offset") int offset, @Query("limit") int limit);

    @GET(Route.IP_URL + Route.ALERT_POWER_OFF_URL)
    Observable<PowerOffBean> getAlertPowerOff(@Query("offset") int offset, @Query("limit") int limit);

    @GET(Route.IP_URL + Route.ALERT_DATA_TRANSFER_URL)
    Observable<DataTransferBean> getAlertDataTransfer(@Query("startTime") String startTime, @Query("offset") int offset, @Query("limit") int limit);

    @GET(Route.IP_URL + Route.WATER_ANALYSIS_MONTH_URL)
    Observable<WaterAnalysisMonthBean> getWaterAnalysisMonth(@Path ("date")String date, @Query("sewageId") int sewageId,
                                                             @Query("offset") int offset, @Query("limit") int limit);

    @GET(Route.IP_URL + Route.WATER_ANALYSIS_YEAR_URL)
    Observable<WaterAnalysisYearBean> getWaterAnalysisYear(@Path ("date")String date, @Query("sewageId") int sewageId,
                                                           @Query("offset") int offset, @Query("limit") int limit);

    @GET(Route.IP_URL + Route.WATER_UP_SEWAGE_URL)
    Observable<WaterSupBySewageBean> getWaterUpBySewage(@Path ("date")String date, @Query("sewageId") int sewageId,
                                                        @Query("offset") int offset, @Query("limit") int limit);

    @GET(Route.IP_URL + Route.WATER_UP_MONTH_URL)
    Observable<WaterUpMonthBean> getWaterUpMonth(@Path ("date")String date, @Query("offset") int offset, @Query("limit") int limit);

    @GET(Route.IP_URL + Route.WATER_UP_YEAR_URL)
    Observable<WaterUpYearBean> getWaterUpYear(@Path ("date")String date, @Query("offset") int offset, @Query("limit") int limit);

    @GET(Route.IP_URL + Route.GET_CARINFO_BY_SYSUSER_ID)
    Observable<CarInfoBean> getCarinfoBySysuserId(@Path ("sysuserId")int sysuserId);

    @POST(Route.IP_URL + Route.GPS_RECORD_CREATE)
    Observable<ResponseBody> createGpsRecord(@Body GpsRecordBean gpsRecordBean);

    @POST(Route.IP_URL + Route.CREATE_CAR_GPS_BY_SYSUSER_ID)
    Observable<ResponseBody> createGpsRecordBySysuser(@Body CreateCarGpsBySysuserBean createCarGpsBySysuserBean);

    @GET(Route.IP_URL + Route.GET_CAR_GPS_BY_ID_AND_PERIOD)
    Observable<CarGpsRecordResBean> getCarGpsByIdAndPeriod(@Path ("carIdOrName")String carIdOrName,
                                                           @Query("offset") int offset, @Query("limit") int limit,
                                                           @Query("start") String startTime, @Query("end") String endTime);

    @GET(Route.IP_URL + Route.GET_CAR_GPS_RECENT_ONCE)
    Observable<CarGpsRecordResBean> getCarGpsRecentOnce(@Query("offset") int offset, @Query("limit") int limit);

    @GET(Route.IP_URL + Route.GET_CAR_ALL_CAR_INFO)
    Observable<CarInfoResBean> getAllCarInfo(@Query("offset") int offset, @Query("limit") int limit);

    @GET(Route.IP_URL + Route.GET_ALL_ASSIGN_ORDER)
    Observable<AssignmentOrderListBean> getAllAssignOrder(@Query("offset") int offset, @Query("limit") int limit);

    //sysuser_id=11&task_status=%E5%B7%B2%E5%AE%8C%E6%88%90&startTime=2010-11-19%2017%3A22%3A00.000&endTime=2100-11-19%2017%3A22%3A00.000&offset=1&limit=1000
    @GET(Route.IP_URL + Route.GET_ASSIGN_ORDER_BY_CONDITION)
    Observable<AssignmentOrderListBean> getAssignOrderByCondition(@Query("sysuser_id") int sysuserId,
                                                                  @Query("task_status") String taskStatus,
                                                                  @Query("startTime") String startTime,
                                                                  @Query("endTime") String endTime,
                                                                  @Query("offset") int offset,
                                                                  @Query("limit") int limit);
    @GET(Route.IP_URL + Route.GET_ASSIGN_ORDER_BY_CONDITION)
    Observable<AssignmentOrderListBean> getAssignOrderByCondition_1(@Query("sysuser_id") int sysuserId,
                                                                    @Query("startTime") String startTime,
                                                                    @Query("endTime") String endTime,
                                                                    @Query("offset") int offset,
                                                                    @Query("limit") int limit);
    @GET(Route.IP_URL + Route.GET_ASSIGN_ORDER_BY_CONDITION)
    Observable<AssignmentOrderListBean> getAssignOrderByCondition_2(@Query("task_status") String taskStatus,
                                                                    @Query("startTime") String startTime,
                                                                    @Query("endTime") String endTime,
                                                                    @Query("offset") int offset,
                                                                    @Query("limit") int limit);
    @GET(Route.IP_URL + Route.GET_ASSIGN_ORDER_BY_CONDITION)
    Observable<AssignmentOrderListBean> getAssignOrderByCondition_3(@Query("startTime") String startTime,
                                                                    @Query("endTime") String endTime,
                                                                    @Query("offset") int offset,
                                                                    @Query("limit") int limit);

    @PUT(Route.IP_URL + Route.PUT_ASSIGN_ORDER_TYPE_STATUS)
    Observable<ResponseBody> updateOrderTypeStatus(@Path ("id")int id,
                                                   @Body AssignOrderPutBody assignOrderPutBody);

    @POST(Route.IP_URL + Route.DEVICE_REPLACE_CREATE)
    Observable<DeviceReplaceCreateBody> createDeviceReplace(@Body DeviceReplaceCreateBody deviceReplaceBody);

    @POST(Route.IP_URL + Route.GATHER_PROBLEM_CREATE)
    Observable<GatherProblemBean> createGatherProblem(@Body GatherProblemBean gatherProblemBody);

    @POST(Route.IP_URL + Route.REPAIRMENT_CREATE)
    Observable<RepairmentBean> createRepairment(@Body RepairmentBean repairmentBean);

    @GET(Route.IP_URL + Route.GET_REPAIRMENT_BY_CONDITION)
    Observable<RepairmentListBean> getRepairmentCondition(
            @Query("status") String status,
            @Query("startTime") String startTime,
            @Query("endTime") String endTime,
            @Query("offset") int offset,
            @Query("limit") int limit);

    @GET(Route.IP_URL + Route.GET_REPAIRMENT_BY_CONDITION)
    Observable<RepairmentListBean> getRepairmentCondition(
            @Query("sewage_id") int sewageId,
            @Query("startTime") String startTime,
            @Query("endTime") String endTime,
            @Query("offset") int offset,
            @Query("limit") int limit);

    @GET(Route.IP_URL + Route.GET_REPAIRMENT_BY_CONDITION)
    Observable<RepairmentListBean> getRepairmentCondition(
            @Query("startTime") String startTime,
            @Query("endTime") String endTime,
            @Query("offset") int offset,
            @Query("limit") int limit);

    @GET(Route.IP_URL + Route.GET_REPAIRMENT_BY_CONDITION)
    Observable<RepairmentListBean> getRepairmentCondition(
            @Query("sewage_id") int sewageId,
            @Query("status") String status,
            @Query("startTime") String startTime,
            @Query("endTime") String endTime,
            @Query("offset") int offset,
            @Query("limit") int limit);

    @PUT(Route.IP_URL + Route.REPAIRMENT_PUT)
    Observable<ResponseBody> updateRepairStatus(@Body RepairmentBean repairmentBean);

    @Multipart
    @POST(Route.IP_URL + Route.UPLOAD_FILE)
    Observable<UploadPicResBean> uploadFile(
            @Path ("folderPath")String folderPath,
            @Path ("filename")String filename,
            @Part MultipartBody.Part file);

    @GET(Route.IP_URL + Route.GET_INSPECTION_BY_CONDITION)
    Observable<InspectionInfoListBean> getInspectionCondition(
            @Query("sewage_id") int sewageId,
            @Query("startTime") String startTime,
            @Query("endTime") String endTime,
            @Query("offset") int offset,
            @Query("limit") int limit);

    @GET(Route.IP_URL + Route.GET_INSPECTION_BY_CONDITION)
    Observable<InspectionInfoListBean> getInspectionCondition(
            @Query("startTime") String startTime,
            @Query("endTime") String endTime,
            @Query("offset") int offset,
            @Query("limit") int limit);

    @POST(Route.IP_URL + Route.INSPECTION_URL_CREATE)
    Observable<InspectionUrls> createInspectionUrl(@Body InspectionUrls inspectionUrls);

    @GET(Route.IP_URL + Route.GET_DEVICE_REPLACE_BY_CONDITION)
    Observable<DeviceReplaceListBean> getDeviceReplaceByCondition(
            @Query("sewage_id") int sewageId,
            @Query("proStartTime") String proStartTime,
            @Query("proEndTime") String proEndTime,
            @Query("repStartTime") String repStartTime,
            @Query("repEndTime") String repEndTime,
            @Query("offset") int offset,
            @Query("limit") int limit);

    @GET(Route.IP_URL + Route.GET_DEVICE_REPLACE_BY_CONDITION)
    Observable<DeviceReplaceListBean> getDeviceReplaceByCondition(
            @Query("proStartTime") String proStartTime,
            @Query("proEndTime") String proEndTime,
            @Query("repStartTime") String repStartTime,
            @Query("repEndTime") String repEndTime,
            @Query("offset") int offset,
            @Query("limit") int limit);

    @GET(Route.IP_URL + Route.GET_EQUIP_REPAIR_RECORD_BY_CONDITION)
    Observable<EquipRepairRecordListBean> getEquipRepairRecordByCondition(
            @Query("sewage_id") int sewageId,
            @Query("repairman") String repairMan,
            @Query("startTime") String startTime,
            @Query("endTime") String endTime,
            @Query("offset") int offset,
            @Query("limit") int limit);

    @GET(Route.IP_URL + Route.GET_EQUIP_REPAIR_RECORD_BY_CONDITION)
    Observable<EquipRepairRecordListBean> getEquipRepairRecordByCondition(
            @Query("repairman") String repairMan,
            @Query("startTime") String startTime,
            @Query("endTime") String endTime,
            @Query("offset") int offset,
            @Query("limit") int limit);

    @GET(Route.IP_URL + Route.GET_EQUIP_REPAIR_RECORD_BY_CONDITION)
    Observable<EquipRepairRecordListBean> getEquipRepairRecordByCondition(
            @Query("sewage_id") int sewageId,
            @Query("startTime") String startTime,
            @Query("endTime") String endTime,
            @Query("offset") int offset,
            @Query("limit") int limit);

    @GET(Route.IP_URL + Route.GET_EQUIP_REPAIR_RECORD_BY_CONDITION)
    Observable<EquipRepairRecordListBean> getEquipRepairRecordByCondition(
            @Query("startTime") String startTime,
            @Query("endTime") String endTime,
            @Query("offset") int offset,
            @Query("limit") int limit);

    @GET(Route.IP_URL + Route.GET_WATER_TEST_MANAGER_BY_CONDITION)
    Observable<WaterTestListBean> getWaterTestManagerByCondition(
            @Query("sewage_id") int sewageId,
            @Query("startTime") String startTime,
            @Query("endTime") String endTime,
            @Query("offset") int offset,
            @Query("limit") int limit);

    @GET(Route.IP_URL + Route.GET_WATER_TEST_MANAGER_BY_CONDITION)
    Observable<WaterTestListBean> getWaterTestManagerByCondition(
            @Query("startTime") String startTime,
            @Query("endTime") String endTime,
            @Query("offset") int offset,
            @Query("limit") int limit);

    @GET(Route.IP_URL + Route.GET_WATER_TEST_TOP_12)
    Observable<WaterTestListBean> getWaterTestTop12(@Query("sewage_id") int sewageId);

    @POST(Route.IP_URL + Route.WATER_TEST_MANAGER_CREATE)
    Observable<WaterTestManageBodyBean> createWaterTestManager(@Body WaterTestManageBodyBean waterTest);
}