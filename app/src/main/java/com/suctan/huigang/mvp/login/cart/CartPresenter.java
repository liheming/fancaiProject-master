package com.suctan.huigang.mvp.login.cart;

import android.util.Log;

import com.example.androidbase.rxjava.ApiCallback;
import com.example.androidbase.rxjava.SubscriberCallBack;
import com.example.androidbase.utils.ToastTool;
import com.suctan.huigang.bean.cart.CartReturn;
import com.suctan.huigang.bean.index.EatFoodReturn;
import com.suctan.huigang.bean.user.ComomBeanReturn;
import com.suctan.huigang.bean.user.ModifyReturn;
import com.suctan.huigang.mvp.login.DemoBasePresenter;
import com.suctan.huigang.util.JSONParstObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tom on 2017/4/11.
 */

public class CartPresenter extends DemoBasePresenter<CartView> {
    private String TAG = "CartPresenter";
    public CartPresenter(CartView mvpView) {
        attachView(mvpView);
    }

    /**
     * 获取首页所有菜的列表
     */
    public void getEatFoodList() {
        addSubscription(apiStores.getEatFoodList(new HashMap<String, Object>()),
                new SubscriberCallBack<>(new ApiCallback<String>() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onCompleted() {
                        mvpView.hideLoading();
                    }

                    @Override
                    public void onSuccess(String model) {
                        if (model != null) {
                            EatFoodReturn eatFoodReturn = JSONParstObject.getRollViewDataList(model, 1);
                            if (eatFoodReturn != null) {
                                System.out.println("首页获取菜的列表" + model);
                                mvpView.getEatfoodListSuc(eatFoodReturn);
                            }
                        }
                    }

                    @Override
                    public void onFailed(String msg) {
                        mvpView.getDataFail(msg);
                    }
                }));
    }

    /**
     * 今日推荐加减号加入购物车
     *
     * @param map
     */
    public void dealCart(Map map) {
        addSubscription(apiStores.addCartReturn(map),
                new SubscriberCallBack<>(new ApiCallback<ComomBeanReturn>() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onCompleted() {
                        mvpView.hideLoading();
                    }

                    @Override
                    public void onSuccess(ComomBeanReturn model) {
                        if (model.getStatus() != 1) {
                            ToastTool.showToast("系统繁忙请稍后再试！", 0);
                        }
                    }

                    @Override
                    public void onFailed(String msg) {
                        mvpView.getDataFail(msg);
                    }
                }));
    }


    /**
     * 获取购物车列表
     *
     * @param map
     */
    public void getCartList(Map map) {
        addSubscription(apiStores.getCartListReturn(map),
                new SubscriberCallBack<>(new ApiCallback<String>() {
                    @Override
                    public void onStart() {
                        Log.i(TAG, "onStart: ");

                    }

                    @Override
                    public void onCompleted() {
                        mvpView.hideLoading();
                    }

                    @Override
                    public void onSuccess(String model) {
                        System.out.println("获取购物车列表" + model);

                        if (model != null) {
                            CartReturn cartReturn = JSONParstObject.getCartListObject(model);
                            if (cartReturn != null & cartReturn.getCartBeenList() != null) {
                                Log.i(TAG, "onSuccess: ");
                                mvpView.getCartListSuc(cartReturn.getCartBeenList());
                            }
                        }

                    }

                    @Override
                    public void onFailed(String msg) {
                        Log.i(TAG, "onFailed: ");
                        mvpView.getDataFail(msg);
                    }
                }));
    }


    /**
     * 修改购物车数量
     *
     * @param map
     */
    public void changeCartCount(Map map) {
        addSubscription(apiStores.changeCartCountReturn(map),
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
                        System.out.println("状态" + model.getStatus()+"msg："+model.getMsg());
                        mvpView.changeCarNumSuc(model.getMsg(),model.getStatus());

                    }

                    @Override
                    public void onFailed(String msg) {
                        mvpView.getDataFail(msg);
                    }
                }));
    }


    /**
     * 结算购物车
     *
     * @param map
     */
    public void payCartPrice(Map map) {
        addSubscription(apiStores.payCarttReturn(map),
                new SubscriberCallBack<>(new ApiCallback<ComomBeanReturn>() {
                    @Override
                    public void onStart() {
                        Log.i(TAG, "onStart: ");
                    }

                    @Override
                    public void onCompleted() {
                        mvpView.hideLoading();
                    }

                    @Override
                    public void onSuccess(ComomBeanReturn model) {
                        Log.i(TAG, "onSuccess: ");
                            mvpView.payCartSuc(model.getMsg(),model.getStatus());
                        if (model.getStatus() == 1) {

                        } else {
                            ToastTool.showToast("结算失败！", 1);
                        }
                        System.out.println("结算购物车" + model);

                    }

                    @Override
                    public void onFailed(String msg) {
                        Log.i(TAG, "onFailed: "+msg);
                        mvpView.getDataFail(msg);
                    }
                }));
    }


    /**
     * 删除购物车
     *
     * @param map
     */
    public void deleteCart(Map map, final int position) {
        addSubscription(apiStores.deleteCartReturn(map),
                new SubscriberCallBack<>(new ApiCallback<ComomBeanReturn>() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onCompleted() {
                        mvpView.hideLoading();
                    }

                    @Override
                    public void onSuccess(ComomBeanReturn model) {
                        mvpView.deleteCartSuc(position,model.getMsg(), model.getStatus());
//                        Toast.makeText(BaseApplication.getContext(), model.getMsg() + model.getStatus(), Toast.LENGTH_SHORT).show();
                        if (model.getStatus() == 1) {

                            Log.i(TAG, "onSuccess:删除成功！ ");

                        } else {
                            Log.i(TAG, "onSuccess:删除成功！ ");
//                            ToastTool.showToast("删除失败！", 0);
                        }
                    }

                    @Override
                    public void onFailed(String msg) {
                        mvpView.getDataFail(msg);
                    }
                }));
    }


}
