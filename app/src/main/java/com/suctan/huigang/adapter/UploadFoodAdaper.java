package com.suctan.huigang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidbase.LoadImageManager;
import com.example.androidbase.utils.ToastTool;
import com.suctan.huigang.R;
import com.suctan.huigang.bean.user.MykitchenBean;
import com.suctan.huigang.numlib.AnimShopButton;
import com.suctan.huigang.numlib.IOnAddDelListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Tom on 2017/4/14.
 */

public class UploadFoodAdaper extends BaseAdapter {
    private int order_id = 0;
    private Context context;
    private ArrayList<MykitchenBean> companyList;
    Map<Integer, Integer> setIdAndNum = new HashMap<>();
    List listId = new ArrayList();

    public UploadFoodAdaper(Context context, ArrayList<MykitchenBean> companyList) {
        this.context = context;
        this.companyList = companyList;
    }

    @Override
    public int getCount() {
        return companyList.size();
    }

    @Override
    public Object getItem(int position) {
        return companyList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    MykitchenOnClickListener mykitchenOnClickListener;

    public void onDetailOnclick(MykitchenOnClickListener mykitchenOnClickListener) {
        this.mykitchenOnClickListener = mykitchenOnClickListener;
    }

    // 接口
    public interface MykitchenOnClickListener {
        void onItemDeleteOnClick(int position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        GridViewHolder holder = null;
        final MykitchenBean mykitchenBean = companyList.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_today_food_release, parent, false);
            holder = new GridViewHolder();
            holder.ItemImage = (ImageView) convertView.findViewById(R.id.recommend_today_ItemImage);
            holder.Mykitchen_ItemTitle = (TextView) convertView.findViewById(R.id.recommend_today_ItemTitle);
            holder.Mykitchen_ItemText = (TextView) convertView.findViewById(R.id.recommend_today_descript);
            holder.Mykitchen_ItemMoney = (TextView) convertView.findViewById(R.id.recommend_today_ItemMoney);
            holder.btn_book_eatfood = (AnimShopButton) convertView.findViewById(R.id.btn_book_eatfood);
            convertView.setTag(holder);
        } else {
            holder = (GridViewHolder) convertView.getTag();
        }
        if (mykitchenBean.getOrder_pic() != null) {
            LoadImageManager.getImageLoader().displayImage(mykitchenBean.getOrder_pic(), holder.ItemImage);
        }

        holder.Mykitchen_ItemTitle.setText(mykitchenBean.getOrder_title());
        holder.Mykitchen_ItemText.setText(mykitchenBean.getFood_description());
        holder.Mykitchen_ItemMoney.setText("￥" + (int) mykitchenBean.getOrder_price());
        holder.btn_book_eatfood.setHintText("上架今日菜色");
        order_id = mykitchenBean.getOrder_id();
        listId.add(order_id);
        holder.btn_book_eatfood.setMaxCount(10);
        holder.btn_book_eatfood.setOnAddDelListener(new IOnAddDelListener() {
            @Override
            public void onAddSuccess(int count) {
//                Toast.makeText(BaseApplication.getContext(), "获得当前前添加的购物车数量" + count, Toast.LENGTH_LONG).show();
                if (count != 0) {
                    if (count == 10) {
                        ToastTool.showToast("为了保证菜品得品质和餐馆的介入，一天内最多只能做10份",2);
                    }
                    setIdAndNum.put((Integer) listId.get(position+1), count);

                }
                uploadFood.uploadFoodListener(setIdAndNum);
//                    ToastTool.showToast("list id 和 num是" + setIdAndNum.toString()+"size是"+setIdAndNum.size()+"position是"+position,1);
                    System.out.println("list id 和 num是" + setIdAndNum.toString()+"size是"+setIdAndNum.size()+"position是"+position);

            }

            @Override
            public void onAddFailed(int count, FailType failType) {

            }

            @Override
            public void onDelSuccess(int count) {
//                Toast.makeText(BaseApplication.getContext(), "获得当前前删除的购物车数量" + count, Toast.LENGTH_LONG).show();

                if (count == 0) {
                    setIdAndNum.remove(listId.get(position+1));
                    uploadFood.uploadFoodListener(setIdAndNum);


                } else {
                    setIdAndNum.put((Integer) listId.get(position+1), count);
                    uploadFood.uploadFoodListener(setIdAndNum);
                }
//                ToastTool.showToast("list id 和 num是" + setIdAndNum.toString()+"size是"+setIdAndNum.size()+"position是"+position,1);
                System.out.println("list id 和 num是" + setIdAndNum.toString()+"size是"+setIdAndNum.size()+"position是"+position);
            }

            @Override
            public void onDelFaild(int count, FailType failType) {

            }
        });

        return convertView;
    }

    //
    public void setDataChange(ArrayList<MykitchenBean> mykitchenBeen) {
        this.companyList = mykitchenBeen;
        notifyDataSetChanged();
    }

    public class GridViewHolder {
        ImageView ItemImage;
        TextView Mykitchen_ItemTitle;
        TextView Mykitchen_ItemText;
        TextView Mykitchen_ItemMoney;
        AnimShopButton btn_book_eatfood;
    }

    UploadFood uploadFood;

    public void setRecomendOnclickLisner(UploadFood uploadFood) {
        this.uploadFood = uploadFood;
    }

    public interface UploadFood {//接口

        void uploadFoodListener(Map<Integer, Integer> setIdAndNum);

    }


}
