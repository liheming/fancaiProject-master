package com.suctan.huigang.mvp.login;

import com.example.androidbase.mvp.BaseMvpView;
import com.suctan.huigang.bean.user.Users;


/**
 * create at 2017/3/23 16:30
 *
 * @author：LZH
 * @explain 登录数据回调接口
 */

public interface LoginView extends BaseMvpView {
    void loginMessageReturn(Users users);
    void loginGoMain();
    void LocationReturn(String msg, int status);
}
