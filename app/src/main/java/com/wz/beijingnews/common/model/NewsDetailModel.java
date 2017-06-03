package com.wz.beijingnews.common.model;

import com.wz.beijingnews.bean.NewsBaseBean;
import com.wz.beijingnews.bean.NewsBean;
import com.wz.beijingnews.bean.NewsDataBean;
import com.wz.beijingnews.bean.TopNewsBean;
import com.wz.beijingnews.common.http.ApiService;
import com.wz.beijingnews.common.http.HttpManager;

import retrofit2.Callback;

/**
 * Created by wz on 17-6-3.
 */

public class NewsDetailModel {

    public void getNewsDetail(String url, Callback<NewsBaseBean<NewsDataBean<NewsBean,TopNewsBean>>> callback){
        HttpManager manager = new HttpManager();
        ApiService apiService = manager.getRetrofit(manager.getOkHttpClient()).create(ApiService.class);
        apiService.getNewsDetail(url).enqueue(callback);
    }
}
