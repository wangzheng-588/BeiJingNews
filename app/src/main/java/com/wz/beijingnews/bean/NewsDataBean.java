package com.wz.beijingnews.bean;

import java.util.List;

/**
 * Created by wz on 17-6-2.
 */

public class NewsDataBean<News extends NewsBean,TopNews extends TopNewsBean> {
    private String countcommenturl;
    private String more;
    private List<News> news;
    private String title;
    private List<TopNews> topnews;

    public String getCountcommenturl() {
        return countcommenturl;
    }

    public void setCountcommenturl(String countcommenturl) {
        this.countcommenturl = countcommenturl;
    }

    public String getMore() {
        return more;
    }

    public void setMore(String more) {
        this.more = more;
    }

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<TopNews> getTopnews() {
        return topnews;
    }

    public void setTopnews(List<TopNews> topnews) {
        this.topnews = topnews;
    }
}
