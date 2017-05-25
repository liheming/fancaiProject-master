package com.suctan.huigang.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.suctan.huigang.R;
import com.suctan.huigang.activity.login.LoginActivity;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends Activity {
	String TAG = "GuideActivity";
	ImageButton tv_liji;
	private ViewPager vpGuide;
	private LinearLayout llGuide;
	private int currIndex = -1;

	private static int[] imagesId = new int[] { R.drawable.one,
			R.drawable.two, R.drawable.three,R.drawable.four };

	private List<ImageView> imageViewList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_guide);
		tv_liji = (ImageButton) findViewById(R.id.tv_liji);
		tv_liji.setVisibility(View.GONE);
		vpGuide = (ViewPager) findViewById(R.id.vp_guide);
		llGuide = (LinearLayout) findViewById(R.id.ll_guide);
		initData();
		vpGuide.setAdapter(new myGuideAdapter());
		tv_liji.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(GuideActivity.this, LoginActivity.class));
				Log.i(TAG, "onClick: ");
				finish();
			}
		});

		currIndex = 0;
		vpGuide.setCurrentItem(currIndex);
		llGuide.getChildAt(0)
				.setBackgroundResource(R.drawable.move_point_shape);
		vpGuide.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				switch (arg0) {
				case 0:
					llGuide.getChildAt(0).setBackgroundResource(
							R.drawable.move_point_shape);
					llGuide.getChildAt(1).setBackgroundResource(
							R.drawable.guide_point_shape);
					llGuide.getChildAt(2).setBackgroundResource(
							R.drawable.guide_point_shape);
					llGuide.getChildAt(3).setBackgroundResource( +

							R.drawable.guide_point_shape);
					tv_liji.setVisibility(View.GONE);
					break;
				case 1:
					llGuide.getChildAt(0).setBackgroundResource(
							R.drawable.guide_point_shape);
					llGuide.getChildAt(1).setBackgroundResource(
							R.drawable.move_point_shape);
					llGuide.getChildAt(2).setBackgroundResource(
							R.drawable.guide_point_shape);
					tv_liji.setVisibility(View.GONE);
					break;
				case 2:
					llGuide.getChildAt(0).setBackgroundResource(
							R.drawable.guide_point_shape);
					llGuide.getChildAt(1).setBackgroundResource(
							R.drawable.guide_point_shape);
					llGuide.getChildAt(2).setBackgroundResource(
							R.drawable.move_point_shape);
					tv_liji.setVisibility(View.GONE);

					break;
					case 3:
						llGuide.getChildAt(0).setBackgroundResource(
								R.drawable.guide_point_shape);
						llGuide.getChildAt(1).setBackgroundResource(
								R.drawable.guide_point_shape);
						llGuide.getChildAt(2).setBackgroundResource(
								R.drawable.guide_point_shape);
						llGuide.getChildAt(3).setBackgroundResource(
								R.drawable.move_point_shape);
						tv_liji.setVisibility(View.VISIBLE);
						break;
				default:
					break;
				}
				currIndex = arg0;
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});

	}

	private void initData() {
		imageViewList = new ArrayList<>();
		MyLayoutParams layoutParams = new MyLayoutParams(10, 10);

		for (int i = 0; i < imagesId.length; i++) {
			ImageView imageView = new ImageView(this);
			imageView.setBackgroundResource(imagesId[i]);
			imageViewList.add(imageView);
		}

		for (int i = 0; i < imagesId.length; i++) {
			View view = new View(this);
			view.setBackgroundResource(R.drawable.guide_point_shape);
			if (i > 0) {
				layoutParams.leftMargin = 10;
			}
			view.setLayoutParams(layoutParams);

			llGuide.addView(view);
		}

	}

	class MyLayoutParams extends LinearLayout.LayoutParams {

		public MyLayoutParams(int arg0, int arg1) {
			super(arg0, arg1);
		}
	}

	private class myGuideAdapter extends PagerAdapter {

		@Override
		public int getCount() {

			return imagesId.length;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public Object instantiateItem(View container, int position) {
			((ViewGroup) container).addView(imageViewList.get(position));
			return imageViewList.get(position);
		}

		@Override
		public void destroyItem(View container, int position, Object object) {
			((ViewGroup) container).removeView((View) object);
		}

	}
}
