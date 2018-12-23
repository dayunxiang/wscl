package com.tmxk.wscl.android.ui.device;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tmxk.wscl.android.R;
import com.tmxk.wscl.android.adpter.DeviceDocListAdapter;
import com.tmxk.wscl.android.adpter.GirdDropDownAdapter;
import com.tmxk.wscl.android.emuns.DataTypeEnum;
import com.tmxk.wscl.android.mvp.model.AreaListBean;
import com.tmxk.wscl.android.mvp.model.SewageListBean;
import com.tmxk.wscl.android.mvp.model.SiteDeviceDocBean;
import com.tmxk.wscl.android.mvp.model.SiteDeviceDocListBean;
import com.tmxk.wscl.android.mvp.model.UserLoginLogBean;
import com.tmxk.wscl.android.mvp.model.UserLoginLogListBean;
import com.tmxk.wscl.android.mvp.presenter.DeviceDocPresenter;
import com.tmxk.wscl.android.mvp.view.SewageArchiveView;
import com.tmxk.wscl.android.ui.base.MvpActivity;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeviceDocActivity extends MvpActivity<DeviceDocPresenter> implements SewageArchiveView {
    @BindView(R.id.dropDownMenu)
    DropDownMenu mDropDownMenu;
    private String headers[] = {};
    private List<View> popupViews = new ArrayList<>();

    private GirdDropDownAdapter regionAdapter;
    private GirdDropDownAdapter stationAdapter;
    private List<String> regions = new ArrayList<>();
    private List<String> sewages = new ArrayList<>();
    ListView regionView;
    ListView stationView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.deviceRefreshLayout)
    RefreshLayout refreshLayout;
    private DeviceDocListAdapter deviceDocListAdapter;
    private ImageView imgRight;
    private static final int DEVICE_MANAGE_ADD_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_device_doc);
        ButterKnife.bind(this);
        StatusBarUtil.setColorNoTranslucent(this, getResources().getColor(R.color.primary));
        initView();
    }

    @Override
    protected DeviceDocPresenter createPresenter() {
        return new DeviceDocPresenter(this);
    }

    private void initView() {
        //init toolbar
        toolbar.setTitle("设备档案");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        //init region menu
        regionView = new ListView(this);
        stationView = new ListView(this);
        //获取区域节点
        mvpPresenter.getAllAreas();
        imgRight = toolbar.findViewById(R.id.imgRight);
        //长按删除
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showDelDialog(position);
                return true;
            }
        });
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
        if (object instanceof SiteDeviceDocListBean) {
            Log.d("DeviceDocActivity","SiteDeviceDocListBean "+object.toString());
            if (deviceDocListAdapter != null) {
                List<SiteDeviceDocBean> siteDeviceDocBeans = deviceDocListAdapter.getList();
                siteDeviceDocBeans.addAll(((SiteDeviceDocListBean) object).getObject());
                deviceDocListAdapter.setList(siteDeviceDocBeans);
                deviceDocListAdapter.notifyDataSetChanged();
            } else {
                deviceDocListAdapter = new DeviceDocListAdapter(this, ((SiteDeviceDocListBean) object).getObject());
                listView.setAdapter(deviceDocListAdapter);
            }
        }else
        if(object instanceof AreaListBean){
            Log.d("SewageArchiveActivity","AreaListBean "+object.toString());
            AreaListBean areaListBean = (AreaListBean) object;
            //init region menu
            final List<Integer> regionIds = new ArrayList<>();
            if(areaListBean!=null&&areaListBean.getObject().size()>0){
                for(AreaListBean.AreaBean areaBean:areaListBean.getObject()){
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
            headers = new String[]{"请选择区县","请选择站点"};

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
                    Log.d("SewageArchiveActivity","onItemClick "+position);
                    Log.d("SewageArchiveActivity","onItemClick-regions "+regions.get(position));
                    regionAdapter.setCheckItem(position);
                    mDropDownMenu.setTabText(regions.get(position));
                    //根据区域获取站点
                    mvpPresenter.getSewages(true,regionIds.get(position));
                    mDropDownMenu.closeMenu();
                }
            });
        }else if(object instanceof SewageListBean){
            final SewageListBean sewageListBean = (SewageListBean) object;
            Log.d("SewageArchiveActivity","SewageListBean "+object.toString());
            if(sewageListBean!=null&&sewageListBean.getObject().size()>0){
                sewages.clear();
                for(SewageListBean.ObjectBean sewageBean:sewageListBean.getObject()){
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
                    final int sewageId=sewageListBean.getObject().get(position).getId();

                    //smart
                    refreshLayout.setEnableLoadMore(true);
                    refreshLayout.setEnableRefresh(true);
                    refreshLayout.setOnRefreshListener(new OnRefreshListener() {
                        @Override
                        public void onRefresh(@android.support.annotation.NonNull RefreshLayout refreshLayout) {
                            deviceDocListAdapter = null;
                            mvpPresenter.getDeviceDocs(true,sewageId);
                            refreshLayout.finishRefresh();
                        }
                    });
                    refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
                        @Override
                        public void onLoadMore(@android.support.annotation.NonNull RefreshLayout refreshLayout) {
                            mvpPresenter.getDeviceDocs(false,sewageId);
                            refreshLayout.finishLoadMore();
                        }
                    });
                    refreshLayout.autoRefresh();

                    mDropDownMenu.closeMenu();
                    imgRight.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.setClass(DeviceDocActivity.this, DeviceDocCreateActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putBoolean("addDevice", true);
                            bundle.putInt("sewageId",sewageId);
                            intent.putExtras(bundle);
                            startActivityForResult(intent, DEVICE_MANAGE_ADD_REQUEST_CODE);
                        }
                    });
                }
            });
        }
    }

    @Override
    public void getDataFail(String msg) {
        toastShow(msg);
    }

    private void showDelDialog(final int position) {
        if (deviceDocListAdapter == null) {
            return;
        }
        final SiteDeviceDocBean deviceBean = ((SiteDeviceDocBean) deviceDocListAdapter.getItem(position));
        if (deviceBean != null) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert inflater != null;
            @SuppressLint("InflateParams")
            View contentView = inflater.inflate(R.layout.dialog_del, null);
            final PopupWindow popupWindow = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            popupWindow.setOutsideTouchable(true);
            popupWindow.setTouchable(true);
            popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
            ((TextView) contentView.findViewById(R.id.tv_tips)).setText("设备".concat(deviceBean.getDeviceName()).concat("删除后不可恢复"));
            contentView.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                    mvpPresenter.delDeviceDocById(deviceBean.getId());
                }
            });
            contentView.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                }
            });
            if (!popupWindow.isShowing()) {
                popupWindow.showAtLocation(findViewById(R.id.deviceParentView), Gravity.CENTER, 0, 0);
            } else {
                popupWindow.dismiss();
            }
        }
    }

    @Override
    public void onRefresh() {
        refreshLayout.autoRefresh();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        Log.d("DeviceDocActivity","onActivityResult-requestCode:"+requestCode);
        Log.d("DeviceDocActivity","onActivityResult-resultCode:"+resultCode);
        if (requestCode == DEVICE_MANAGE_ADD_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (intent.hasExtra("hasAddDevice")) {
                deviceDocListAdapter = null;
                Log.d("DeviceDocActivity","onActivityResult: refresh");
                refreshLayout.autoRefresh();
            }
        }
    }
}