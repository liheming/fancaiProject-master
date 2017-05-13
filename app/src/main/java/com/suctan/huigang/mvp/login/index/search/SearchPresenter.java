package com.suctan.huigang.mvp.login.index.search;


import android.util.Log;

import com.example.androidbase.rxjava.ApiCallback;
import com.example.androidbase.rxjava.SubscriberCallBack;
import com.suctan.huigang.mvp.login.DemoBasePresenter;

import java.util.Map;

/**
 *  Created by haily on 2017/3/4.
 */

public class SearchPresenter extends DemoBasePresenter<SearchIndexView> {
    public SearchPresenter(SearchIndexView mvpView) {
        attachView(mvpView);
    }

    public final static String TAG = "SearchPresenter";
    /**
     * 请求搜索菜色
     */
    public void searchAction(Map map) {
        addSubscription(apiStores.search_MakeFood(map),new SubscriberCallBack<>(new ApiCallback<String>() {
            @Override
            public void onStart() {
                Log.i(TAG, "onStart: ");
            }

            @Override
            public void onSuccess(String model) {
                Log.i(TAG, "onSuccess:搜索的菜品是 "+model);
                mvpView.searchResult(model);
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
