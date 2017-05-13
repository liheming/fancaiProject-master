package com.suctan.huigang.mvp.login.wanteat;

import com.example.androidbase.mvp.BaseMvpView;
import com.suctan.huigang.bean.index.EatFoodBean;
import com.suctan.huigang.bean.wanteat.WantEatOtherComment;

import java.util.ArrayList;

/**
 * Created by Tom on 2017/4/11.
 */

public interface WantEatView extends BaseMvpView {
    void postWantEatSuc();

    void postWantEatFail();

    void getOtherWantEatListSuc(ArrayList<EatFoodBean> eatFoodBeanList);

    void addCartSuc();

    void getOtherWantEatListFail();

    void getOtherWantCommentListSuc(ArrayList<WantEatOtherComment> wantEatOtherCommentList);

    void getOtherWantCommentListFail();

}
