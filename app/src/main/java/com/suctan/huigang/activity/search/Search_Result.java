package com.suctan.huigang.activity.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidbase.mvp.MvpActivity;
import com.example.androidbase.utils.ToastTool;
import com.jaeger.library.StatusBarUtil;
import com.suctan.huigang.R;
import com.suctan.huigang.acache.TokenManager;
import com.suctan.huigang.activity.cart.ShoppingCart;
import com.suctan.huigang.activity.eatfood.EatFoodDetail;
import com.suctan.huigang.adapter.SearchResultAdapter;
import com.suctan.huigang.bean.cart.CartBean;
import com.suctan.huigang.bean.index.EatFoodBean;
import com.suctan.huigang.bean.index.EatFoodReturn;
import com.suctan.huigang.mvp.login.cart.CartPresenter;
import com.suctan.huigang.mvp.login.cart.CartView;
import com.suctan.huigang.util.JSONParstObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Change by haily on 2017/5/04
 */

public class Search_Result extends MvpActivity<CartPresenter>  implements CartView , View.OnClickListener ,SearchResultAdapter.Recommend {
    private static final String ACTIVITY_TAG = "Search_Result";
    private GridView gridView;
    private ImageView shopping_cart;
    private ImageButton  btnBack;
    private TextView tv_head;

    private boolean initFirstCreateGrid;
    SearchResultAdapter searchAdapter;
        private ArrayList<EatFoodBean> foodList = new ArrayList<>();
    String eatFoodList = "";




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.header_screen);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary), 0);
        initView();
        getEatFoodList();
    }

    @Override
    protected CartPresenter createPresenter() {
        return new CartPresenter(this);
    }

    /**
     * 获取搜索列表
     */
    private void getEatFoodList() {

    }
    private void initView() {

//        SearchActivity search = new SearchActivity(Search_Result.this);
//        search.ListenerOnclick(this);
        tv_head = (TextView) findViewById(R.id.tv_head);
        tv_head.setText("搜索列表");
        gridView = (GridView) findViewById(R.id.gridView);
        shopping_cart = (ImageView) findViewById(R.id.shopping_cart);
        btnBack = (ImageButton) findViewById(R.id.btnBack);
        shopping_cart.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goEatDetail(position);
            }
        });

        eatFoodList =   getIntent().getStringExtra("eatFoodList");
        EatFoodReturn eatReturn = JSONParstObject.getRollViewDataList(eatFoodList, 0);
        if (eatReturn.getStatus() == 1) {
            foodList =  eatReturn.getEatFoodBeanList();
            onShow(foodList);
        }
        else {
            ToastTool.showToast("没有搜索到数据",2);
        }


//        gridView.setAdapter();
    }

    private void onShow(ArrayList<EatFoodBean> foodList) {
              if (!initFirstCreateGrid) {
            searchAdapter = new SearchResultAdapter(this, foodList);
                  searchAdapter.setRecomendOnclickLisner(this);
            gridView.setAdapter(searchAdapter);
            initFirstCreateGrid = true;
        } else
            {
            searchAdapter.notifyDataSetChanged();
      }
    }


    /**
     * 跳到想吃的详情页
     */

    private void goEatDetail(int position) {
        Intent intent = new Intent(this, EatFoodDetail.class);
        intent.putExtra("nowEatFood", foodList.get(position));
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                finish();
                break;
            case R.id.shopping_cart:
                Intent intent = new Intent(this, ShoppingCart.class);
                startActivity(intent);
                break;
        }
    }

    public final static String TAG = "Search_Result";

    private void CartDeal(int index, int count) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_token", TokenManager.getToken());
        map.put("order_id", foodList.get(index).getOrder_id());
        map.put("order_title", foodList.get(index).getOrder_title());
        map.put("order_pic", foodList.get(index).getOrder_pic());
        map.put("order_price", foodList.get(index).getOrder_price());
        map.put("order_type", foodList.get(index).getOrder_type());
        map.put("order_num", count);
        mvpPresenter.dealCart(map);
    }



    @Override
    public void addCartItemListener(int addCartPosition, int count) {
        CartDeal(addCartPosition, count);
    }

    @Override
    public void deleteCartItemListener(int deleteCartPosition, int count) {
        CartDeal(deleteCartPosition, count);
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
    public void getEatfoodListSuc(EatFoodReturn mEatFoodReturn) {

    }

    @Override
    public void getEatfoodListFail() {

    }

    @Override
    public void getCartListSuc(ArrayList<CartBean> cartBeenList) {

    }

    @Override
    public void getCartListFail() {

    }

    @Override
    public void deleteCartSuc(int position, String msg, int status) {

    }

    @Override
    public void payCartSuc(String msg, int status) {

    }

    @Override
    public void changeCarNumSuc(String msg, int status) {

    }

    @Override
    public void payCartFail() {

    }
}