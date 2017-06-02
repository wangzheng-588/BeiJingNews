package com.wz.beijingnews.bean;

import java.util.List;

/**
 * Created by wz on 17-6-2.
 */

public class NewsDataBean<News,TopNews> {
    private String countcommenturl;
    private String more;
    private List<News> news;
    private String title;
    private List<TopNews> topnews;
}
