package com.wz.beijingnews.common.http;

import com.wz.beijingnews.bean.NewsBaseBean;
import com.wz.beijingnews.bean.NewsBean;
import com.wz.beijingnews.bean.NewsDataBean;
import com.wz.beijingnews.bean.NewsTypeBaseBean;
import com.wz.beijingnews.bean.NewsTypeChildBean;
import com.wz.beijingnews.bean.NewsTypeDataBean;
import com.wz.beijingnews.bean.TopNewsBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by wz on 17-6-2.
 */

public interface ApiService {


    @GET("static/api/news/categories.json")
    Call<NewsTypeBaseBean<NewsTypeDataBean<NewsTypeChildBean>>> getNewsType();

    @GET
    Call<NewsBaseBean<NewsDataBean<NewsBean,TopNewsBean>>> getNewsDetail(@Url String url);


    @GET
    Call<NewsBaseBean<NewsDataBean<NewsBean,TopNewsBean>>> loadMoreNewsDetail(@Url String moreUrl);
}
