package com.wz.beijingnews.bean;

import java.util.List;

/**
 * Created by wz on 17-6-5.
 */

public class PhotosDataBean<News> {

    private String title;
    private List topic;
    private List<News> news;
    private String countcommenturl;
    private String more;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List getTopic() {
        return topic;
    }

    public void setTopic(List topic) {
        this.topic = topic;
    }

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }

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
}
