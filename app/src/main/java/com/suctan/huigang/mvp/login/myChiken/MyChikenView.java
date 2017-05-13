package com.suctan.huigang.mvp.login.myChiken;

import com.example.androidbase.mvp.BaseMvpView;
import com.suctan.huigang.bean.user.MykitchenBean;

import java.util.ArrayList;

/**
 * Created by Tom on 2017/4/11.
 */


public interface MyChikenView extends BaseMvpView {
    void getMakeOrderList(ArrayList<MykitchenBean> mykitchenBeenlist);
    void Deletelreturn(int orderID);
    void deleteMakeOrder(int status ,String msg);
}
