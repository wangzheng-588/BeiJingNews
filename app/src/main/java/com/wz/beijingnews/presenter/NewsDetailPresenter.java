package com.wz.beijingnews.presenter;

import com.wz.beijingnews.bean.NewsBaseBean;
import com.wz.beijingnews.bean.NewsBean;
import com.wz.beijingnews.bean.NewsDataBean;
import com.wz.beijingnews.bean.TopNewsBean;
import com.wz.beijingnews.common.model.NewsDetailModel;
import com.wz.beijingnews.presenter.contract.NewsDetailContract;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wz on 17-6-3.
 */

public class NewsDetailPresenter extends BasePresenter<NewsDetailModel, NewsDetailContract.View> {


    public NewsDetailPresenter(NewsDetailModel model, NewsDetailContract.View view) {
        super(model, view);
    }

    public void requestDatas(String url) {
        mView.showLoading();

        mModel.getNewsDetail(url).compose(new ObservableTransformer<NewsBaseBean<NewsDataBean<NewsBean, TopNewsBean>>, NewsDataBean<NewsBean, TopNewsBean>>() {
            @Override
            public ObservableSource<NewsDataBean<NewsBean, TopNewsBean>> apply(Observable<NewsBaseBean<NewsDataBean<NewsBean, TopNewsBean>>> upstream) {
                return upstream.flatMap(new Function<NewsBaseBean<NewsDataBean<NewsBean, TopNewsBean>>, ObservableSource<NewsDataBean<NewsBean, TopNewsBean>>>() {
                    @Override
                    public ObservableSource<NewsDataBean<NewsBean, TopNewsBean>> apply(final NewsBaseBean<NewsDataBean<NewsBean, TopNewsBean>> newsDataBeanNewsBaseBean) throws Exception {
                        return Observable.create(new ObservableOnSubscribe<NewsDataBean<NewsBean, TopNewsBean>>() {
                            @Override
                            public void subscribe(ObservableEmitter<NewsDataBean<NewsBean, TopNewsBean>> e) throws Exception {
                                NewsDataBean<NewsBean, TopNewsBean> data = newsDataBeanNewsBaseBean.getData();
                                e.onNext(data);
                                e.onComplete();

                            }
                        });
                    }
                });
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<NewsDataBean<NewsBean, TopNewsBean>>() {
                    @Override
                    public void onNext(NewsDataBean<NewsBean, TopNewsBean> value) {
                        mView.showResult(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError();
                    }

                    @Override
                    public void onComplete() {
                        mView.dismissLoading();
                    }
                });

    }

    public void loadMoreDatas(String moreUrl) {
        mView.showLoading();


        mModel.loadMoreNewsDetail(moreUrl).compose(new ObservableTransformer<NewsBaseBean<NewsDataBean<NewsBean, TopNewsBean>>, NewsDataBean<NewsBean, TopNewsBean>>() {
            @Override
            public ObservableSource<NewsDataBean<NewsBean, TopNewsBean>> apply(Observable<NewsBaseBean<NewsDataBean<NewsBean, TopNewsBean>>> upstream) {
                return upstream.flatMap(new Function<NewsBaseBean<NewsDataBean<NewsBean, TopNewsBean>>, ObservableSource<NewsDataBean<NewsBean, TopNewsBean>>>() {
                    @Override
                    public ObservableSource<NewsDataBean<NewsBean, TopNewsBean>> apply(final NewsBaseBean<NewsDataBean<NewsBean, TopNewsBean>> newsDataBeanNewsBaseBean) throws Exception {
                        return Observable.create(new ObservableOnSubscribe<NewsDataBean<NewsBean, TopNewsBean>>() {
                            @Override
                            public void subscribe(ObservableEmitter<NewsDataBean<NewsBean, TopNewsBean>> e) throws Exception {
                                NewsDataBean<NewsBean, TopNewsBean> data = newsDataBeanNewsBaseBean.getData();
                                e.onNext(data);
                                e.onComplete();

                            }
                        });
                    }
                });
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<NewsDataBean<NewsBean, TopNewsBean>>() {
                    @Override
                    public void onNext(NewsDataBean<NewsBean, TopNewsBean> value) {
                        mView.showLoadMoreData(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError();
                    }

                    @Override
                    public void onComplete() {
                        mView.dismissLoading();
                    }
                });

    }

}
