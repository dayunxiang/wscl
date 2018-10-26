package com.tmxk.wscl.android.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.tmxk.wscl.android.R;
import com.tmxk.wscl.android.application.MainApplication;
import com.tmxk.wscl.android.mvp.model.UserBean;
import com.tmxk.wscl.android.mvp.presenter.LoginPresenter;
import com.tmxk.wscl.android.mvp.view.LoginView;

import butterknife.BindView;

/**
 * Created by wjf on 18/10/25.
 * login activity
 */
public class LoginActivity extends MvpActivity<LoginPresenter> implements LoginView {
    @BindView(R.id.username)
    EditText edtUsername;
    @BindView(R.id.password)
    EditText edtPassword;
    @BindView(R.id.appName)
    TextView tvAppName;
    private MainApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        initView();
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    private void initView() {
        application = (MainApplication) getApplication();
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
    public void getDataSuccess(UserBean model) {
        if (model != null) {
            application.setUserBean(model);
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        } else {
            toastShow("登录失败");
        }
    }

    @Override
    public void getDataFail(String msg) {
        toastShow(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mvpPresenter.detachView();
        mvpPresenter.onUnSubscribe();
    }
}