package com.tmxk.wscl.android.ui.device;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.jaeger.library.StatusBarUtil;
import com.tmxk.wscl.android.R;
import com.tmxk.wscl.android.application.MainApplication;
import com.tmxk.wscl.android.emuns.DataTypeEnum;
import com.tmxk.wscl.android.mvp.model.SewageListBean;
import com.tmxk.wscl.android.mvp.model.SiteDeviceDocBean;
import com.tmxk.wscl.android.mvp.model.UserBean;
import com.tmxk.wscl.android.mvp.presenter.DeviceDocPresenter;
import com.tmxk.wscl.android.mvp.presenter.UserPresenter;
import com.tmxk.wscl.android.mvp.view.SewageArchiveView;
import com.tmxk.wscl.android.mvp.view.UserView;
import com.tmxk.wscl.android.ui.base.MvpActivity;
import com.tmxk.wscl.android.util.CommonUtil;
import com.tmxk.wscl.android.util.Constant;

import java.text.ParseException;
import java.util.Date;
import java.util.Objects;

import butterknife.BindView;

/**
 * Created by wy on 18/12/21.
 * device doc create
 */
public class DeviceDocCreateActivity extends MvpActivity<DeviceDocPresenter> implements SewageArchiveView {
    @BindView(R.id.edtDeviceName)
    EditText edtDeviceName;
    @BindView(R.id.edtDeviceNo)
    EditText edtDeviceNo;
    @BindView(R.id.edtDeviceType)
    EditText edtDeviceType;
    @BindView(R.id.edtDevicePower)
    EditText edtDevicePower;
    @BindView(R.id.edtDeviceLimitYear)
    EditText edtDeviceLimitYear;
    @BindView(R.id.btnDeviceSetupDate)
    Button btnDeviceSetupDate;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private TimePickerView pvTime;
    private int timerPickerPos = -1;

    private SiteDeviceDocBean deviceDocBean = new SiteDeviceDocBean();
    private int sewageId;
    private boolean isAddDeviceDoc = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_device_doc_create);
        StatusBarUtil.setColorNoTranslucent(this, getResources().getColor(R.color.primary));
        initData();
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        initTimePicker();
    }

    private void initData() {
        Intent intent = getIntent();
        if(intent.getBooleanExtra("addDevice",false)){
            isAddDeviceDoc=true;
            toolbar.setTitle("新建设备档案");
            sewageId = intent.getIntExtra("sewageId",0);
        }
    }

    private void initTimePicker() {
        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                if (timerPickerPos == 0) {
                    btnDeviceSetupDate.setText(CommonUtil.getTime(date));
                }
                timerPickerPos = -1;
                Log.i("pvTime", "onTimeSelect");
            }
        })
                .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                    @Override
                    public void onTimeSelectChanged(Date date) {
                        Log.i("pvTime", "onTimeSelectChanged");
                    }
                })
                .setOutSideCancelable(true)
                .setCancelColor(Color.parseColor("#333333"))
                .setSubmitColor(Color.parseColor("#333333"))
                .setType(new boolean[]{true, true, true, true, true, true})
                .isDialog(true)
                .build();
        Dialog mDialog = pvTime.getDialog();
        if (mDialog != null) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);
            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime.getDialogContainerLayout().setLayoutParams(params);
            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);
                dialogWindow.setGravity(Gravity.BOTTOM);
            }
        }
    }

    @Override
    protected DeviceDocPresenter createPresenter() {
        return new DeviceDocPresenter(this);
    }

    @Override
    public void getDataSuccess(Object model, DataTypeEnum dataTypeEnum) {
        toastShow(Constant.CREATE_SUCCESS);
        setResultIntent();
    }

    @Override
    public void getDataFail(String msg) {
        toastShow(msg);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                setResultIntent();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnOk:
                deviceDocBean.setDeviceName(edtDeviceName.getText().toString().trim());
                deviceDocBean.setDeviceNo(edtDeviceNo.getText().toString().trim());
                if(edtDevicePower.getText().toString().trim()!=null&&!("").equals(edtDevicePower.getText().toString())){
                    deviceDocBean.setDevicePower(Integer.parseInt(edtDevicePower.getText().toString().trim()));
                }else{
                    deviceDocBean.setDevicePower(0);
                }
                deviceDocBean.setDeviceType(edtDeviceType.getText().toString().trim());
                deviceDocBean.setSewage(new SewageListBean.ObjectBean(sewageId));
                if(edtDeviceLimitYear.getText().toString().trim()!=null&&!edtDeviceLimitYear.getText().toString().equals("")){
                    deviceDocBean.setLimitYears(Integer.parseInt(edtDeviceLimitYear.getText().toString().trim()));
                }else{
                    deviceDocBean.setLimitYears(0);
                }
                deviceDocBean.setSetupTime(btnDeviceSetupDate.getText().toString().replace(" ","T").concat(".164Z"));
//                deviceDocBean.setSetupTime("2018-12-21T02:44:09.164Z");
                if (isAddDeviceDoc) {
                    mvpPresenter.createDeviceDoc(deviceDocBean);
                }
                break;
            case R.id.btnDeviceSetupDate:
                if (pvTime != null) {
                    timerPickerPos = 0;
                    pvTime.show(view);
                }
                break;
        }
    }

    private void setResultIntent() {
        if (isAddDeviceDoc) {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putBoolean("hasAddDevice", true);
            intent.putExtras(bundle);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResultIntent();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
            mvpPresenter.onUnSubscribe();
            mvpPresenter = null;
        }
    }

    @Override
    public void onRefresh() {}
}