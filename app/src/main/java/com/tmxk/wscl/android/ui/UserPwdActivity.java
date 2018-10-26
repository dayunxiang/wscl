package com.tmxk.wscl.android.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import com.jaeger.library.StatusBarUtil;
import com.tmxk.wscl.android.R;
import com.tmxk.wscl.android.application.MainApplication;
import com.tmxk.wscl.android.mvp.model.HttpReturnBean;
import com.tmxk.wscl.android.mvp.model.UserBean;
import com.tmxk.wscl.android.mvp.presenter.UserPresenter;
import com.tmxk.wscl.android.mvp.view.UserView;
import com.tmxk.wscl.android.util.Constant;

import java.util.Objects;

import butterknife.BindView;

/**
 * Created by wjf on 18/10/26.
 * user modify password
 */
public class UserPwdActivity extends MvpActivity<UserPresenter> implements UserView {
    private MainApplication application;
    @BindView(R.id.edtOldPassword)
    EditText edtOldPassword;
    @BindView(R.id.edtNewPassword)
    EditText edtNewPassword;
    @BindView(R.id.edtConfirmPassword)
    EditText edtConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_user_pwd);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.primary));
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("密码修改");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        application = (MainApplication) getApplication();
    }

    @Override
    protected UserPresenter createPresenter() {
        return new UserPresenter(this);
    }

    @Override
    public void getDataSuccess(UserBean model) {
        application.getUserBean().setLoginPwd(edtNewPassword.getText().toString().trim());
        toastShow(Constant.MODIFY_SUCCESS);
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
                mvpPresenter.modifyUserPwd(
                        application.getUserBean().getId(),
                        application.getUserBean().getLoginPwd(),
                        edtOldPassword.getText().toString().trim(),
                        edtNewPassword.getText().toString().trim(),
                        edtConfirmPassword.getText().toString().trim()
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