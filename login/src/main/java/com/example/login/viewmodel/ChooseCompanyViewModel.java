package com.example.login.viewmodel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.login.model.RegisterCompanyEntity;
import com.example.login.repository.ChooseCompanyRepository;
import com.example.mvvmlibrary.base.BaseViewModel;
import com.example.networkrequest.arouter.Constance;
import com.example.networkrequest.cache.ACache;
import com.example.networkrequest.callback.RequestMultiplyCallback;
import com.example.networkrequest.exception.BaseException;
import com.example.networkrequest.utils.ToastUtil;

import java.util.List;

public class ChooseCompanyViewModel extends BaseViewModel<ChooseCompanyRepository> {

    private  MutableLiveData<List<RegisterCompanyEntity>> companyList = new MutableLiveData<>();
    public ChooseCompanyViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected ChooseCompanyRepository getRepository(){
        return new ChooseCompanyRepository(this);
    }


    //接口的请求数据以及回调
    public void getCompanyList() {
        mRepository.getCompanyList(new RequestMultiplyCallback<List<RegisterCompanyEntity>>() {

            @Override
            public void onFail(BaseException e) {
                ToastUtil.showToast("aaa");
            }

            @Override
            public void onSuccess(List<RegisterCompanyEntity> companyEntityList) {
                companyList.setValue(companyEntityList);

            }
        });
    }

    public MutableLiveData<List<RegisterCompanyEntity>> getCompany(){
        return companyList;
    }
}
