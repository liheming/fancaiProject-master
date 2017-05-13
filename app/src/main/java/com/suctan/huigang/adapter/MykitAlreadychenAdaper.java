package com.suctan.huigang.adapter;

/**
 * Created by haily on 2017/4/29.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidbase.LoadImageManager;
import com.suctan.huigang.R;
import com.suctan.huigang.bean.user.MykitchenBean;

import java.util.ArrayList;

/**
 * Created by Tom on 2017/4/14.
 */

public class MykitAlreadychenAdaper extends BaseAdapter {
    private int order_id = 0;
    private Context context;
    private ArrayList<MykitchenBean> companyList;

    public MykitAlreadychenAdaper(Context context,ArrayList<MykitchenBean> companyList){
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
    public interface MykitchenOnClickListener {
        void onItemDownOnClick(int position);
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        GridViewHolder holder= null;
        MykitchenBean mykitchenBean = companyList.get(position);
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.mykitchen_item,parent,false);

            holder = new GridViewHolder();
            holder.ItemImage= (ImageView) convertView.findViewById(R.id.Mykitchen_ItemImage);
            holder.Mykitchen_ItemTitle = (TextView) convertView.findViewById(R.id.Mykitchen_ItemTitle);
            holder.Mykitchen_ItemText= (TextView) convertView.findViewById(R.id.Mykitchen_ItemText);
            holder.Mykitchen_ItemMoney = (TextView) convertView.findViewById(R.id.Mykitchen_ItemMoney);
            holder.tv_operateAction = (TextView) convertView.findViewById(R.id.tv_operateAction);
            convertView.setTag(holder);
        }else {
            holder = (GridViewHolder) convertView.getTag();
        }
        if (mykitchenBean.getOrder_pic()!=null){
            LoadImageManager.getImageLoader().displayImage(mykitchenBean.getOrder_pic(),holder.ItemImage);
        }

        holder.Mykitchen_ItemTitle.setText(mykitchenBean.getOrder_title());
        holder.Mykitchen_ItemText.setText(mykitchenBean.getFood_description());
        holder.Mykitchen_ItemMoney.setText("￥"+(int) mykitchenBean.getOrder_price());
        order_id = mykitchenBean.getOrder_id();
        holder.tv_operateAction.setText("下架");
        holder.tv_operateAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mykitchenOnClickListener.onItemDownOnClick(position);
            }
        });
        return convertView;
    }
    public void setDataChange(ArrayList<MykitchenBean> mykitchenBeen) {
        this.companyList = mykitchenBeen;
        notifyDataSetChanged();
    }

    public class GridViewHolder{
        ImageView ItemImage;
        TextView Mykitchen_ItemTitle;
        TextView Mykitchen_ItemText;
        TextView Mykitchen_ItemMoney;
        TextView tv_operateAction;
    }


}
