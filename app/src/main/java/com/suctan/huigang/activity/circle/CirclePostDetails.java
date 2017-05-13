package com.suctan.huigang.activity.circle;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidbase.BaseApplication;
import com.example.androidbase.LoadImageManager;
import com.example.androidbase.mvp.MvpActivity;
import com.example.androidbase.utils.ToastTool;
import com.example.androidbase.widget.CircleImageView;
import com.jaeger.library.StatusBarUtil;
import com.sqk.emojirelease.Emoji;
import com.sqk.emojirelease.EmojiUtil;
import com.sqk.emojirelease.FaceFragment;
import com.suctan.huigang.R;
import com.suctan.huigang.acache.CurrentUser;
import com.suctan.huigang.acache.TokenManager;
import com.suctan.huigang.activity.MainActivity;
import com.suctan.huigang.adapter.topic.CirclePostAdapter;
import com.suctan.huigang.bean.topic.AddCommentReturn;
import com.suctan.huigang.bean.topic.DellCommentBean;
import com.suctan.huigang.bean.topic.TopicBean;
import com.suctan.huigang.bean.topic.TopicCommentBean;
import com.suctan.huigang.mvp.login.postRelease.PostPublishPresenter;
import com.suctan.huigang.mvp.login.postRelease.PostPublishView;
import com.suctan.huigang.widget.dialog.AlertDialog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by B-305 on 2017/4/8.
 */

public class CirclePostDetails extends MvpActivity<PostPublishPresenter> implements CirclePostAdapter.TopicInterface, FaceFragment.OnEmojiClickListener, View.OnClickListener, PostPublishView {
    private ImageButton post_emoticon;
    private EditText et_reply;
    private FrameLayout emojicons_layout;
    private ImageButton postDetailsBack;
    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private CircleImageView circleImv_psb_userHead;
    private TextView tv_pbs_userName;
    private TextView tv_pbs_title;
    private TextView tv_pbs_commentTime;
    private TextView tv_pbs_content;
    private TextView topicTitle;
    private ImageView imv_pbs_imvdetail;
    private ImageView imv_pbs_delete;//删除整条话题和下面的评论
    private Button btn_send_comment;
    private ArrayList<TopicCommentBean> topicCommentList = new ArrayList<>();
    private CirclePostAdapter mAdapter;
    private ScrollView post_details_ScrollView;
    private TopicBean mTopicBean;//当前的话题对象
    private boolean isFirstCreateRecycleAdatper;
    private String tempContent;
    private DellCommentBean mDellCommentBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_details);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary), 0);
        initView();
        getIntentData();
        getCommmentList();
        /*deletetppiceSSS();*/
    }

    /*获取当前话题的评论*/
    private void getCommmentList() {
        Map map = new HashMap();
        map.put("topic_id", mTopicBean.getTopic_id());
        mvpPresenter.getTopicCommentList(map);
    }

    private void getIntentData() {
        mTopicBean = (TopicBean) getIntent().getSerializableExtra("nowTopic");
        if (mTopicBean != null) {
            initData(mTopicBean);
        }


    }

    public static final String TAG = "CirclePostDetails";

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.post_recyclerview);
        postDetailsBack = (ImageButton) findViewById(R.id.post_details_back);
        circleImv_psb_userHead = (CircleImageView) findViewById(R.id.circleImv_psb_userHead);
        tv_pbs_userName = (TextView) findViewById(R.id.tv_pbs_userName);
        tv_pbs_commentTime = (TextView) findViewById(R.id.tv_pbs_commentTime);
        tv_pbs_content = (TextView) findViewById(R.id.tv_pbs_content);
        topicTitle = (TextView) findViewById(R.id.topicTitle);
        imv_pbs_imvdetail = (ImageView) findViewById(R.id.imv_pbs_imvdetail);
        imv_pbs_delete = (ImageView) findViewById(R.id.imv_pbs_delete);


        btn_send_comment = (Button) findViewById(R.id.btn_send_comment);
        tv_pbs_title = (TextView) findViewById(R.id.tv_pbs_title);
        post_details_ScrollView = (ScrollView) findViewById(R.id.post_details_ScrollView);

        post_emoticon = (ImageButton) findViewById(R.id.post_emoticon);
        et_reply = (EditText) findViewById(R.id.et_reply);
        emojicons_layout = (FrameLayout) findViewById(R.id.emojicons_layout);

        et_reply.setOnClickListener(this);
        postDetailsBack.setOnClickListener(this);
        btn_send_comment.setOnClickListener(this);
        imv_pbs_delete.setOnClickListener(this);
        post_emoticon.setOnClickListener(this);

        initEmotion();
        initRecycleView();
    }


    private void initRecycleView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setFocusable(false);
    }

    @Override
    protected PostPublishPresenter createPresenter() {
        return new PostPublishPresenter(this);
    }

    //初始化数据
    protected void initData(TopicBean mTopicBean) {
        if (mTopicBean.getUser_icon() != null) {
            LoadImageManager.getImageLoader().displayImage(mTopicBean.getUser_icon(), circleImv_psb_userHead);
        }
        tv_pbs_userName.setText(mTopicBean.getUser_name());
        Log.i(TAG, "initData: 图片的url是" + mTopicBean.getTopic_picture());
        if (mTopicBean.getTopic_picture() != null) {
            LoadImageManager.getImageLoader().displayImage(mTopicBean.getTopic_picture(), imv_pbs_imvdetail);
        }
        tv_pbs_commentTime.setText(mTopicBean.getPub_topic_time());
        tv_pbs_content.setText(mTopicBean.getTopic_content());
        tv_pbs_title.setText(mTopicBean.getTopic_title());
//        Log.i(TAG, "initData: "+TokenManager.getToken() +"==="+mTopicBean.getUser_token());
//
        topicTitle.setText("话题详情");
        if (mTopicBean.getUser_token().equals(TokenManager.getToken())) {
            imv_pbs_delete.setVisibility(View.VISIBLE);
            Log.i(TAG, "initView: 显示");
        } else {
            Log.i(TAG, "initView: 隐藏");
            imv_pbs_delete.setVisibility(View.GONE);
        }


    }

    //按钮的点击事件,并且对数据进行传输
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_send_comment:
                toolgleSendComment(false);
                postCommentVearity();
//                hideEmotionView(true);
                break;
            case R.id.post_details_back:
                finish();
                break;
            case R.id.imv_pbs_delete: // 楼主删除自己的话题
//                Toast.makeText(this,"已删除",Toast.LENGTH_SHORT).show();
                showDeleteTopic();

                break;
            case R.id.et_reply:
                hideEmotionView(true);
                break;
            case R.id.post_emoticon:
                if (emojicons_layout.isShown()) {
                    hideEmotionView(false);
                } else {
                    showEmotionView();
                }
                break;
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


    @Override
    public void getTopicListSrc(ArrayList<TopicBean> topicBeenList) {

    }

    @Override
    public void getTopicListFail() {

    }

    /**
     * 发表评论回调
     */
    @Override
    public void postPublishCommentSuc(AddCommentReturn addCommentBean) {
        addNewComemntObject(addCommentBean);
        toolgleSendComment(false);
    }


    /**
     * 添加评论在列表中
     */
    private void addNewComemntObject(AddCommentReturn addCommentBean) {
        //发表成功后处理逻辑 清空内容
        et_reply.setText("");
//        hideEmotionView(false);
//        InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
//         mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);


        TopicCommentBean mTopicComment = new TopicCommentBean();
        mTopicComment.setContent(tempContent);
        String commentId = addCommentBean.getComment_id();
        if (commentId != null) {
            mTopicComment.setComment_id(Integer.parseInt(commentId));
        }
        mTopicComment.setUser_icon(CurrentUser.getInstance().getUserBean().getUser_icon());
        mTopicComment.setUser_name(CurrentUser.getInstance().getUserBean().getUser_name());
        mTopicComment.setComment_time(addCommentBean.getComment_time());
        topicCommentList.add(topicCommentList.size(), mTopicComment);
        mAdapter.notifyDataSetChanged();
        // FIXME: 2017/5/5  列表滑动到最新发布的那条评论下面
    }


    @Override
    public void postPublishCommentFail(String msg) {
        Toast.makeText(BaseApplication.getContext(), msg, Toast.LENGTH_LONG).show();
        toolgleSendComment(true);
    }

    /**
     * 获取该话题下面的评论回调
     */
    @Override
    public void getCommentListSuc(ArrayList<TopicCommentBean> topicCommentBeen) {
        topicCommentList.addAll(topicCommentBeen);
        if (!isFirstCreateRecycleAdatper) {
            isFirstCreateRecycleAdatper = true;
            mAdapter = new CirclePostAdapter(this, topicCommentList);
            mAdapter.onTopicOnclick(this); // 注册监听器
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getComemtnListFail() {

    }

    /**
     * 删除本人发布的话题回调
     */
    @Override
    public void DeleteTopicReturn(int position , String msg, int status) {
        Log.i(TAG, "DeleteTopicReturn: "+msg+status+position);
        ToastTool.showToast(msg, status);
        Intent intent=new Intent(CirclePostDetails.this,MainActivity.class);
        intent.putExtra("id", 2);
        startActivity(intent);
        Log.i(TAG, "DeleteTopicReturn: 跳转到社区  go ");


    }

    /**
     * 删除一条评论回调
     */
    @Override
    public void delete_comment(int position, String msg, int status) {
        ToastTool.showToast(msg, status);
        for (int i = 0; i < topicCommentList.size(); i++) {
            if (topicCommentList.get(i).getComment_id() == position) {
                topicCommentList.remove(topicCommentList.get(i));
                break;
            }
        }
        if (topicCommentList != null) {
            mAdapter.setDataChange(topicCommentList);
        }
    }


    /**
     * 表情键盘
     */
    private void initEmotion() {
        FaceFragment faceFragment = FaceFragment.Instance();
        getSupportFragmentManager().beginTransaction().add(R.id.emojicons_layout, faceFragment).commit();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

    }

    private void toolgleSendComment(boolean isClick) {
        if (isClick) {
            btn_send_comment.setClickable(false);
        } else {
            btn_send_comment.setClickable(true);
        }
    }


    /**
     * 隐藏emoji
     */
    private void hideEmotionView(boolean showKeyBoard) {
        if (emojicons_layout.isShown()) {
            if (showKeyBoard) {
                emojicons_layout.setVisibility(View.GONE);
                //etWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
            } else {
                emojicons_layout.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 显示emoji
     */
    private void showEmotionView() {
        emojicons_layout.setVisibility(View.VISIBLE);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(et_reply.getWindowToken(), 0);
    }

    @Override
    public void onEmojiDelete() {
        String text = et_reply.getText().toString();
        if (text.isEmpty()) {
            return;
        }
        if ("]".equals(text.substring(text.length() - 1, text.length()))) {
            int index = text.lastIndexOf("[");
            if (index == -1) {
                int action = KeyEvent.ACTION_DOWN;
                int code = KeyEvent.KEYCODE_DEL;
                KeyEvent event = new KeyEvent(action, code);
                et_reply.onKeyDown(KeyEvent.KEYCODE_DEL, event);
                //displayTextView();
                return;
            }
            et_reply.getText().delete(index, text.length());
            //displayTextView();
            return;
        }
        int action = KeyEvent.ACTION_DOWN;
        int code = KeyEvent.KEYCODE_DEL;
        KeyEvent event = new KeyEvent(action, code);
        et_reply.onKeyDown(KeyEvent.KEYCODE_DEL, event);
        //displayTextView();
    }

    private void displayTextView() {
        try {
            EmojiUtil.handlerEmojiText(et_reply, et_reply.getText().toString(), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onEmojiClick(Emoji emoji) {
        if (emoji != null) {
            int index = et_reply.getSelectionStart();
            Editable editable = et_reply.getEditableText();
            if (index < 0) {
                editable.append(emoji.getContent());
            } else {
                editable.insert(index, emoji.getContent());
            }
        }
        displayTextView();
        et_reply.setSelection(et_reply.getText().length());
        //Log.i("onEmojiClick", "onEmojiClick: "+et_reply.getText().toString());
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (null != this.getCurrentFocus()) {
            /**
             * 点击空白位置 隐藏软键盘
             */
            hideEmotionView(false);
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(event);

    }

    /**
     * 上传前进行判断
     */
    private void postCommentVearity() {
        if (et_reply.getText().toString().isEmpty()) {
            ToastTool.showToast("请先输入内容", 2);
            toolgleSendComment(true);
            return;
        }
        publishComment();

    }

    /**
     * 请求发表评论
     */
    private void publishComment() {

        Map<String, Object> map = new HashMap<>();
        map.put("user_token", TokenManager.getToken());
        map.put("topic_id", mTopicBean.getTopic_id());
        map.put("content", et_reply.getText().toString());
        tempContent = et_reply.getText().toString();
        mvpPresenter.postPublishComment(map);

    }

    /**
     * 请求删除一条评论
     */
    private void deleteOneComment(int position) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_token", TokenManager.getToken());
        map.put("comment_id", topicCommentList.get(position).getComment_id());
        mvpPresenter.delete_comment(map, topicCommentList.get(position).getComment_id());

    }

//    private void delete() {
//        /*post_details_ScrollView.removeAllViews();*/
//        /*Intent intent = new Intent(this, FragmentIndex.class);
//        intent.putExtra("nowTopic", topicBeanList.get(position));
//        startActivity(intent);*/
//        deletetppiceSSS();
//        finish();
//        /*setResult(RESULT_OK,(new Intent()).setAction((FragmentFind.class)));*/
//    }

    /**
     * 请求删除话题
     */
    private void deleteTopicS() {

        Map<String, Object> map = new HashMap<>();
        map.put("topic_id", mTopicBean.getTopic_id());
        mvpPresenter.getdeletetopicSSS(map ,mTopicBean.getTopic_id());
    }

    /**
     * 删除本人发的一条评论Adapter 回调
     */
    @Override
    public void onDeleteCommentClick(int position) {
        showDeleteComment(position);
    }

    private AlertDialog mDialog;

    private void showDeleteComment(final int position) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("")
                .setMessage("确定要删除这条评论嘛嘛！")
                .setTopImage(R.drawable.icon_tanchuang_wenhao)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDialog.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDialog.dismiss();
                        deleteOneComment(position);
                    }
                });
        mDialog = builder.create();
        mDialog.show();
    }

    private void showDeleteTopic() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("")
                .setMessage("确定要删除这个话题和下面的评论嘛！ 删除后无法恢复噢")
                .setTopImage(R.drawable.icon_tanchuang_tanhao)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDialog.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDialog.dismiss();
                        deleteTopicS();
                    }
                });
        mDialog = builder.create();
        mDialog.show();
    }



    /***
     *adapter数据更新方法
     */
//    public void setTopicDataChange(TopicBean topicBean) {
//        this.mTopicBean = topicBean;
//        notifyDataSetChanged();
//    }
//
//    /***
//     * 接口
//     */
//    public interface TopicInter {
//        void onTopicDeleteOnClick(int position);
//    }
//
//    TopicInter topic;
//
//    public void onDelTopicClick(TopicInter topic) {
//        this.topic = topic;
//    }




}
