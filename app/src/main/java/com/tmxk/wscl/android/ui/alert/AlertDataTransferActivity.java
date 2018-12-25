package com.tmxk.wscl.android.ui.alert;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bin.david.form.core.SmartTable;
import com.jaeger.library.StatusBarUtil;
import com.tmxk.wscl.android.R;
import com.tmxk.wscl.android.emuns.DataTypeEnum;
import com.tmxk.wscl.android.mvp.model.DataTransferBean;
import com.tmxk.wscl.android.mvp.model.SewageListBean;
import com.tmxk.wscl.android.mvp.model.TableDataTrans;
import com.tmxk.wscl.android.mvp.model.TableWaterQuality;
import com.tmxk.wscl.android.mvp.model.WaterQualityStatusBean;
import com.tmxk.wscl.android.mvp.presenter.AlertPresenter;
import com.tmxk.wscl.android.mvp.view.SewageArchiveView;
import com.tmxk.wscl.android.ui.base.MvpActivity;
import com.tmxk.wscl.android.util.CommonUtil;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlertDataTransferActivity extends MvpActivity<AlertPresenter> implements SewageArchiveView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private SmartTable table;
    private Button btnDeviceSetupDate;
    private Button btnOk;
    private TimePickerView pvTime;
    private int timerPickerPos = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_alert_data_transfer);
        ButterKnife.bind(this);
        StatusBarUtil.setColorNoTranslucent(this, getResources().getColor(R.color.primary));
        initView();
    }

    @Override
    protected AlertPresenter createPresenter() {
        return new AlertPresenter(this);
    }

    private void initView() {
        btnDeviceSetupDate = (Button) findViewById(R.id.btnDeviceSetupDate);
        btnOk = (Button) findViewById(R.id.btnOk);
        //init toolbar
        toolbar.setTitle("数据传输查询");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        table = (SmartTable) findViewById(R.id.table);
        initTimePicker();
        // 请求数据传输查询
        mvpPresenter.getAlertWaterQualityStatus();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void getDataSuccess(Object object, DataTypeEnum dataTypeEnum) {
        if (object instanceof DataTransferBean) {
            Log.d("AlertDataTransActiviy", "DataTransferBean " + object.toString());
            DataTransferBean dataTransferBean = (DataTransferBean) object;
            //init region menu
            List<TableDataTrans> tableDataTrans = new ArrayList<>();
            int sortNum = 0;
            if (dataTransferBean != null && dataTransferBean.getObject().size() > 0) {
                for (DataTransferBean.ObjectBean a : dataTransferBean.getObject()) {
                    if (a != null) {
                        TableDataTrans t = new TableDataTrans();
                        t.setSortNum(++sortNum);
                        t.setContrlId(a.getSewageID());
                        t.setOperatrNum(a.getOperationNum());
                        t.setShortTitle(a.getShortTitle());
                        t.setRecentUpdateTime(CommonUtil.stampToDate(String.valueOf(a.getTestingTime())));
                        tableDataTrans.add(t);
                    }
                }
            }else {
                toastShow("无数据");
            }
            table.getConfig().setShowXSequence(false);
            table.getConfig().setShowYSequence(false);
            table.setZoom(true);
            table.setData(tableDataTrans);
        }
    }

    @Override
    public void getDataFail(String msg) {
        toastShow(msg);
    }

    @Override
    public void onRefresh() {
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnOk:
                if(btnDeviceSetupDate.getText().toString()!=null&&!btnDeviceSetupDate.getText().toString().contains("请选择时间")){
//                  Date startDate = CommonUtil.String2Date(btnDeviceSetupDate.getText().toString().concat(".000"));
                    Log.d("AlertData","startTime:"+btnDeviceSetupDate.getText().toString());
                    mvpPresenter.getAlertDataTransfer(btnDeviceSetupDate.getText().toString());
                }else{
                    toastShow("请选择时间");
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
}