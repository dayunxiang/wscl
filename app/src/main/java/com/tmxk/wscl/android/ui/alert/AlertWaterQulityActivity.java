package com.tmxk.wscl.android.ui.alert;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Window;

import com.bin.david.form.core.SmartTable;
import com.jaeger.library.StatusBarUtil;
import com.tmxk.wscl.android.R;
import com.tmxk.wscl.android.emuns.DataTypeEnum;
import com.tmxk.wscl.android.mvp.model.TableWaterQuality;
import com.tmxk.wscl.android.mvp.model.WaterQualityStatusBean;
import com.tmxk.wscl.android.mvp.presenter.AlertPresenter;
import com.tmxk.wscl.android.mvp.view.SewageArchiveView;
import com.tmxk.wscl.android.ui.base.MvpActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlertWaterQulityActivity extends MvpActivity<AlertPresenter> implements SewageArchiveView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private SmartTable table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_alert_water_quality);
        ButterKnife.bind(this);
        StatusBarUtil.setColorNoTranslucent(this, getResources().getColor(R.color.primary));
        initView();
    }

    @Override
    protected AlertPresenter createPresenter() {
        return new AlertPresenter(this);
    }

    private void initView() {
        //init toolbar
        toolbar.setTitle("水质异常");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        table = (SmartTable) findViewById(R.id.table);
        // 请求水质异常
        mvpPresenter.getAlertWaterQualityStatus();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void getDataSuccess(Object object, DataTypeEnum dataTypeEnum) {
        if (object instanceof WaterQualityStatusBean) {
            Log.d("AlertWaterActiviy", "WaterQualityStatusBean " + object.toString());
            WaterQualityStatusBean waterQualityStatusBean = (WaterQualityStatusBean) object;
            //init region menu
            List<TableWaterQuality> tableWaterQualities = new ArrayList<>();
            int sortNum=0;
            if (waterQualityStatusBean != null && waterQualityStatusBean.getObject().size() > 0) {
                for (WaterQualityStatusBean.ObjectBean a : waterQualityStatusBean.getObject()) {
                    if(a!=null&&a.getQualityInfo()!=null&&a.getQualityInfo().size()>0){
                       for(WaterQualityStatusBean.ObjectBean.QualityInfoBean q:a.getQualityInfo()){
                           if(q!=null&&q.getName()!=null){
                               TableWaterQuality t = new TableWaterQuality();
                               t.setSortNum(++sortNum);
                               t.setContrlId(a.getSewageID());
                               t.setParamName(q.getName());
                               t.setParamVal(q.getVal());
                               t.setOperatrNum(a.getOperationNum());
                               t.setShortTitle(a.getShortTitle());
                               tableWaterQualities.add(t);
                           }
                       }
                    }
                }
            }else {
                toastShow("无数据");
            }
            table.getConfig().setShowXSequence(false);
            table.getConfig().setShowYSequence(false);
            table.setZoom(true);
            table.setData(tableWaterQualities);
        }
    }

    @Override
    public void getDataFail(String msg) {
        toastShow(msg);
    }

    @Override
    public void onRefresh() {
    }
}