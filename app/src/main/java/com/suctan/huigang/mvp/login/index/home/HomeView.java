package com.suctan.huigang.mvp.login.index.home;

import com.example.androidbase.mvp.BaseMvpView;
import com.suctan.huigang.bean.index.EatFoodReturn;

/**
 * Created by Administrator on 2017/3/4.
 */

public interface HomeView extends BaseMvpView {
    void getRollViewListSuc(EatFoodReturn mEatFoodReturn);

    void getRollViewListFail();


    void getEatfoodListSuc(EatFoodReturn mEatFoodReturn);

    void getEatfoodListFail();

}
