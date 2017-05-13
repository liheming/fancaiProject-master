package com.suctan.huigang.mvp.login.Addaddress;

import com.example.androidbase.mvp.BaseMvpView;
import com.suctan.huigang.bean.address.AddressBean;

import java.util.ArrayList;

/**
 * Created by B-305 on 2017/4/20.
 */

public interface addressView extends BaseMvpView {
    void addAdressSuc(int id);

    void addAdressFail();

    void getAddressListSuc(ArrayList<AddressBean> addressBeenList);

    void getAddressListFail();

    void changeAddressSuc();

    void changeAddressFail();

}
