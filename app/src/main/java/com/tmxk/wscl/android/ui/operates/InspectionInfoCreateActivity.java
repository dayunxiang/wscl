package com.tmxk.wscl.android.ui.operates;

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
import com.tmxk.wscl.android.adpter.RadioListAdapter;
import com.tmxk.wscl.android.adpter.SpinnerListAdapter;
import com.tmxk.wscl.android.emuns.DataTypeEnum;
import com.tmxk.wscl.android.mvp.model.AreaListBean;
import com.tmxk.wscl.android.mvp.model.AssignmentOrderListBean;
import com.tmxk.wscl.android.mvp.model.InspectionEntryBean;
import com.tmxk.wscl.android.mvp.model.InspectionEntryListBean;
import com.tmxk.wscl.android.mvp.model.InspectionInfoListBean;
import com.tmxk.wscl.android.mvp.model.SewageListBean;
import com.tmxk.wscl.android.mvp.presenter.OperatePresenter;
import com.tmxk.wscl.android.mvp.view.SewageArchiveView;
import com.tmxk.wscl.android.ui.base.MvpActivity;
import com.tmxk.wscl.android.util.CommonUtil;
import com.tmxk.wscl.android.util.Const;
import com.tmxk.wscl.android.util.Constant;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by wy on 19/04/28.
 * inspection info create
 */
public class InspectionInfoCreateActivity extends MvpActivity<OperatePresenter> implements SewageArchiveView {
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
    private TimePickerView pvTime;

    private Toolbar toolbar;
    private ScrollView scrollView;
    private LinearLayout ll1;
    private TextView tv1;
    private DropDownMenu dropDownMenu;
    private LinearLayout ll2;
    private EditText edtElectricityMeterNo;
    private LinearLayout ll3;
    private EditText edtElectricityMeterValue;
    private LinearLayout ll4;
    private EditText edtWorkTeam;
    private LinearLayout ll5;
    private Button btnInspectInfoDate;
    private LinearLayout ll6;
    private ListView listViewDeviceCheck;
    private LinearLayout ll7;
    private ListView listViewInOutWater;
    private LinearLayout ll8;
    private ListView listViewTechnology;
    private LinearLayout ll9;
    private ListView listViewEnvironmental;
    private LinearLayout l20;
    private ListView listViewDangerInfo;
    private Button btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_inspection_info_create);
        StatusBarUtil.setColorNoTranslucent(this, getResources().getColor(R.color.primary));
        initView();
        toolbar.setTitle("巡检录入");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        initData();
    }

    private void initData() {
        //time picker
        initTimePicker();
        //init region menu
        regionView = new ListView(this);
        stationView = new ListView(this);
        //获取区域节点
        mvpPresenter.getAllAreas();
        //获取所有的巡检录入表单数据
        mvpPresenter.getAllInspectinEntries();
        btnOk.setEnabled(true);
        Const.INSPECTION_ENTRY = new HashMap<>();
    }

    @Override
    protected OperatePresenter createPresenter() {
        return new OperatePresenter(this);
    }

    @Override
    public void getDataSuccess(Object object, DataTypeEnum dataTypeEnum) {
        if (object instanceof InspectionEntryListBean && DataTypeEnum.TYPE00.equals(dataTypeEnum)) {
            Const.INSPECTION_ENTRY = new HashMap<>();
            InspectionEntryListBean inspectionEntryListBean = (InspectionEntryListBean) object;
            if (inspectionEntryListBean != null&&inspectionEntryListBean.getObject()!=null&&
                    inspectionEntryListBean.getObject().size()>0) {
                for(InspectionEntryBean entry:inspectionEntryListBean.getObject()){
                    if(entry.getNo()==1){
                        for(InspectionEntryBean e:entry.getChildren()){
                            Const.INSPECTION_ENTRY.put(e.getAlias(),e.getChildren().get(0).getAlias());
                        }
                        SpinnerListAdapter spinnerAdapter = new SpinnerListAdapter(this,entry.getChildren());
                        listViewDeviceCheck.setAdapter(spinnerAdapter);
                    }else if(entry.getNo()==2){
                        for(InspectionEntryBean e:entry.getChildren()){
                            Const.INSPECTION_ENTRY.put(e.getAlias(),e.getChildren().get(0).getAlias());
                        }
                        SpinnerListAdapter spinnerAdapter = new SpinnerListAdapter(this,entry.getChildren());
                        listViewInOutWater.setAdapter(spinnerAdapter);
                    }else if(entry.getNo()==3){
                        for(InspectionEntryBean e:entry.getChildren()){
                            Const.INSPECTION_ENTRY.put(e.getAlias(),e.getChildren().get(0).getAlias());
                        }
                        SpinnerListAdapter spinnerAdapter = new SpinnerListAdapter(this,entry.getChildren());
                        listViewTechnology.setAdapter(spinnerAdapter);
                    }else if(entry.getNo()==4){
                        for(InspectionEntryBean e:entry.getChildren()){
                            Const.INSPECTION_ENTRY.put(e.getAlias(),e.getChildren().get(0).getAlias());
                        }
                        RadioListAdapter radioListAdapter = new RadioListAdapter(this,entry.getChildren());
                        listViewEnvironmental.setAdapter(radioListAdapter);
                    }else if(entry.getNo()==5){
                        for(InspectionEntryBean e:entry.getChildren()){
                            Const.INSPECTION_ENTRY.put(e.getAlias(),e.getChildren().get(0).getAlias());
                        }
                        RadioListAdapter radioListAdapter = new RadioListAdapter(this,entry.getChildren());
                        listViewDangerInfo.setAdapter(radioListAdapter);
                    }
                }
                CommonUtil.setListViewHeightBasedOnChildren(listViewDeviceCheck);
                CommonUtil.setListViewHeightBasedOnChildren(listViewInOutWater);
                CommonUtil.setListViewHeightBasedOnChildren(listViewTechnology);
                CommonUtil.setListViewHeightBasedOnChildren(listViewEnvironmental);
                CommonUtil.setListViewHeightBasedOnChildren(listViewDangerInfo);
            }
        } else if (object instanceof AreaListBean) {
            Log.d("SewageArchiveActivity", "AreaListBean " + object.toString());
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
        } else if (object instanceof InspectionInfoListBean.ObjectBean) {
            toastShow(Constant.CREATE_SUCCESS);
            btnOk.setEnabled(true);
            finish();
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
            case R.id.btnInspectInfoDate:
                if (pvTime != null) {
                    pvTime.show(view);
                }
                break;
            case R.id.btnOk:
                if (edtElectricityMeterNo.getText().toString().trim() == null || ("").equals(edtElectricityMeterNo.getText().toString().trim())) {
                    toastShow("请填写电表编号");
                } else if (edtElectricityMeterValue.getText().toString().trim() == null || ("").equals(edtElectricityMeterValue.getText().toString().trim())) {
                    toastShow("请填写电表数值");
                }else if (edtWorkTeam.getText().toString().trim() == null || ("").equals(edtWorkTeam.getText().toString().trim())) {
                    toastShow("请填写巡检班组");
                } else if (("不限").equals(btnInspectInfoDate.getText().toString().trim()) || ("").equals(btnInspectInfoDate.getText().toString().trim())) {
                    toastShow("请填写巡检时间");
                } else if (SEWAGE_ID == null || SEWAGE_ID == 0) {
                    toastShow("请选择站点");
                } else if(Const.INSPECTION_ENTRY.isEmpty()){
                    toastShow("请选择各项巡检指标");
                }else {
                    InspectionInfoListBean.ObjectBean inspectionInfo = new InspectionInfoListBean.ObjectBean();
                    inspectionInfo.setSewage(new AssignmentOrderListBean.ObjectBean.SewageBean(SEWAGE_ID));
                    inspectionInfo.setElectricityMeterNo(edtElectricityMeterNo.getText().toString());
                    inspectionInfo.setElectricityMeterValue(edtElectricityMeterValue.getText().toString());
                    inspectionInfo.setInspectionDate(btnInspectInfoDate.getText().toString().replace(" ", "T") + ".000");
                    inspectionInfo.setInspectionTeamWork(edtWorkTeam.getText().toString());
                    StringBuilder sb = new StringBuilder();
                    sb.append("{");
                    for (Map.Entry<String, String> entry : Const.INSPECTION_ENTRY.entrySet()) {
                        sb.append("\""+entry.getKey()+"\":\""+entry.getValue()+"\",");
                    }
                    String entries = sb.toString();
                    if(entries.contains(",")){
                        inspectionInfo.setEntries(entries.substring(0,entries.length()-1)+"}");
                    }else {
                        inspectionInfo.setEntries(entries+"}");
                    }
                    mvpPresenter.createInspectionInfo(inspectionInfo);
                    btnOk.setEnabled(false);
                    finish();
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


    private void initTimePicker() {
        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                btnInspectInfoDate.setText(CommonUtil.getTime(date));
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
    public void onRefresh() {
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        ll1 = (LinearLayout) findViewById(R.id.ll1);
        tv1 = (TextView) findViewById(R.id.tv1);
        dropDownMenu = (DropDownMenu) findViewById(R.id.dropDownMenu);
        ll2 = (LinearLayout) findViewById(R.id.ll2);
        edtElectricityMeterNo = (EditText) findViewById(R.id.edtElectricityMeterNo);
        ll3 = (LinearLayout) findViewById(R.id.ll3);
        edtElectricityMeterValue = (EditText) findViewById(R.id.edtElectricityMeterValue);
        ll4 = (LinearLayout) findViewById(R.id.ll4);
        edtWorkTeam = (EditText) findViewById(R.id.edtWorkTeam);
        ll5 = (LinearLayout) findViewById(R.id.ll5);
        btnInspectInfoDate = (Button) findViewById(R.id.btnInspectInfoDate);
        ll6 = (LinearLayout) findViewById(R.id.ll6);
        listViewDeviceCheck = (ListView) findViewById(R.id.listViewDeviceCheck);
        ll7 = (LinearLayout) findViewById(R.id.ll7);
        listViewInOutWater = (ListView) findViewById(R.id.listViewInOutWater);
        ll8 = (LinearLayout) findViewById(R.id.ll8);
        listViewTechnology = (ListView) findViewById(R.id.listViewTechnology);
        ll9 = (LinearLayout) findViewById(R.id.ll9);
        listViewEnvironmental = (ListView) findViewById(R.id.listViewEnvironmental);
        l20 = (LinearLayout) findViewById(R.id.l20);
        listViewDangerInfo = (ListView) findViewById(R.id.listViewDangerInfo);
        btnOk = (Button) findViewById(R.id.btnOk);
    }
}