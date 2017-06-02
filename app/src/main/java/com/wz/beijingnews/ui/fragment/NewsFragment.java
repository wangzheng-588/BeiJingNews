package com.wz.beijingnews.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.wz.beijingnews.R;
import com.wz.beijingnews.bean.NewsTypeChildBean;
import com.wz.beijingnews.bean.NewsTypeDataBean;
import com.wz.beijingnews.common.model.NewsTypeModel;
import com.wz.beijingnews.presenter.contract.NewsTypeContract;
import com.wz.beijingnews.presenter.NewsTypePresenter;
import com.wz.beijingnews.ui.adapter.NewsDetailAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by wz on 17-6-2.
 * 首页新闻页面
 */

public class NewsFragment extends BaseFragment implements NewsTypeContract.View {
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    Unbinder unbinder;

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


    private void initFragment(List<Fragment> fragments, List<String> titles) {
        NewsDetailAdapter adapter = new NewsDetailAdapter(getChildFragmentManager(), fragments, titles);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
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
            fragments.add(new NewsDetailFragment());
        }

        NewsDetailAdapter adapter = new NewsDetailAdapter(getChildFragmentManager(), fragments, strings);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void showNoData() {

    }

    @Override
    public void showError() {

    }
}
