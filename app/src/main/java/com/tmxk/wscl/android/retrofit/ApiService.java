package com.tmxk.wscl.android.retrofit;

import com.tmxk.wscl.android.util.Route;
import com.tmxk.wscl.android.mvp.model.LoginModel;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST(Route.IP_URL + Route.LOGIN_URL)
    Observable<LoginModel> login(@Body RequestBody body);

}