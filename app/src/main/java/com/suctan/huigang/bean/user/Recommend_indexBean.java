package com.suctan.huigang.bean.user;

/**
 * Created by Tom on 2017/4/13.
 */

public class Recommend_indexBean {
    private String ImageUrl;

    public Recommend_indexBean(String ImageUrl){
        this.ImageUrl=ImageUrl;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }
}
