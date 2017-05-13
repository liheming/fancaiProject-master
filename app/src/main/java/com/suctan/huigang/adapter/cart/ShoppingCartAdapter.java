package com.suctan.huigang.adapter.cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidbase.LoadImageManager;
import com.example.androidbase.utils.ToastTool;
import com.suctan.huigang.R;
import com.suctan.huigang.bean.cart.CartBean;
import com.suctan.huigang.numlib.AnimShopButton;
import com.suctan.huigang.numlib.IOnAddDelListener;

import java.util.ArrayList;

/**
 * Created by 黄淑翰 on 2017/4/22.
 */
public class ShoppingCartAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<CartBean> CartBeanList;
    private int maxNum;
    private double allMoney = 0;
    private int tempCartNum;

    public ShoppingCartAdapter(Context context, ArrayList<CartBean> CartBeanList) {
        this.context = context;
        this.CartBeanList = CartBeanList;
    }

    @Override
    public int getCount() {
        return CartBeanList.size();
    }

    @Override
    public Object getItem(int i) {
        return CartBeanList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
     MyViewHolder holder = null;
        final CartBean mCartBean = CartBeanList.get(i);
        maxNum = mCartBean.getMax_num();
        tempCartNum = mCartBean.getOrder_num();
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.shopping_cart_item, viewGroup, false);
            holder = new MyViewHolder();
            holder.checkbox_cartitem = (CheckBox) view.findViewById(R.id.checkbox_cartitem);//checkbox按钮
            holder.imv_cart_image = (ImageView) view.findViewById(R.id.imv_cart_image);//菜品图片名
            holder.tv_cartitem_servertype = (TextView) view.findViewById(R.id.tv_cartitem_servertype);//服务类型
            holder.tv_cartitem_momeny = (TextView) view.findViewById(R.id.tv_cartitem_momeny);//单价
            holder.tv_cartitme_title = (TextView) view.findViewById(R.id.tv_cartitme_title);//菜名
            holder.tv_cartitem_delete = (TextView) view.findViewById(R.id.tv_cartitem_delete);//删除
            holder.btn_book_eatfood = (AnimShopButton) view.findViewById(R.id.btn_book_eatfood);//修改数量
             MyViewHolder finalHolder = holder;
            holder.btn_book_eatfood.setOnAddDelListener(new IOnAddDelListener() {
                @Override
                public void onAddSuccess(int count) {
                    Listener.onChangeNum(1, mCartBean.isCheck(), i,count);
                    if (count == maxNum) {
                        ToastTool.showToast("无法再添加了噢，菜品数量只剩那么多了！",2);
                        Listener.onCheckBoxItemClick(i);
                    }
//
                }

                @Override
                public void onAddFailed(int count, FailType failType) {

                }

                @Override
                public void onDelSuccess(int count) {
//
                    if (count == 1) {
                        ToastTool.showToast("数量只剩下一个了噢！ 再点击就会删除该菜品的噢",2);
                    } else if (count==0) {
                        Listener.onDetele(i);
                        return;
                    }
                    Listener.onChangeNum(0,mCartBean.isCheck(),i,count);

                }

                @Override
                public void onDelFaild(int count, FailType failType) {

                }
            });

                    view.setTag(holder);

        } else {
            holder = (MyViewHolder) view.getTag();
        }
        if (mCartBean.getOrder_pic() != null) {
            LoadImageManager.getImageLoader().displayImage(mCartBean.getOrder_pic(), holder.imv_cart_image);
        }
        String serverType = null;
        if (mCartBean.getOrder_type() == 0) {
            serverType = "上门做";
        } else if (mCartBean.getOrder_type() == 1) {
            serverType = "送上门";
        } else {
            serverType = "自提";
        }
//        allMoney = mCartBean.getOrder_allprice()+allMoney;
//        System.out.println("总价是多少呢"+allMoney);
        holder.btn_book_eatfood.setMaxCount(maxNum);
        holder.btn_book_eatfood.setCount(mCartBean.getOrder_num());
        LoadImageManager.getImageLoader().displayImage(mCartBean.getOrder_pic(),holder.imv_cart_image);
        holder.tv_cartitme_title.setText(mCartBean.getOrder_title());
        holder.tv_cartitem_servertype.setText(serverType);//设置购物车菜品的数量
        holder.tv_cartitem_momeny.setText(mCartBean.getOrder_price()+"");

        holder.tv_cartitem_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Listener.onDetele(i);
//                 allMoney =allMoney - mCartBean.getOrder_allprice();
//                Listener.onAllMoney(allMoney);
//                System.out.println("点击删除后的总价是"+allMoney);
            }
        });

   /**
    * 购物车复选框监听事件
    **/
        holder.checkbox_cartitem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                // FIXME: 2017/5/2 checkbox选中计算价格
                   mCartBean.setCheck(b);
                Listener.onCheckBoxItemClick(i);

            }
        });
        if (mCartBean.isCheck()) {
            holder.checkbox_cartitem.setChecked(true);
//            Listener.onCheck();

        } else {
            holder.checkbox_cartitem.setChecked(false);
//            Listener.onCheck();
        }

        return view;
    }

    /***
     *adapter数据更新方法
     */
    public void setDataChange(ArrayList<CartBean> CartBeanList) {
        this.CartBeanList = CartBeanList;
        notifyDataSetChanged();
    }


    /***
     * 接口
     */
    public interface OnCartListClickListner {
        void onChangeNum(int type , boolean ischeck ,int position, int count);
        void onDetele(int position);
        void onCaculatePrice(int i ,double price);
        void onCheckBoxItemClick(int position);
    }

    OnCartListClickListner Listener;
    /***
     * 注册监听器
     */
    public void setOnCartListClick(OnCartListClickListner Listener) {
        this.Listener = Listener;
    }


    class MyViewHolder {
        CheckBox checkbox_cartitem;
        ImageView imv_cart_image;
        TextView tv_cartitem_servertype;
        TextView tv_cartitem_momeny;
//        TextView tv_cartitem_decrease;
//        TextView tv_cartitem_countshow;
//        TextView tv_cartitem_arise;
        TextView tv_cartitem_delete;
        TextView tv_cartitme_title;
        AnimShopButton btn_book_eatfood;
    }
}
