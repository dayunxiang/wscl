package com.tmxk.wscl.android.retrofit;

import com.tmxk.wscl.android.BuildConfig;
import com.tmxk.wscl.android.util.Constant;
import com.tmxk.wscl.android.util.Route;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit mRetrofit;

    public static Retrofit retrofit() {
        if (mRetrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                builder.addInterceptor(loggingInterceptor);
            }
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(6, TimeUnit.SECONDS)
                    .writeTimeout(6, TimeUnit.SECONDS)
                    .readTimeout(6, TimeUnit.SECONDS)
                    .build();
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(Route.IP_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return mRetrofit;
    }
}