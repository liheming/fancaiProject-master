package com.suctan.huigang.mvp.login.index.location;

import android.util.Log;

import com.example.androidbase.rxjava.ApiCallback;
import com.example.androidbase.rxjava.SubscriberCallBack;
import com.example.androidbase.utils.ToastTool;
import com.suctan.huigang.bean.user.ModifyReturn;
import com.suctan.huigang.mvp.login.DemoBasePresenter;

import java.util.Map;

public class LocationPresenter extends DemoBasePresenter<LocationView> {
    String TAG = "LocationPresenter";
    public LocationPresenter(LocationView mvpView) {
        attachView(mvpView);
    }
    public void getLocation(Map map) {
        addSubscription(apiStores.get_userLocation(map),
                new SubscriberCallBack<>(new ApiCallback<ModifyReturn>() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess(ModifyReturn model) {
                        mvpView.LocationReturn(model.getMsg(),model.getStatus());
                        Log.i(TAG, "onSuccess: "+model.getMsg()+model.getStatus());
                        ToastTool.showToast(model.getMsg(),model.getStatus());

                    }

                    @Override
                    public void onFailed(String msg) {
                        Log.i(TAG, "onFailed: "+msg);
                    }

                    @Override
                    public void onCompleted() {

                    }
                }));
    }
}