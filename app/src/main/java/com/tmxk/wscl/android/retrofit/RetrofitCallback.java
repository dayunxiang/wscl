package com.tmxk.wscl.android.retrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class RetrofitCallback<M> implements Callback<M> {

    abstract void onSuccess(M model);

    abstract void onFailure(int code, String msg);

    abstract void onThrowable(Throwable t);

    abstract void onFinish();

    @Override
    public void onResponse(Call<M> call, Response<M> response) {
        if (response.isSuccessful()) {
            onSuccess(response.body());
        } else {
            onFailure(response.code(), response.errorBody().toString());
        }
        onFinish();
    }

    @Override
    public void onFailure(Call<M> call, Throwable t) {
        onThrowable(t);
        onFinish();
    }
}