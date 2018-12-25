package com.tmxk.wscl.android.ui.alert;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Window;

import com.bin.david.form.core.SmartTable;
import com.jaeger.library.StatusBarUtil;
import com.tmxk.wscl.android.R;
import com.tmxk.wscl.android.emuns.DataTypeEnum;
import com.tmxk.wscl.android.mvp.model.PowerOffBean;
import com.tmxk.wscl.android.mvp.model.TablePowerOff;
import com.tmxk.wscl.android.mvp.model.TableWaterQuality;
import com.tmxk.wscl.android.mvp.model.WaterQualityStatusBean;
import com.tmxk.wscl.android.mvp.presenter.AlertPresenter;
import com.tmxk.wscl.android.mvp.view.SewageArchiveView;
import com.tmxk.wscl.android.ui.base.MvpActivity;
import com.tmxk.wscl.android.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlertPowerOffActivity extends MvpActivity<AlertPresenter> implements SewageArchiveView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private SmartTable table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_alert_power_off);
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
        toolbar.setTitle("断电断线");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        table = (SmartTable) findViewById(R.id.table);
        // 请求断电断线
        mvpPresenter.getAlertPowerOff();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void getDataSuccess(Object object, DataTypeEnum dataTypeEnum) {
        if (object instanceof PowerOffBean) {
            Log.d("AlertPowerOffActiviy", "PowerOffBean " + object.toString());
            PowerOffBean powerOffBean = (PowerOffBean) object;
            //init region menu
            List<TablePowerOff> tablePowerOffs = new ArrayList<>();
            int sortNum=0;
            if (powerOffBean != null && powerOffBean.getObject().size() > 0) {
                for (PowerOffBean.ObjectBean a : powerOffBean.getObject()) {
                    if(a!=null){
                        TablePowerOff t = new TablePowerOff();
                        t.setSortNum(++sortNum);
                        t.setContrlId(a.getSewageID());
                        t.setOperatrNum(a.getOperationNum());
                        t.setShortTitle(a.getShortTitle());
                        long current = System.currentTimeMillis();
                        long zero = current/(1000*3600*24)*(1000*3600*24) - TimeZone.getDefault().getRawOffset();
                        t.setOffTime(CommonUtil.stampToDate(String.valueOf(zero)));
                        tablePowerOffs.add(t);
                    }
                }
            }else {
                toastShow("无数据");
            }
            table.getConfig().setShowXSequence(false);
            table.getConfig().setShowYSequence(false);
            table.setZoom(true);
            table.setData(tablePowerOffs);
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