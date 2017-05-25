package com.suctan.huigang.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.suctan.huigang.R;


public  class TakePhotoPopWin extends PopupWindow {
    String TAG = "TakePhotoPopWin";
TextView user_account ,total_money, pay_type;
    EditText ed_pay;
    private Context mContext;

    private View view;

    public void setPay_type(String pay_type) {
        this.pay_type.setText(pay_type);
    }

    public void setTotal_money(String total_money) {
        this.total_money.setText(total_money);
    }

    public void setUser_account(String user_account) {
        this.user_account.setText(user_account);
    }


    public TakePhotoPopWin(Context mContext, View.OnClickListener itemsOnClick) {

        view = LayoutInflater.from(mContext).inflate(R.layout.layout_popupwindow, null);
        user_account = (TextView) view.findViewById(R.id.user_account);
        total_money = (TextView) view.findViewById(R.id.total_money);
        pay_type = (TextView) view.findViewById(R.id.pay_type);
        ed_pay = (EditText) view.findViewById(R.id.ed_pay);
        ed_pay.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i(TAG, "onTextChanged: "+start);
                if (start == 5) {
                    payInterface.paySuccess();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i(TAG, "afterTextChanged: ");

            }
        });

        ImageView cancle = (ImageView) view.findViewById(R.id.cancle);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        // 设置外部可点击
     /*   this.setOutsideTouchable(true);*/
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
      /*  this.view.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = view.findViewById(R.id.pop_layout).getTop();

                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });*/


        /* 设置弹出窗口特征 */
        // 设置视图
        this.setContentView(this.view);
        // 设置弹出窗体的宽和高
        this.setHeight(RelativeLayout.LayoutParams.MATCH_PARENT);
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);

        // 设置弹出窗体可点击
        this.setFocusable(true);

        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 设置弹出窗体的背景
        this.setBackgroundDrawable(dw);

        // 设置弹出窗体显示时的动画，从底部向上弹出
        this.setAnimationStyle(R.style.take_photo_anim);

    }

        PayInterface payInterface = null;
     public interface PayInterface {
        void paySuccess();
    }
    public void onLisener(PayInterface payInterface) {
        this.payInterface = payInterface;

    }
}
