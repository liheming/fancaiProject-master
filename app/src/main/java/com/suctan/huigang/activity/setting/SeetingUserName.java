package com.suctan.huigang.activity.setting;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.suctan.huigang.R;
import com.suctan.huigang.activity.BaseActivity;
import com.suctan.huigang.activity.myself.SettingActivity;

/**
 * Created by 黄淑翰 on 2017/4/13.
 */
public class SeetingUserName extends BaseActivity implements View.OnClickListener {
    private static final int resultUserName = 1003;//获取用户名
    private static final int requestUserVeriatyBody = 1004;//验证身份
    private   int rescode = 1003;//验证身份
    private ImageView imv_UserName_back;
    private EditText edt_changeUserName;
    private TextView head_title;
    private Button btn_changUserName_comfirm;
    private String tempUserName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_username);
        initView();
        getIntentData();
    }

    private void initView() {
        head_title = (TextView) findViewById(R.id.head_title);

        imv_UserName_back = (ImageView) findViewById(R.id.imv_UserName_back);
        edt_changeUserName = (EditText) findViewById(R.id.edt_changeUserName);
        btn_changUserName_comfirm = (Button) findViewById(R.id.btn_changUserName_comfirm);

        //实现监听
        imv_UserName_back.setOnClickListener(this);
        btn_changUserName_comfirm.setOnClickListener(this);
    }


    private void getIntentData() {
        Intent intent = getIntent();
        tempUserName =  intent.getStringExtra("oldData");
        rescode = intent.getIntExtra("title", 1003);
        // FIXME: 2017/5/5  显示标题
        if (rescode == 1003) {
        head_title.setText("修改用户名");//设置页面title

        } else {
        head_title.setText("实名认证");//设置页面title
        }
        setHoppyEdit();
    }

    //设置输入框的数据
    private void setHoppyEdit() {
        if (tempUserName == null) {
            return;
        }
        edt_changeUserName.setText(tempUserName);
        edt_changeUserName.setSelection(tempUserName.length());
    }

    //设置回调数据
    private void setResultData(String temAgeString) {
        Intent intent = new Intent(this, SettingActivity.class);
        intent.putExtra("newData", temAgeString);
        if (rescode == 1003) {
            setResult(resultUserName, intent);
        } else {
            setResult(requestUserVeriatyBody, intent);
        }

        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imv_UserName_back:
                finish();
                break;
            case R.id.btn_changUserName_comfirm:
                tempUserName = edt_changeUserName.getText().toString();
                setResultData(tempUserName);
                break;
        }
    }

}
