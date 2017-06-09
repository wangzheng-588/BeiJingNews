package com.wz.beijingnews.common.utils.rximageload.Bean;

import android.graphics.Bitmap;

/**
 * =====作者=====
 * 许英俊
 * =====时间=====
 * 2017/4/4.
 */
public class ImageBean {
    private String url;
    private Bitmap bitmap;
    public ImageBean(Bitmap bitmap, String url) {
        this.bitmap = bitmap;
        this.url = url;
    }
    public Bitmap getBitmap() {
        return bitmap;
    }
    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
