package com.suctan.huigang.mvp.login.myChiken;

import com.example.androidbase.mvp.BaseMvpView;
import com.suctan.huigang.bean.user.MykitchenBean;

import java.util.ArrayList;

/**
 * Created by Tom on 2017/4/11.
 */


public interface MyAlreadyChikenView extends BaseMvpView {

    void getMakeOrderList(ArrayList<MykitchenBean> mykitchenBeen);
    void downlreturn(int orderID);
    void downMakeOrder(int status ,String msg);

}
