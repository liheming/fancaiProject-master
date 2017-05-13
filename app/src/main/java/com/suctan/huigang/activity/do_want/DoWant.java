package com.suctan.huigang.activity.do_want;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.androidbase.mvp.MvpActivity;
import com.jaeger.library.StatusBarUtil;
import com.suctan.huigang.R;
import com.suctan.huigang.adapter.dofood.DofoodAdapter;
import com.suctan.huigang.bean.dowant.DoWantOrderBean;
import com.suctan.huigang.mvp.login.dowant.DoWantPresenter;
import com.suctan.huigang.mvp.login.dowant.DoWantView;

import java.util.ArrayList;

/**
 * Created by Tom on 2017/4/12.
 */

public class DoWant extends MvpActivity<DoWantPresenter> implements DoWantView, View.OnClickListener {
    private GridView lv_wantdo_list;
    private ArrayList<DoWantOrderBean> dowantOrderList = new ArrayList<>();
    private boolean isFirstCreateAdapter;
    private ImageButton imvb_doeat_back;
    private TextView tv_head;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.header_screen);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary), 0);
        initVew();
        getDoWantEatList();
    }

    private void getDoWantEatList() {
        mvpPresenter.getDoWantList();
    }

    @Override
    protected DoWantPresenter createPresenter() {
        return new DoWantPresenter(this);
    }

    private void initVew() {
        tv_head = (TextView) findViewById(R.id.tv_head);
        tv_head.setText("我要做饭");
        lv_wantdo_list = (GridView) findViewById(R.id.gridView);
        imvb_doeat_back = (ImageButton) findViewById(R.id.btnBack);
        imvb_doeat_back.setOnClickListener(this);


        lv_wantdo_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(DoWant.this, DoWantOrderDetatil.class);
                intent.putExtra("nowDoWantOrder", dowantOrderList.get(i));
                startActivity(intent);
            }
        });
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
    public void getDoWantOrderSuc(ArrayList<DoWantOrderBean> doWantOrderBeen) {

        initAdapter(doWantOrderBeen);


    }

    DofoodAdapter dofoodAdapter;

    private void initAdapter(ArrayList<DoWantOrderBean> mDowantList) {
        dowantOrderList.addAll(mDowantList);
        if (!isFirstCreateAdapter) {
            dofoodAdapter = new DofoodAdapter(this, mDowantList);
            lv_wantdo_list.setAdapter(dofoodAdapter);
            isFirstCreateAdapter=true;
        } else {
            dofoodAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getDoWantOrderFail() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                finish();
                break;

        }
    }
}
