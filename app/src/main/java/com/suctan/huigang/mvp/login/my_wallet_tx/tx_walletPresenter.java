package com.suctan.huigang.mvp.login.my_wallet_tx;

import android.util.Log;

import com.example.androidbase.rxjava.ApiCallback;
import com.example.androidbase.rxjava.SubscriberCallBack;
import com.suctan.huigang.bean.user.ModifyReturn;
import com.suctan.huigang.mvp.login.DemoBasePresenter;

import java.util.Map;

/**
 * Created by B-305 on 2017/4/20.
 */

public class tx_walletPresenter extends DemoBasePresenter<tx_walletView>{
    String TAG = "tx_walletPresenter";

    public tx_walletPresenter(tx_walletView mvpView){
        attachView(mvpView);
    }

    public void withrawalsAction(Map map){
        addSubscription(apiStores.outmoneyReturn(map),
          new SubscriberCallBack<>(new ApiCallback<ModifyReturn>(){
              @Override
              public void onStart() {
                  System.out.println("onStart");
              }

              @Override
              public void onSuccess(ModifyReturn model) {
                  Log.i(TAG, "onSuccess: ");
                  System.out.println("onSuccess 提现成功");
                  System.out.println("status="+model.getStatus()+"msg="+model.getMsg());
                  mvpView.loadCourseDone();
              }


              @Override
              public void onFailed(String msg) {
                  Log.i(TAG, "onFailed: ");
                  System.out.println("status="+msg);
                  System.out.println("onFailed");
              }

              @Override
              public void onCompleted() {
                  mvpView.hideLoading();
              }
          })
        );
    }


}
