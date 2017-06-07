package com.wz.beijingnews.bean;

import java.io.Serializable;

/**
 * Created by wz on 17-6-5.
 */

public class PhotosNewsBean implements Serializable{


    /**
     * id : 146853
     * title : 新兵为叠出“豆腐块”也是拼了
     * url : /static/html/2015/10/13/714C6D534A6E197068267C42.html
     * listimage : /static/images/2015/10/13/3/467283560QSZ.jpg
     * smallimage : /static/images/2015/10/13/61/1987564164AF3I.jpg
     * largeimage : /static/images/2015/10/13/31/1485924659MIXV.jpg
     * pubdate : 2015-10-13 10:56
     * comment : true
     * commenturl : /client/user/newComment/146853
     * type : news
     * commentlist : /static/api/news/10003/53/146853/comment_1.json
     */

    private int id;
    private String title;
    private String url;
    private String listimage;
    private String smallimage;
    private String largeimage;
    private String pubdate;
    private boolean comment;
    private String commenturl;
    private String type;
    private String commentlist;

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setListimage(String listimage) {
        this.listimage = listimage;
    }

    public void setSmallimage(String smallimage) {
        this.smallimage = smallimage;
    }

    public void setLargeimage(String largeimage) {
        this.largeimage = largeimage;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public void setComment(boolean comment) {
        this.comment = comment;
    }

    public void setCommenturl(String commenturl) {
        this.commenturl = commenturl;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCommentlist(String commentlist) {
        this.commentlist = commentlist;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getListimage() {
        return listimage;
    }

    public String getSmallimage() {
        return smallimage;
    }

    public String getLargeimage() {
        return largeimage;
    }

    public String getPubdate() {
        return pubdate;
    }

    public boolean getComment() {
        return comment;
    }

    public String getCommenturl() {
        return commenturl;
    }

    public String getType() {
        return type;
    }

    public String getCommentlist() {
        return commentlist;
    }
}
