package com.suctan.huigang.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.androidbase.mvp.MvpFragment;
import com.example.androidbase.utils.ToastTool;
import com.suctan.huigang.R;
import com.suctan.huigang.acache.TokenManager;
import com.suctan.huigang.activity.circle.CirclePostDetails;
import com.suctan.huigang.adapter.topic.TopicAdapter;
import com.suctan.huigang.bean.topic.AddCommentReturn;
import com.suctan.huigang.bean.topic.TopicBean;
import com.suctan.huigang.bean.topic.TopicCommentBean;
import com.suctan.huigang.mvp.login.postRelease.PostPublishPresenter;
import com.suctan.huigang.mvp.login.postRelease.PostPublishView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;

public class FragmentFind extends MvpFragment<PostPublishPresenter> implements View.OnClickListener, PostPublishView, SwipeRefreshLayout.OnRefreshListener, TopicAdapter.OnTopicListenter {

    private View viewFind;
    private SwipeRefreshLayout swipe_circle;
//    private RecyclerView mRecyclerView;
    private GridView gridView;
    private List<String> mDatas;
    private ArrayList<TopicBean> topicBeanList = new ArrayList<>();
    private boolean isFirstCreateRecycle ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: ");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: ");
            if (viewFind == null) {
            viewFind = inflater.inflate(R.layout.fragment_circle, container, false);
        }
        ViewGroup parent = (ViewGroup) viewFind.getParent();
        if (parent != null) {
            parent.removeView(viewFind);
        }
        ButterKnife.bind(this, viewFind);
        return viewFind;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "onActivityCreated: ");
        initViews();
        getTopicData();

    }

    private void getTopicData() {
        Map<String, Object> map = new HashMap<>();
        map.put("user_token", TokenManager.getToken());
        mvpPresenter.getTopicList(map);
    }

    @Override
    public void onRefresh() {
        Log.i(TAG, "run: 我在刷新中");
        // FIXME: 2017/5/5 刷新更新数据
        getTopicData();


//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//
//            }
//        }, 1000);
    }


    private void initRefreshView() {
        swipe_circle.setColorSchemeColors(
                getResources().getColor(R.color.gplus_color_1),
                getResources().getColor(R.color.gplus_color_2),
                getResources().getColor(R.color.gplus_color_3),
                getResources().getColor(R.color.gplus_color_4));
        swipe_circle.setOnRefreshListener(this);
    }

    private void initViews() {



        gridView = (GridView) getView().findViewById(R.id.gridViewCircle);
//        mRecyclerView = (RecyclerView) getView().findViewById(R.id.circleView_topic);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        swipe_circle = (SwipeRefreshLayout) viewFind.findViewById(R.id.swipe_circle);
        initRefreshView();

    }


    @Override
    public void onClick(View v) {

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

    @Override
    protected PostPublishPresenter createPresenter() {
        return new PostPublishPresenter(this);
    }

    private TopicAdapter topListAdatper;

    @Override
    public void getTopicListSrc(ArrayList<TopicBean> topicBeenList) {
        swipe_circle.setRefreshing(false);
        Log.i(TAG, "getTopicListSrc: 话题回调成功");
        this.topicBeanList.clear();
        this.topicBeanList.addAll(topicBeenList);
        if (!isFirstCreateRecycle) {
            isFirstCreateRecycle = true;
            topListAdatper = new TopicAdapter(getActivity(), topicBeanList);
            topListAdatper.setOnClickTopicListner(this);
            gridView.setAdapter(topListAdatper);

        } else {
            Log.i(TAG, "getTopicListSrc: notifyDataSetChanged");
            topListAdatper.notifyDataSetChanged();
        }
    }

    private void InitRecycleViewAdapter(ArrayList<TopicBean> topicBeenList) {

    }

    @Override
    public void getTopicListFail() {
    }

    @Override
    public void postPublishCommentSuc(AddCommentReturn addCommentBean) {

    }


    @Override
    public void postPublishCommentFail(String msg) {

    }

    @Override
    public void getCommentListSuc(ArrayList<TopicCommentBean> topicCommentBeen) {

    }

    @Override
    public void getComemtnListFail() {

    }

    public static final String TAG = "FragmentFind";
    /**
     * 删除本人发布的话题回调
     */
    @Override
    public void DeleteTopicReturn(int position,String msg, int status) {
                ToastTool.showToast(msg, status);

//        Log.i(TAG, "DeleteTopicReturn: 我是删除回调 ");
//        for (int i = 0; i < topicBeanList.size(); i++) {
//            if (topicBeanList.get(i).getTopic_id() == position) {
//                topicBeanList.remove(topicBeanList.get(i));
//                break;
//            }
//        }
//        if (topicBeanList != null) {
//            topListAdatper.setDataChange(topicBeanList);
//        }
    }

    @Override
    public void delete_comment(int position, String msg, int status) {

    }


    @Override
    public void onClickTopicItem(int position) {
        if (topicBeanList != null) {
            Intent intent = new Intent(getActivity(), CirclePostDetails.class);
            intent.putExtra("nowTopic", topicBeanList.get(position));
            startActivity(intent);
        }
    }

    @Override
    protected void lazyLoad() {
        Log.i(TAG, "lazyLoad: ");

    }

//    @Override
//    public void onTopicDeleteOnClick(int position) {
//        new CirclePostDetails().onDelTopicClick(FragmentFind.this);
//
//        Log.i(TAG, "DeleteTopicReturn: 我是删除回调 ");
//        for (int i = 0; i < topicBeanList.size(); i++) {
//            if (topicBeanList.get(i).getTopic_id() == position) {
//                topicBeanList.remove(topicBeanList.get(i));
//                break;
//            }
//        }
//        if (topicBeanList != null) {
//            topListAdatper.setDataChange(topicBeanList);
//        }
//    }
//



}