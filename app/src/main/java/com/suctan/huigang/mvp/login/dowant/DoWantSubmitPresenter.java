package com.suctan.huigang.mvp.login.dowant;

import android.util.Log;

import com.example.androidbase.rxjava.ApiCallback;
import com.example.androidbase.rxjava.SubscriberCallBack;
import com.suctan.huigang.bean.user.ModifyReturn;
import com.suctan.huigang.mvp.login.DemoBasePresenter;

import java.util.Map;

/**
 * Created by Tom on 2017/4/11.
 */

public class DoWantSubmitPresenter extends DemoBasePresenter<DoWantSubmitView> {
    String TAG = "DoWantSubmitPresenter";
    public DoWantSubmitPresenter(DoWantSubmitView mvpView) {
        attachView(mvpView);
    }

    /**
     * 厨师请求我要接单
     */
    public void submitOrder(Map map) {
        addSubscription(apiStores.submitOrder(map),
                new SubscriberCallBack<>(new ApiCallback<ModifyReturn>() {
                    @Override
                    public void onStart() {
                        Log.i(TAG, "onStart: ");
                    }
                    @Override
                    public void onCompleted() {
                        mvpView.hideLoading();
                    }

                    @Override
                    public void onSuccess(ModifyReturn model) {
                        Log.i(TAG, "onSuccess: ");
                        if (model != null) {
                            mvpView.submitOrderReturn(model.getMsg(), model.getStatus());
                        }
                    }

                    @Override
                    public void onFailed(String msg) {
                        Log.i(TAG, "onFailed: ");
                        mvpView.getDataFail(msg);
                    }
                }));
    }
}
