package com.suctan.huigang.adapter.order.buyorder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.suctan.huigang.R;
import com.suctan.huigang.bean.wanteat.WantEatFoodItem;

import java.util.ArrayList;

/**
 * Created by 黄淑翰 on 2017/4/22.
 */
public class BuyDetailItemAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<WantEatFoodItem> wantEatItemList;

    public BuyDetailItemAdapter(Context context, ArrayList<WantEatFoodItem> wantEatItemList) {
        this.context = context;
        this.wantEatItemList = wantEatItemList;
    }

    @Override
    public int getCount() {
        return wantEatItemList.size();
    }

    @Override
    public Object getItem(int i) {
        return wantEatItemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyViewHolder holder = null;
        WantEatFoodItem wantEatFoodItem = wantEatItemList.get(i);
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.activity_dowant_item, viewGroup, false);
            holder = new MyViewHolder();
            holder.tv_food_name = (TextView) view.findViewById(R.id.tv_food_name);
            holder.tv_food_num = (TextView) view.findViewById(R.id.tv_food_num);
            holder.tv_food_type = (TextView) view.findViewById(R.id.tv_food_type);
            view.setTag(holder);
        } else {
            holder = (MyViewHolder) view.getTag();
        }
        String orderType = "";
        if (wantEatFoodItem.getFoodType() != -1) {
            if (wantEatFoodItem.getFoodType() == 0) {
                orderType = "(小份)";
            } else if (wantEatFoodItem.getFoodType() == 1) {
                orderType = "(中份)";
            } else {
                orderType = "(大份)";
            }
        }
        holder.tv_food_name.setText(wantEatFoodItem.getFoodName() );
        holder.tv_food_num.setText(wantEatFoodItem.getFoodCount()+"");
        holder.tv_food_type.setText(orderType);
        return view;
    }

    class MyViewHolder {
        TextView tv_food_name;
        TextView tv_food_num;
        TextView tv_food_type;
    }
}
