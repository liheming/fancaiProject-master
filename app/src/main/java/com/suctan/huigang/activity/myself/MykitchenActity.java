package com.suctan.huigang.activity.myself;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.androidbase.LoadImageManager;
import com.example.androidbase.mvp.MvpActivity;
import com.example.androidbase.utils.ToastTool;
import com.suctan.huigang.R;
import com.suctan.huigang.acache.CurrentUser;
import com.suctan.huigang.acache.TokenManager;
import com.suctan.huigang.activity.Popupwindow.my_kitchen_popupwin_release;
import com.suctan.huigang.adapter.MykitchenAdaper;
import com.suctan.huigang.bean.user.MykitchenBean;
import com.suctan.huigang.mvp.login.myChiken.MyChikenPresenter;
import com.suctan.huigang.mvp.login.myChiken.MyChikenView;
import com.suctan.huigang.widget.dialog.AlertDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by B-305 on 2017/4/13.我的厨房
 */

public class MykitchenActity extends MvpActivity<MyChikenPresenter> implements View.OnClickListener, MyChikenView, MykitchenAdaper.MykitchenOnClickListener {
    private static final String TAG = "MykitchenActity";
    /*private  ListView lv;*/
    private ImageView Mykitchen_back;
    GridView Mykitchen_gridview;
    private ImageView chiken_UserHead;//头像
    private ArrayList<MykitchenBean> mykitchenList = new ArrayList<>();
    boolean initFirstmykitchen;
    MykitchenAdaper mykitchenAdaper;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_kitchen);
        MyAddChiken();
        initView();
        initViewData();

    }


    @Override
    protected MyChikenPresenter createPresenter() {
        return new MyChikenPresenter(this);
    }

    private void initView() {
        Mykitchen_gridview = (GridView) findViewById(R.id.Mykitchen_gridview);
        Mykitchen_back = (ImageView) findViewById(R.id.Mykitchen_back);  //定义一个返回按钮
        chiken_UserHead = (ImageView) findViewById(R.id.chiken_UserHead);
//监听
        Mykitchen_back.setOnClickListener(this);
    }

    private void initViewData() {
        if (CurrentUser.getInstance().getUserBean().getUser_icon() != null) {
            LoadImageManager.getImageLoader().displayImage(CurrentUser.getInstance().getUserBean().getUser_icon(), chiken_UserHead);
        }

    }


    @Override
    public void getMakeOrderList(ArrayList<MykitchenBean> mykitchenBeenlist) {
        Log.i(TAG, "getMakeOrderList: ");
        this.mykitchenList.addAll(mykitchenBeenlist);
        if (!initFirstmykitchen) {
            mykitchenAdaper = new MykitchenAdaper(this, mykitchenList);
            mykitchenAdaper.onDetailOnclick(MykitchenActity.this);
            Mykitchen_gridview.setAdapter(mykitchenAdaper);
            initFirstmykitchen = true;
        } else {
            mykitchenAdaper.setDataChange(mykitchenBeenlist);
        }
    }

    /**
     *  删除我的厨房菜品成功后的回调
     */
    @Override
    public void Deletelreturn(int orderID) {

        for (int i = 0; i < mykitchenList.size(); i++) {
            if (mykitchenList.get(i).getOrder_id() == orderID) {
                mykitchenList.remove(mykitchenList.get(i));
                break;
            }
        }
        if (mykitchenList != null) {
            mykitchenAdaper.setDataChange(mykitchenList);
        }
    }

    @Override
    public void deleteMakeOrder(int status, String msg) {
        ToastTool.showToast(msg, status);
    }

    private void MyAddChiken() {
        Map<String, Object> map = new HashMap<>();
        map.put("user_token", TokenManager.getToken());
        mvpPresenter.AddChiken(map);
    }
//    public  void   deleteMakeFood(int id) {
//        Map <String,Object>map=new HashMap<>();
//        map.put("user_token", TokenManager.getToken());
//        map.put("order_id", id);
//        mvpPresenter.deleteMakeFood(map);
//    }



    /*这个功能是我的厨房中的发布按钮弹出的popupwindow*/
    public void showPopFormBottom(View view) {
        my_kitchen_popupwin_release my_kitchen_popupwin_release = new my_kitchen_popupwin_release(this);
        my_kitchen_popupwin_release.showAtLocation(findViewById(R.id.main_view), Gravity.CENTER, 0, 0);

        my_kitchen_popupwin_release.setAddChiChenLisetner(new my_kitchen_popupwin_release.AddKitChen() {
            @Override
            public void AddToDayFood() {
                Intent gotoTodayfood = new Intent(MykitchenActity.this, release_todayfood_Activiity.class);
                startActivity(gotoTodayfood);
            }

            @Override
            public void AddNewFood() {
                Intent gotoNewfood = new Intent(MykitchenActity.this, release_new_todayfoodActivity.class);
                startActivity(gotoNewfood);
            }

            public void AlreadyFood() {
                Intent gotoaleadyfood = new Intent(MykitchenActity.this, Already_release_today_foodActivity.class);
                startActivity(gotoaleadyfood);

            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Mykitchen_back:
                finish();
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

    //    public void getMyChikenData() {
//        Map<String, Object> myChikenMap = new HashMap<String, Object>();
//
//    }
    private AlertDialog mDialog;
    private void showDeletelTip(final int position) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("警告")
                .setMessage("确定要删除该菜色嘛！")
                .setTopImage(R.drawable.icon_tanchuang_tanhao)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDialog.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDialog.dismiss();
                        requstDeleteFood(position);
                    }
                });
        mDialog = builder.create();
        mDialog.show();
    }
//        final TipsCancelOrderDialog cancelDialog = new TipsCancelOrderDialog(this);
//        cancelDialog.setTipClickLisener(new TipsCancelOrderDialog.OnTipLisetner() {
//            @Override
//            public void comfirm() {
//                cancelDialog.dismiss();
//                requstDeleteFood(position);
//            }
//
//            @Override
//            public void cancel() {
//                cancelDialog.dismiss();
//            }
//        });
//        cancelDialog.show();



    //请求删除菜色
    private void requstDeleteFood(int position) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_token", TokenManager.getToken());
        map.put("order_id", mykitchenList.get(position).getOrder_id());
        mvpPresenter.deleteMakeFood(map, mykitchenList.get(position).getOrder_id());
    }


    @Override
    public void onItemDeleteOnClick(int position) {
        showDeletelTip(position);
    }
}
