package com.wz.beijingnews.bean;

import java.util.List;

/**
 * Created by wz on 17-6-2.
 */

public class NewsTypeDataBean<T> {

    /**
     * id : 10000
     * title : 新闻
     * type : 1
     * children : [{"id":10007,"title":"北京","type":1,"url":"/static/api/news/10007/list_1.json"},{"id":10006,"title":"中国","type":1,"url":"/static/api/news/10006/list_5.json"},{"id":10008,"title":"国际","type":1,"url":"/static/api/news/10008/list_7.json"},{"id":10014,"title":"文娱","type":1,"url":"/static/api/news/10014/list_3.json"},{"id":10010,"title":"体育","type":1,"url":"/static/api/news/10010/list_3.json"},{"id":10091,"title":"生活","type":1,"url":"/static/api/news/10091/list_1.json"},{"id":10012,"title":"旅游","type":1,"url":"/static/api/news/10012/list_3.json"},{"id":10095,"title":"科技","type":1,"url":"/static/api/news/10095/list_1.json"},{"id":10009,"title":"军事","type":1,"url":"/static/api/news/10009/list_3.json"},{"id":10011,"title":"财经","type":1,"url":"/static/api/news/10011/list_3.json"},{"id":10093,"title":"女性","type":1,"url":"/static/api/news/10093/list_1.json"},{"id":10192,"title":"倍儿逗","type":1,"url":"/static/api/news/10192/list_1.json"}]
     */

    private int id;
    private String title;
    private int type;
    private List<T> children;

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setChildren(List<T> children) {
        this.children = children;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getType() {
        return type;
    }

    public List<T> getChildren() {
        return children;
    }

}
