package com.wz.beijingnews.bean;

/**
 * Created by wz on 17-6-2.
 */

public class NewsBaseBean<T> {

    private int retcode;
    private T data;

    public int getRetcode() {
        return retcode;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
