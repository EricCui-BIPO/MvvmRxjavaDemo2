package com.example.login.view;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.login.R;
import com.example.login.viewmodel.LoginAndRegisterViewModel;
import com.example.mvvmlibrary.base.BaseActivity;
import com.example.networkrequest.arouter.Constance;

@Route(path = Constance.ACTIVITY_URL_LOGIN_REGISTER)
public class LoginAndRegisterActivity extends BaseActivity<LoginAndRegisterViewModel> {

    @Override
    protected LoginAndRegisterViewModel getViewModel() {
        return ViewModelProviders.of(this).get(LoginAndRegisterViewModel.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_and_register);
        TextView tv_lr = ((TextView) findViewById(R.id.tv_lr));
        tv_lr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance().build(Constance.ACTIVITY_URL_REGISTER).navigation();
            }
        });
    }
}
