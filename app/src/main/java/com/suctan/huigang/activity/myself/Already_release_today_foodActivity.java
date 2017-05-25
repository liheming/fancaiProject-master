package com.suctan.huigang.activity.myself;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.androidbase.mvp.MvpActivity;
import com.example.androidbase.utils.ToastTool;
import com.suctan.huigang.R;
import com.suctan.huigang.acache.TokenManager;
import com.suctan.huigang.adapter.MykitAlreadychenAdaper;
import com.suctan.huigang.bean.user.MykitchenBean;
import com.suctan.huigang.mvp.login.myChiken.MyAlreadyChikenPresenter;
import com.suctan.huigang.mvp.login.myChiken.MyAlreadyChikenView;
import com.suctan.huigang.widget.TipsCancelOrderDialog;
import com.suctan.huigang.widget.dialog.AlertDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by B-305 on 2017/4/22. 已上架在今日菜色 显示在推荐首页的
 */

public class Already_release_today_foodActivity extends MvpActivity<MyAlreadyChikenPresenter> implements View.OnClickListener ,MyAlreadyChikenView , MykitAlreadychenAdaper.MykitchenOnClickListener {
    private ImageView Alreadyback;
    private ImageView Mykitchen_back;
    GridView Myalreadykitchen_gridview;

    private ArrayList<MykitchenBean> mykitchenList = new ArrayList<>();
    boolean initFirstmykitchen;
    MykitAlreadychenAdaper mykitAlreadychenAdaper;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.already_release_today_food);
        initView();
        lookAlreadyChiken();
    }

    @Override
    protected MyAlreadyChikenPresenter createPresenter() {
        return new MyAlreadyChikenPresenter(this);
    }

    private void lookAlreadyChiken() {
        Map<String,Object> map=new HashMap<>();
        map.put("user_token", TokenManager.getToken());
        mvpPresenter.lookAlreadyChiken(map);
    }
    private void initView() {
        Alreadyback = (ImageView) findViewById(R.id.AlreadyBack);
        Myalreadykitchen_gridview = (GridView) findViewById(R.id.Myalreadykitchen_gridview);

    }


    @Override
    public void onClick(View v) {
     switch (v.getId()){
         case R.id.AlreadyBack:
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

    private void goMykitchenAdapter() {

        mykitAlreadychenAdaper = new MykitAlreadychenAdaper(this,mykitchenList);
        mykitAlreadychenAdaper.onDetailOnclick(Already_release_today_foodActivity.this);
        Myalreadykitchen_gridview.setAdapter(mykitAlreadychenAdaper);

        /*ArrayList<MykitchenBean> companys = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            MykitchenBean mykitchenBean = new MykitchenBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492091993911&di=804ff682760b588e56abfc96f9d43ecd&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F13%2F82%2F51%2F77P58PICFKD_1024.jpg");
            companys.add(mykitchenBean);
        }
        MykitchenAdaper adapter = new MykitchenAdaper(this, companys);
        Mykitchen_gridview.setAdapter(adapter);*/
    }



    @Override
    public void getMakeOrderList(ArrayList<MykitchenBean> mykitchenBeen) {
        ToastTool.showToast("获取数据成功" ,1);
        this.mykitchenList.addAll(mykitchenBeen);
        if (!initFirstmykitchen){
            goMykitchenAdapter();
            initFirstmykitchen = true;
        }else {
            mykitAlreadychenAdaper.notifyDataSetChanged();
        }
    }

    @Override
    public void downlreturn(int orderID) {
        for (int i = 0; i < mykitchenList.size(); i++) {
            if (mykitchenList.get(i).getOrder_id() == orderID) {
                mykitchenList.remove(mykitchenList.get(i));
                break;
            }
        }
        if (mykitchenList != null) {
            mykitAlreadychenAdaper.setDataChange(mykitchenList);
        }
    }


    //请求下架菜色
    private void requstDeleteFood(int position) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_token", TokenManager.getToken());
        map.put("order_id", mykitchenList.get(position).getOrder_id());
        String TAG = "MykitchenActity";
        mvpPresenter.DownMakeFood(map, mykitchenList.get(position).getOrder_id());
    }



    private AlertDialog mDialog;
    private void showDownlTip(final int position) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示")
                .setMessage("确定要下架该菜色嘛！")
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




//    private void showDownlTip(final int position) {
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
//    }

    @Override
    public void downMakeOrder(int status, String msg) {
        ToastTool.showToast(msg ,status);
    }

    @Override
    public void onItemDownOnClick(int position) {
        showDownlTip(position);
    }
}
