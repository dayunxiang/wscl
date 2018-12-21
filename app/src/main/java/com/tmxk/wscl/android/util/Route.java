package com.tmxk.wscl.android.util;

import java.net.URL;

import okhttp3.MediaType;

public class Route {
    public final static MediaType JSON = MediaType.parse("application/json; charset=utf-8");
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
    public static final String SITE_SEWAGE_GET_SEWAGE_BY_ID_URL = "/tmxk/sewage/query/id";
    public static final String AREA_GET_ALL_URL = "/tmxk/areas/query/all";
    public static final int STATUS_CODE = 200;
}