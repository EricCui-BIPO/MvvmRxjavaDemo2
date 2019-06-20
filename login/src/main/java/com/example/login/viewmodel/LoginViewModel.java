package com.example.login.viewmodel;

import android.app.Application;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.login.model.LoginEntity;
import com.example.login.repository.LoginRepository;
import com.example.mvvmlibrary.base.BaseViewModel;
import com.example.networkrequest.arouter.Constance;
import com.example.networkrequest.cache.ACache;
import com.example.networkrequest.cache.EnumKey;
import com.example.networkrequest.callback.RequestMultiplyCallback;
import com.example.networkrequest.exception.BaseException;
import com.example.networkrequest.utils.Utils;

import okhttp3.RequestBody;

public class LoginViewModel extends BaseViewModel<LoginRepository> {

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected LoginRepository getRepository(){
        return new LoginRepository(this);
    }

    //接口的请求数据以及回调
    public void postLogin(RequestBody body) {
        mRepository.postLogin(body,new RequestMultiplyCallback<LoginEntity>() {

            @Override
            public void onFail(BaseException e) {

            }

            @Override
            public void onSuccess(LoginEntity entity) {

                ACache.get(Utils.getContext().getApplicationContext()).put(EnumKey.User.USER_TOKEN,entity.getAccessToken());
            }
        });
    }
}
