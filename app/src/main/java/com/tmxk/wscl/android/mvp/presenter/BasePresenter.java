package com.tmxk.wscl.android.mvp.presenter;

import android.annotation.SuppressLint;

import com.tmxk.wscl.android.retrofit.ApiClient;
import com.tmxk.wscl.android.retrofit.ApiService;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class BasePresenter<V> {
    public V mvpView;
    protected ApiService apiService;
    private CompositeDisposable mCompositeDisposable;

    public void attachView(V mvpView) {
        this.mvpView = mvpView;
        apiService = ApiClient.retrofit().create(ApiService.class);
    }

    public void detachView() {
        this.mvpView = null;
        onUnSubscribe();
    }

    public void onUnSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }

    @SuppressLint("CheckResult")
    public void addSubscription(Observable observable, DisposableObserver observer) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(observer);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer);
    }
}