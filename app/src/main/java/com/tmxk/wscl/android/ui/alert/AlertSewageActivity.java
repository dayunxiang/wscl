package com.tmxk.wscl.android.ui.alert;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.bin.david.form.core.SmartTable;
import com.jaeger.library.StatusBarUtil;
import com.tmxk.wscl.android.R;
import com.tmxk.wscl.android.adpter.GirdDropDownAdapter;
import com.tmxk.wscl.android.emuns.DataTypeEnum;
import com.tmxk.wscl.android.mvp.model.AlertEquipStatusBean;
import com.tmxk.wscl.android.mvp.model.AreaListBean;
import com.tmxk.wscl.android.mvp.model.SewageListBean;
import com.tmxk.wscl.android.mvp.model.SewageMonitorBean;
import com.tmxk.wscl.android.mvp.model.TableEquipStatus;
import com.tmxk.wscl.android.mvp.presenter.AlertPresenter;
import com.tmxk.wscl.android.mvp.view.SewageArchiveView;
import com.tmxk.wscl.android.ui.base.MvpActivity;
import com.tmxk.wscl.android.util.CommonUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlertSewageActivity extends MvpActivity<AlertPresenter> implements SewageArchiveView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private SmartTable table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_alert_sewage);
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
        toolbar.setTitle("设备故障");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        table = (SmartTable) findViewById(R.id.table);
        // 请求告警状态
        mvpPresenter.getAlertEquipmentStatus();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void getDataSuccess(Object object, DataTypeEnum dataTypeEnum) {
        if (object instanceof AlertEquipStatusBean) {
            Log.d("AlertSewageActiviy", "AlertEquipStatusBean " + object.toString());
            AlertEquipStatusBean alertEquipStatusBean = (AlertEquipStatusBean) object;
            //init region menu
            List<TableEquipStatus> tableEquipStatuses = new ArrayList<>();
            int sortNum=0;
            if (alertEquipStatusBean != null && alertEquipStatusBean.getObject().size() > 0) {
                for (AlertEquipStatusBean.ObjectBean a : alertEquipStatusBean.getObject()) {
                    if(a!=null&&a.getEquipName()!=null&&a.getEquipName().size()>0){
                       for(String s:a.getEquipName()){
                           if(s!=null&&s.contains("故障")){
                               TableEquipStatus t = new TableEquipStatus();
                               t.setSortNum(++sortNum);
                               t.setContrlId(a.getSewageID());
                               t.setEquitStatus(s);
                               t.setOperatrNum(a.getOperationNum());
                               t.setShortTitle(a.getShortTitle());
                               tableEquipStatuses.add(t);
                           }
                       }
                    }
                }
            }
            table.getConfig().setShowXSequence(false);
            table.getConfig().setShowYSequence(false);
            table.setZoom(true);
            table.setData(tableEquipStatuses);
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