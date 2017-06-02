package com.wz.beijingnews.bean;

/**
 * Created by wz on 17-6-2.
 */

public class NewsTypeChildBean {

    /**
     * id : 10007
     * title : 北京
     * type : 1
     * url : /static/api/news/10007/list_1.json
     */

    private int id;
    private String title;
    private int type;
    private String url;

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getUrl() {
        return url;
    }

}
