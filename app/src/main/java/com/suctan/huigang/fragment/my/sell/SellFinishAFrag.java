package com.suctan.huigang.fragment.my.sell;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.suctan.huigang.R;
import com.suctan.huigang.acache.TokenManager;
import com.suctan.huigang.activity.order.SellOrderAublicComment;
import com.suctan.huigang.activity.order.SellOrderDetailActivity;
import com.suctan.huigang.adapter.order.sellorder.SellAFinishOrederAdapter;
import com.suctan.huigang.bean.order.sell.SellOrderBean;
import com.suctan.huigang.fragmentinterface.InterfaceMysellOrderState;
import com.suctan.huigang.mvp.login.sellorder.MySellOrderPresenter;
import com.suctan.huigang.mvp.login.sellorder.MySellOrderView;
import com.suctan.huigang.widget.MvpFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by B-305 on 2017/4/19.
 */

public class SellFinishAFrag extends MvpFragment<MySellOrderPresenter> implements MySellOrderView, SellAFinishOrederAdapter.SellAFinishDetailOnClickListener {
    private InterfaceMysellOrderState Listener;
    private ListView listView;
    private boolean isFirstCreate, isFresh = false; //是否是第一次创建 和 是否是刷新

    ArrayList<SellOrderBean> sellAFinishs = new ArrayList<>();


    public static SellFinishAFrag getFragInstant() {
        SellFinishAFrag mySell_three = new SellFinishAFrag();
        return mySell_three;
    }

    public void setListanter(InterfaceMysellOrderState Listener) {
        this.Listener = Listener;
    }


    //这个fragment对应这个，我卖出的中的两个选项栏中的已完成


    @Override
    protected void onDataRefresh() {
        isFresh = true;
        getSellFinishAOrderData();
    }

    @Override
    protected void onDataLoadMore() {
        onfinishRefreshOrLoad();
    }

    private void getSellFinishAOrderData() {
        Map<String, Object> map = new HashMap<>();
        map.put("user_token", TokenManager.getToken());
        mvpPresenter.getMySellFinishAOrderList(map);
    }

    @Override
    protected void dataLazyLoad() {
        getSellFinishAOrderData();
    }

    @Override
    protected void initViewsAddEvents() {
        listView = getListView();
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.mysell_fragment_four;
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
    protected MySellOrderPresenter createPresenter() {
        return new MySellOrderPresenter(this);
    }

    @Override
    public void sellAllPSuc(ArrayList<SellOrderBean> sellAll) {

    }

    @Override
    public void sellAllPFail() {

    }

    @Override
    public void sellDoingPSuc(ArrayList<SellOrderBean> sellDoing) {

    }

    @Override
    public void sellDoingPFail() {

    }

    @Override
    public void sellFinishPSuc(ArrayList<SellOrderBean> sellFinish) {

    }

    @Override
    public void sellFinishPFail() {

    }

    @Override
    public void sellAllASuc(ArrayList<SellOrderBean> sellAAll) {

    }

    @Override
    public void sellAllAFail() {

    }

    @Override
    public void sellAgreeASuc(ArrayList<SellOrderBean> sellAAgree) {

    }

    @Override
    public void sellAgreeAFail() {

    }

    @Override
    public void sellDoingASuc(ArrayList<SellOrderBean> sellADoing) {

    }

    @Override
    public void sellDoingAFail() {

    }

    @Override
    public void sellFinishASuc(ArrayList<SellOrderBean> sellAFinish) {
        sellAFinishs.clear();//清除之前的数据
        if (isFresh) {
            isFresh = false;
        }

        sellAFinishs.addAll(sellAFinish);
        initAdapter(sellAFinishs);
        onfinishRefreshOrLoad(true);
    }


    SellAFinishOrederAdapter finishAdapter;

    private void initAdapter(ArrayList<SellOrderBean> sellAFinishs) {
        if (!isFirstCreate) {
            finishAdapter = new SellAFinishOrederAdapter(getActivity(), sellAFinishs);
            listView.setAdapter(finishAdapter);
            isFirstCreate = true;
            finishAdapter.onDetailOnclick(this);
        } else {
            finishAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void sellFinishAFail() {
        onfinishRefreshOrLoad(false);
    }

    @Override
    public void sellCancleOrderSuc(int orderId) {

    }

    @Override
    public void sellAcceptOrderSuc(SellOrderBean orderId) {

    }

    @Override
    public void addSellCommendPSuc() {

    }

    @Override
    public void onItemOnClick(int position) {
        Intent intent = new Intent(getActivity(), SellOrderDetailActivity.class);
        intent.putExtra("sell", sellAFinishs.get(position));
        intent.putExtra("buySort", 0);
        getActivity().startActivity(intent);
    }

    @Override
    public void onSellAllComments(int position) {
        Intent intent = new Intent(getActivity(), SellOrderAublicComment.class);
        intent.putExtra("ACommend", sellAFinishs.get(position));
        startActivity(intent);
    }
}
