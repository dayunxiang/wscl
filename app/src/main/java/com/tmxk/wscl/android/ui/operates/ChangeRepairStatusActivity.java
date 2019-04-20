package com.tmxk.wscl.android.ui.operates;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.jaeger.library.StatusBarUtil;
import com.tmxk.wscl.android.R;
import com.tmxk.wscl.android.emuns.DataTypeEnum;
import com.tmxk.wscl.android.mvp.model.RepairmentBean;
import com.tmxk.wscl.android.mvp.model.UserBean;
import com.tmxk.wscl.android.mvp.presenter.OperatePresenter;
import com.tmxk.wscl.android.mvp.view.SewageArchiveView;
import com.tmxk.wscl.android.ui.base.BaseActivity;
import com.tmxk.wscl.android.ui.base.MvpActivity;
import com.tmxk.wscl.android.util.CommonUtil;

import java.util.Date;
import java.util.Objects;

import butterknife.ButterKnife;

public class ChangeRepairStatusActivity extends MvpActivity<OperatePresenter> implements SewageArchiveView {

    private LinearLayout deviceParentView;
    private Toolbar toolbar;
    private EditText edtHandleMan;
    private Button btnHandleDate;
    private Button btnOk;
    private Button btnCancel;
    private TimePickerView pvTime;
    private int repairId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_change_repair_status);
        ButterKnife.bind(this);
        StatusBarUtil.setColorNoTranslucent(this, getResources().getColor(R.color.primary));
        initView();
        //init toolbar
        toolbar.setTitle("报修完成");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        initTimePicker();

        Intent intent = getIntent();
        if (intent.hasExtra("repairId")) {
            repairId = intent.getIntExtra("repairId", 0);
        }

        Log.d("ChangeRepairStatus","repairId:"+repairId);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(repairId<1){
                    toastShow("获取报修单id异常,请返回重试 id:"+repairId);
                }
                if(CommonUtil.isEmpty(edtHandleMan.getText().toString().trim())){
                    toastShow("请填写处理人");
                } else if(CommonUtil.isEmpty(btnHandleDate.getText().toString().trim())||
                        ("不限").equals(btnHandleDate.getText().toString().trim())){
                    toastShow("请选择处理时间");
                }else {
                    RepairmentBean repairmentBean = new RepairmentBean(repairId);
                    repairmentBean.setHandlman(edtHandleMan.getText().toString());
                    repairmentBean.setHandltime(btnHandleDate.getText().toString().replace(" ", "T") + ".000");
                    mvpPresenter.updateRepairStatus(repairmentBean);
                    finish();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnHandleDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pvTime != null) {
                    pvTime.show(v);
                }
            }
        });
    }

    @Override
    protected OperatePresenter createPresenter() {
        return new OperatePresenter(this);
    }

    private void initTimePicker() {
        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                btnHandleDate.setText(CommonUtil.getTime(date));
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
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    @Override
    public void onRefresh() {

    }

    private void initView() {
        deviceParentView = (LinearLayout) findViewById(R.id.deviceParentView);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        edtHandleMan = (EditText) findViewById(R.id.edtHandleMan);
        btnHandleDate = (Button) findViewById(R.id.btnHandleDate);
        btnOk = (Button) findViewById(R.id.btn_ok);
        btnCancel = (Button) findViewById(R.id.btn_cancel);
    }

    @Override
    public void getDataSuccess(Object model, DataTypeEnum dataTypeEnum) {
        toastShow("更新成功");
        finish();
    }

    @Override
    public void getDataFail(String msg) {

    }
}