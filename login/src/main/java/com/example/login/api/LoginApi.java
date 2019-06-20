package com.example.login.api;

import com.example.login.model.LoginEntity;
import com.example.networkrequest.base.BaseResponse;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginApi {

    @POST("dukang-user/login")
    Observable<BaseResponse<LoginEntity>> postLogin(
            @Body RequestBody requestBody
    );
}
