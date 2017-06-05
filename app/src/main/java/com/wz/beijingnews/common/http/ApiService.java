package com.wz.beijingnews.common.http;

import com.wz.beijingnews.bean.NewsBaseBean;
import com.wz.beijingnews.bean.NewsBean;
import com.wz.beijingnews.bean.NewsDataBean;
import com.wz.beijingnews.bean.NewsTypeBaseBean;
import com.wz.beijingnews.bean.NewsTypeChildBean;
import com.wz.beijingnews.bean.NewsTypeDataBean;
import com.wz.beijingnews.bean.TopNewsBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by wz on 17-6-2.
 */

public interface ApiService {


    @GET("static/api/news/categories.json")
    Observable<NewsTypeBaseBean<NewsTypeDataBean<NewsTypeChildBean>>> getNewsType();


    @GET
    Observable<NewsBaseBean<NewsDataBean<NewsBean,TopNewsBean>>> getNewsDetail(@Url String url);


    @GET
    Observable<NewsBaseBean<NewsDataBean<NewsBean,TopNewsBean>>> loadMoreNewsDetail(@Url String moreUrl);

    @GET
    Observable<NewsBaseBean<NewsDataBean<NewsBean,TopNewsBean>>> getTopNewsDetail(@Url String topNewsUrl);
}
