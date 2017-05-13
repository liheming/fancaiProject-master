package com.suctan.huigang.mvp.login.myChiken;

import com.example.androidbase.mvp.BaseMvpView;
import com.suctan.huigang.bean.user.MykitchenBean;

import java.util.ArrayList;

/**
 * Created by Tom on 2017/4/11.
 */


public interface UploadFoodView extends BaseMvpView {
    void getUnloadOrderList(ArrayList<MykitchenBean> mykitchenBeenlist);
    void Uploadreturn(int status , String msg);
//    void deleteMakeOrder(int status, String msg);
}
