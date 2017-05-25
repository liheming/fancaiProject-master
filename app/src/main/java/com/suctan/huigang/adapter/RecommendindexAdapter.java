package com.suctan.huigang.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.LatLng;
import com.example.androidbase.BaseApplication;
import com.example.androidbase.LoadImageManager;
import com.suctan.huigang.R;
import com.suctan.huigang.acache.CurrentUser;
import com.suctan.huigang.bean.index.EatFoodBean;
import com.suctan.huigang.numlib.AnimShopButton;
import com.suctan.huigang.numlib.IOnAddDelListener;

import java.util.ArrayList;

/**
 * Created by Tom on 2017/4/14.
 */

public class RecommendindexAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<EatFoodBean> eatFoodBeanArrayList;

    public RecommendindexAdapter(Context context, ArrayList<EatFoodBean> eatFoodBeanArrayList) {
        this.context = context;
        this.eatFoodBeanArrayList = eatFoodBeanArrayList;
    }

    @Override
    public int getCount() {
        return eatFoodBeanArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return eatFoodBeanArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        GridViewHolder holder = null;
        EatFoodBean mFoodBean = eatFoodBeanArrayList.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_today_food_release, parent, false);
            holder = new GridViewHolder();
            convertView.setTag(holder);
            holder.ItemImage = (ImageView) convertView.findViewById(R.id.recommend_today_ItemImage);
            holder.recommend_today_ItemTitle = (TextView) convertView.findViewById(R.id.recommend_today_ItemTitle);
            holder.recommend_today_descript = (TextView) convertView.findViewById(R.id.recommend_today_descript);
            holder.recommend_today_ItemMoney = (TextView) convertView.findViewById(R.id.recommend_today_ItemMoney);
            holder.location = (TextView) convertView.findViewById(R.id.location);

            holder.btn_book_eatfood = (AnimShopButton) convertView.findViewById(R.id.btn_book_eatfood);
        } else {
            holder = (GridViewHolder) convertView.getTag();
        }
        if (mFoodBean.getOrder_pic() != null) {
            LoadImageManager.getImageLoader().displayImage(mFoodBean.getOrder_pic(), holder.ItemImage);
        }
        double d1 = CurrentUser.getInstance().getUserBean().getLatitude();
        double d2 = CurrentUser.getInstance().getUserBean().getLongitude();
        double d3 = mFoodBean.getLatitude();
        double d4 = mFoodBean.getLongitude();
        LatLng startLatlng = new LatLng(d1,d2);
        LatLng  endLatlng =  new LatLng(d3,d4);
        int juli = (int) AMapUtils.calculateLineDistance(startLatlng, endLatlng); //来计算两点距离，单位：米。
        holder.location.setText("距离"+juli+"米");
        Log.i("123132132", "getView: "+"距离"+juli+"米");
        holder.recommend_today_ItemTitle.setText(mFoodBean.getOrder_title());
        holder.recommend_today_descript.setText(mFoodBean.getFood_description());
        holder.recommend_today_ItemMoney.setText("￥" + mFoodBean.getOrder_price());
        holder.btn_book_eatfood.setCount(0);
        holder.btn_book_eatfood.setMaxCount(mFoodBean.getNum());
        holder.btn_book_eatfood.setHintText("加入购物车");
        holder.btn_book_eatfood.setOnAddDelListener(new IOnAddDelListener() {

            @Override
            public void onAddSuccess(int count) {
                Toast.makeText(BaseApplication.getContext(), "获得当前前添加的购物车数量"+count, Toast.LENGTH_LONG).show();

                RecommendListener.addCartItemListener(position,count);
            }

            @Override
            public void onAddFailed(int count, FailType failType) {





            }

            @Override
            public void onDelSuccess(int count) {
                Toast.makeText(BaseApplication.getContext(), "获得当前前删除的购物车数量"+count, Toast.LENGTH_LONG).show();

                RecommendListener.deleteCartItemListener(position, count);
            }

            @Override
            public void onDelFaild(int count, FailType failType) {

            }
        });
        return convertView;
    }

    public class GridViewHolder {
        ImageView ItemImage;
        TextView recommend_today_ItemTitle;
        TextView location;
        TextView recommend_today_descript;
        TextView recommend_today_ItemMoney;
        AnimShopButton btn_book_eatfood;
    }


    Recommend RecommendListener;

    public void setRecomendOnclickLisner(Recommend RecommendListener) {
        this.RecommendListener = RecommendListener;
    }

    public interface Recommend {
        void addCartItemListener(int addCartPosition, int count);

        void deleteCartItemListener(int deleteCartPosition, int count);
    }
}
