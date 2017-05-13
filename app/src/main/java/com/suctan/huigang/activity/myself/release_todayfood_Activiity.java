package com.suctan.huigang.activity.myself;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.androidbase.mvp.MvpActivity;
import com.example.androidbase.utils.ToastTool;
import com.suctan.huigang.R;
import com.suctan.huigang.acache.TokenManager;
import com.suctan.huigang.adapter.UploadFoodAdaper;
import com.suctan.huigang.bean.user.MykitchenBean;
import com.suctan.huigang.mvp.login.myChiken.UploadFoodPresenter;
import com.suctan.huigang.mvp.login.myChiken.UploadFoodView;
import com.suctan.huigang.widget.TipsCancelOrderDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by haily on 2017/5/01
 */

public class release_todayfood_Activiity extends MvpActivity<UploadFoodPresenter> implements View.OnClickListener, UploadFoodView, UploadFoodAdaper.MykitchenOnClickListener, UploadFoodAdaper.UploadFood {
    private ImageView today_food_back;
    private ArrayList<MykitchenBean> mykitchenList = new ArrayList<>();
    boolean initFirstmykitchen;
    private GridView loadGridView;
    private Button publishFood;

    UploadFoodAdaper uploadFoodAdaper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.release_today_food);
        getMakeFoodList();
        initView();
        goMykitchenAdapter();
    }

    @Override
    protected UploadFoodPresenter createPresenter() {
        return new UploadFoodPresenter(this);
    }

    private void initView() {
//        uploadFoodAdaper = new UploadFoodAdaper(this ,mykitchenList);
        loadGridView = (GridView) findViewById(R.id.loadGridView);
        publishFood = (Button) findViewById(R.id.publishFood);
        today_food_back = (ImageView) findViewById(R.id.today_food_back);
        today_food_back.setOnClickListener(this);
        publishFood.setOnClickListener(this);
//        publishFood.setEnabled(false);


//        loadGridView.setAdapter(uploadFoodAdaper);
    }

    private void getMakeFoodList() {
        Map<String, Object> map = new HashMap<>();
        map.put("user_token", TokenManager.getToken());
        mvpPresenter.getMyUnloadFoodList(map);
    }


    private void goMykitchenAdapter() {

        uploadFoodAdaper = new UploadFoodAdaper(this, mykitchenList);
        uploadFoodAdaper.onDetailOnclick(release_todayfood_Activiity.this);
        uploadFoodAdaper.setRecomendOnclickLisner(this);
        loadGridView.setAdapter(uploadFoodAdaper);
    }

    //请求上传今日菜色
    private void requestUploadFood() {
        Map<String, String> map = new HashMap<>();
        map.put("user_token", TokenManager.getToken());
        map.put("order_idArr", order_idArr.toString());
        map.put("order_numArr", order_numArr.toString());
        mvpPresenter.LoadMakeFood(map);
    }

    private void showUploadTip() {
        final TipsCancelOrderDialog cancelDialog = new TipsCancelOrderDialog(this);
        cancelDialog.setTipClickLisener(new TipsCancelOrderDialog.OnTipLisetner() {
            @Override
            public void comfirm() {
                cancelDialog.dismiss();
                requestUploadFood();
            }

            @Override
            public void cancel() {
                cancelDialog.dismiss();
            }
        });
        cancelDialog.show();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.today_food_back:
                finish();
                break;
            case R.id.publishFood:
                if (order_idArr.length()==0){
                    ToastTool.showToast("您还没选择要上架的菜品呢",2);
                }
                else {
                showUploadTip();

                }
                break;
        }

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
    public void getUnloadOrderList(ArrayList<MykitchenBean> mykitchenBeenlist) {
        this.mykitchenList.addAll(mykitchenBeenlist);
        if (!initFirstmykitchen) {
            goMykitchenAdapter();
            initFirstmykitchen = true;
        } else {
            uploadFoodAdaper.setDataChange(mykitchenBeenlist);
        }


    }
  // 上传成功之后的回调
    @Override
    public void Uploadreturn(int status, String msg) {
        ToastTool.showToast(msg, status);
        startActivity( new Intent(release_todayfood_Activiity.this,MykitchenActity.class));// 跳转到我的厨房
    }

    @Override
    public void onItemDeleteOnClick(int position) {

    }

    private StringBuilder order_idArr = new StringBuilder();
    private StringBuilder order_numArr = new StringBuilder();


    @Override
    public void uploadFoodListener(Map<Integer, Integer> setIdAndNum) {
        order_idArr.delete(0, order_idArr.length());
        order_numArr.delete(0, order_numArr.length());
        System.out.println("回调size是" + setIdAndNum.size());
        if (setIdAndNum.size() != 0 && !setIdAndNum.isEmpty()) {
            Iterator iter = setIdAndNum.entrySet().iterator();        //获取key和value的set
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();        //把hashmap转成Iterator再迭代到entry
                Object key = entry.getKey();        //从entry获取key
                Object val = entry.getValue();    //从entry获取value
                order_idArr.append(key + "next");
                order_numArr.append(val + "next");
                System.out.println("key = " + key + "----" + "value=" + val);
//                publishFood.setEnabled(true);
            }
        }
        else {
//            publishFood.setEnabled(false);
            ToastTool.showToast("你还没选择要上架的菜品呢",2);
        }

        System.out.println("订单编号数组字符串是" + order_idArr.toString());
        System.out.println("订单数量数组字符串是" + order_numArr.toString());

    }
}
