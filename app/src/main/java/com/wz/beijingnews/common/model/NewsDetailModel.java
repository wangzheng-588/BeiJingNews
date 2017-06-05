package com.wz.beijingnews.common.model;

import com.wz.beijingnews.bean.NewsBaseBean;
import com.wz.beijingnews.bean.NewsBean;
import com.wz.beijingnews.bean.NewsDataBean;
import com.wz.beijingnews.bean.TopNewsBean;
import com.wz.beijingnews.common.http.ApiService;

import io.reactivex.Observable;

/**
 * Created by wz on 17-6-3.
 */

public class NewsDetailModel {

    ApiService mApiService;

    public NewsDetailModel(ApiService apiService) {
        mApiService = apiService;
    }

    public Observable<NewsBaseBean<NewsDataBean<NewsBean,TopNewsBean>>> getNewsDetail(String url){
        return mApiService.getNewsDetail(url);
    }

    public Observable<NewsBaseBean<NewsDataBean<NewsBean,TopNewsBean>>> loadMoreNewsDetail(String url){
        return mApiService.loadMoreNewsDetail(url);
    }

    public Observable<NewsBaseBean<NewsDataBean<NewsBean,TopNewsBean>>> getTopNewsDetail(String url){
        return mApiService.getTopNewsDetail(url);
    }
}
