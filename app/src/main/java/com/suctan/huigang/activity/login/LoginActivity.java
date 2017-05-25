package com.suctan.huigang.activity.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidbase.BaseApplication;
import com.example.androidbase.mvp.MvpActivity;
import com.example.androidbase.utils.ACache;
import com.example.androidbase.utils.NetConnectUtils;
import com.example.androidbase.utils.ToastTool;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.suctan.huigang.R;
import com.suctan.huigang.acache.CurrentUser;
import com.suctan.huigang.acache.TokenManager;
import com.suctan.huigang.activity.MainActivity;
import com.suctan.huigang.activity.ShopMap;
import com.suctan.huigang.activity.rememberPsw.SmoothCheckBox;
import com.suctan.huigang.activity.setting.SeetingFogetPwd;
import com.suctan.huigang.activity.signup.signupActivity;
import com.suctan.huigang.bean.user.Users;
import com.suctan.huigang.mvp.login.LoginPresener;
import com.suctan.huigang.mvp.login.LoginView;

import java.util.HashMap;
import java.util.Map;

import cn.bmob.v3.Bmob;

/**
 * create at 2017/3/23 17:23
 *
 * @author：LZH
 * @explain 用户登录
 */
public class LoginActivity extends MvpActivity<LoginPresener> implements LoginView, View.OnClickListener {

    //title
    private ImageView imgBack;
    private TextView txtTitle;
    private Button btnLogin,btn_signup;
    private TextView forgetpsw;
    private TextView shopMap;
    //edt
    private EditText edtUserName;
    private EditText edtPwd;
    private String TAG ="LoginActivity";
    private boolean xuanzhong;

    //记住密码
    private SmoothCheckBox scb;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        第一：默认初始化
        if ( CurrentUser.getInstance().getUserBean() ==null) {
            //保存于缓存中
            ACache aCache = ACache.get(BaseApplication.getContext());
//            从缓存中获取对象
        String userStr = aCache.getAsString("User");
        if (userStr != null) {
            Users userBean = new Gson().fromJson(userStr, Users.class);
            if (userBean != null) {
                CurrentUser.getInstance().setUserBean(userBean);
                Log.i(TAG, "缓存为空:  setUserBean 成功");
            }
        }
        }

      if (CurrentUser.getInstance().getUserBean()!=null){
          if (!TextUtils.isEmpty(TokenManager.getToken()) && CurrentUser.getInstance().getUserBean().getLatitude()!=0 && CurrentUser.getInstance().getUserBean().getLongitude()!=0) {
              goActivity();
              String token = TokenManager.getToken();
              ToastTool.showToast(token,2);
              Log.i(TAG, "onCreate: 用户信息是： "+ CurrentUser.getInstance().getUserBean());
              return;
          }
      }

        setContentView(R.layout.activity_login);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary), 0);

//        if (!TextUtils.isEmpty(token)) {
//            goActivity();
////            startActivity(new Intent(this , MainActivity.class));
//        }
//        mvpPresenter.getHelloText();
//        //
//        TelephonyManager TelephonyMgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
//        String szImei = TelephonyMgr.getDeviceId();
        initView();
    }

    @Override
    protected LoginPresener createPresenter() {
        return new LoginPresener(this);
    }

    private void initView() {


        imgBack = (ImageView) findViewById(R.id.logins_back);
        imgBack.setOnClickListener(this);

        //注册
        btn_signup = (Button) findViewById(R.id.btn_signup);
        btn_signup.setOnClickListener(this);

        //忘记密码
        forgetpsw = (TextView) findViewById(R.id.forgetpsw);
        forgetpsw.setOnClickListener(this);
        shopMap = (TextView) findViewById(R.id.shopMap);
        shopMap.setOnClickListener(this);

        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);

        //EditText
        edtUserName = (EditText) findViewById(R.id.edt_login_user_name);
        edtPwd = (EditText) findViewById(R.id.edt_login_user_pwd);

        //记住密码
        sp = getSharedPreferences("config", MODE_PRIVATE);
        scb = (SmoothCheckBox) findViewById(R.id.remember_pass);
        //获取sp里面存储的数据
        String savedphone= sp.getString("phone", "");
        String savedPassword = sp.getString("password", "");
        boolean saveremenber = sp.getBoolean("xuanzhong",false);
        edtUserName.setText(savedphone);
        edtPwd.setText(savedPassword);
        scb.setChecked(saveremenber);
    }

    /**
     * created at 2017/3/23 17:22
     *
     * @param canClick true 可以点击 false 不可点击
     * @explain 用来控制按钮是否可以点击, 避免多次点击请求
     */
    private void toogleBtnClickStyle(boolean canClick) {
        if (canClick) {
            btnLogin.setClickable(true);
        } else {
            btnLogin.setClickable(false);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logins_back:
                finish();
                break;

            case R.id.btn_login:
//              TODO 调试阶段，登陆任意放行
//                toogleBtnClickStyle(false);
//                LoginVariety("1", "1");
                Variety();
                break;
            case R.id.forgetpsw:
                Intent intentLogPwd = new Intent(this, SeetingFogetPwd.class);
                startActivity(intentLogPwd);
                break;
            case R.id.btn_signup:
                Intent intentsignup= new Intent(this, signupActivity.class);
                startActivity(intentsignup);
                break;

            case R.id.shopMap:
                Intent intent= new Intent(this, ShopMap.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * 判断用户名与密码是否为空，网路是否正常
     */
    private void Variety() {
        String userName = edtUserName.getText().toString().trim();
        String pwd = edtPwd.getText().toString().trim();
        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(this, getResources().getString(R.string.inputActTip), Toast.LENGTH_SHORT).show();
            toogleBtnClickStyle(true);

            return;
        } else if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(this, getResources().getString(R.string.inputPwdTip), Toast.LENGTH_SHORT).show();
            toogleBtnClickStyle(true);
            return;
        } else {
            if (NetConnectUtils.isNetConnected(LoginActivity.this)) {
//                System.out.println("你输入的手机号码和密码是" + userName + pwd);
                LoginVariety(userName, pwd);
            } else {
                ToastTool.showToast(getResources().getString(R.string.checkNetTip), 0);
                toogleBtnClickStyle(true);
            }
        }
    }

    /**
     * created at 2017/3/23 15:57
     *
     * @param userName 用户名
     * @param pwd      密码
     * @explain 用户登录验证
     */
    private void LoginVariety(String userName, String pwd) {
        Map<String, Object> mapLogin = new HashMap();
        mapLogin.put("user_phone", userName);
        mapLogin.put("user_pass", pwd);
        mvpPresenter.getLoginAction(mapLogin);
    }

    /**
     * 跳到主页面
     */
    private void goActivity() {
//        ToastTool.showToast(getResources().getString(R.string.loginSucTip), 1);
        Intent goMainIntent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(goMainIntent);
        finish();
    }

    @Override
    public void loginGoMain() {
        //获取正确的数据储存起来
        String account1 = edtUserName.getText().toString();
        String password1 = edtPwd.getText().toString();
        if (scb.isChecked()) {
            // 说明勾选框被选中了。把用户名和密码给记录下来
            // 获取到一个参数文件的编辑器。
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("phone", account1);
            editor.putString("password", password1);
            editor.putBoolean("xuanzhong",true);
            // 把数据给保存到sp里面
            editor.commit();
        }


    }

    /**
     * 定位成功
     * */
    @Override
    public void LocationReturn(String msg, int status) {
        Log.i(TAG, "LocationReturn: "+msg+status);
        ToastTool.showToast(msg,status);
    }

    @Override
    public void loginMessageReturn(Users users) {
        Log.i(TAG, "loginMessageReturn: 电话号码是 " +users.getUser_phone() );
        if (users != null) {
            if (CurrentUser.getInstance().getUserBean().getLongitude() != 0 && CurrentUser.getInstance().getUserBean().getLatitude() != 0) {
                Log.i(TAG, "onCreate: 用户位置信息已经存在 ");
        goActivity();
            } else {
                startActivityForResult(new Intent(this,ShopMap.class),1);
                Log.i(TAG, "onCreate: 去获取用户的位置信息");

            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "onActivityResult: 返回");
        if (requestCode == 1 && resultCode==2) {
            double latitude = data.getDoubleExtra("latitude", 0);
            double longitude = data.getDoubleExtra("longitude", 0);
            String area = data.getStringExtra("area");
            Map<String ,Object>  map =  new HashMap<>();
            map.put("user_token", TokenManager.getToken());
            map.put("longitude", longitude);
            map.put("latitude", latitude);
            map.put("area", area);
            mvpPresenter.getLocation(map);
            Log.i(TAG, "getIntentData: 执行mvp操作");

        }
    }

    @Override
    public void getDataFail(String msg) {
        toogleBtnClickStyle(true);
        ToastTool.showToast(msg, 0);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
