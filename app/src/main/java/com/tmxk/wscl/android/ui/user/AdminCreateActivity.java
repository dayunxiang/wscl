package com.tmxk.wscl.android.ui.user;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.jaeger.library.StatusBarUtil;
import com.tmxk.wscl.android.R;
import com.tmxk.wscl.android.emuns.DataTypeEnum;
import com.tmxk.wscl.android.mvp.model.AdminListBean;
import com.tmxk.wscl.android.mvp.model.SewageListBean;
import com.tmxk.wscl.android.mvp.model.SiteDeviceDocBean;
import com.tmxk.wscl.android.mvp.presenter.DeviceDocPresenter;
import com.tmxk.wscl.android.mvp.presenter.UserPresenter;
import com.tmxk.wscl.android.mvp.view.SewageArchiveView;
import com.tmxk.wscl.android.mvp.view.UserView;
import com.tmxk.wscl.android.ui.base.MvpActivity;
import com.tmxk.wscl.android.util.CommonUtil;
import com.tmxk.wscl.android.util.Constant;

import java.util.Date;
import java.util.Objects;

import butterknife.BindView;

/**
 * Created by wy on 18/12/21.
 * device doc create
 */
public class AdminCreateActivity extends MvpActivity<UserPresenter> implements UserView {
    @BindView(R.id.edtAdminName)
    EditText edtAdminName;
    @BindView(R.id.edtAdminTelephone)
    EditText edtAdminTelephone;
    @BindView(R.id.edtAdminEmail)
    EditText edtAdminEmail;
    @BindView(R.id.edtAdminAddress)
    EditText edtAdminAddress;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private AdminListBean.ObjectBean adminBean = new AdminListBean.ObjectBean();
    private boolean isAddAdmin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_admin_create);
        StatusBarUtil.setColorNoTranslucent(this, getResources().getColor(R.color.primary));
        initData();
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    private void initData() {
        Intent intent = getIntent();
        if(intent.getBooleanExtra("addAdmin",false)){
            Log.d("AdminCreateActivity","addAdmin-flag: true");
            isAddAdmin=true;
            toolbar.setTitle("新建管理员");
        }
    }

    @Override
    protected UserPresenter createPresenter() {
        return new UserPresenter(this);
    }

    @Override
    public void getDataSuccess(Object model, DataTypeEnum dataTypeEnum) {
        toastShow(Constant.CREATE_SUCCESS);
        setResultIntent();
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
                adminBean.setName(edtAdminName.getText().toString().trim());
                adminBean.setTelephone(edtAdminTelephone.getText().toString().trim());
                adminBean.setEmail(edtAdminEmail.getText().toString().trim());
                if(edtAdminAddress.getText()!=null){
                    adminBean.setAddress(edtAdminAddress.getText().toString().trim());
                }
                if (isAddAdmin) {
                    mvpPresenter.createAdmin(adminBean);
                }
                break;
        }
    }

    private void setResultIntent() {
        if (isAddAdmin) {
            Log.d("AdminActivity","setResultIntent");
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putBoolean("hasAddAdmin", true);
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
    public void onRefresh(){

    }
}