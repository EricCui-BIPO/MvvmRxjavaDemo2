package com.example.login.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.login.R;
import com.example.login.viewmodel.LoginViewModel;
import com.example.mvvmlibrary.base.BaseActivity;
import com.example.networkrequest.arouter.Constance;
import com.example.networkrequest.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;

@Route(path = Constance.ACTIVITY_URL_LOGIN)
public class LoginActivity extends BaseActivity<LoginViewModel> {

    TextView btnLogin;
    EditText loginEtUsername;
    EditText loginEtPassword;
    private TextView loginUsernameTip,loginPasswordTip;
    private String username,password;
    @Override
    protected LoginViewModel getViewModel() {
        return ViewModelProviders.of(LoginActivity.this).get(LoginViewModel.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ARouter.getInstance().inject(this);
        loginEtUsername = ((EditText) findViewById(R.id.login_et_username));
        loginEtPassword = ((EditText) findViewById(R.id.login_et_password));
        loginUsernameTip = ((TextView) findViewById(R.id.login_username_tip));
        loginPasswordTip = ((TextView) findViewById(R.id.login_password_tip));
        btnLogin = ((TextView) findViewById(R.id.login_btn_login));
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance().build(Constance.ACTIVITY_URL_LOGIN_REGISTER).withFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK).navigation();
//                initSubmit();
            }
        });
    }

    private void initSubmit() {
        username = loginEtUsername.getText().toString();
        password = loginEtPassword.getText().toString();
        if(username.length()!=11){
            ToastUtil.showToast(LoginActivity.this,getResources().getString(R.string.A_IZCX2C));
            return;
        }

        JSONObject requestData = new JSONObject();
        try {
            requestData.put("username", username);
            requestData.put("password", password);
            requestData.put("clientId", "hydrangea");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), requestData.toString());
        if (TextUtils.isEmpty(username) || username.length() != 11) {
            loginUsernameTip.setVisibility(View.VISIBLE);
            loginUsernameTip.setText(getResources().getString(R.string.A_34YLRS));
        } else if (TextUtils.isEmpty(password)) {
            loginPasswordTip.setVisibility(View.VISIBLE);
            loginPasswordTip.setText(getResources().getString(R.string.A_OVVODH));
        } else {
            loginUsernameTip.setVisibility(View.GONE);
            loginPasswordTip.setVisibility(View.GONE);
            viewModel.postLogin(requestBody);
        }
    }
}
