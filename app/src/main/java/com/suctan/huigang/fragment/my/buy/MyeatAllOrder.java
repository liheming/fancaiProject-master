package com.suctan.huigang.fragment.my.buy;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.suctan.huigang.R;
import com.suctan.huigang.acache.TokenManager;
import com.suctan.huigang.activity.order.BuyOrderAublicComment;
import com.suctan.huigang.activity.order.BuyOrderDetailActivity;
import com.suctan.huigang.adapter.order.buyorder.AllMakeOrederAdapter;
import com.suctan.huigang.bean.commend.buy.BuyACommendReturn;
import com.suctan.huigang.bean.commend.buy.BuyPCommendReturn;
import com.suctan.huigang.bean.order.buy.BuyRecommendBean;
import com.suctan.huigang.fragmentinterface.InterFaceOrderState;
import com.suctan.huigang.mvp.login.buyorder.MyBuyOrderPresenter;
import com.suctan.huigang.mvp.login.buyorder.MyBuyOrderView;
import com.suctan.huigang.widget.MvpFragment;
import com.suctan.huigang.widget.TipsComfirmOrderDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by B-305 on 2017/4/19.
 */

public class MyeatAllOrder extends MvpFragment<MyBuyOrderPresenter> implements MyBuyOrderView, AllMakeOrederAdapter.AllMakeDetailOnClickListener {

    //这个fragment对应这个，我卖出的中的两个选项栏中的已完成
    private InterFaceOrderState Listner;
    private int orderSortKey;//当前显示的订单类型
    private ListView allListView;
    private boolean isFirstCreate, isFresh = false; //是否是第一次创建 和 是否是刷新
    private ArrayList<BuyRecommendBean> makeAllLists = new ArrayList<>();

    public static MyeatAllOrder getFragmentInstant() {
//        Toast.makeText(BaseApplication.getContext(), "我是吃的订单", Toast.LENGTH_LONG).show();
        MyeatAllOrder allOrderFrag = new MyeatAllOrder();
        return allOrderFrag;
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
        getMakeBuyAllOrderList();
    }

    @Override
    protected void onDataLoadMore() {
        onfinishRefreshOrLoad();
    }

    @Override
    protected void dataLazyLoad() {
        getMakeBuyAllOrderList();
    }


    private void getMakeBuyAllOrderList() {
        Map<String, Object> map = new HashMap<>();
        map.put("user_token", TokenManager.getToken());
        mvpPresenter.getBuyMakeAllOrderList(map);
    }


    @Override
    protected void initViewsAddEvents() {
        allListView = getListView();
    }

    AllMakeOrederAdapter allAdapter;

    private void initAdapter(ArrayList<BuyRecommendBean> makeAllLists) {
        if (!isFirstCreate && allListView != null) {
            allAdapter = new AllMakeOrederAdapter(getActivity(), makeAllLists);
            allListView.setAdapter(allAdapter);
            isFirstCreate = true;
            allAdapter.onDetailOnclick(this);
        } else {
            allAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.mybuy_one;
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
        makeAllLists.clear();//清除之前的数据
        if (isFresh) {
            isFresh = false;
        }
        makeAllLists.addAll(makeAllList);
        initAdapter(makeAllLists);
        onfinishRefreshOrLoad(true);

    }

    @Override
    public void getAllMakeOrderFail() {
        onfinishRefreshOrLoad(false);
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

    }

    @Override
    public void getMakeFinishOrderFail() {

    }

    @Override
    public void buyAComfirmSuc(BuyRecommendBean buyRecommendBean) {
        for (int i = 0; i < makeAllLists.size(); i++) {
            if (makeAllLists.get(i).getId() == buyRecommendBean.getId()) {
                makeAllLists.get(i).setOrder_status(3);
                break;
            }
        }
        if (allAdapter != null) {
            allAdapter.setDataChange(makeAllLists);
        }
    }

    @Override
    public void addBuyACommendSuc() {

    }

    /**
     * 查看详情
     */
    @Override
    public void onDetailOnClick(int position) {
        Intent intent = new Intent(getActivity(), BuyOrderDetailActivity.class);
        intent.putExtra("buy", makeAllLists.get(position));
        intent.putExtra("buySort", 0);
        getActivity().startActivity(intent);
    }

    /**
     * 请求确认收货
     */
    @Override
    public void onComfirmAOnclick(int position) {
        showComfirmTips(position);
    }

    /**
     * 查看评价
     */
    @Override
    public void onAllCommentOnclick(int position) {
        Intent intent = new Intent(getActivity(), BuyOrderAublicComment.class);
        intent.putExtra("ACommend", makeAllLists.get(position));
        startActivity(intent);
    }

    private void requestComfirmOrder(int position) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_token", TokenManager.getToken());
        map.put("id", makeAllLists.get(position).getId());
        mvpPresenter.buyComfirmAOrder(map, makeAllLists.get(position));
    }

    private void showComfirmTips(final int position) {
        final TipsComfirmOrderDialog comfirmTip = new TipsComfirmOrderDialog(getActivity());
        comfirmTip.setTipClickLisener(new TipsComfirmOrderDialog.OnTipLisetner() {
            @Override
            public void comfirm() {
                requestComfirmOrder(position);
                comfirmTip.dismiss();
            }

            @Override
            public void cancel() {
                comfirmTip.dismiss();
            }
        });
        comfirmTip.show();
    }
}
