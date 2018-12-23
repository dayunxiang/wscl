package com.tmxk.wscl.android.ui.device;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import com.jaeger.library.StatusBarUtil;
import com.tmxk.wscl.android.R;
import com.tmxk.wscl.android.emuns.DataTypeEnum;
import com.tmxk.wscl.android.mvp.model.AdminListBean;
import com.tmxk.wscl.android.mvp.model.AreaListBean;
import com.tmxk.wscl.android.mvp.model.SewageListBean;
import com.tmxk.wscl.android.mvp.presenter.DeviceDocPresenter;
import com.tmxk.wscl.android.mvp.view.SewageArchiveView;
import com.tmxk.wscl.android.ui.base.MvpActivity;
import com.tmxk.wscl.android.util.Constant;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;

/**
 * Created by wy on 18/12/21.
 * device doc create
 */
public class SewageCreateActivity extends MvpActivity<DeviceDocPresenter> implements SewageArchiveView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private int areaId;
    private int controlMethod;
    private int adminId;
    private SewageListBean.ObjectBean sewageBean = new SewageListBean.ObjectBean();
    private boolean isAddSewage = false;
    private NiceSpinner niceSpinnerArea;
    private NiceSpinner niceSpinnerAdmin;
    private EditText edtSewageName;
    private EditText edtSewageShortTitle;
    private EditText edtSewageOperateNum;
    private NiceSpinner niceSpinnerControlMethod;
    private EditText edtSewageControlId;
    private EditText edtSewageX;
    private EditText edtSewageY;
    private EditText edtTempUp;
    private EditText edtTempDown;
    private EditText edtPhUp;
    private EditText edtPhDown;
    private EditText edtOrpUp;
    private EditText edtOrpDown;
    private EditText edtDoUp;
    private EditText edtDoDown;
    private EditText edtCodUp;
    private EditText edtCodDown;
    private EditText edtNh3nUp;
    private EditText edtNh3nDown;
    private EditText edtTpUp;
    private EditText edtTpDown;
    private EditText edtCod;
    private EditText edtNh3n;
    private EditText edtP;
    private EditText edtVideoPuid;
    private EditText edtTounge;
    private EditText edtEmperStand;
    private EditText edtDevicePower1;
    private EditText edtDevicePower2;
    private EditText edtDevicePower3;
    private EditText edtDevicePower4;
    private EditText edtDevicePower5;
    private EditText edtSsUp;
    private EditText edtSsDown;
    private EditText etdSewageId;
    private Button btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_sewage_create);
        StatusBarUtil.setColorNoTranslucent(this, getResources().getColor(R.color.primary));
        toolbar.setTitle("新建站点");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        initView();
        initData();
    }

    private void initView() {
        niceSpinnerArea = (NiceSpinner) findViewById(R.id.nice_spinner_area);
        niceSpinnerAdmin = (NiceSpinner) findViewById(R.id.nice_spinner_admin);
        edtSewageName = (EditText) findViewById(R.id.edtSewageName);
        edtSewageShortTitle = (EditText) findViewById(R.id.edtSewageShortTitle);
        edtSewageOperateNum = (EditText) findViewById(R.id.edtSewageOperateNum);
        niceSpinnerControlMethod = (NiceSpinner) findViewById(R.id.nice_spinner_control_method);
        edtSewageControlId = (EditText) findViewById(R.id.edtSewageControlId);
        edtSewageX = (EditText) findViewById(R.id.edtSewageX);
        edtSewageY = (EditText) findViewById(R.id.edtSewageY);
        edtTempUp = (EditText) findViewById(R.id.edtTempUp);
        edtTempDown = (EditText) findViewById(R.id.edtTempDown);
        edtPhUp = (EditText) findViewById(R.id.edtPhUp);
        edtPhDown = (EditText) findViewById(R.id.edtPhDown);
        edtOrpUp = (EditText) findViewById(R.id.edtOrpUp);
        edtOrpDown = (EditText) findViewById(R.id.edtOrpDown);
        edtDoUp = (EditText) findViewById(R.id.edtDoUp);
        edtDoDown = (EditText) findViewById(R.id.edtDoDown);
        edtCodUp = (EditText) findViewById(R.id.edtCodUp);
        edtCodDown = (EditText) findViewById(R.id.edtCodDown);
        edtNh3nUp = (EditText) findViewById(R.id.edtNh3nUp);
        edtNh3nDown = (EditText) findViewById(R.id.edtNh3nDown);
        edtTpUp = (EditText) findViewById(R.id.edtTpUp);
        edtTpDown = (EditText) findViewById(R.id.edtTpDown);
        edtCod = (EditText) findViewById(R.id.edtCod);
        edtNh3n = (EditText) findViewById(R.id.edtNh3n);
        edtP = (EditText) findViewById(R.id.edtP);
        edtVideoPuid = (EditText) findViewById(R.id.edtVideoPuid);
        edtTounge = (EditText) findViewById(R.id.edtTounge);
        edtEmperStand = (EditText) findViewById(R.id.edtEmperStand);
        edtDevicePower1 = (EditText) findViewById(R.id.edtDevicePower1);
        edtDevicePower2 = (EditText) findViewById(R.id.edtDevicePower2);
        edtDevicePower3 = (EditText) findViewById(R.id.edtDevicePower3);
        edtDevicePower4 = (EditText) findViewById(R.id.edtDevicePower4);
        edtDevicePower5 = (EditText) findViewById(R.id.edtDevicePower5);
        edtSsUp = (EditText) findViewById(R.id.edtSsUp);
        edtSsDown = (EditText) findViewById(R.id.edtSsDown);
        etdSewageId = (EditText) findViewById(R.id.edtSewageId);
        btnOk = (Button) findViewById(R.id.btnOk);
    }

    private void initData() {
        isAddSewage = true;
        mvpPresenter.getAllAreas();
        mvpPresenter.getAdminList();
        List<String> controlMethodBeans = new ArrayList<>();
        controlMethodBeans.add("请选择工艺");
        controlMethodBeans.add("AOF工艺");
        controlMethodBeans.add("AO+人工湿地");
        controlMethodBeans.add("AO工艺");
        controlMethodBeans.add("MBR工艺");
        controlMethodBeans.add("SBR工艺");
        controlMethodBeans.add("跌水充氧接触氧化+人工湿地工艺");
        controlMethodBeans.add("复合生物滤床+人工湿地工艺");
        controlMethodBeans.add("复合塔式生态滤池+人工湿地工艺");
        controlMethodBeans.add("高负荷地下渗滤工艺");
        controlMethodBeans.add("海沃特工艺");
        controlMethodBeans.add("接触氧化+人工湿地工艺");
        controlMethodBeans.add("脉冲多层复合滤池+人工湿地工艺");
        controlMethodBeans.add("厌氧滤池+好氧流化床工艺");
        controlMethodBeans.add("转笼式生物膜工艺");

        niceSpinnerControlMethod.attachDataSource(controlMethodBeans);

        niceSpinnerControlMethod.addOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                controlMethod = position;
            }
        });
    }

    @Override
    protected DeviceDocPresenter createPresenter() {
        return new DeviceDocPresenter(this);
    }

    @Override
    public void getDataSuccess(Object object, DataTypeEnum dataTypeEnum) {
        if(object instanceof AreaListBean){
            Log.d("SewageArchiveActivity","AreaListBean "+object.toString());
            AreaListBean areaListBean = (AreaListBean) object;
            List<String> regions = new ArrayList<>();
            regions.add("请选择区县");
            final List<Integer> regionIds = new ArrayList<>();
            regionIds.add(0);
            if(areaListBean!=null&&areaListBean.getObject().size()>0){
                for(AreaListBean.AreaBean areaBean:areaListBean.getObject()){
                    regions.add(areaBean.getName());
                    regionIds.add(areaBean.getId());
                }
            }
            niceSpinnerArea.attachDataSource(regions);

            niceSpinnerArea.addOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    areaId = regionIds.get(position);
                }
            });
        }else if(object instanceof AdminListBean){
            Log.d("SewageArchiveActivity","AdminListBean "+object.toString());
            AdminListBean adminListBean = (AdminListBean) object;
            List<String> admins = new ArrayList<>();
            admins.add("请选择管理员");
            final List<Integer> adminIds = new ArrayList<>();
            adminIds.add(0);
            if(adminListBean!=null&&adminListBean.getObject().size()>0){
                for(AdminListBean.ObjectBean adminBean:adminListBean.getObject()){
                    admins.add(adminBean.getName());
                    adminIds.add(adminBean.getId());
                }
            }
            niceSpinnerAdmin.attachDataSource(admins);

            niceSpinnerAdmin.addOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    adminId = adminIds.get(position);
                }
            });
        }else{
            toastShow(Constant.CREATE_SUCCESS);
            setResultIntent();
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
                setResultIntent();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnOk:
                if(!("请选择区县").equals(niceSpinnerArea.getText().toString())){
                    sewageBean.setArea(new SewageListBean.ObjectBean.AreaBean(areaId));
                }else{
                    toastShow("请选择区县");
                    break;
                }
                if(!("请选择管理员").equals(niceSpinnerAdmin.getText().toString())){
                    sewageBean.setAdministrator(new SewageListBean.ObjectBean.AdministratorBean(adminId));
                }else{
                    toastShow("请选择管理员");
                    break;
                }
                if(!("请选择工艺").equals(niceSpinnerControlMethod.getText().toString())){
                    sewageBean.setControlMethod(controlMethod);
                }else{
                    toastShow("请选择工艺");
                    break;
                }
                if(edtSewageName.getText()!=null&&!("").equals(edtSewageName.getText().toString().trim())){
                    sewageBean.setName(edtSewageName.getText().toString().trim());
                }else{
                    toastShow("请输入站点名称");
                    break;
                }
                if(edtSewageShortTitle.getText()!=null&&!("").equals(edtSewageShortTitle.getText().toString().trim())){
                    sewageBean.setShortTitle(edtSewageShortTitle.getText().toString().trim());
                }else{
                    sewageBean.setShortTitle("");
                }
                if(etdSewageId.getText()!=null&&!("").equals(etdSewageId.getText().toString().trim())){
                    sewageBean.setId(Integer.parseInt(etdSewageId.getText().toString().trim()));
                }else{
                    toastShow("请输入站点ID");
                    break;
                }
                if(edtSewageOperateNum.getText()!=null&&!("").equals(edtSewageOperateNum.getText().toString().trim())){
                    sewageBean.setOperationNum(edtSewageOperateNum.getText().toString().trim());
                }else{
                    toastShow("请输入运营编号");
                    break;
                }
                if(edtSewageControlId.getText()!=null&&!("").equals(edtSewageControlId.getText().toString().trim())){
                    sewageBean.setControlId(Integer.parseInt(edtSewageControlId.getText().toString().trim()));
                }else{
                    toastShow("请输入控制ID");
                    break;
                }
                if(edtSewageX.getText()!=null&&!("").equals(edtSewageX.getText().toString().trim())){
                    sewageBean.setCoordinateX(Float.parseFloat(edtSewageX.getText().toString().trim()));
                }else{
                    toastShow("请输入纬度");
                    break;
                }
                if(edtSewageY.getText()!=null&&!("").equals(edtSewageY.getText().toString().trim())){
                    sewageBean.setCoordinateY(Float.parseFloat(edtSewageY.getText().toString().trim()));
                }else{
                    toastShow("请输入经度");
                    break;
                }
                if(edtTempUp.getText()!=null&&!("").equals(edtTempUp.getText().toString().trim())){
                    sewageBean.setDetection1UL(Integer.parseInt(edtTempUp.getText().toString().trim()));
                }else{
                    toastShow("请输入温度上限");
                    break;
                }
                if(edtTempDown.getText()!=null&&!("").equals(edtTempDown.getText().toString().trim())){
                    sewageBean.setDetection1DL(Integer.parseInt(edtTempDown.getText().toString().trim()));
                }else{
                    toastShow("请输入温度下限");
                    break;
                }
                if(edtPhUp.getText()!=null&&!("").equals(edtPhUp.getText().toString().trim())){
                    sewageBean.setDetection2UL(Float.parseFloat(edtPhUp.getText().toString().trim()));
                }else{
                    toastShow("请输入PH上限");
                    break;
                }
                if(edtPhDown.getText()!=null&&!("").equals(edtPhDown.getText().toString().trim())){
                    sewageBean.setDetection2DL(Float.parseFloat(edtPhDown.getText().toString().trim()));
                }else{
                    toastShow("请输入PH下限");
                    break;
                }

                if(edtOrpUp.getText()!=null&&!("").equals(edtOrpUp.getText().toString().trim())){
                    sewageBean.setDetection3UL(Integer.parseInt(edtOrpUp.getText().toString().trim()));
                }else{
                    toastShow("请输入ORP上限");
                    break;
                }
                if(edtOrpDown.getText()!=null&&!("").equals(edtOrpDown.getText().toString().trim())){
                    sewageBean.setDetection3DL(Integer.parseInt(edtOrpDown.getText().toString().trim()));
                }else{
                    toastShow("请输入ORP下限");
                    break;
                }

                if(edtDoUp.getText()!=null&&!("").equals(edtDoUp.getText().toString().trim())){
                    sewageBean.setDetection5UL(Integer.parseInt(edtDoUp.getText().toString().trim()));
                }else{
                    toastShow("请输入DO上限");
                    break;
                }
                if(edtDoDown.getText()!=null&&!("").equals(edtDoDown.getText().toString().trim())){
                    sewageBean.setDetection5DL(Integer.parseInt(edtDoDown.getText().toString().trim()));
                }else{
                    toastShow("请输入DO下限");
                    break;
                }

                if(edtCodUp.getText()!=null&&!("").equals(edtCodUp.getText().toString().trim())){
                    sewageBean.setDetection10DL(Integer.parseInt(edtCodUp.getText().toString().trim()));
                }else{
                    toastShow("请输入COD上限");
                    break;
                }
                if(edtCodDown.getText()!=null&&!("").equals(edtCodDown.getText().toString().trim())){
                    sewageBean.setDetection10DL(Integer.parseInt(edtCodDown.getText().toString().trim()));
                }else{
                    toastShow("请输入COD下限");
                    break;
                }

                if(edtNh3nUp.getText()!=null&&!("").equals(edtNh3nUp.getText().toString().trim())){
                    sewageBean.setDetection11DL(Integer.parseInt(edtNh3nUp.getText().toString().trim()));
                }else{
                    toastShow("请输入NH3-N上限");
                    break;
                }
                if(edtNh3nDown.getText()!=null&&!("").equals(edtNh3nDown.getText().toString().trim())){
                    sewageBean.setDetection11DL(Integer.parseInt(edtNh3nDown.getText().toString().trim()));
                }else{
                    toastShow("请输入NH3-N下限");
                    break;
                }

                if(edtTpUp.getText()!=null&&!("").equals(edtTpUp.getText().toString().trim())){
                    sewageBean.setDetection12DL(Integer.parseInt(edtTpUp.getText().toString().trim()));
                }else{
                    toastShow("请输入TP上限");
                    break;
                }
                if(edtTpDown.getText()!=null&&!("").equals(edtTpDown.getText().toString().trim())){
                    sewageBean.setDetection12DL(Integer.parseInt(edtTpDown.getText().toString().trim()));
                }else{
                    toastShow("请输入TP下限");
                    break;
                }

                if(edtSsUp.getText()!=null&&!("").equals(edtSsUp.getText().toString().trim())){
                    sewageBean.setDetection13DL(Integer.parseInt(edtSsUp.getText().toString().trim()));
                }else{
                    toastShow("请输入SS上限");
                    break;
                }
                if(edtSsDown.getText()!=null&&!("").equals(edtSsDown.getText().toString().trim())){
                    sewageBean.setDetection13DL(Integer.parseInt(edtSsDown.getText().toString().trim()));
                }else{
                    toastShow("请输入SS下限");
                    break;
                }
                if(edtCod.getText()!=null&&!("").equals(edtCod.getText().toString().trim())){
                    sewageBean.setReduceCOD(Integer.parseInt(edtCod.getText().toString().trim()));
                }else{
                    toastShow("请输入日消减COD");
                    break;
                }
                if(edtNh3n.getText()!=null&&!("").equals(edtNh3n.getText().toString().trim())){
                    sewageBean.setReduceNH3N(Integer.parseInt(edtNh3n.getText().toString().trim()));
                }else{
                    toastShow("请输入日消减NH3-N");
                    break;
                }
                if(edtP.getText()!=null&&!("").equals(edtP.getText().toString().trim())){
                    sewageBean.setReduceP(Integer.parseInt(edtP.getText().toString().trim()));
                }else{
                    toastShow("请输入日消减P");
                    break;
                }
                if(edtVideoPuid.getText()!=null&&!("").equals(edtVideoPuid.getText().toString().trim())){
                    sewageBean.setVideoPuid(edtVideoPuid.getText().toString().trim());
                }else{
                    sewageBean.setVideoPuid("");
                }
                if(edtTounge.getText()!=null&&!("").equals(edtTounge.getText().toString().trim())){
                    sewageBean.setTonnage(Integer.parseInt(edtTounge.getText().toString().trim()));
                }else{
                    toastShow("请输入设计吨位");
                    break;
                }
                if(edtEmperStand.getText()!=null&&!("").equals(edtEmperStand.getText().toString().trim())){
                    sewageBean.setEmissionStandard(edtEmperStand.getText().toString().trim());
                }else{
                    sewageBean.setEmissionStandard("");
                }
                if(edtDevicePower1.getText()!=null&&!("").equals(edtDevicePower1.getText().toString().trim())){
                    sewageBean.setEquipment1Power(Integer.parseInt(edtDevicePower1.getText().toString().trim()));
                }else{
                    sewageBean.setEquipment1Power(0);
                }
                if(edtDevicePower2.getText()!=null&&!("").equals(edtDevicePower2.getText().toString().trim())){
                    sewageBean.setEquipment2Power(Integer.parseInt(edtDevicePower2.getText().toString().trim()));
                }else{
                    sewageBean.setEquipment2Power(0);
                }
                if(edtDevicePower3.getText()!=null&&!("").equals(edtDevicePower3.getText().toString().trim())){
                    sewageBean.setEquipment3Power(Integer.parseInt(edtDevicePower3.getText().toString().trim()));
                }else{
                    sewageBean.setEquipment3Power(0);
                }
                if(edtDevicePower4.getText()!=null&&!("").equals(edtDevicePower4.getText().toString().trim())){
                    sewageBean.setEquipment4Power(Integer.parseInt(edtDevicePower4.getText().toString().trim()));
                }else{
                    sewageBean.setEquipment4Power(0);
                }
                if(edtDevicePower5.getText()!=null&&!("").equals(edtDevicePower5.getText().toString().trim())){
                    sewageBean.setEquipment5Power(Integer.parseInt(edtDevicePower5.getText().toString().trim()));
                }else{
                    sewageBean.setEquipment5Power(0);
                }
                if (isAddSewage) {
                    mvpPresenter.createSewage(sewageBean);
                }
                break;
        }
    }

    private void setResultIntent() {
        if (isAddSewage) {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putBoolean("hasAddSewage", true);
            intent.putExtras(bundle);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResultIntent();
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

    @Override
    public void onRefresh() {
    }
}