package com.tmxk.wscl.android.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.tmxk.wscl.android.R;
import com.tmxk.wscl.android.mvp.model.LoginModel;
import com.tmxk.wscl.android.mvp.presenter.LoginPresenter;
import com.tmxk.wscl.android.mvp.view.LoginView;

import butterknife.BindView;

public class LoginActivity extends MvpActivity<LoginPresenter> implements LoginView {
    @BindView(R.id.username)
    EditText edtUsername;
    @BindView(R.id.password)
    EditText edtPassword;
    @BindView(R.id.appName)
    TextView tvAppName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        initView();
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    private void initView() {
        tvAppName.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/TTLingHeiJ.ttf"));//设置登录页面标题字体
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                mvpPresenter.login(edtUsername.getText().toString(), edtPassword.getText().toString());
                break;
        }
    }

    @Override
    public void getDataSuccess(LoginModel model) {
        startActivity(new Intent(this,HomeActivity.class));
        finish();
    }

    @Override
    public void getDataFail(String msg) {
        toastShow(msg);
    }
}