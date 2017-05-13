package com.suctan.huigang.bean.user;

/**
 * Created by Administrator on 2016/11/11.
 */

public class Result {
    private int status;//请求结果状态
    private String msg;//返回信息
    private Users users;//返回用户数据

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

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


}
