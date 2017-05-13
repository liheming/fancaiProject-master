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

public class MyAlreadyChikenPresenter extends DemoBasePresenter<MyAlreadyChikenView> {
    public MyAlreadyChikenPresenter(MyAlreadyChikenView mvpView) {
        attachView(mvpView);
    }

/** 请求下架今日菜色*/
    public void DownMakeFood(final Map map , final int id) {
        addSubscription(apiStores.down_today_makeFood(map),
                new SubscriberCallBack<>(new ApiCallback<ModifyReturn>() {
                    @Override
                    public void onStart() {

                    }
                    @Override
                    public void onSuccess(ModifyReturn model) {
                        if (model!=null){
                            System.out.println("接口调用情况 status=" + model.getStatus() + "mag="+model .getMsg());
                            mvpView.downlreturn(id);
                            mvpView.downMakeOrder(model.getStatus(),model.getMsg());
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

/**查看今日上架的菜色
 * */
    public void lookAlreadyChiken(final Map map ) {
        addSubscription(apiStores.look_today_makeFood(map),
                new SubscriberCallBack<>(new ApiCallback<String>() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess(String model) {
                        if (model!=null){
                            MykitchenReturn mykitchenReturn = JSONParstObject.getMykitchenBeanList(model,2);
                            System.out.println("查看已上架的菜色" + model);
                            if (mykitchenReturn != null) {
//                            MyAlreadykitchenReturn myAlreadykitchenReturn = JSONParstObject.getMyAlreadykitchenBeanList(model);
//                            System.out.println("查看已上架的菜色" + model);
//                            if(myAlreadykitchenReturn!=null) {
                                mvpView.getMakeOrderList(mykitchenReturn.getMykitchenBeanList());
                            }
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
