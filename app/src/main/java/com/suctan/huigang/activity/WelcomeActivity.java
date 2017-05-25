package com.suctan.huigang.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.suctan.huigang.R;
import com.suctan.huigang.activity.login.LoginActivity;

import cn.bmob.v3.Bmob;

public class WelcomeActivity extends Activity {


	private   Boolean isFirstIn = false;

	private static final int TIME = 500;
	private static final int HOME = 1000;
	private static final int GUIDE = 1001;

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case GUIDE:
					GoGuide();

					break;

				case HOME:
					GoHome();
					break;
			}

		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		Bmob.initialize(this, "1fe47f6bb8ec6a3eb640c3617952b5a6");
		initView();
	}


	private void initView() {
		SharedPreferences sp = getSharedPreferences("hello", MODE_PRIVATE);
		isFirstIn = sp.getBoolean("isFirstIn", true);
		System.out.println(isFirstIn);
		if (!isFirstIn) {

			handler.sendEmptyMessageDelayed(HOME, TIME);

		} else {

			SharedPreferences.Editor editor = sp.edit();
			editor.putBoolean("isFirstIn", false);

			editor.commit();
			handler.sendEmptyMessageDelayed(GUIDE, TIME);
		}

	}

	void GoHome() {
		startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
		finish();
	}


	void GoGuide() {
		startActivity(new Intent(WelcomeActivity.this, GuideActivity.class));
		finish();
	}
}
