package com.suctan.huigang.adapter.order.buyorder;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.suctan.huigang.R;
import com.suctan.huigang.bean.order.buy.BuyRecommendBean;

import java.util.ArrayList;

/**
 * Created by 黄淑翰 on 2017/4/26.
 */
public class AllMakeOrederAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<BuyRecommendBean> AllBuyRecommendList;

    public AllMakeOrederAdapter(Context context, ArrayList<BuyRecommendBean> AllBuyRecommendList) {
        this.context = context;
        this.AllBuyRecommendList = AllBuyRecommendList;
    }

    @Override
    public int getCount() {
        return AllBuyRecommendList.size();
    }

    @Override
    public Object getItem(int i) {
        return AllBuyRecommendList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        MyViewHolder holder = null;
        BuyRecommendBean buyRecommendBean = AllBuyRecommendList.get(i);
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.makebuy_alllist_item, null, false);
            holder = new MyViewHolder();
            holder.imv_makebuy_pic = (ImageView) view.findViewById(R.id.imv_makebuy_pic);
            holder.tv_makebuy_status = (TextView) view.findViewById(R.id.tv_makebuy_status);
            holder.tv_makebuy_time = (TextView) view.findViewById(R.id.tv_makebuy_time);
            holder.tv_makebuy_foodname = (TextView) view.findViewById(R.id.tv_makebuy_foodname);
            holder.tv_makebuy_price = (TextView) view.findViewById(R.id.tv_makebuy_price);
            holder.tv_makebuy_orderId = (TextView) view.findViewById(R.id.tv_makebuy_orderId);
            holder.tv_makebuy_result = (TextView) view.findViewById(R.id.tv_makebuy_result);
            holder.tv_makebuy_checkcommend = (TextView) view.findViewById(R.id.tv_makebuy_checkcommend);//查看评价
            holder.tv_makebuy_cancel = (TextView) view.findViewById(R.id.tv_makebuy_cancel);//取消订单
            holder.tv_makebuy_comfirm = (TextView) view.findViewById(R.id.tv_makebuy_comfirm);//确认收货
            holder.tv_makebuy_detail = (TextView) view.findViewById(R.id.tv_makebuy_detail);//详情
            view.setTag(holder);
        } else {
            holder = (MyViewHolder) view.getTag();
        }
        switch (buyRecommendBean.getOrder_status()) {
            case 0:
                holder.tv_makebuy_status.setText("已取消");
                holder.tv_makebuy_result.setVisibility(View.GONE);
                holder.tv_makebuy_checkcommend.setVisibility(View.GONE);
                holder.tv_makebuy_cancel.setVisibility(View.GONE);
                holder.tv_makebuy_comfirm.setVisibility(View.GONE);
                holder.tv_makebuy_detail.setVisibility(View.VISIBLE);
                break;
            case 1:
                holder.tv_makebuy_status.setText("待接单");
                holder.tv_makebuy_result.setText("距离截至接单时间:");
                // FIXME: 2017/5/3  响应时间
                holder.tv_makebuy_checkcommend.setVisibility(View.GONE);
                holder.tv_makebuy_cancel.setVisibility(View.GONE);
                holder.tv_makebuy_comfirm.setVisibility(View.GONE);
                holder.tv_makebuy_detail.setVisibility(View.VISIBLE);
                break;
            case 2:
                holder.tv_makebuy_status.setText("待送餐");
                holder.tv_makebuy_result.setText("已受理");
                holder.tv_makebuy_checkcommend.setVisibility(View.GONE);
                holder.tv_makebuy_cancel.setVisibility(View.GONE);
                holder.tv_makebuy_comfirm.setVisibility(View.VISIBLE);
                holder.tv_makebuy_detail.setVisibility(View.VISIBLE);
                break;
            case 3:
                holder.tv_makebuy_status.setText("已完成");
                holder.tv_makebuy_result.setText("订单已完成");
                holder.tv_makebuy_checkcommend.setVisibility(View.VISIBLE);
                holder.tv_makebuy_cancel.setVisibility(View.GONE);
                holder.tv_makebuy_comfirm.setVisibility(View.GONE);
                holder.tv_makebuy_detail.setVisibility(View.VISIBLE);
                break;

        }
        holder.tv_makebuy_time.setText(buyRecommendBean.getOrder_expect_time());//  期望时间
        holder.tv_makebuy_foodname.setText(buyRecommendBean.getOrder_title());//菜名
        holder.tv_makebuy_price.setText(buyRecommendBean.getOrder_price() + "");//价格
        holder.tv_makebuy_orderId.setText(buyRecommendBean.getId() + "");//订单id
        if (buyRecommendBean.getPic() != null) {
            ImageLoader.getInstance().displayImage(buyRecommendBean.getPic(), holder.imv_makebuy_pic);
        }
        /**
         *  查看详情
         **/
        holder.tv_makebuy_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onDetailOnClick: ");
                detialOnClick.onDetailOnClick(i);
            }
        });
        /**
         * 确认收货
         */
        holder.tv_makebuy_comfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onComfirmAOnclick: ");
                detialOnClick.onComfirmAOnclick(i);
            }
        });

        /**
         * 查看评价
         */
        holder.tv_makebuy_checkcommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onAllCommentOnclick: ");
                detialOnClick.onAllCommentOnclick(i);
            }
        });
        return view;
    }

    public final static String TAG = "AllMakeOrederAdapter";
    AllMakeDetailOnClickListener detialOnClick;

    public void onDetailOnclick(AllMakeDetailOnClickListener detialOnClick) {
        this.detialOnClick = detialOnClick;
    }

    public void setDataChange(ArrayList<BuyRecommendBean> makeAllLists) {
        this.AllBuyRecommendList = makeAllLists;
        notifyDataSetChanged();
    }

    public interface AllMakeDetailOnClickListener {
        void onDetailOnClick(int position);

        void onComfirmAOnclick(int position);

        void onAllCommentOnclick(int position);
    }

    class MyViewHolder {
        ImageView imv_makebuy_pic;
        TextView tv_makebuy_status;
        TextView tv_makebuy_time;
        TextView tv_makebuy_foodname;
        TextView tv_makebuy_orderId;
        TextView tv_makebuy_result;
        TextView tv_makebuy_checkcommend;
        TextView tv_makebuy_cancel;
        TextView tv_makebuy_comfirm;
        TextView tv_makebuy_detail;
        TextView tv_makebuy_price;
    }

}
