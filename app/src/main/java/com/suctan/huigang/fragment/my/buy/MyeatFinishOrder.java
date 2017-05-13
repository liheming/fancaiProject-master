package com.suctan.huigang.fragment.my.buy;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.suctan.huigang.R;
import com.suctan.huigang.acache.TokenManager;
import com.suctan.huigang.activity.order.BuyOrderAublicComment;
import com.suctan.huigang.activity.order.BuyOrderDetailActivity;
import com.suctan.huigang.adapter.order.buyorder.FinishMakeOrederAdapter;
import com.suctan.huigang.bean.commend.buy.BuyACommendReturn;
import com.suctan.huigang.bean.commend.buy.BuyPCommendReturn;
import com.suctan.huigang.bean.order.buy.BuyRecommendBean;
import com.suctan.huigang.fragmentinterface.InterFaceOrderState;
import com.suctan.huigang.mvp.login.buyorder.MyBuyOrderPresenter;
import com.suctan.huigang.mvp.login.buyorder.MyBuyOrderView;
import com.suctan.huigang.widget.MvpFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by B-305 on 2017/4/19.
 */

public class MyeatFinishOrder extends MvpFragment<MyBuyOrderPresenter> implements MyBuyOrderView, FinishMakeOrederAdapter.FinishMakeDetailOnClickListener {

    //这个fragment对应这个，我卖出的中的两个选项栏中的已完成
    private InterFaceOrderState Listner;
    private int orderSortKey;//当前显示的订单类型
    private boolean isFirstCreate, isFresh = false;
    ;
    private ListView finishListView;
    private ArrayList<BuyRecommendBean> makeFinishLists = new ArrayList<>();

    public static MyeatFinishOrder getFragmentInstant() {
        MyeatFinishOrder finishOrderFrag = new MyeatFinishOrder();
        return finishOrderFrag;
    }

    public void setOrderSortKey(int orderSortKey) {
        this.orderSortKey = orderSortKey;
    }

    public void setFragListner(InterFaceOrderState Listner) {
        this.Listner = Listner;
    }


    @Override
    protected void onDataRefresh() {
        isFresh = true;
        getMakeBuyFinishOrderList();
    }

    @Override
    protected void onDataLoadMore() {
        onfinishRefreshOrLoad();

    }

    @Override
    protected void dataLazyLoad() {
        getMakeBuyFinishOrderList();
    }

    private void getMakeBuyFinishOrderList() {
        Map<String, Object> map = new HashMap<>();
        map.put("user_token", TokenManager.getToken());
        mvpPresenter.getBuyMakeFinishOrderList(map);
    }

    @Override
    protected void initViewsAddEvents() {
        finishListView = getListView();
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.mybuy_four;
    }

    @Override
    protected void onMItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void getDataFail(String msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected MyBuyOrderPresenter createPresenter() {
        return new MyBuyOrderPresenter(this);
    }

    @Override
    public void getAllRecommderOrderSuc(ArrayList<BuyRecommendBean> allRecommendOrder) {

    }

    @Override
    public void getAllRecommderOrderFail() {

    }

    @Override
    public void getWaitRecommderOrderSuc(ArrayList<BuyRecommendBean> waitRecommendOrder) {

    }

    @Override
    public void getWaitRecommderOrderFail() {

    }

    @Override
    public void getWaitSendRecommderOrderSuc(ArrayList<BuyRecommendBean> waitSendRecommendOrder) {

    }

    @Override
    public void getWaitSendRecommderOrderFail() {

    }

    @Override
    public void getFinishRecommderOrderSuc(ArrayList<BuyRecommendBean> finishRecommendOrder) {

    }

    @Override
    public void getFinishRecommderOrderFail() {

    }

    @Override
    public void buyPuCancelSuc(int pisition) {

    }

    @Override
    public void buyPuComfirmSuc(int orderId) {

    }

    @Override
    public void getCommendPSuc(BuyPCommendReturn buyPCommendReturn) {

    }

    @Override
    public void getCommendPFail() {

    }

    @Override
    public void addBuyCommendPSuc() {

    }

    @Override
    public void getCommendASuc(BuyACommendReturn buyACommendReturn) {

    }


    @Override
    public void getCommendAFail() {

    }


    @Override
    public void getAllMakeOrderSrc(ArrayList<BuyRecommendBean> makeAllList) {

    }

    @Override
    public void getAllMakeOrderFail() {

    }

    @Override
    public void getWaitMakeOrderSuc(ArrayList<BuyRecommendBean> makeWaitList) {

    }

    @Override
    public void getWaitMakeOrderFail() {

    }

    @Override
    public void getMakeWaitSendOrderSuc(ArrayList<BuyRecommendBean> makeWaitSendList) {

    }

    @Override
    public void getMakeWaitSendOrderFail() {

    }


    @Override
    public void getMakeFinishOrderSuc(ArrayList<BuyRecommendBean> makeFinishList) {
        makeFinishLists.clear();//清除之前的数据
        if (isFresh) {
            isFresh = false;
        }

        makeFinishLists.addAll(makeFinishList);
        initAdapter(makeFinishLists);
        onfinishRefreshOrLoad(true);
    }

    FinishMakeOrederAdapter finishAdapter;

    private void initAdapter(ArrayList<BuyRecommendBean> makeFinishLists) {
        if (!isFirstCreate) {
            finishAdapter = new FinishMakeOrederAdapter(getActivity(), makeFinishLists);
            finishListView.setAdapter(finishAdapter);
            isFirstCreate = true;
            finishAdapter.onDetailOnclick(this);
        } else {
            finishAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void getMakeFinishOrderFail() {
        onfinishRefreshOrLoad(false);
    }

    @Override
    public void buyAComfirmSuc(BuyRecommendBean buyRecommendBean) {
        if (finishAdapter != null) {
            finishAdapter.setDataChange(makeFinishLists);
        } else {
            finishAdapter = new FinishMakeOrederAdapter(getActivity(), makeFinishLists);
            finishListView.setAdapter(finishAdapter);
            isFirstCreate = true;
            finishAdapter.onDetailOnclick(this);
        }
    }

    @Override
    public void addBuyACommendSuc() {

    }

    @Override
    public void onItemOnClick(int position) {
        Intent intent = new Intent(getActivity(), BuyOrderDetailActivity.class);
        intent.putExtra("buy", makeFinishLists.get(position));
        intent.putExtra("buySort", 0);
        getActivity().startActivity(intent);
    }

    @Override
    public void onFinishCommendOnclick(int position) {

        Intent intent = new Intent(getActivity(), BuyOrderAublicComment.class);
        intent.putExtra("ACommend", makeFinishLists.get(position));
        startActivity(intent);
    }
}
