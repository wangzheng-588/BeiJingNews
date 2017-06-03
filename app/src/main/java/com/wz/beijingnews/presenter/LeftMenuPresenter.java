package com.wz.beijingnews.presenter;

import com.wz.beijingnews.bean.NewsTypeBaseBean;
import com.wz.beijingnews.bean.NewsTypeChildBean;
import com.wz.beijingnews.bean.NewsTypeDataBean;
import com.wz.beijingnews.common.model.NewsTypeModel;
import com.wz.beijingnews.presenter.contract.LeftMenuContract;

import java.util.ArrayList;
import java.util.List;

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

public class LeftMenuPresenter extends BasePresenter<NewsTypeModel,LeftMenuContract.View>{
    public LeftMenuPresenter(NewsTypeModel model, LeftMenuContract.View view) {
        super(model, view);
    }

    /**
     * 请求侧滑菜单标题
     */
    public void getLeftMenuTitle(){
        mModel.getNewsType().compose(new ObservableTransformer<NewsTypeBaseBean<NewsTypeDataBean<NewsTypeChildBean>>, List<String>>() {
            @Override
            public ObservableSource<List<String>> apply(final Observable<NewsTypeBaseBean<NewsTypeDataBean<NewsTypeChildBean>>> upstream) {
                return upstream.flatMap(new Function<NewsTypeBaseBean<NewsTypeDataBean<NewsTypeChildBean>>, ObservableSource<List<String>>>() {
                    @Override
                    public ObservableSource<List<String>> apply(final NewsTypeBaseBean<NewsTypeDataBean<NewsTypeChildBean>> newsTypeData) throws Exception {
                        return Observable.create(new ObservableOnSubscribe<List<String>>() {
                            @Override
                            public void subscribe(ObservableEmitter<List<String>> e) throws Exception {
                                List<NewsTypeDataBean<NewsTypeChildBean>> data = newsTypeData.getData();
                                List<String> titles = new ArrayList<String>(data.size());
                                for (int i = 0; i < data.size(); i++) {
                                    titles.add(data.get(i).getTitle());
                                }

                                e.onNext(titles);
                                e.onComplete();
                            }
                        });
                    }
                });
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<List<String>>() {
                    @Override
                    public void onNext(List<String> value) {
                        mView.showLeftMenuTitle(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
