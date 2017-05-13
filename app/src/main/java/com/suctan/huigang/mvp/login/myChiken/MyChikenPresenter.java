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

public class MyChikenPresenter extends DemoBasePresenter<MyChikenView> {
    public MyChikenPresenter(MyChikenView mvpView) {
        attachView(mvpView);
    }


    public void deleteMakeFood(final Map map, final int orderID) {
        addSubscription(apiStores.delete_makeFood(map),
                new SubscriberCallBack<>(new ApiCallback<ModifyReturn>() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess(ModifyReturn model) {
                        if (model != null) {
                            mvpView.deleteMakeOrder(model.getStatus(),model.getMsg());
                            mvpView.Deletelreturn(orderID);
                            System.out.println("接口调用情况 status=" + model.getStatus() + "mag=" + model.getMsg());
                        }
                    }

                   /* @Override
                    public void onSuccess(String model) {
                        if (model!=null){
                            MykitchenReturn mykitchenReturn = JSONParstObject.getMykitchenBeanList(model);
                            System.out.println("我的厨房获取菜的列表" + model);
                            if(mykitchenReturn!=null){
                                mvpView.getMakeOrderList(mykitchenReturn);
                            }
                        }
                    }*/


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


    public void AddChiken(final Map map) {
        addSubscription(apiStores.getMakeOrderListReturn(map),
                new SubscriberCallBack<>(new ApiCallback<String>() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess(String model) {
                        if (model != null) {
                            MykitchenReturn mykitchenReturn = JSONParstObject.getMykitchenBeanList(model,1);
                            System.out.println("我的厨房获取菜的列表" + model);
                            if (mykitchenReturn != null) {
                                mvpView.getMakeOrderList(mykitchenReturn.getMykitchenBeanList());
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
