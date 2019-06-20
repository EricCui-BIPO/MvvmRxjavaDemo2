package com.example.networkrequest.base;

import com.example.networkrequest.callback.RequestCallback;
import com.example.networkrequest.callback.RequestMultiplyCallback;
import com.example.networkrequest.exception.ApiException;
import com.example.networkrequest.exception.BaseException;
import com.example.networkrequest.exception.NetworkConnectionException;
import com.example.networkrequest.utils.ToastUtil;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.observers.DisposableObserver;

/**
 *
 */
public class BaseObserver<T> extends DisposableObserver<T> {

    private RequestCallback<T> requestCallback;

    public BaseObserver(RequestCallback<T> requestCallback) {
        this.requestCallback = requestCallback;
    }

    @Override
    public void onNext(T t) {
        if (requestCallback != null) {
            requestCallback.onSuccess(t);
        }
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (requestCallback instanceof RequestMultiplyCallback) {
            RequestMultiplyCallback callback = (RequestMultiplyCallback) requestCallback;

            if (e instanceof SocketTimeoutException) {
                ToastUtil.showToast("网络状态不佳，请稍后重试");
            } else if (e instanceof ConnectException) {
                ToastUtil.showToast("网络状态不佳，请稍后重试");
            } else if (e instanceof NetworkConnectionException) {
                ToastUtil.showToast("网络状态不佳，请稍后重试");
            } else if (e instanceof ApiException) {
                if(((ApiException) e).getErrorMsg().equals("")){
                    callback.onFail(new BaseException(e.getMessage()));
                }else{
                    ToastUtil.showToast(((ApiException) e).getErrorMsg());
                }


            }

        }
    }

    @Override
    public void onComplete() {

    }

}