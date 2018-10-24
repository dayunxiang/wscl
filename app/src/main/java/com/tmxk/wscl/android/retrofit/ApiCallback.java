package com.tmxk.wscl.android.retrofit;

import com.tmxk.wscl.android.util.Constant;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeoutException;

import io.reactivex.observers.DisposableObserver;
import retrofit2.HttpException;

public abstract class ApiCallback<M> extends DisposableObserver<M> {

    public abstract void onSuccess(M model);

    public abstract void onFailure(String msg);

    public abstract void onFinish();


    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            int code = httpException.code();
            String msg = httpException.getMessage();
            if (code == 504) {
                msg = Constant.NETWORK_DISABLE;
            }
            if (code == 502 || code == 404) {
                msg = Constant.SERVER_DISABLE;
            }
            onFailure(msg);
        } else if (
                e instanceof SocketTimeoutException ||
                        e instanceof ConnectException ||
                        e instanceof TimeoutException
                ) {
            onFailure(Constant.CONNECT_TIMEOUT);
        } else {
            onFailure(e.getMessage());
        }
        onFinish();
    }

    @Override
    public void onNext(M model) {
        onSuccess(model);
    }

    @Override
    public void onComplete() {
        onFinish();
    }
}