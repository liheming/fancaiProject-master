package com.suctan.huigang.activity.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.androidbase.mvp.MvpActivity;
import com.example.androidbase.utils.ToastTool;
import com.jaeger.library.StatusBarUtil;
import com.suctan.huigang.R;
import com.suctan.huigang.mvp.login.index.search.SearchIndexView;
import com.suctan.huigang.mvp.login.index.search.SearchPresenter;

import java.util.Map;

/**
 * Change by haily on 2017/5/04
 */

public class SearchActivity extends MvpActivity<SearchPresenter> implements SearchIndexView, Search_View.SearInterface {
    private String eatFoodList = "";
//    private ArrayList<EatFoodBean> eatFoodList = new ArrayList<>();
    // FIXME: 2017/5/3 搜索功能
    private ImageView search_back;
    Context context;

//    public Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what) {
//                case 666:
//                    Log.i(TAG, "handleMessage: 快点发送数据");
//                    Intent intent = new Intent(SearchActivity.this, Search_Result.class);
//                    intent.putExtra("eatFoodList", eatFoodList );
//                    startActivity(intent);
////                    resultInterface.onResultSuccess(eatFoodList);
//                    break;
//
//            }
//        }
//    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);
        Search_View searchView = new Search_View(this);
        setContentView(searchView);
        searchView.onSearchOnclick(this);

        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary), 0);

        search_back = (ImageView) findViewById(R.id.search_back);
        search_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public SearchActivity() {
    }
  public SearchActivity(Context context) {
        this.context = context;
    }

    @Override
    protected SearchPresenter createPresenter() {
        return new SearchPresenter(this);
    }

    @Override
    public void getDataFail(String msg) {
        ToastTool.showToast("搜索失败 ，错误"+msg ,0);

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    /**
     * 成功回调后跳转到搜索结果页面
     */

    @Override
    public void searchResult(String foodBean) {
        this.eatFoodList = foodBean;
        Log.i(TAG, "searchResult: 成功回调");
        Intent intent = new Intent(SearchActivity.this, Search_Result.class);//跳转到搜索结果页面
        intent.putExtra("eatFoodList", eatFoodList );
        startActivity(intent);


//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                handler.sendEmptyMessage(666);
//                    }
//        }).start();

    }

    public final static String TAG = "SearchActivity";
    //请求搜索菜品
    @Override
    public void onSearch(Map map) {
        mvpPresenter.searchAction(map);
//        ToastTool.showToast("我被触发了", 2);
    }

}
