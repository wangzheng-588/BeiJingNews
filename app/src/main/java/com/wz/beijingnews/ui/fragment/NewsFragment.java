package com.wz.beijingnews.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.wz.beijingnews.R;
import com.wz.beijingnews.bean.NewsTypeChildBean;
import com.wz.beijingnews.bean.NewsTypeDataBean;
import com.wz.beijingnews.common.model.NewsTypeModel;
import com.wz.beijingnews.presenter.NewsTypePresenter;
import com.wz.beijingnews.presenter.contract.NewsTypeContract;
import com.wz.beijingnews.ui.adapter.NewsDetailFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by wz on 17-6-2.
 * 首页新闻页面
 */

public class NewsFragment extends BaseFragment implements NewsTypeContract.View {
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    private NewsDetailFragmentAdapter mAdapter;

    @Override
    protected int setLayoutResID() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initData() {
        NewsTypeModel model = new NewsTypeModel();
        NewsTypePresenter newsTypePresenter = new NewsTypePresenter(model, this);
        newsTypePresenter.requestDatas();

    }


    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showResult(List<NewsTypeDataBean<NewsTypeChildBean>> data) {
        initNewsDetailFragment(data);
    }

    private void initNewsDetailFragment(List<NewsTypeDataBean<NewsTypeChildBean>> data) {
        List children = data.get(0).getChildren();
        List<Fragment> fragments = new ArrayList<>(data.size());
        List<String> strings = new ArrayList<>(data.size());
        for (int i = 0; i < children.size(); i++) {
            NewsTypeChildBean newsTypeChildBean = (NewsTypeChildBean) children.get(i);
            String title = newsTypeChildBean.getTitle();
            strings.add(title);
            if (i==0){

                fragments.add(NewsDetailListFragment.newInstance(((NewsTypeChildBean) children.get(i)).getUrl(),false));
            } else {
                fragments.add(NewsDetailListFragment.newInstance(((NewsTypeChildBean) children.get(i)).getUrl(),true));

            }
        }

        mAdapter = new NewsDetailFragmentAdapter(getChildFragmentManager(), fragments, strings);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(children.size());
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void showNoData() {

    }

    @Override
    public void showError() {

    }
}
