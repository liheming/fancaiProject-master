package com.suctan.huigang.mvp.login.myMydiscount;

import com.example.androidbase.mvp.BaseMvpView;
import com.suctan.huigang.bean.Mydiscount.MydiscountBean;

/**
 * Created by Tom on 2017/4/25.
 */

public interface MydiscountView extends BaseMvpView {
    void getMydiscountS(MydiscountBean mydiscountBean);
}
