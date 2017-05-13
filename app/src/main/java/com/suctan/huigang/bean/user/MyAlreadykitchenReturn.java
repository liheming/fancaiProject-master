package com.suctan.huigang.bean.user;

import java.util.ArrayList;

/**
 * Created by Tom on 2017/4/24.
 */

public class MyAlreadykitchenReturn {

    private int status;
    private String msg;

    private ArrayList<MyAlreadykitchenBean> MykitchenBeanList;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<MyAlreadykitchenBean> getMykitchenBeanList() {
        return MykitchenBeanList;
    }

    public void setMykitchenBeanList(ArrayList<MyAlreadykitchenBean> mykitchenBeanList) {
        MykitchenBeanList = mykitchenBeanList;
    }
}
