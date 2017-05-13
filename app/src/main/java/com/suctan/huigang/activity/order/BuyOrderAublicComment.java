package com.suctan.huigang.activity.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.androidbase.BaseApplication;
import com.example.androidbase.mvp.MvpActivity;
import com.jaeger.library.StatusBarUtil;
import com.suctan.huigang.R;
import com.suctan.huigang.acache.TokenManager;
import com.suctan.huigang.bean.commend.buy.BuyACommendBean;
import com.suctan.huigang.bean.commend.buy.BuyACommendReturn;
import com.suctan.huigang.bean.commend.buy.BuyPCommendReturn;
import com.suctan.huigang.bean.order.buy.BuyRecommendBean;
import com.suctan.huigang.mvp.login.buyorder.MyBuyOrderPresenter;
import com.suctan.huigang.mvp.login.buyorder.MyBuyOrderView;
import com.suctan.huigang.widget.StarBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 黄淑翰 on 2017/4/28.
 */
public class BuyOrderAublicComment extends MvpActivity<MyBuyOrderPresenter> implements View.OnClickListener, MyBuyOrderView {

    public final static String TAG = "BuyOrderAublicComment";
    ImageView imv_BuyAcomment_back;
    EditText edt_BuyAcomment_content;
    StarBar start_BuyAcomment_miao ;
    StarBar start_BuyAcomment_serivice;
    StarBar start_BuyAcomment_level;
    Button btn_BuyAcomment_psot;
    private BuyRecommendBean buyRecommendBean;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.release_assess);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary), 0);
        initView();
        getIntentData();
    }

    private void getIntentData() {
        buyRecommendBean = (BuyRecommendBean) getIntent().getSerializableExtra("ACommend");
        Log.i(TAG, "getIntentData: "+buyRecommendBean.getOrder_title()+"id是"+buyRecommendBean.getId());
        if (buyRecommendBean != null) {
            Map<String, Object> map = new HashMap();
            map.put("user_token", TokenManager.getToken());
            map.put("id", buyRecommendBean.getId());
            mvpPresenter.buyCheckACommentOrder(map);
        }
    }

    private void initView() {
        imv_BuyAcomment_back = (ImageView) findViewById(R.id.imv_BuyAcomment_back);
        edt_BuyAcomment_content = (EditText) findViewById(R.id.edt_BuyAcomment_content);
        start_BuyAcomment_miao = (StarBar) findViewById(R.id.start_BuyAcomment_miao);
        start_BuyAcomment_serivice = (StarBar) findViewById(R.id.start_BuyAcomment_serivice);
        start_BuyAcomment_level = (StarBar) findViewById(R.id.start_BuyAcomment_level);
        btn_BuyAcomment_psot = (Button) findViewById(R.id.btn_BuyAcomment_psot);
        btn_BuyAcomment_psot.setOnClickListener(this);
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
    public void buyPuCancelSuc(int orderID) {

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
/**
 * 查看评价成功回调
 * */
    @Override
    public void getCommendASuc(BuyACommendReturn buyACommendReturn) {
        Log.i(TAG, "getCommendASuc:查看评价成功回调");
        if (buyACommendReturn.getComment() != null) {
        Log.i(TAG, "getCommendASuc:不为null");
            initViewData(buyACommendReturn.getComment());
        } else {
        Log.i(TAG, "getCommendASuc:null");
            edt_BuyAcomment_content.setEnabled(true);
//            start_BuyAcomment_miao.setCanCheck(true);
            start_BuyAcomment_miao.setCurrentChoose(0);
//            start_BuyAcomment_serivice.setCanCheck(true);
            start_BuyAcomment_serivice.setCurrentChoose(0);
//            start_BuyAcomment_level.setCanCheck(true);
            start_BuyAcomment_level.setCurrentChoose(0);
            btn_BuyAcomment_psot.setVisibility(View.VISIBLE);
        }
    }

    private void initViewData(BuyACommendBean buyACommendBean) {
        Log.i(TAG, "initViewData: 评价"+buyACommendBean.getComment_content()+"描述"+buyACommendBean.getComment_describe());
        btn_BuyAcomment_psot.setVisibility(View.GONE);
        edt_BuyAcomment_content.setText(buyACommendBean.getComment_content());
        edt_BuyAcomment_content.setEnabled(false);
        start_BuyAcomment_miao.setCurrentChoose(buyACommendBean.getComment_describe());
        start_BuyAcomment_miao.setCanCheck(false);
        start_BuyAcomment_serivice.setCurrentChoose(buyACommendBean.getComment_service());
        start_BuyAcomment_serivice.setEnabled(false);
        start_BuyAcomment_level.setCurrentChoose(buyACommendBean.getComment_taste());
        start_BuyAcomment_level.setEnabled(false);
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

    }

    @Override
    public void getMakeFinishOrderFail() {

    }

    @Override
    public void buyAComfirmSuc(BuyRecommendBean buyRecommendBean) {

    }

    @Override
    public void addBuyACommendSuc() {
        finish();
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

    private void varityText() {
        if (TextUtils.isEmpty(edt_BuyAcomment_content.getText())) {
            Toast.makeText(BaseApplication.getContext(), "评价内容不能为空", Toast.LENGTH_LONG).show();
            return;
        }

        Map<String, Object> map = new HashMap<>();
        map.put("id", buyRecommendBean.getId());
        map.put("order_id", buyRecommendBean.getOrder_id());
        map.put("comment", edt_BuyAcomment_content.getText());
        map.put("comment_describe", start_BuyAcomment_miao.getCurrentStartNum());
        map.put("comment_service", start_BuyAcomment_serivice.getCurrentStartNum());
        map.put("comment_taste", start_BuyAcomment_level.getCurrentStartNum());
        mvpPresenter.buyAddACommentOrder(map);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_BuyAcomment_psot:
                varityText();
                break;
        }
    }
}
