package com.tmxk.wscl.android.ui.water.testManager;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.jaeger.library.StatusBarUtil;
import com.tmxk.wscl.android.R;
import com.tmxk.wscl.android.adpter.GirdDropDownAdapter;
import com.tmxk.wscl.android.emuns.DataTypeEnum;
import com.tmxk.wscl.android.mvp.model.AreaListBean;
import com.tmxk.wscl.android.mvp.model.AssignmentOrderListBean;
import com.tmxk.wscl.android.mvp.model.SewageListBean;
import com.tmxk.wscl.android.mvp.model.WaterTestManageBodyBean;
import com.tmxk.wscl.android.mvp.presenter.OperatePresenter;
import com.tmxk.wscl.android.mvp.view.SewageArchiveView;
import com.tmxk.wscl.android.ui.base.MvpActivity;
import com.tmxk.wscl.android.util.CommonUtil;
import com.tmxk.wscl.android.util.Constant;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by wy on 19/04/20.
 * device doc create
 */
public class WaterTestCreateActivity extends MvpActivity<OperatePresenter> implements SewageArchiveView {
    private TimePickerView pvTime;
    private String headers[] = {};
    private List<View> popupViews = new ArrayList<>();
    private GirdDropDownAdapter regionAdapter;
    private GirdDropDownAdapter stationAdapter;
    private List<String> regions = new ArrayList<>();
    private List<String> sewages = new ArrayList<>();
    private List<Integer> sewageIds = new ArrayList<>();
    ListView regionView;
    ListView stationView;
    private Integer SEWAGE_ID;
    private Toolbar toolbar;
    private ScrollView scrollView;
    private LinearLayout ll1;
    private TextView tv1;
    private DropDownMenu dropDownMenu;
    private LinearLayout ll2;
    private TextView tv2;
    private EditText edtOutCod;
    private LinearLayout ll3;
    private TextView tv3;
    private EditText edtOutNh3n;
    private LinearLayout ll4;
    private TextView tv4;
    private EditText edtOutP;
    private LinearLayout ll5;
    private TextView tv5;
    private EditText edtInCod;
    private LinearLayout ll6;
    private TextView tv6;
    private EditText edtInNh3n;
    private LinearLayout ll7;
    private TextView tv7;
    private EditText edtInP;
    private LinearLayout ll8;
    private TextView tv8;
    private EditText edtReportNo;
    private LinearLayout ll9;
    private TextView tv9;
    private Button btnTestDate;
    private Button btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_water_test_manager_create);
        StatusBarUtil.setColorNoTranslucent(this, getResources().getColor(R.color.primary));
        initView();
        toolbar.setTitle("化验结果录入");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        initTimePicker();
        initData();
        btnOk.setEnabled(true);
    }

    private void initData() {
        //init region menu
        regionView = new ListView(this);
        stationView = new ListView(this);
        //获取区域节点
        mvpPresenter.getAllAreas();
    }

    private void initTimePicker() {
        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                btnTestDate.setText(CommonUtil.getTime(date));
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
    protected OperatePresenter createPresenter() {
        return new OperatePresenter(this);
    }

    @Override
    public void getDataSuccess(Object object, DataTypeEnum dataTypeEnum) {
        if (object instanceof AreaListBean) {
            Log.d("WaterTestActivity", "AreaListBean " + object.toString());
            AreaListBean areaListBean = (AreaListBean) object;
            //init region menu
            final List<Integer> regionIds = new ArrayList<>();
            if (areaListBean != null && areaListBean.getObject().size() > 0) {
                for (AreaListBean.AreaBean areaBean : areaListBean.getObject()) {
                    regions.add(areaBean.getName());
                    regionIds.add(areaBean.getId());
                }
            }
            ListView regionView = new ListView(this);
            regionAdapter = new GirdDropDownAdapter(this, regions);
            regionView.setDividerHeight(0);
            regionView.setAdapter(regionAdapter);
            popupViews.add(regionView);
            stationView.setDividerHeight(0);
            stationAdapter = new GirdDropDownAdapter(this, sewages);
            stationView.setAdapter(stationAdapter);
            //init popupViews
            popupViews.add(stationView);
            headers = new String[]{"请选择区县", "请选择站点"};

            //init context view
            TextView contentView = new TextView(this);
            contentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            contentView.setGravity(Gravity.CENTER);
            contentView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            //init dropDownView
            if (dropDownMenu != null) {
                dropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, contentView);
            }

            regionView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.d("SewageArchiveActivity", "onItemClick " + position);
                    Log.d("SewageArchiveActivity", "onItemClick-regions " + regions.get(position));
                    regionAdapter.setCheckItem(position);
                    dropDownMenu.setTabText(regions.get(position));
                    //根据区域获取站点
                    mvpPresenter.getSewages(true, regionIds.get(position));
                    dropDownMenu.closeMenu();
                }
            });
        } else if (object instanceof SewageListBean) {
            final SewageListBean sewageListBean = (SewageListBean) object;
            Log.d("SewageArchiveActivity", "SewageListBean " + object.toString());
            if (sewageListBean != null && sewageListBean.getObject().size() > 0) {
                sewages.clear();
                sewageIds.clear();
                for (SewageListBean.ObjectBean sewageBean : sewageListBean.getObject()) {
                    sewages.add(sewageBean.getName());
                    sewageIds.add(sewageBean.getId());
                }
            }
            //init context view
            TextView contentView = new TextView(this);
            contentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            contentView.setGravity(Gravity.CENTER);
            contentView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            //init dropDownView
            if (dropDownMenu != null) {
                dropDownMenu.refreshDrawableState();
            }
            stationView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                    stationAdapter.setCheckItem(position);
                    dropDownMenu.setTabText(sewages.get(position));
                    SEWAGE_ID = sewageIds.get(position);
                    dropDownMenu.closeMenu();
                }
            });
        } else if (object instanceof WaterTestManageBodyBean) {
            toastShow(Constant.CREATE_SUCCESS);
            btnOk.setEnabled(true);
        }
    }

    @Override
    public void getDataFail(String msg) {
        toastShow(msg);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnOk:
                if (("请选择时间").equals(btnTestDate.getText().toString().trim()) || ("").equals(btnTestDate.getText().toString().trim())) {
                    toastShow("取样时间");
                } else if (CommonUtil.isEmpty(edtOutCod.getText().toString().trim())) {
                    toastShow("请填写出水COD");
                } else if (CommonUtil.isEmpty(edtOutNh3n.getText().toString().trim())) {
                    toastShow("请填写出水Nh3n");
                }  else if (CommonUtil.isEmpty(edtOutP.getText().toString().trim())) {
                    toastShow("请填写出水P");
                } else if (CommonUtil.isEmpty(edtInCod.getText().toString().trim())) {
                    toastShow("请填写进水COD");
                } else if (CommonUtil.isEmpty(edtInNh3n.getText().toString().trim())) {
                    toastShow("请填写进水Nh3n");
                } else if (CommonUtil.isEmpty(edtInP.getText().toString().trim())) {
                    toastShow("请填写进水P");
                }else if (CommonUtil.isEmpty(edtReportNo.getText().toString().trim())) {
                    toastShow("请填写化验单号");
                } else {
                    WaterTestManageBodyBean waterTest = new WaterTestManageBodyBean();
                    waterTest.setInCod(Float.parseFloat(edtInCod.getText().toString().trim()));
                    waterTest.setInNh3n(Float.parseFloat(edtInNh3n.getText().toString().trim()));
                    waterTest.setInP(Float.parseFloat(edtInP.getText().toString().trim()));
                    waterTest.setOutCod(Float.parseFloat(edtOutCod.getText().toString().trim()));
                    waterTest.setOutNh3n(Float.parseFloat(edtOutNh3n.getText().toString().trim()));
                    waterTest.setOutP(Float.parseFloat(edtOutP.getText().toString().trim()));
                    waterTest.setTestingTime(btnTestDate.getText().toString().replace(" ", "T") + ".000");
                    waterTest.setSewage(new AssignmentOrderListBean.ObjectBean.SewageBean(SEWAGE_ID));
                    waterTest.setReportNo(edtReportNo.getText().toString().trim());
                    mvpPresenter.createWaterTestManager(waterTest);
                    btnOk.setEnabled(false);
                    finish();
                }
                break;
            case R.id.btnTestDate:
                if (pvTime != null) {
                    pvTime.show(view);
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
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
    public void onRefresh() {
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        ll1 = (LinearLayout) findViewById(R.id.ll1);
        tv1 = (TextView) findViewById(R.id.tv1);
        dropDownMenu = (DropDownMenu) findViewById(R.id.dropDownMenu);
        ll2 = (LinearLayout) findViewById(R.id.ll2);
        tv2 = (TextView) findViewById(R.id.tv2);
        edtOutCod = (EditText) findViewById(R.id.edtOutCod);
        ll3 = (LinearLayout) findViewById(R.id.ll3);
        tv3 = (TextView) findViewById(R.id.tv3);
        edtOutNh3n = (EditText) findViewById(R.id.edtOutNh3n);
        ll4 = (LinearLayout) findViewById(R.id.ll4);
        tv4 = (TextView) findViewById(R.id.tv4);
        edtOutP = (EditText) findViewById(R.id.edtOutP);
        ll5 = (LinearLayout) findViewById(R.id.ll5);
        tv5 = (TextView) findViewById(R.id.tv5);
        edtInCod = (EditText) findViewById(R.id.edtInCod);
        ll6 = (LinearLayout) findViewById(R.id.ll6);
        tv6 = (TextView) findViewById(R.id.tv6);
        edtInNh3n = (EditText) findViewById(R.id.edtInNh3n);
        ll7 = (LinearLayout) findViewById(R.id.ll7);
        tv7 = (TextView) findViewById(R.id.tv7);
        edtInP = (EditText) findViewById(R.id.edtInP);
        ll8 = (LinearLayout) findViewById(R.id.ll8);
        tv8 = (TextView) findViewById(R.id.tv8);
        edtReportNo = (EditText) findViewById(R.id.edtReportNo);
        ll9 = (LinearLayout) findViewById(R.id.ll9);
        tv9 = (TextView) findViewById(R.id.tv9);
        btnTestDate = (Button) findViewById(R.id.btnTestDate);
        btnOk = (Button) findViewById(R.id.btnOk);
    }
}