package com.suctan.huigang.activity.share;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.LatLng;
import com.example.androidbase.LoadImageManager;
import com.example.androidbase.mvp.MvpActivity;
import com.example.androidbase.utils.ToastTool;
import com.example.androidbase.widget.CircleImageView;
import com.suctan.huigang.R;
import com.suctan.huigang.acache.CurrentUser;
import com.suctan.huigang.bean.need.NeedBean;
import com.suctan.huigang.mvp.login.share.GetNeedListPresener;
import com.suctan.huigang.mvp.login.share.GetNeedListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.suctan.huigang.R.id.login_title;

/**
 * Created by B-305 on 2017/4/13.
 */

public class ServiceList extends MvpActivity<GetNeedListPresener> implements GetNeedListView {
    private ArrayList<NeedBean> needBeanList = new ArrayList<>();
    private int needType = 8;

    @BindView(R.id.login_back)
    ImageView loginBack;
    @BindView(login_title)
    TextView loginTitle;
    @BindView(R.id.search)
    ImageView search;
    @BindView(R.id.add_post)
    Button serviceFb;
    private boolean isFirstCreateRecycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_list);
        ButterKnife.bind(this);
        initView();
        getNeedList();


        mRecyclerView = (RecyclerView) findViewById(R.id.service_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter = new ServiceListAdapter());
        initData();

    }

    @Override
    protected GetNeedListPresener createPresenter() {
        return new GetNeedListPresener(this);
    }

    private void getNeedList() {
        Map<String, Object> map = new HashMap<>();
        map.put("need_type", needType);
        mvpPresenter.getNeedListAction(map);

    }

    /*
    * 初始化标题栏
    * */
    private void initView() {
        search.setVisibility(View.GONE);
        serviceFb.setVisibility(View.VISIBLE);


        Intent it = getIntent();
        final String serviceType = it.getStringExtra("serviceType");
        needType = it.getIntExtra("needType", 0);

        loginTitle.setText(serviceType);
        loginBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        serviceFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goReleaseService = new Intent(ServiceList.this, ReleaseService.class);
                goReleaseService.putExtra("serviceType", serviceType);
                goReleaseService.putExtra("needType", needType);
                startActivity(goReleaseService);
            }
        });
    }

    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private ServiceListAdapter mAdapter;

    //初始化数据
    protected void initData() {
//        mDatas = new ArrayList<String>();
        for (int i = 0; i < needBeanList.size(); i++) {
            needBeanList.add(needBeanList.get(i));
        }

    }

    @Override
    public void getDataFail(String msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    private void initNedAdapter() {
        if (!isFirstCreateRecycle) {
            mRecyclerView.setAdapter(mAdapter = new ServiceListAdapter());
            isFirstCreateRecycle = true;
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getNeedListSrc(ArrayList<NeedBean> needBeenList) {
        ToastTool.showToast("获取共享需求列表成功", 1);
        this.needBeanList.addAll(needBeenList);
        initNedAdapter();

    }

    @Override
    public void getNeedBack() {

    }

    class ServiceListAdapter extends RecyclerView.Adapter<ServiceListAdapter.MyViewHolder> {


        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    ServiceList.this).inflate(R.layout.service_list_item, parent,
                    false));
            return holder;
        }


        public void onBindViewHolder(MyViewHolder holder, int position) {
            final String name = needBeanList.get(position).getUser_name();
            final String phone = needBeanList.get(position).getUser_phone();
            final String icon = needBeanList.get(position).getUser_icon();
            final String address = needBeanList.get(position).getUser_address();
            final String title = needBeanList.get(position).getNeed_title();
            final String content = needBeanList.get(position).getNeed_content();
            final String price = needBeanList.get(position).getNeed_price();
            final String pub_time = needBeanList.get(position).getPub_time();
            final int need_status = needBeanList.get(position).getNeed_status();


            double d1 = CurrentUser.getInstance().getUserBean().getLatitude();
            double d2 = CurrentUser.getInstance().getUserBean().getLongitude();
            double d3 = needBeanList.get(position).getLatitude();
            double d4 = needBeanList.get(position).getLongitude();
            LatLng startLatlng = new LatLng(d1, d2);
            LatLng endLatlng = new LatLng(d3, d4);
            int location = (int) AMapUtils.calculateLineDistance(startLatlng, endLatlng); //来计算两点距离，单位：米。
            holder.location.setText("距离" + location + "米");
            Log.i("123132132", "getView: " + "距离" + location + "米"+"d1"+d1+"=====d2"+d2+"====d3"+d3+"====d4"+d4);
            holder.item_name.setText(name);
            holder.item_time.setText(pub_time);

            if (need_status==1) {
                holder.item_boolean.setText("未被接单");

            } else {

                holder.item_boolean.setText("已经被接单");

            }


            holder.item_content.setText(title);
            holder.item_monry.setText(price);
            LoadImageManager.getImageLoader().displayImage(icon, holder.item_tx);


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent goServiceDetail = new Intent(ServiceList.this, ServiceDetail.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("name", name);
                    bundle.putString("phone", phone);
                    bundle.putString("icon", icon);
                    bundle.putString("title", title);
                    bundle.putString("content", content);
                    bundle.putString("address", address);
                    bundle.putString("price", price);
                    bundle.putString("pub_time", pub_time);
                    goServiceDetail.putExtra("need", bundle);
                    startActivity(goServiceDetail);
                }
            });

        }

        public int getItemCount() {
            return needBeanList.size();
        }

        /**
         * param item_name String 用户名
         * param item_time String 发布时间
         * param item_boolean String 状态
         * param item_content String 标题
         * param item_monry String 价钱
         * param item_tx String 头像
         */

        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView item_name, item_time, item_content, item_monry, item_boolean, location;
            CircleImageView item_tx;

            public MyViewHolder(View view) {
                super(view);
                item_name = (TextView) view.findViewById(R.id.item_name);//
                item_time = (TextView) view.findViewById(R.id.item_time);//时间
                item_boolean = (TextView) view.findViewById(R.id.item_boolean);//是否接单
                item_content = (TextView) view.findViewById(R.id.item_content);// 标题
                item_monry = (TextView) view.findViewById(R.id.item_money);
                location = (TextView) view.findViewById(R.id.location);
                item_tx = (CircleImageView) view.findViewById(R.id.item_tx);
            }
        }


    }


}