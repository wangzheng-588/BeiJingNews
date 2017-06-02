package com.wz.beijingnews.bean;

import java.util.List;

/**
 * Created by wz on 17-6-2.
 */

public class NewsTypeBaseBean<T> {

    private int retcode;
    private List<T> data;
    private List<Integer> extend;

    public int getRetcode() {
        return retcode;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public List<Integer> getExtend() {
        return extend;
    }

    public void setExtend(List<Integer> extend) {
        this.extend = extend;
    }
}
