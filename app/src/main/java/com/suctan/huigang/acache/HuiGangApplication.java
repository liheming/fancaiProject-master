package com.suctan.huigang.acache;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.example.androidbase.BaseApplication;

import org.xutils.x;

/**
 * Created by Administrator on 2017/3/20.
 */

public class HuiGangApplication extends BaseApplication {
    @Override
    protected void otherInit(Context context) {
        x.Ext.init(this);
    }
    /** 复制下面方法 */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
