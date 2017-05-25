package com.suctan.huigang.activity.cart;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.androidbase.mvp.MvpActivity;
import com.example.androidbase.utils.ScreenTools;
import com.example.androidbase.utils.ToastTool;
import com.jaeger.library.StatusBarUtil;
import com.roger.catloadinglibrary.CatLoadingView;
import com.suctan.huigang.R;
import com.suctan.huigang.acache.CurrentUser;
import com.suctan.huigang.acache.TokenManager;
import com.suctan.huigang.activity.address.addressActivity;
import com.suctan.huigang.activity.recommend.RecommendActivity;
import com.suctan.huigang.adapter.cart.ShoppingCartAdapter;
import com.suctan.huigang.bean.cart.CartBean;
import com.suctan.huigang.bean.index.EatFoodReturn;
import com.suctan.huigang.mvp.login.cart.CartPresenter;
import com.suctan.huigang.mvp.login.cart.CartView;
import com.suctan.huigang.widget.TakePhotoPopWin;
import com.suctan.huigang.widget.TimePickDialog;
import com.suctan.huigang.widget.TipsAddAddressDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 黄淑翰 on 2017/4/24.
 */
public class ShoppingCart extends MvpActivity<CartPresenter> implements TakePhotoPopWin.PayInterface, View.OnClickListener, CartView, ShoppingCartAdapter.OnCartListClickListner {
    private static final String TAG = "ShoppingCart";
    private ImageButton imb_cart_back;
    private TextView tv_anore_address;
    private TextView tv_receiver_time;
    private GridView shopping_cart_gridView;
    private CheckBox checkbox_cart_all;
    private TextView tv_allMoney;
    private TimePickDialog timePickDialg;
    private LinearLayout ly_change_receiveTime;
    private int selectHour, selectMinute;
    private double cartAllPrice;
    private ArrayList<CartBean> cartBeanList = new ArrayList<>();

    private Button btn_pay_cart; //结算购物车
    private boolean isFirstCreatCartAdapter;
    private String tempTime = "01:01:00";
    ShoppingCartAdapter cartAdapter;// 适配器
    private CatLoadingView catLoadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_cart);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary), 0);
        initView();
        getCartListRequest();
        tv_allMoney.setText("0.0");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (tv_anore_address != null) {
            if (CurrentUser.getInstance().getUserBean().getUser_address() != null) {
                tv_anore_address.setText(CurrentUser.getInstance().getUserBean().getUser_address());
            }
        }
    }

    @Override
    protected CartPresenter createPresenter() {
        return new CartPresenter(this);
    }

    private void getCartListRequest() {
        Map<String, Object> map = new HashMap<>();
        map.put("user_token", TokenManager.getToken());
        mvpPresenter.getCartList(map);
    }

    private void initView() {


        catLoadingView = new CatLoadingView();
        ly_change_receiveTime = (LinearLayout) findViewById(R.id.ly_change_receiveTime);
        imb_cart_back = (ImageButton) findViewById(R.id.imb_cart_back);
        tv_anore_address = (TextView) findViewById(R.id.tv_anore_address);
        tv_receiver_time = (TextView) findViewById(R.id.tv_receiver_time);
        shopping_cart_gridView = (GridView) findViewById(R.id.shopping_cart_gridView);
        checkbox_cart_all = (CheckBox) findViewById(R.id.checkbox_cart_all);
        tv_allMoney = (TextView) findViewById(R.id.tv_allMoney);
        btn_pay_cart = (Button) findViewById(R.id.btn_pay_cart);


        //监听
        imb_cart_back.setOnClickListener(this);
        tv_anore_address.setOnClickListener(this);
        btn_pay_cart.setOnClickListener(this);
        ly_change_receiveTime.setOnClickListener(this);

//全选反选的选择
        checkbox_cart_all.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (cartBeanList.size() == 0 | cartAdapter == null) {
                    return;
                }
                toogleCheckAll(b);
                cartAdapter.notifyDataSetChanged();
            }
        });
    }

    private void toogleShowCataloading(boolean isShow) {
        if (isShow) {
            catLoadingView.show(getSupportFragmentManager(), "");
        } else {
            catLoadingView.dismiss();
        }
    }

    /**
     * 请求结算购物车
     */
    private void requestCartPay() {

        Map<String, Object> map = new HashMap<>();
        map.put("user_token", TokenManager.getToken());
        map.put("expect_time", tempTime);
        map.put("address", CurrentUser.getInstance().getUserBean().getUser_address());
        System.out.println("结算的购物车包括：" + PayCart());
        map.put("orderArrStr", PayCart());
        mvpPresenter.payCartPrice(map);

    }


    /**
     * 拼接订单id和数量字符串
     */
    private String PayCart() {
        StringBuffer cartBuffer = new StringBuffer();
        for (int i = 0; i < cartBeanList.size(); i++) {
            if (cartBeanList.get(i).isCheck() == true) {
                CartBean cartBean = cartBeanList.get(i);
                if (cartBeanList.size() == cartBeanList.size() - 1) {
                    String item = cartBean.getOrder_id() + "comma" + cartBean.getOrder_num();
                    cartBuffer.append(item);
                } else {
                    String item = cartBean.getOrder_id() + "comma" + cartBean.getOrder_num() + "period";
                    cartBuffer.append(item);
                }
            }
        }
        return cartBuffer.toString();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imb_cart_back:
                finish();
                break;
            case R.id.btn_pay_cart:

                if (tv_allMoney.getText() == "0.0") {
                    ToastTool.showToast("至少选中一个商品才能结算哦", 2);
                } else if (TextUtils.isEmpty(CurrentUser.getInstance().getUserBean().getUser_address())) {
                    Log.i(TAG, "initView: 我没有地址");
                    final TipsAddAddressDialog tipsAddAddressDialog = new TipsAddAddressDialog(this);
                    tipsAddAddressDialog.setTipClickLisener(new TipsAddAddressDialog.OnTipLisetner() {
                        @Override
                        public void comfirm() {
                            Intent intent = new Intent(ShoppingCart.this, addressActivity.class);//如果没有地址信息就跳转到选择地址
                            // FIXME: 2017/5/2 结算购物车选择地址问题待解决 &&
                            startActivity(intent);
                            tipsAddAddressDialog.dismiss();
                        }

                        @Override
                        public void cancel() {
                            tipsAddAddressDialog.dismiss();
                        }
                    });

                    tipsAddAddressDialog.show();

                } else {

                    showPopFormBottom();
                }

                break;
            case R.id.tv_anore_address:

                break;
            case R.id.ly_change_receiveTime:
                initTimerTip();
                break;
        }
    }

    private void initTimerTip() {
        selectHour = 1;
        selectMinute = 1;
        timePickDialg = new TimePickDialog(this, ScreenTools.getWindowsWidth(this));
        timePickDialg.setOnTimePickOnclick(new TimePickDialog.OnHourPickListener() {
            @Override
            public void onHourSeletTime(int selectHours) {
                selectHour = selectHours;
            }

            @Override
            public void onMinuteSelectTime(int selectMinutes) {
                selectMinute = selectMinutes;
            }

            @Override
            public void comfirm() {
                String hour, mimute = null;
                if (selectHour < 10) {
                    hour = "0" + selectHour;
                } else {
                    hour = "" + selectHour;
                }
                if (selectMinute < 10) {
                    mimute = "0" + selectMinute;
                } else {
                    mimute = "" + selectMinute;
                }
                tempTime = hour + ":" + mimute + ":" + "00";
                tv_receiver_time.setText(hour + ":" + mimute);
                timePickDialg.dismiss();
            }

            @Override
            public void cancel() {
                timePickDialg.dismiss();
            }
        });
        timePickDialg.show();

    }

    /**
     * 更新购物车数量
     *
     * @param position int
     * @param count    int
     */
    private void updateCartCount(int position, int count) {

//        if (i == 1) {
//            money  =  money + price;
//            tv_allMoney.setText(money+"");
//            Log.i(TAG, "onCaculatePrice: 加上之后的金额是"+money);
//        }
//        else{
//            money  =  money - price;
//            tv_allMoney.setText(money+"");
//            Log.i(TAG, "onCaculatePrice: 应该减去之后的金额是"+money);
//        }
    }

    /**
     * 删除购物车该菜品
     *
     * @param position int
     */
    private void dealCartItme(int position) {
        Map<String, Object> map = new HashMap<>();
        map.put("sc_id", cartBeanList.get(position).getSc_id());
        mvpPresenter.deleteCart(map, cartBeanList.get(position).getSc_id());
    }


    @Override

    public void getEatfoodListSuc(EatFoodReturn mEatFoodReturn) {

    }

    @Override
    public void getEatfoodListFail() {

    }

    @Override
    public void getCartListSuc(ArrayList<CartBean> cartBeenLists) {
        Log.i(TAG, "getCartListSuc: 回调成功 ");
        this.cartBeanList.addAll(cartBeenLists);
        if (!isFirstCreatCartAdapter) {
            cartAdapter = new ShoppingCartAdapter(ShoppingCart.this, this.cartBeanList);
            cartAdapter.setOnCartListClick(ShoppingCart.this);
            shopping_cart_gridView.setAdapter(cartAdapter);
            isFirstCreatCartAdapter = true;
        } else {
            cartAdapter.setDataChange(cartBeenLists);
//            cartAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void getCartListFail() {

    }

    /**
     * 删除购物车菜品成功后的回调
     */
    @Override
    public void deleteCartSuc(int position, String msg, int status) {
        Log.i(TAG, "deleteCartSuc:删除购物车菜品成功后的回调移除当前的item" + position + "size" + cartBeanList.size());
        ToastTool.showToast(msg, status);
        for (int i = 0; i < cartBeanList.size(); i++) {
            if (cartBeanList.get(i).getSc_id() == position) {
                Log.i(TAG, "deleteCartSuc: 移除当前的item");
                cartBeanList.remove(cartBeanList.get(i));
//                cartAdapter.setDataChange(cartBeanList);
//                    cartAdapter.notifyDataSetInvalidated();
                break;
            }
        }
        if (cartBeanList != null) {
            cartAdapter.setDataChange(cartBeanList);
        }

    }

    /**
     * 结算购物车成功后的回调
     */
    @Override
    public void payCartSuc(String msg, int status) {
        ToastTool.showToast(msg, status);
        toogleShowCataloading(false);
        startActivity(new Intent(this, RecommendActivity.class));
        finish();
    }

    /**
     * 修改购物车菜品数量成功后的回调
     */
    @Override
    public void changeCarNumSuc(String msg, int status) {
        Log.i(TAG, "changeCarNumSuc: " + msg + status);
    }

    @Override
    public void payCartFail() {
        toogleShowCataloading(false);
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

    /**
     * 更新购物车数量
     *
     * @param position int
     * @param count    int
     */
    @Override
    public void onChangeNum(int type, boolean ischeck, int position, int count) {
        if (ischeck) {
            if (type == 1) {
                money = money + cartBeanList.get(position).getOrder_price();
                tv_allMoney.setText(money + "");
                Log.i(TAG, "onCaculatePrice: 加上之后的金额是" + money);
            } else {

                money = money - cartBeanList.get(position).getOrder_price();
                tv_allMoney.setText(money + "");
                Log.i(TAG, "onCaculatePrice: 加上之后的金额是" + money);
            }
        }
        Map<String, Object> map = new HashMap();
        map.put("sc_id", cartBeanList.get(position).getSc_id());
        map.put("order_num", count);
        map.put("order_price", cartBeanList.get(position).getOrder_price());
        mvpPresenter.changeCartCount(map);
    }


    @Override
    public void onDetele(int position) {
        dealCartItme(position);
    }

    public double money = 0.0;

    @Override
    public void onCaculatePrice(int i, double price) {


//        if (i == 1) {
//           money  =  money + price;
//            tv_allMoney.setText(money+"");
//            Log.i(TAG, "onCaculatePrice: 加上之后的金额是"+money);
//        }
//        else{
//            money  =  money - price;
//            tv_allMoney.setText(money+"");
//            Log.i(TAG, "onCaculatePrice: 应该减去之后的金额是"+money);
//        }


    }


    public double setAllMoney() {
        double allMoney = 0;
        for (int i = 0; i < cartBeanList.size(); i++) {
            if (cartBeanList.get(i).isCheck() == true) {
                CartBean cartBean = cartBeanList.get(i);
                allMoney = cartBean.getOrder_allprice() + allMoney;
//                if (cartBeanList.size() == cartBeanList.size() - 1) {
// FIXME: 2017/5/2 在购物车调整数量不会自动更新问题
//                    String item = cartBean.getOrder_id() + "comma" + cartBean.getOrder_num();
//                    cartBuffer.append(item);
//                } else {
//                    String item = cartBean.getOrder_id() + "comma" + cartBean.getOrder_num() + "period";
//                    cartBuffer.append(item);
//                }
            }
        }
        Log.i(TAG, "setAllMoney: 返回的金额是多少" + allMoney);
        return allMoney;
    }

    private void toogleCheckAll(boolean isCheck) {
        if (isCheck) {
            for (int i = 0; i < cartBeanList.size(); i++) {
                cartBeanList.get(i).setCheck(true);
            }
        } else {
            for (int i = 0; i < cartBeanList.size(); i++) {
                cartBeanList.get(i).setCheck(false);
            }
        }
    }

    @Override
    public void onCheckBoxItemClick(int position) {
        money = setAllMoney();
        tv_allMoney.setText(money + "");
    }


    //dialog的加载等待框
//    Dialog dialog = null;
//    public void showDialog() {
//        dialog = LoadingDialog.createLoading(this, "加载中...");
//        dialog.show();
//    }
//    public void dismissDialog() {
//        dialog.dismiss();
//    }


    //跳去popupwindiw的方法
    public void showPopFormBottom() {
        TakePhotoPopWin takePhotoPopWin = new TakePhotoPopWin(this, onClickListener);
        takePhotoPopWin.onLisener(this);
        takePhotoPopWin.showAtLocation(findViewById(R.id.main_view_pop), Gravity.CENTER, 0, 0);
        takePhotoPopWin.setPay_type("支付宝");
        takePhotoPopWin.setTotal_money(money + "");
        takePhotoPopWin.setUser_account(CurrentUser.getInstance().getUserBean().getUser_phone());

    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };


    /**
     * 输入支付密码后
     */
    @Override
    public void paySuccess() {
        requestCartPay();
//        showDialog();
        toogleShowCataloading(true);
    }
}
