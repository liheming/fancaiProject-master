package com.suctan.huigang.fragment.my.sell;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.suctan.huigang.R;
import com.suctan.huigang.acache.TokenManager;
import com.suctan.huigang.activity.order.SellOrderDetailActivity;
import com.suctan.huigang.activity.order.SellOrderPublicComment;
import com.suctan.huigang.adapter.order.sellorder.SellPAllOrederAdapter;
import com.suctan.huigang.bean.order.sell.SellOrderBean;
import com.suctan.huigang.fragmentinterface.InterfaceMysellOrderState;
import com.suctan.huigang.mvp.login.sellorder.MySellOrderPresenter;
import com.suctan.huigang.mvp.login.sellorder.MySellOrderView;
import com.suctan.huigang.widget.MvpFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class SellAllPFrag extends MvpFragment<MySellOrderPresenter> implements MySellOrderView, SellPAllOrederAdapter.SellPAllDetailOnClickListener {

    private InterfaceMysellOrderState Listener;
    private ListView sellALlPListView;
    private boolean isFirstCreate, isFresh = false;
    private ArrayList<SellOrderBean> sellAlls = new ArrayList<>();

    public static SellAllPFrag getFragInstant() {
        SellAllPFrag mysellAllFrag = new SellAllPFrag();
        return mysellAllFrag;
    }

    public void setListanter(InterfaceMysellOrderState Listener) {
        this.Listener = Listener;
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
    protected void onDataRefresh() {
        isFresh = true;
        getSellAllPOrderData();
    }

    @Override
    protected void onDataLoadMore() {
        onfinishRefreshOrLoad();
    }

    private void getSellAllPOrderData() {
        Map<String, Object> map = new HashMap<>();
        map.put("user_token", TokenManager.getToken());
        mvpPresenter.getMySellAllPOrderList(map);
    }

    @Override
    protected void dataLazyLoad() {
        getSellAllPOrderData();
    }


    @Override
    protected void initViewsAddEvents() {
        sellALlPListView = getListView();
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.firsts_fragment;
    }

    @Override
    protected void onMItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    protected MySellOrderPresenter createPresenter() {
        return new MySellOrderPresenter(this);
    }


    @Override
    public void sellAllPSuc(ArrayList<SellOrderBean> sellAll) {
        sellAlls.clear();//清除之前的数据
        if (isFresh) {
            isFresh = false;
        }
        sellAlls.addAll(sellAll);
        initAdapter(sellAlls);
        onfinishRefreshOrLoad(true);
    }

    SellPAllOrederAdapter allAdapter;

    private void initAdapter(ArrayList<SellOrderBean> sellAlls) {
        if (!isFirstCreate) {
            allAdapter = new SellPAllOrederAdapter(getActivity(), sellAlls);
            sellALlPListView.setAdapter(allAdapter);
            isFirstCreate = true;
            allAdapter.onDetailOnclick(this);
        } else {
            allAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void sellAllPFail() {
        onfinishRefreshOrLoad(false);
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

    }

    @Override
    public void sellFinishAFail() {

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
        intent.putExtra("sell", sellAlls.get(position));
        intent.putExtra("buySort", 1);
        getActivity().startActivity(intent);
    }

    @Override
    public void onSellPCommentOnClick(int position) {
        Intent intent = new Intent(getActivity(), SellOrderPublicComment.class);
        intent.putExtra("pComment", sellAlls.get(position));
        startActivity(intent);
    }
}
