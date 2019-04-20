package com.tmxk.wscl.android.util;

import java.net.URL;

import okhttp3.MediaType;

public class Route {
    public final static MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//    public static final String IP_URL = "http://61.147.198.178:8186";
    public static final String IP_URL = "http://10.254.4.29:8186";
    public static final String LOGIN_URL = "/tmxk/users/sys/login";
    public static final String USER_SYS_URL = "/tmxk/users/sys";
    public static final String USER_SYS_PWD_URL = "/tmxk/users/sys/modifyPwd";
    public static final String USER_SYS_DEL_URL = "/tmxk/users/sys/delete/name";
    public static final String USER_SYS_LOGIN_LOG_URL = "/tmxk/users/loginRecord/query/all";
    public static final String SITE_DEVICE_DOC_ALL_URL = "/tmxk/device/doc/query/all";
    public static final String SITE_DEVICE_DOC_URL = "/tmxk/device/doc";
    public static final String SITE_DEVICE_DOC_ID_URL = "/tmxk/device/doc/delete/id";
    public static final String SITE_DEVICE_DOC_NAME_URL = "/tmxk/device/doc/delete/name";
    public static final String SITE_DEVICE_DOC_SEWAGE_URL = "/tmxk/device/doc/delete/sewage";
    public static final String SITE_DEVICE_DOC_GET_SEWAGE_URL = "/tmxk/device/doc/query/sewage";
    public static final String SITE_SEWAGE_GET_SEWAGE_BY_AREA_URL = "/tmxk/sewage/query/area";
    public static final String SITE_SEWAGE_GET_ALL_SEWAGE = "/tmxk/sewage/query/all";
    public static final String SITE_SEWAGE_GET_SEWAGE_BY_ID_URL = "/tmxk/sewage/query/id";
    public static final String SITE_SEWAGE_DEL_SEWAGE_BY_ID_URL = "/tmxk/sewage/delete/id";
    public static final String SITE_SEWAGE_URL = "/tmxk/sewage";
    public static final String AREA_GET_ALL_URL = "/tmxk/areas/query/all";
    public static final String ADMIN_GET_ALL_URL = "/tmxk/users/admin";
    public static final String ADMIN_DEL_ALL_URL = "/tmxk/users/admin/delete/id";
    public static final String MONITOR_SEWAGE_URL = "/tmxk/monitors/sewage";
    public static final String ALERT_EQUIP_STATUS_URL = "/tmxk/alerts/query/equipmentStatus";
    public static final String ALERT_WATER_QUALITY_URL = "/tmxk/alerts/query/waterQuality";
    public static final String ALERT_POWER_OFF_URL = "/tmxk/alerts/query/powerOffTime";
    public static final String ALERT_DATA_TRANSFER_URL = "/tmxk/alerts/query/dataTransfer";
    public static final String WATER_ANALYSIS_MONTH_URL = "/tmxk/waters/query-month/{date}";
    public static final String WATER_ANALYSIS_YEAR_URL = "/tmxk/waters/query-year/{date}";
    public static final String WATER_UP_SEWAGE_URL = "/tmxk/waters/query-month-status/{date}";
    public static final String WATER_UP_MONTH_URL = "/tmxk/waters/query-month-status-all/{date}";
    public static final String WATER_UP_YEAR_URL = "/tmxk/waters/query-year-status-all/{date}";
    public static final String GPS_RECORD_CREATE = "/tmxk/monitors/gps";
    public static final String GET_CARINFO_BY_SYSUSER_ID = "/tmxk/monitors/carInfo/sysuser/{sysuserId}";
    public static final String CREATE_CAR_GPS_BY_SYSUSER_ID = "/tmxk/monitors/carGpsBySysuser";
    public static final String GET_CAR_GPS_BY_ID_AND_PERIOD = "/tmxk/monitors/carGps/{carIdOrName}";
    public static final String GET_CAR_GPS_RECENT_ONCE = "/tmxk/monitors/carInfoAndGpsGetRecentOnce";
    public static final String GET_CAR_ALL_CAR_INFO = "/tmxk/monitors/allCarInfo";
    public static final String GET_ALL_ASSIGN_ORDER = "/tmxk/operates/order-assignment/query/all";
    public static final String GET_ASSIGN_ORDER_BY_CONDITION = "/tmxk/operates/order-assignment/query/condition";
    public static final String PUT_ASSIGN_ORDER_TYPE_STATUS = "/tmxk/operates/order-assignment/status/{id}";
    public static final String DEVICE_REPLACE_CREATE = "/tmxk/operates/device-replacement";
    public static final String GATHER_PROBLEM_CREATE = "/tmxk/operates/problem-gather";
    public static final String REPAIRMENT_CREATE = "/tmxk/operates/repairment";
    public static final String REPAIRMENT_PUT = "/tmxk/operates/repairment";
    public static final String GET_REPAIRMENT_BY_CONDITION = "/tmxk/operates/repairment/query/condition";
    public static final int STATUS_CODE = 200;
}