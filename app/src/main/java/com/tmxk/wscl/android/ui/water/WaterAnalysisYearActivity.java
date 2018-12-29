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
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.jaeger.library.StatusBarUtil;
import com.tmxk.wscl.android.R;
import com.tmxk.wscl.android.adpter.GirdDropDownAdapter;
import com.tmxk.wscl.android.emuns.DataTypeEnum;
import com.tmxk.wscl.android.mvp.model.AreaListBean;
import com.tmxk.wscl.android.mvp.model.SewageListBean;
import com.tmxk.wscl.android.mvp.model.WaterAnalysisYearBean;
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

public class WaterAnalysisYearActivity extends MvpActivity<WaterAnalysisPresenter> implements SewageArchiveView {
    @BindView(R.id.dropDownMenu)
    DropDownMenu mDropDownMenu;
    private String headers[] = {};
    private List<View> popupViews = new ArrayList<>();

    private GirdDropDownAdapter regionAdapter;
    private GirdDropDownAdapter stationAdapter;
    private List<String> regions = new ArrayList<>();
    private List<String> sewages = new ArrayList<>();
    private int sewageId;
    ListView regionView;
    ListView stationView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private Button btnCheckDate;
    private Button btnOk;
    private LineChart chart;
    private TimePickerView pvTime;
    private int timerPickerPos = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_water_year);
        ButterKnife.bind(this);
        StatusBarUtil.setColorNoTranslucent(this, getResources().getColor(R.color.primary));
        initView();
    }

    @Override
    protected WaterAnalysisPresenter createPresenter() {
        return new WaterAnalysisPresenter(this);
    }

    private void initView() {
        //init toolbar
        toolbar.setTitle("年处理水量");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        btnCheckDate = (Button) findViewById(R.id.btnCheckTime);
        btnOk = (Button) findViewById(R.id.btnOk);
        chart = (LineChart) findViewById(R.id.chart);
        chart.setVisibility(View.INVISIBLE);
        //init region menu
        regionView = new ListView(this);
        stationView = new ListView(this);
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
            Log.d("WaterAnalysisYActivity", "AreaListBean " + object.toString());
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
            if (mDropDownMenu != null) {
                mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, contentView);
            }

            regionView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.d("WaterAnalysisYActivity", "onItemClick " + position);
                    Log.d("WaterAnalysisYActivity", "onItemClick-regions " + regions.get(position));
                    regionAdapter.setCheckItem(position);
                    mDropDownMenu.setTabText(regions.get(position));
                    //根据区域获取站点
                    mvpPresenter.getSewages(true, regionIds.get(position));
                    mDropDownMenu.closeMenu();
                }
            });
        } else if (object instanceof SewageListBean) {
            final SewageListBean sewageListBean = (SewageListBean) object;
            Log.d("WaterAnalysisYActivity", "SewageListBean " + object.toString());
            if (sewageListBean != null && sewageListBean.getObject().size() > 0) {
                sewages.clear();
                for (SewageListBean.ObjectBean sewageBean : sewageListBean.getObject()) {
                    sewages.add(sewageBean.getName());
                }
            }

            //init context view
            TextView contentView = new TextView(this);
            contentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            contentView.setGravity(Gravity.CENTER);
            contentView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            //init dropDownView
            if (mDropDownMenu != null) {
                mDropDownMenu.refreshDrawableState();
//                mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, contentView);
            }
            stationView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                    stationAdapter.setCheckItem(position);
                    mDropDownMenu.setTabText(sewages.get(position));
                    sewageId = sewageListBean.getObject().get(position).getId();
                    mDropDownMenu.closeMenu();
                }
            });
        }else if(object instanceof WaterAnalysisYearBean){
            WaterAnalysisYearBean waterAnalysisYearBean = (WaterAnalysisYearBean) object;
            Log.d("WaterAnalysisYActivity", "WaterAnalysisYearBean " + object.toString());
            Log.d("WaterAnalysisYActivity", "WaterAnalysisYearBean.size " + waterAnalysisYearBean.getObject().size());
            if (waterAnalysisYearBean != null && waterAnalysisYearBean.getObject().size() > 0) {
                Log.d("WaterAnalysisYActivity", "size>0");
                final ArrayList<String> xValues = new ArrayList<>();
                List<Entry> yValues = new ArrayList<>();
                List<Entry> y2Values = new ArrayList<>();
                for (int i=0;i<waterAnalysisYearBean.getObject().size();i++) {
                    //初始化数据
                    // the labels that should be drawn on the XAxis
                    xValues.add(CommonUtil.stampToStr(waterAnalysisYearBean.getObject().get(i).getDate()).substring(0,7));
                    yValues.add(new Entry(i, waterAnalysisYearBean.getObject().get(i).getDailyData()));
                    y2Values.add(new Entry(i, waterAnalysisYearBean.getObject().get(i).getDesignData()));
                }
                //TODO 绘制曲线
                chart.setVisibility(View.VISIBLE);chart.setVisibility(View.VISIBLE);
                LineDataSet setComp1 = new LineDataSet(yValues, "水量值");
                setComp1.setAxisDependency(YAxis.AxisDependency.LEFT);
                setComp1.setColor(Color.BLUE);
                LineDataSet setComp2 = new LineDataSet(y2Values, "设计值");
                setComp2.setAxisDependency(YAxis.AxisDependency.LEFT);
                setComp2.setColor(Color.RED);
                IAxisValueFormatter formatter = new IAxisValueFormatter() {
                    @Override
                    public String getFormattedValue(float value, AxisBase axis) {
                        return xValues.get((int) value);
                    }
                };
                XAxis xAxis = chart.getXAxis();
                xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
                xAxis.setValueFormatter(formatter);
                xAxis.setTextSize(10f);
                xAxis.setTextColor(Color.BLACK);
                xAxis.setDrawAxisLine(true);
                xAxis.setDrawGridLines(false);

                YAxis yAxis = chart.getAxisLeft();
                yAxis.setTextSize(10f); // set the text size
//                yAxis.setAxisMinimum(0f); // start at zero
//                yAxis.setAxisMaximum(100f); // the axis maximum is 100
                yAxis.setTextColor(Color.BLACK);
                yAxis.setGranularity(1f); // interval 1
                yAxis.setLabelCount(6, true); // force 6 labels
                YAxis yAxisR = chart.getAxisRight();
                yAxisR.setEnabled(false);

                List<ILineDataSet> dataSets = new ArrayList<>();
                dataSets.add(setComp1);
                dataSets.add(setComp2);
//                dataSets.setColor(...);
//                dataSets.setValueTextColor(...);

                LineData data = new LineData(dataSets);
                chart.setPinchZoom(true);
                chart.setData(data);
                chart.invalidate(); // refresh
            }else {
                chart.setVisibility(View.INVISIBLE);
                toastShow("无数据");
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
                if(btnCheckDate.getText().toString()!=null&&!btnCheckDate.getText().toString().contains("请选择时间")){
//                  Date startDate = CommonUtil.String2Date(btnDeviceSetupDate.getText().toString().concat(".000"));
                    Log.d("WaterAnalysisActivity","startTime:"+btnCheckDate.getText().toString().substring(0,4));
                    Log.d("WaterAnalysisActivity","sewageId:"+sewageId);
                    if(sewageId>0){
                        mvpPresenter.getWaterAnalysisYear(btnCheckDate.getText().toString().substring(0,4),sewageId);
                    }else{
                        toastShow("请选择站点");
                    }
                }else{
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