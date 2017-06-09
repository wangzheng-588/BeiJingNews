package com.wz.beijingnews.common.utils.rximageload.Cache;


import android.support.annotation.NonNull;

import com.wz.beijingnews.common.utils.rximageload.Bean.ImageBean;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * =====作者=====
 * 许英俊
 * =====时间=====
 * 2017/4/5.
 */
public abstract class CacheObservable {

    /**
     * 获取缓存数据
     * @param url
     * @return
     */
    public Observable<ImageBean> getImage(final String url) {
        return Observable.create(new ObservableOnSubscribe<ImageBean>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<ImageBean> e) throws Exception {
                if (!e.isDisposed()) {
                    ImageBean image = getDataFromCache(url);
                    e.onNext(image);
                    e.onComplete();
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 取出缓存数据
     * @param url
     * @return
     */
    public abstract ImageBean getDataFromCache(String url);

    /**
     * 缓存数据
     * @param image
     */
    public abstract void putDataToCache(ImageBean image);
}
