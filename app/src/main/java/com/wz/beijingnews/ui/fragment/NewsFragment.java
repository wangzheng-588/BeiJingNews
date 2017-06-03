package com.wz.beijingnews.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.wz.beijingnews.R;
import com.wz.beijingnews.bean.NewsTypeChildBean;
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
//        newsTypePresenter.requestDatas();
        newsTypePresenter.getNewsTypeTitle();

    }


    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }


    @Override
    public void showNewsTypeTitle(List<NewsTypeChildBean> value) {
        initNewsDetailFragment(value);
    }

    private void initNewsDetailFragment(List<NewsTypeChildBean> value) {

        List<Fragment> fragments = new ArrayList<>(value.size());
        List<String> strings = new ArrayList<>(value.size());
        for (int i = 0; i < value.size(); i++) {
            NewsTypeChildBean newsTypeChildBean =  value.get(i);
            String title = newsTypeChildBean.getTitle();
            strings.add(title);
            if (i==0){

                fragments.add(NewsDetailListFragment.newInstance(value.get(i).getUrl(),false));
            } else {
                fragments.add(NewsDetailListFragment.newInstance(value.get(i).getUrl(),true));

            }
        }

        mAdapter = new NewsDetailFragmentAdapter(getChildFragmentManager(), fragments, strings);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(value.size());
        mTabLayout.setupWithViewPager(mViewPager);
    }


    @Override
    public void showError() {

    }

    @Override
    public void showLeftMenuTitle(List<String> value) {

    }
}
