package com.wz.beijingnews.common.model;

import com.wz.beijingnews.bean.NewsBaseBean;
import com.wz.beijingnews.bean.NewsBean;
import com.wz.beijingnews.bean.NewsDataBean;
import com.wz.beijingnews.bean.TopNewsBean;
import com.wz.beijingnews.common.http.ApiService;
import com.wz.beijingnews.common.http.HttpManager;

import io.reactivex.Observable;

/**
 * Created by wz on 17-6-3.
 */

public class NewsDetailModel {

    public Observable<NewsBaseBean<NewsDataBean<NewsBean,TopNewsBean>>> getNewsDetail(String url){
        HttpManager manager = new HttpManager();
        ApiService apiService = manager.getRetrofit(manager.getOkHttpClient()).create(ApiService.class);
        return apiService.getNewsDetail(url);
    }

    public Observable<NewsBaseBean<NewsDataBean<NewsBean,TopNewsBean>>> loadMoreNewsDetail(String url){
        HttpManager manager = new HttpManager();
        ApiService apiService = manager.getRetrofit(manager.getOkHttpClient()).create(ApiService.class);
        return apiService.loadMoreNewsDetail(url);
    }
}
