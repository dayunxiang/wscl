package com.tmxk.wscl.android.util;

import okhttp3.MediaType;

public class Route {
    public final static MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final String IP_URL = "http://10.254.4.29:8186";
    public static final String LOGIN_URL = "/tmxk/users/sys/login";
    public static final String USER_SYS_URL = "/tmxk/users/sys";
    public static final String USER_SYS_PWD_URL = "/tmxk/users/sys/modifyPwd";
}