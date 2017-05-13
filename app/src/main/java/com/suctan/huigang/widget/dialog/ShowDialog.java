package com.suctan.huigang.widget.dialog;

import android.content.Context;
import android.content.DialogInterface;

import com.suctan.huigang.R;

/**
 * Created by haily on 2017/5/2.
 */

public class ShowDialog {
    Context context;

    ShowDialog(Context context) {
        this.context = context;

    }

    private static AlertDialog mDialog;
    public void show(String title ,String message ) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

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
//                        requstDeleteFood(position);
                    }
                });
        mDialog = builder.create();
        mDialog.show();
    }


}
