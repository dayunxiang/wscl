package com.tmxk.wscl.android.ui.water.testManager;

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
import android.widget.ListView;
import android.widget.TextView;

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
import com.tmxk.wscl.android.mvp.model.WaterTestListBean;
import com.tmxk.wscl.android.mvp.presenter.OperatePresenter;
import com.tmxk.wscl.android.mvp.view.SewageArchiveView;
import com.tmxk.wscl.android.ui.base.MvpActivity;
import com.tmxk.wscl.android.util.CommonUtil;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import butterknife.ButterKnife;

public class WaterTestHistoryTop12Activity extends MvpActivity<OperatePresenter> implements SewageArchiveView {
    private String headers[] = {};
    private List<View> popupViews = new ArrayList<>();
    private GirdDropDownAdapter regionAdapter;
    private GirdDropDownAdapter stationAdapter;
    private List<String> regions = new ArrayList<>();
    private List<String> sewages = new ArrayList<>();
    private int sewageId;
    ListView regionView;
    ListView stationView;
    private Toolbar toolbar;
    private DropDownMenu mDropDownMenu;
    private Button btnOk;
    private LineChart chartCod;
    private LineChart chartNh3n;
    private LineChart chartP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_water_test_history);
        ButterKnife.bind(this);
        StatusBarUtil.setColorNoTranslucent(this, getResources().getColor(R.color.primary));
        initView();
        //init toolbar
        toolbar.setTitle("历史数据绘图");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        //init region menu
        regionView = new ListView(this);
        stationView = new ListView(this);
        //获取区域节点
        mvpPresenter.getAllAreas();
    }

    @Override
    protected OperatePresenter createPresenter() {
        return new OperatePresenter(this);
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mDropDownMenu = (DropDownMenu) findViewById(R.id.dropDownMenu);
        btnOk = (Button) findViewById(R.id.btnOk);
        chartCod = (LineChart) findViewById(R.id.chartCod);
        chartNh3n = (LineChart) findViewById(R.id.chartNh3n);
        chartP = (LineChart) findViewById(R.id.chartP);
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
        } else if (object instanceof WaterTestListBean) {
            WaterTestListBean waterTestListBean = (WaterTestListBean) object;
            Log.d("WaterTestActivity", "waterTestListBean " + object.toString());
            Log.d("WaterTestActivity", "waterTestListBean.size " + waterTestListBean.getObject().size());
            if (waterTestListBean != null && waterTestListBean.getObject().size() > 0) {
                Log.d("WaterTestActivity", "size>0");
                final ArrayList<String> xValues = new ArrayList<>();
                List<Entry> inCodValues = new ArrayList<>();
                List<Entry> outCodValues = new ArrayList<>();
                List<Entry> inNh3nValues = new ArrayList<>();
                List<Entry> outNh3nValues = new ArrayList<>();
                List<Entry> inPValues = new ArrayList<>();
                List<Entry> outPValues = new ArrayList<>();
                for (int i = 0; i < waterTestListBean.getObject().size(); i++) {
                    //初始化数据
                    // the labels that should be drawn on the XAxis
                    xValues.add(CommonUtil.stampToStr(waterTestListBean.getObject().get(i).getTestingTime()).substring(0, 7));
                    inCodValues.add(new Entry(i, waterTestListBean.getObject().get(i).getInCod()));
                    outCodValues.add(new Entry(i, waterTestListBean.getObject().get(i).getOutCod()));
                    inNh3nValues.add(new Entry(i, waterTestListBean.getObject().get(i).getInNh3n()));
                    outNh3nValues.add(new Entry(i, waterTestListBean.getObject().get(i).getOutNh3n()));
                    inPValues.add(new Entry(i, waterTestListBean.getObject().get(i).getInP()));
                    outPValues.add(new Entry(i, waterTestListBean.getObject().get(i).getOutP()));
                }
                //绘制曲线Cod
                drawPain(chartCod, xValues, inCodValues, outCodValues, "进水水质COD", "出水水质COD",waterTestListBean.getObject().size());
                //绘制曲线Nh3n
                drawPain(chartNh3n, xValues, inNh3nValues, outNh3nValues, "进水水质NH3N", "出水水质NH3N",waterTestListBean.getObject().size());
                //绘制曲线P
                drawPain(chartP, xValues, inPValues, outPValues, "进水水质P", "出水水质P",waterTestListBean.getObject().size());

            } else {
                chartCod.setVisibility(View.INVISIBLE);
                chartNh3n.setVisibility(View.INVISIBLE);
                chartP.setVisibility(View.INVISIBLE);
                toastShow("无数据");
            }
        }
    }

    private void drawPain(LineChart chart, final ArrayList<String> xValues, List<Entry> y1Values,
                          List<Entry> y2Values, String y1Lable, String Y2Lable, int labelNum){
        //TODO 绘制曲线
        chart.setVisibility(View.VISIBLE);
        LineDataSet setComp1 = new LineDataSet(y1Values, y1Lable);
        setComp1.setAxisDependency(YAxis.AxisDependency.LEFT);
        setComp1.setColor(Color.RED);
        LineDataSet setComp2 = new LineDataSet(y2Values, Y2Lable);
        setComp2.setAxisDependency(YAxis.AxisDependency.LEFT);
        setComp2.setColor(Color.BLUE);
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
        yAxis.setTextColor(Color.BLACK);
        yAxis.setGranularity(1f); // interval 1
        yAxis.setLabelCount(labelNum, true); // force 6 labels
        YAxis yAxisR = chart.getAxisRight();
        yAxisR.setEnabled(false);

        List<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(setComp1);
        dataSets.add(setComp2);
        LineData data = new LineData(dataSets);
        chart.setPinchZoom(true);
        chart.setData(data);
        chart.invalidate(); // refresh
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
                if (sewageId > 0) {
                    mvpPresenter.getWaterTestTop12(sewageId);
                } else {
                    toastShow("请选择站点");
                }
                break;
        }
    }
}