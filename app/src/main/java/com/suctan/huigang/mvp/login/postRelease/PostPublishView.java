package com.suctan.huigang.mvp.login.postRelease;

import com.example.androidbase.mvp.BaseMvpView;
import com.suctan.huigang.bean.topic.AddCommentReturn;
import com.suctan.huigang.bean.topic.TopicBean;
import com.suctan.huigang.bean.topic.TopicCommentBean;

import java.util.ArrayList;

/**
 * Created by B-305 on 2017/4/19.
 */

public interface PostPublishView extends BaseMvpView {
    void getTopicListSrc(ArrayList<TopicBean> topicBeenList);
    void getTopicListFail();

    void postPublishCommentSuc(AddCommentReturn addCommentBean);

    void postPublishCommentFail(String msg);

    void getCommentListSuc(ArrayList<TopicCommentBean> topicCommentBeen);

    void getComemtnListFail();

    void DeleteTopicReturn(int position , String msg , int status);

    void delete_comment(int position , String msg , int status);

}