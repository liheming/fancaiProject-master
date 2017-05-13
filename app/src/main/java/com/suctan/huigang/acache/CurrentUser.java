package com.suctan.huigang.acache;


import com.suctan.huigang.bean.user.Users;

/**
 * Created by yao23 on 16/11/6.
 */
public class CurrentUser {
    public final static String TAG = "CurrentUser";
    private static CurrentUser ourInstance = new CurrentUser();

    public static CurrentUser getInstance() {

        return ourInstance;
    }

    private CurrentUser() {
    }

    private Users userBean;

    public Users getUserBean() {

        return userBean;
    }

    public void setUserBean(Users userBean) {
        this.userBean = userBean;

    }
}
