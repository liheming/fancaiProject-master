package com.suctan.huigang.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.suctan.huigang.R;

/**
 * Created by B-305 on 2017/4/16.
 */

public class ZeroFragment extends Fragment{

    //这个fragment对应这个，我卖出的中的两个选项栏中的进行中
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //加载布局，换成fragment所对应的布局，而这个fragment_view布局，是一个临时调试的布局。
        return inflater.inflate(R.layout.zerofragment, container, false);
    }
}
