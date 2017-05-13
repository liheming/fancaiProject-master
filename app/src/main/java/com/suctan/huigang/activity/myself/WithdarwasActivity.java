package com.suctan.huigang.activity.myself;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidbase.mvp.MvpActivity;
import com.example.androidbase.utils.ToastTool;
import com.suctan.huigang.R;
import com.suctan.huigang.acache.TokenManager;
import com.suctan.huigang.mvp.login.my_wallet_tx.tx_walletPresenter;
import com.suctan.huigang.mvp.login.my_wallet_tx.tx_walletView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by B-305 on 2017/4/13.
 */

public class WithdarwasActivity extends MvpActivity<tx_walletPresenter> implements View.OnClickListener,tx_walletView {
    String money = "";
    double s1;
    private ImageView withdarwas_back;
    @BindView(R.id.btnwitharwals)
    Button btnwitharwals;
    @BindView(R.id.witharwals)
    EditText witharwals;
    @BindView(R.id.all_money)
    TextView all_money;
    @BindView(R.id.select_AllMoney)
    TextView select_AllMoney;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.withdarwas);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        //提现页面的返回按钮
        withdarwas_back = (ImageView) findViewById(R.id.withdarwas_back);
        withdarwas_back.setOnClickListener(this);
        //提现页面的确认按钮
        btnwitharwals.setOnClickListener(this);
        select_AllMoney.setOnClickListener(this);
        //得到从我的钱包的页面调回来的参数 多少钱
        Intent intent=getIntent();
        money = intent.getStringExtra("money");
        all_money.setText(money);
        s1 = Double.parseDouble(money);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.withdarwas_back:
                finish();
                break;
            case R.id.btnwitharwals:
                ToastTool.showToast("我被点击了 " ,2);
                witharwalsVariety();
                break;

            case R.id.select_AllMoney:
                witharwals.setText(money);
                break;

        }

    }

    private void witharwalsVariety() {
        double s;
        String token = TokenManager.getToken();
        String outmoney = witharwals.getText().toString().trim();
        if (TextUtils.isEmpty(outmoney)){
            ToastTool.showToast("你还没有输入提现的数字,谢谢!",1);
            return;
        }else{
        s=Double.parseDouble(outmoney);
            if(s1>=s) {
                Map<String, Object> map = new HashMap<>();
                map.put("user_token", token);
                map.put("money", outmoney);
                mvpPresenter.withrawalsAction(map);
            }else{
                ToastTool.showToast("你并没有这么多钱!",0);
            }
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
    public void loadCourseDone() {
        ToastTool.showToast("提现成功回调 此处该跳转",1);
        startActivity(new Intent(this,MymoneyActivity.class));

    }

    @Override
    protected tx_walletPresenter createPresenter() {
        return new tx_walletPresenter(this);
    }
}