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
import com.tmxk.wscl.android.mvp.model.UserModel;
import com.tmxk.wscl.android.mvp.presenter.UserPresenter;
import com.tmxk.wscl.android.mvp.view.UserView;

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
        setContentView(R.layout.activity_user_info);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.primary));
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("用户管理");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        application = (MainApplication) getApplication();
        initData(application.getUserModel());
    }

    private void initData(UserModel userModel) {
        if (userModel != null) {
            tvLoginName.setText(userModel.getLoginName());
            edtUserName.setText(userModel.getUserName());
            edtDepart.setText(userModel.getDepartment());
            edtEmail.setText(userModel.getUserEmail());
            edtTelephone.setText(userModel.getTelephone());
        }
    }

    @Override
    protected UserPresenter createPresenter() {
        return new UserPresenter(this);
    }

    @Override
    public void getDataSuccess(UserModel model) {
        application.setUserModel(model);
        toastShow("修改成功");
        finish();
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
                        application.getUserModel().getId(),
                        application.getUserModel().getLoginName(),
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
}