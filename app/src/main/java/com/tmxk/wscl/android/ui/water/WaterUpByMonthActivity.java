package com.tmxk.wscl.android.ui.water;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bin.david.form.core.SmartTable;
import com.jaeger.library.StatusBarUtil;
import com.tmxk.wscl.android.R;
import com.tmxk.wscl.android.adpter.GirdDropDownAdapter;
import com.tmxk.wscl.android.emuns.DataTypeEnum;
import com.tmxk.wscl.android.mvp.model.AreaListBean;
import com.tmxk.wscl.android.mvp.model.SewageListBean;
import com.tmxk.wscl.android.mvp.model.TableWaterUp;
import com.tmxk.wscl.android.mvp.model.TableWaterUpByMonth;
import com.tmxk.wscl.android.mvp.model.WaterSupBySewageBean;
import com.tmxk.wscl.android.mvp.model.WaterUpMonthBean;
import com.tmxk.wscl.android.mvp.presenter.WaterAnalysisPresenter;
import com.tmxk.wscl.android.mvp.view.SewageArchiveView;
import com.tmxk.wscl.android.ui.base.MvpActivity;
import com.tmxk.wscl.android.util.CommonUtil;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WaterUpByMonthActivity extends MvpActivity<WaterAnalysisPresenter> implements SewageArchiveView {
    @BindView(R.id.dropDownMenu)
    DropDownMenu mDropDownMenu;
    private String headers[] = {};
    private List<View> popupViews = new ArrayList<>();

    private GirdDropDownAdapter regionAdapter;
    private List<String> regions = new ArrayList<>();
    ListView regionView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private Button btnCheckDate;
    private Button btnOk;
    private TimePickerView pvTime;
    private int timerPickerPos = -1;
    private SmartTable table;
    private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_water_up_mouth);
        ButterKnife.bind(this);
        StatusBarUtil.setColorNoTranslucent(this, getResources().getColor(R.color.primary));
        initView();
    }

    @Override
    protected WaterAnalysisPresenter createPresenter() {
        return new WaterAnalysisPresenter(this);
    }

    private void initView() {
        table = (SmartTable) findViewById(R.id.table);
        //init toolbar
        toolbar.setTitle("月度超水量分析");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        btnCheckDate = (Button) findViewById(R.id.btnCheckTime);
        btnOk = (Button) findViewById(R.id.btnOk);
        //init region menu
        regionView = new ListView(this);
        initTimePicker();
        //获取区域节点
        mvpPresenter.getAllAreas();
    }

    @Override
    public void onBackPressed() {
        if (mDropDownMenu.isShowing()) {
            mDropDownMenu.closeMenu();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void getDataSuccess(Object object, DataTypeEnum dataTypeEnum) {
        if (object instanceof AreaListBean) {
            Log.d("WaterUpByMonthActivity", "AreaListBean " + object.toString());
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
            headers = new String[]{"请选择区县"};

            //init context view
            TextView contentView = new TextView(this);
            contentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            contentView.setGravity(Gravity.CENTER);
            contentView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            //init dropDownView
            if (mDropDownMenu != null) {
                mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, contentView);
            }

            regionView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.d("WaterUpByMonthActivity", "onItemClick " + position);
                    Log.d("WaterUpByMonthActivity", "onItemClick-regions " + regions.get(position));
                    flag = true;
                    regionAdapter.setCheckItem(position);
                    mDropDownMenu.setTabText(regions.get(position));
                    mDropDownMenu.closeMenu();
                }
            });
        }  else if (object instanceof WaterUpMonthBean) {
            WaterUpMonthBean waterUpMonthBean = (WaterUpMonthBean) object;
            Log.d("WaterUpByMonthActivity", "WaterUpMonthBean " + object.toString());
            Log.d("WaterUpByMonthActivity", "WaterUpMonthBean.size " + waterUpMonthBean.getObject().size());
            if (waterUpMonthBean != null && waterUpMonthBean.getObject().size() > 0) {
                Log.d("WaterUpByMonthActivity", "size>0");
                int sortNum = 0;
                List<TableWaterUpByMonth> tableWaterUpList = new ArrayList<>();
                for (WaterUpMonthBean.ObjectBean w : waterUpMonthBean.getObject()) {
                    //初始化数据TableWaterUp
                    if(w!=null){
                        TableWaterUpByMonth t = new TableWaterUpByMonth();
                        t.setSortNum(++sortNum);
                        t.setContrlId(w.getSewageId());
                        t.setOperatrNum(w.getOperateId());
                        t.setShortTitle(w.getSewageName());
                        t.setUpDay(w.getUpNum());
                        if(w.getBit()!=null){
                            t.setBit(w.getBit());
                        }else {
                            t.setBit("0%");
                        }
                        tableWaterUpList.add(t);
                    }
                }
                //绘制表格
                table.setVisibility(View.VISIBLE);
                table.getConfig().setShowXSequence(false);
                table.getConfig().setShowYSequence(false);
                table.setZoom(true);
                table.setData(tableWaterUpList);

            } else {
                toastShow("无数据");
                //表格去除
                table.setVisibility(View.INVISIBLE);
            }
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
                if (btnCheckDate.getText().toString() != null && !btnCheckDate.getText().toString().contains("请选择时间")) {
//                  Date startDate = CommonUtil.String2Date(btnDeviceSetupDate.getText().toString().concat(".000"));
                    Log.d("WaterAnalysisActivity", "startTime:" + btnCheckDate.getText().toString().substring(0, 7));
                    if (flag) {
                        mvpPresenter.getWaterUpMonth(btnCheckDate.getText().toString().substring(0, 7));
                    } else {
                        toastShow("请选择区县");
                    }
                } else {
                    toastShow("请选择时间");
                }
                break;
            case R.id.btnCheckTime:
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
                    btnCheckDate.setText(CommonUtil.getTime(date));
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