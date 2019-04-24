package com.tmxk.wscl.android.ui.operates;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.jaeger.library.StatusBarUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tmxk.wscl.android.R;
import com.tmxk.wscl.android.adpter.DeviceReplaceListAdapter;
import com.tmxk.wscl.android.adpter.EquipRepairRecordListAdapter;
import com.tmxk.wscl.android.adpter.GirdDropDownAdapter;
import com.tmxk.wscl.android.emuns.DataTypeEnum;
import com.tmxk.wscl.android.mvp.model.AreaListBean;
import com.tmxk.wscl.android.mvp.model.DeviceReplaceListBean;
import com.tmxk.wscl.android.mvp.model.EquipRepairRecordListBean;
import com.tmxk.wscl.android.mvp.model.SewageListBean;
import com.tmxk.wscl.android.mvp.presenter.OperatePresenter;
import com.tmxk.wscl.android.mvp.view.SewageArchiveView;
import com.tmxk.wscl.android.ui.base.MvpActivity;
import com.tmxk.wscl.android.util.CommonUtil;
import com.tmxk.wscl.android.widget.DropDownMenuView;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by wy on 19/04/20.
 * user manage page
 */
public class EquipRepairRecordActivity extends MvpActivity<OperatePresenter> implements SewageArchiveView {
    private int SEWAGE_ID;
    private EquipRepairRecordListAdapter equipRepairRecordListAdapter;
    private TimePickerView pvTime;
    private int timerPickerPos = -1;

    private GirdDropDownAdapter regionAdapter;
    private GirdDropDownAdapter stationAdapter;
    private List<String> regions = new ArrayList<>();
    private List<String> sewages = new ArrayList<>();
    private List<Integer> sewageIds = new ArrayList<>();
    ListView regionView;
    ListView stationView;
    private String headers[] = {};
    private List<View> popupViews = new ArrayList<>();
    private FrameLayout parentView;
    private SmartRefreshLayout refreshLayout;
    private ListView listView;
    private DropDownMenuView dropDownMenu;
    private Toolbar toolbar;
    private Button btnFilter;
    private LinearLayout ll1;
    private TextView tv1;
    private DropDownMenu sewageDropDownMenu;
    private LinearLayout ll2;
    private TextView tv2;
    private EditText edtRepairMan;
    private LinearLayout ll3;
    private TextView tv3;
    private Button btnStartDate;
    private LinearLayout ll4;
    private TextView tv4;
    private Button btnEndDate;
    private Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_equip_repair_record_list);
        //status bar
        StatusBarUtil.setColorNoTranslucent(this, getResources().getColor(R.color.primary));
        initView();
        //tool bar
        toolbar.setTitle("维保日志");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        //time picker
        initTimePicker();
        initData();
        //smart
        refreshLayout.setEnableLoadMore(true);
        refreshLayout.setEnableRefresh(true);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                equipRepairRecordListAdapter = null;
                String start, end;
                if (("不限").equals(btnStartDate.getText().toString().trim()) || ("").equals(btnStartDate.getText().toString().trim())) {
                    start = "2000-01-01 00:00:00.000";
                } else {
                    start = btnStartDate.getText().toString().trim() + " 00:00:00.000";
                }
                if (("不限").equals(btnEndDate.getText().toString().trim()) || ("").equals(btnEndDate.getText().toString().trim())) {
                    end = "2100-01-01 00:00:00.000";
                } else {
                    end = btnEndDate.getText().toString().trim() + " 00:00:00.000";
                }
                if (SEWAGE_ID != 0 && !CommonUtil.isEmpty(edtRepairMan.getText().toString().trim())) {
                    mvpPresenter.getEquipRepairRecordByCondition(true, SEWAGE_ID,
                            edtRepairMan.getText().toString(), start, end);
                } else if (SEWAGE_ID != 0 && CommonUtil.isEmpty(edtRepairMan.getText().toString().trim())) {
                    mvpPresenter.getEquipRepairRecordByCondition(true, SEWAGE_ID, start, end);
                } else if (SEWAGE_ID == 0 && CommonUtil.isEmpty(edtRepairMan.getText().toString().trim())) {
                    mvpPresenter.getEquipRepairRecordByCondition(true, start, end);
                } else if (SEWAGE_ID == 0 && !CommonUtil.isEmpty(edtRepairMan.getText().toString().trim())) {
                    mvpPresenter.getEquipRepairRecordByCondition(true, edtRepairMan.getText().toString(),
                            start, end);
                }
                refreshLayout.finishRefresh();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                String start, end;
                if (("不限").equals(btnStartDate.getText().toString().trim()) || ("").equals(btnStartDate.getText().toString().trim())) {
                    start = "2000-01-01 00:00:00.000";
                } else {
                    start = btnStartDate.getText().toString().trim() + " 00:00:00.000";
                }
                if (("不限").equals(btnEndDate.getText().toString().trim()) || ("").equals(btnEndDate.getText().toString().trim())) {
                    end = "2100-01-01 00:00:00.000";
                } else {
                    end = btnEndDate.getText().toString().trim() + " 00:00:00.000";
                }
                if (SEWAGE_ID != 0 && !CommonUtil.isEmpty(edtRepairMan.getText().toString().trim())) {
                    mvpPresenter.getEquipRepairRecordByCondition(false, SEWAGE_ID,
                            edtRepairMan.getText().toString(), start, end);
                } else if (SEWAGE_ID != 0 && CommonUtil.isEmpty(edtRepairMan.getText().toString().trim())) {
                    mvpPresenter.getEquipRepairRecordByCondition(false, SEWAGE_ID, start, end);
                } else if (SEWAGE_ID == 0 && CommonUtil.isEmpty(edtRepairMan.getText().toString().trim())) {
                    mvpPresenter.getEquipRepairRecordByCondition(false, start, end);
                } else if (SEWAGE_ID == 0 && !CommonUtil.isEmpty(edtRepairMan.getText().toString().trim())) {
                    mvpPresenter.getEquipRepairRecordByCondition(false, edtRepairMan.getText().toString(),
                            start, end);
                }
                refreshLayout.finishLoadMore();
            }
        });
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            }
//        });
        refreshLayout.autoRefresh();
    }

    @Override
    protected OperatePresenter createPresenter() {
        return new OperatePresenter(this);
    }

    private void initTimePicker() {
        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                if (timerPickerPos == 0) {
                    btnStartDate.setText(CommonUtil.getTimeByDay(date));
                } else if (timerPickerPos == 1) {
                    btnEndDate.setText(CommonUtil.getTimeByDay(date));
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

    private void hideInputMethod() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null && inputMethodManager.isActive()) {
            inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public void onRefresh() {
        refreshLayout.autoRefresh();
    }

    @Override
    public void getDataSuccess(Object object, DataTypeEnum dataTypeEnum) {
        if (object instanceof EquipRepairRecordListBean) {
            if (equipRepairRecordListAdapter != null) {
                List<EquipRepairRecordListBean.ObjectBean> equipRepairRecordBeans = equipRepairRecordListAdapter.getList();
                equipRepairRecordBeans.addAll(((EquipRepairRecordListBean) object).getObject());
                equipRepairRecordListAdapter.setList(equipRepairRecordBeans);
                equipRepairRecordListAdapter.notifyDataSetChanged();
            } else {
                equipRepairRecordListAdapter = new EquipRepairRecordListAdapter(this, ((EquipRepairRecordListBean) object).getObject());
                listView.setAdapter(equipRepairRecordListAdapter);
            }
        } else if (object instanceof AreaListBean) {
            Log.d("RepairDealActivity", "AreaListBean " + object.toString());
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
            if (sewageDropDownMenu != null) {
                sewageDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, contentView);
            }

            regionView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.d("SewageArchiveActivity", "onItemClick " + position);
                    Log.d("SewageArchiveActivity", "onItemClick-regions " + regions.get(position));
                    regionAdapter.setCheckItem(position);
                    sewageDropDownMenu.setTabText(regions.get(position));
                    //根据区域获取站点
                    mvpPresenter.getSewages(true, regionIds.get(position));
                    sewageDropDownMenu.closeMenu();
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
            if (sewageDropDownMenu != null) {
                sewageDropDownMenu.refreshDrawableState();
            }
            stationView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                    stationAdapter.setCheckItem(position);
                    sewageDropDownMenu.setTabText(sewages.get(position));
                    SEWAGE_ID = sewageIds.get(position);
                    sewageDropDownMenu.closeMenu();
                }
            });
        }
    }

    @Override
    public void getDataFail(String msg) {
        toastShow(msg);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnFilter:
                if (!dropDownMenu.isOpen()) {
                    dropDownMenu.open();
                } else {
                    dropDownMenu.close();
                    hideInputMethod();
                }
                break;
            case R.id.btnSearch:
                if (dropDownMenu.isOpen()) {
                    equipRepairRecordListAdapter = null;
                    dropDownMenu.close();
                    hideInputMethod();
                }
                refreshLayout.autoRefresh();
                break;
            case R.id.btnStartDate:
                if (pvTime != null) {
                    timerPickerPos = 0;
                    pvTime.show(view);
                }
                break;
            case R.id.btnEndDate:
                if (pvTime != null) {
                    timerPickerPos = 1;
                    pvTime.show(view);
                }
                break;
        }
    }

    private void initData() {
        //init region menu
        regionView = new ListView(this);
        stationView = new ListView(this);
        //获取区县
        mvpPresenter.getAllAreas();
    }

    private void initView() {
        parentView = (FrameLayout) findViewById(R.id.parentView);
        refreshLayout = (SmartRefreshLayout) findViewById(R.id.refreshLayout);
        listView = (ListView) findViewById(R.id.listView);
        dropDownMenu = (DropDownMenuView) findViewById(R.id.dropDownMenu);
        toolbar = (Toolbar) findViewById(R.id.viewTop);
        btnFilter = (Button) findViewById(R.id.btnFilter);
        ll1 = (LinearLayout) findViewById(R.id.ll1);
        tv1 = (TextView) findViewById(R.id.tv1);
        sewageDropDownMenu = (DropDownMenu) findViewById(R.id.sewageDropDownMenu);
        ll2 = (LinearLayout) findViewById(R.id.ll2);
        tv2 = (TextView) findViewById(R.id.tv2);
        edtRepairMan = (EditText) findViewById(R.id.edtRepairMan);
        ll3 = (LinearLayout) findViewById(R.id.ll3);
        tv3 = (TextView) findViewById(R.id.tv3);
        btnStartDate = (Button) findViewById(R.id.btnStartDate);
        ll4 = (LinearLayout) findViewById(R.id.ll4);
        tv4 = (TextView) findViewById(R.id.tv4);
        btnEndDate = (Button) findViewById(R.id.btnEndDate);
        btnSearch = (Button) findViewById(R.id.btnSearch);
    }
}