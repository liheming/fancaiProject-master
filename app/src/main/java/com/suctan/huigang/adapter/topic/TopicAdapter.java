package com.suctan.huigang.adapter.topic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.androidbase.LoadImageManager;
import com.example.androidbase.widget.CircleImageView;
import com.suctan.huigang.R;
import com.suctan.huigang.bean.topic.TopicBean;

import java.util.ArrayList;

public class TopicAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<TopicBean> topicBeenList;


    public TopicAdapter(Context context, ArrayList<TopicBean> topicBeenList) {
        this.context = context;
        this.topicBeenList = topicBeenList;
    }

//    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(
//                context).inflate(R.layout.circle_list, parent,
//                false);
//        MyViewHolder holder = new MyViewHolder(view);
//        return holder;
//    }
//
//    public void onBindViewHolder(MyViewHolder holder, final int position) {
//        TopicBean mtoPicBean = topicBeenList.get(position);
//
//        MyViewHolder myViewHolder = holder;
//        myViewHolder.item_name.setText(mtoPicBean.getUser_name());
//        myViewHolder.item_time.setText(mtoPicBean.getPub_topic_time());
//        myViewHolder.item_title.setText(mtoPicBean.getTopic_title());
//        myViewHolder.item_reply.setText(mtoPicBean.getComment_num()+"");
//        if(mtoPicBean.getUser_icon()!=null){
//            LoadImageManager.getImageLoader().displayImage(mtoPicBean.getUser_icon(),myViewHolder.item_imv);
//        }
//
//
//
//        myViewHolder.ly_topic_arroud.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Lisetner.onClickTopicItem(position);
//            }
//        });
//    }


//    public int getItemCount() {
//        return topicBeenList.size();
//    }



    @Override
    public int getCount() {
        return topicBeenList.size();
    }

    @Override
    public Object getItem(int position) {
        return topicBeenList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        MyViewHolder viewHolder = null;
        TopicBean topicBean = topicBeenList.get(position);
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.circle_list, parent, false);
            viewHolder = new MyViewHolder();

            viewHolder.item_name = (TextView) view.findViewById(R.id.item_name);
            viewHolder.item_time = (TextView) view.findViewById(R.id.item_time);
            viewHolder.item_title = (TextView) view.findViewById(R.id.item_title);
            viewHolder.item_reply_num = (TextView) view.findViewById(R.id.item_reply_num);
            viewHolder.item_imv = (CircleImageView) view.findViewById(R.id.item_tx);
            viewHolder.ly_topic_arroud = (LinearLayout) view.findViewById(R.id.ly_topic_arroud);
            view.setTag(viewHolder);
        } else {
            viewHolder = (MyViewHolder) view.getTag();
        }
        viewHolder.item_name.setText(topicBean.getUser_name());
        viewHolder.item_time.setText(topicBean.getPub_topic_time());
        viewHolder.item_title.setText(topicBean.getTopic_title());
        viewHolder.item_reply_num.setText(topicBean.getComment_num()+"");
        if(topicBean.getUser_icon()!=null){
            LoadImageManager.getImageLoader().displayImage(topicBean.getUser_icon(),viewHolder.item_imv);
        }
        viewHolder.ly_topic_arroud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Lisetner.onClickTopicItem(position);
            }
        });

        return view;
    }


    /***
     *adapter数据更新方法
     */
    public void setDataChange(ArrayList<TopicBean> topicBeen) {
        this.topicBeenList = topicBeen;
        notifyDataSetChanged();
    }

    private OnTopicListenter Lisetner;

    public void setOnClickTopicListner(OnTopicListenter Lisetner) {
        this.Lisetner = Lisetner;
    }

    public interface OnTopicListenter {
        void onClickTopicItem(int position);
    }


    class MyViewHolder  {
        TextView item_name, item_time, item_title, item_reply_num;
        CircleImageView item_imv;
        LinearLayout ly_topic_arroud;


    }
}