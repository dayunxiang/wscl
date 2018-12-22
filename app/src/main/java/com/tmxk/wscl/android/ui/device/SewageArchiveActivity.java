package com.tmxk.wscl.android.ui.device;

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

import com.jaeger.library.StatusBarUtil;
import com.tmxk.wscl.android.R;
import com.tmxk.wscl.android.adpter.GirdDropDownAdapter;
import com.tmxk.wscl.android.adpter.SewageArchiveListAdapter;
import com.tmxk.wscl.android.emuns.DataTypeEnum;
import com.tmxk.wscl.android.mvp.model.AreaListBean;
import com.tmxk.wscl.android.mvp.model.ControlMethodBean;
import com.tmxk.wscl.android.mvp.model.SewageListBean;
import com.tmxk.wscl.android.mvp.presenter.SewageArchivePresenter;
import com.tmxk.wscl.android.mvp.view.SewageArchiveView;
import com.tmxk.wscl.android.ui.base.MvpActivity;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SewageArchiveActivity extends MvpActivity<SewageArchivePresenter> implements SewageArchiveView {
    @BindView(R.id.dropDownMenu)
    DropDownMenu mDropDownMenu;
    private String headers[] = {};
    private List<View> popupViews = new ArrayList<>();

    private GirdDropDownAdapter regionAdapter;
    private GirdDropDownAdapter stationAdapter;

//    private String regions[] = {"不限", "惠山区"};
    private List<String> regions = new ArrayList<>();
    private List<String> sewages = new ArrayList<>();
    ListView stationView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.sewageName)
    TextView sewageName;
    @BindView(R.id.sewageShortName)
    TextView sewageShortName;
    @BindView(R.id.sewageOperator)
    TextView sewageOperator;
    @BindView(R.id.sewageControlId)
    TextView sewageControlId;
    @BindView(R.id.sewageOperateNo)
    TextView sewageOperateNo;
    @BindView(R.id.sewageTonnage)
    TextView sewageTonnage;
    @BindView(R.id.sewageControlMethod)
    TextView sewageControlMethod;
    @BindView(R.id.sewageEmissionStandard)
    TextView sewageEmissionStandard;
    private SewageArchiveListAdapter sewageArchiveListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_sewage_manage);
        ButterKnife.bind(this);
        StatusBarUtil.setColorNoTranslucent(this, getResources().getColor(R.color.primary));
        initView();
    }

    @Override
    protected SewageArchivePresenter createPresenter() {
        return new SewageArchivePresenter(this);
    }

    private void initView() {
        //init toolbar
        toolbar.setTitle("站点档案");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        //init station menu
        stationView = new ListView(this);
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
//        if (object instanceof SewageListBean) {
//            if (sewageArchiveListAdapter != null) {
//                sewageArchiveListAdapter.notifyDataSetChanged();
//            } else {
//                sewageArchiveListAdapter = new SewageArchiveListAdapter(this, ((SewageListBean) object).getObject());
//                listView.setAdapter(sewageArchiveListAdapter);
//            }
//        }
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
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    stationAdapter.setCheckItem(position);
                    mDropDownMenu.setTabText(sewages.get(position));
                    List<ControlMethodBean> controlMethodBeans = new ArrayList<>();
                    controlMethodBeans.add(new ControlMethodBean(1,"AOF工艺"));
                    controlMethodBeans.add(new ControlMethodBean(2,"AO+人工湿地"));
                    controlMethodBeans.add(new ControlMethodBean(3,"AO工艺"));
                    controlMethodBeans.add(new ControlMethodBean(4,"MBR工艺"));
                    controlMethodBeans.add(new ControlMethodBean(5,"SBR工艺"));
                    controlMethodBeans.add(new ControlMethodBean(6,"跌水充氧接触氧化+人工湿地工艺"));
                    controlMethodBeans.add(new ControlMethodBean(7,"复合生物滤床+人工湿地工艺"));
                    controlMethodBeans.add(new ControlMethodBean(8,"复合塔式生态滤池+人工湿地工艺"));
                    controlMethodBeans.add(new ControlMethodBean(9,"高负荷地下渗滤工艺"));
                    controlMethodBeans.add(new ControlMethodBean(10,"海沃特工艺"));
                    controlMethodBeans.add(new ControlMethodBean(11,"接触氧化+人工湿地工艺"));
                    controlMethodBeans.add(new ControlMethodBean(12,"脉冲多层复合滤池+人工湿地工艺"));
                    controlMethodBeans.add(new ControlMethodBean(13,"厌氧滤池+好氧流化床工艺"));
                    controlMethodBeans.add(new ControlMethodBean(14,"转笼式生物膜工艺"));

                    sewageName.setText("站点名称："+sewageListBean.getObject().get(position).getName());
                    sewageShortName.setText("简称："+sewageListBean.getObject().get(position).getShortTitle());
                    sewageOperator.setText("管理员："+sewageListBean.getObject().get(position).getAdministrator().getName());
                    sewageControlId.setText("控制ID："+sewageListBean.getObject().get(position).getControlId()+"");
                    sewageOperateNo.setText("运营编号："+sewageListBean.getObject().get(position).getOperationNum());
                    sewageTonnage.setText("吨位："+sewageListBean.getObject().get(position).getTonnage()+" 吨");
                    sewageControlMethod.setText("处理工艺："+controlMethodBeans.get(sewageListBean.getObject().get(position).getControlMethod()).getName());
                    sewageEmissionStandard.setText("排放标准："+sewageListBean.getObject().get(position).getEmissionStandard());
                    mDropDownMenu.closeMenu();
                }
            });
        }
    }

    @Override
    public void getDataFail(String msg) {
        toastShow(msg);
    }

    @Override
    public void onRefresh() {}
}