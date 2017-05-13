package com.suctan.huigang.mvp.login.myChiken;

import com.example.androidbase.rxjava.ApiCallback;
import com.example.androidbase.rxjava.SubscriberCallBack;
import com.suctan.huigang.bean.user.ModifyReturn;
import com.suctan.huigang.bean.user.MykitchenReturn;
import com.suctan.huigang.mvp.login.DemoBasePresenter;
import com.suctan.huigang.util.JSONParstObject;

import java.util.Map;

/**
 * Created by Tom on 2017/4/11.
 */

public class UploadFoodPresenter extends DemoBasePresenter<UploadFoodView> {
    public UploadFoodPresenter(UploadFoodView mvpView) {
        attachView(mvpView);
    }

/** 请求上架今日菜色*/
    public void LoadMakeFood( Map map ) {
        addSubscription(apiStores.put_today_makeFood(map),
                new SubscriberCallBack<>(new ApiCallback<ModifyReturn>() {
                    @Override
                    public void onStart() {

                    }
                    @Override
                    public void onSuccess(ModifyReturn model) {
                        if (model!=null){
                            System.out.println("接口调用情况 status=" + model.getStatus() + "mag="+model .getMsg());
                            mvpView.Uploadreturn(model.getStatus(),model.getMsg());
                        }
                    }

                    @Override
                    public void onCompleted() {
                        mvpView.hideLoading();
                    }


                    @Override
                    public void onFailed(String msg) {
                        mvpView.getDataFail(msg);
                    }
                }));
    }

/**查看我的厨房未上传的菜品列表
 * */
    public void getMyUnloadFoodList(final Map map ) {
        addSubscription(apiStores.look_unLoad_makeFood(map),
                new SubscriberCallBack<>(new ApiCallback<String>() {
                    @Override
                    public void onStart() {

                    }
                    @Override
                    public void onSuccess(String model) {
                        if (model != null) {
                            MykitchenReturn mykitchenReturn = JSONParstObject.getMykitchenBeanList(model,3);
                            System.out.println("我的厨房未上传的菜品列表" + model);
                            if (mykitchenReturn != null) {
                                mvpView.getUnloadOrderList(mykitchenReturn.getMykitchenBeanList());
                            }
                        }
                    }


                    @Override
                    public void onCompleted() {
                        mvpView.hideLoading();
                    }


                    @Override
                    public void onFailed(String msg) {
                        mvpView.getDataFail(msg);
                    }
                }));
    }





    /*public void AddChiken(Map map) {
        addSubscription(apiStores.ModifyUserReturn(new HashMap<String, Object>()),
                new SubscriberCallBack<>(new ApiCallback<ModifyReturn>() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onCompleted() {
                        mvpView.hideLoading();
                    }

                    @Override
                    public void onSuccess(ModifyReturn model) {

                    }
                    @Override
                    public void onFailed(String msg) {
                        mvpView.getDataFail(msg);
                    }
                }));
    }*/
}
