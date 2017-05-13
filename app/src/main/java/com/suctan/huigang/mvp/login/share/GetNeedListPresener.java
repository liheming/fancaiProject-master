package com.suctan.huigang.mvp.login.share;

import android.util.Log;

import com.example.androidbase.rxjava.ApiCallback;
import com.example.androidbase.rxjava.SubscriberCallBack;
import com.suctan.huigang.bean.need.NeedReturn;
import com.suctan.huigang.mvp.login.DemoBasePresenter;
import com.suctan.huigang.util.JSONParstObject;

import java.util.Map;

/**
 * Created by haily on 2017/4/19.
 */

public class GetNeedListPresener extends DemoBasePresenter<GetNeedListView> {
    private static String TAG = "GetNeedListPresener";
    public GetNeedListPresener(GetNeedListView mvpView) {
        attachView(mvpView);
    }

    /**
     * @param map Map
     * @explain 请求获取对应的共享需求列表
     */
    public void getNeedListAction(Map map) {
        addSubscription(apiStores.getNeedList(map),
                new SubscriberCallBack<>(new ApiCallback<String>() {
                    @Override
                    public void onStart() {
                        System.out.println("onStart");
                    }
                    @Override
                    public void onSuccess(String model) {
                        NeedReturn needReturn = JSONParstObject.getNeedStringObject(model);
                        if (needReturn != null && needReturn.getTipBeanList() != null) {
                            System.out.println("共享需求列表" + model);
//                            mvpView.getNeedBack();
//                            mvpView.getNeedListSrc();
                            mvpView.getNeedListSrc(needReturn.getTipBeanList());
                        }
                        Log.i(TAG, "onSuccess: 返回的数据是"+model );
                        mvpView.getNeedBack();
                    }


                    @Override
                    public void onFailed(String msg) {
                        Log.i(TAG, "onFailed: ");

                    }

                    @Override
                    public void onCompleted() {

                    }
                }));

    }
}
