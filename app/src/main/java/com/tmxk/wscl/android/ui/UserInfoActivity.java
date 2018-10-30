package com.tmxk.wscl.android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import com.jaeger.library.StatusBarUtil;
import com.tmxk.wscl.android.R;
import com.tmxk.wscl.android.application.MainApplication;
import com.tmxk.wscl.android.mvp.model.UserBean;
import com.tmxk.wscl.android.mvp.presenter.UserPresenter;
import com.tmxk.wscl.android.mvp.view.UserView;
import com.tmxk.wscl.android.util.Constant;

import java.util.Objects;

import butterknife.BindView;

/**
 * Created by wjf on 18/10/25.
 * user basic info
 */
public class UserInfoActivity extends MvpActivity<UserPresenter> implements UserView {
    private MainApplication application;
    @BindView(R.id.edtLoginName)
    EditText edtLoginName;
    @BindView(R.id.edtDepart)
    EditText edtDepart;
    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.edtTelephone)
    EditText edtTelephone;
    @BindView(R.id.edtUserName)
    EditText edtUserName;
    private UserBean userBean;
    private int position;
    private boolean isAddUser = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_user_basic);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.primary));
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("基本信息");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        application = (MainApplication) getApplication();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("userBean")) {
            edtLoginName.setEnabled(false);
            userBean = (UserBean) intent.getSerializableExtra("userBean");
            position = intent.getIntExtra("position", -1);
        } else if (intent != null && intent.hasExtra("userAdd")) {
            edtLoginName.setEnabled(true);
            isAddUser = true;
        } else {
            edtLoginName.setEnabled(false);
            userBean = application.getUserBean();
        }
        if (userBean != null) {
            edtLoginName.setText(userBean.getLoginName());
            edtUserName.setText(userBean.getUserName());
            edtDepart.setText(userBean.getDepartment());
            edtEmail.setText(userBean.getUserEmail());
            edtTelephone.setText(userBean.getTelephone());
        }
    }

    @Override
    protected UserPresenter createPresenter() {
        return new UserPresenter(this);
    }

    @Override
    public void getDataSuccess(Object model) {
        toastShow(Constant.MODIFY_SUCCESS);
        setResultIntent();
        if (model instanceof UserBean) {
            application.setUserBean((UserBean) model);
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
                userBean.setUserName(edtLoginName.getText().toString().trim());
                userBean.setUserName(edtUserName.getText().toString().trim());
                userBean.setUserEmail(edtEmail.getText().toString().trim());
                userBean.setDepartment(edtDepart.getText().toString().trim());
                userBean.setTelephone(edtTelephone.getText().toString().trim());
                if (!isAddUser) {
                    mvpPresenter.modifyUserInfo(userBean);
                } else {
                    mvpPresenter.addUserInfo(userBean);
                }
                break;
        }
    }

    private void setResultIntent() {
        if (!isAddUser) {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putSerializable("userBean", userBean);
            bundle.putInt("position", position);
            intent.putExtras(bundle);
            setResult(RESULT_OK, intent);
            finish();
        } else {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putBoolean("hasAddUser", true);
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
    public void autoRefresh() {

    }
}