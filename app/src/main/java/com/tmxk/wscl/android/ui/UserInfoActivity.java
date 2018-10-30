package com.tmxk.wscl.android.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.tmxk.wscl.android.R;
import com.tmxk.wscl.android.application.MainApplication;
import com.tmxk.wscl.android.mvp.model.UserBean;
import com.tmxk.wscl.android.mvp.model.UserListBean;
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
    @BindView(R.id.tvLoginName)
    TextView tvLoginName;
    @BindView(R.id.edtDepart)
    EditText edtDepart;
    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.edtTelephone)
    EditText edtTelephone;
    @BindView(R.id.edtUserName)
    EditText edtUserName;

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
        initData(application.getUserBean());
    }

    private void initData(UserBean userBean) {
        if (userBean != null) {
            tvLoginName.setText(userBean.getLoginName());
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
        if (model instanceof UserBean) {
            application.setUserBean((UserBean) model);
            toastShow(Constant.MODIFY_SUCCESS);
            finish();
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
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnOk:
                mvpPresenter.modifyUserInfo(
                        application.getUserBean().getId(),
                        application.getUserBean().getLoginName(),
                        edtUserName.getText().toString().trim(),
                        edtEmail.getText().toString().trim(),
                        edtDepart.getText().toString().trim(),
                        edtTelephone.getText().toString().trim()
                );
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mvpPresenter.detachView();
        mvpPresenter.onUnSubscribe();
    }
}