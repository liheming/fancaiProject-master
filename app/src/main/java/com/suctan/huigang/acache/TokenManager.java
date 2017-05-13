package com.suctan.huigang.acache;

import android.util.Log;

import com.example.androidbase.BaseApplication;
import com.example.androidbase.utils.ACache;

/**
 * Created by 黄淑翰 on 2017/4/14.
 */
public class TokenManager {
    public static final String TAG = "TokenManager";
    public static String getToken() {
        String token = ACache.get(BaseApplication.getContext()).getAsString("nowToken");
        if (token == null) {
            Log.i(TAG, "token==null");
        }
        return token;
    }

    public static void clearToken() {
        ACache acache = ACache.get(BaseApplication.getContext());
        if (acache.getAsString("nowToken") != null) {
            acache.remove("nowToken");
        }
    }


}
