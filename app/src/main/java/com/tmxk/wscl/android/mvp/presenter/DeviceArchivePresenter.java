package com.tmxk.wscl.android.mvp.presenter;

import com.tmxk.wscl.android.mvp.view.DeviceArchiveView;

public class DeviceArchivePresenter extends BasePresenter<DeviceArchiveView> {
    public DeviceArchivePresenter(DeviceArchiveView view) {
        attachView(view);
    }
}