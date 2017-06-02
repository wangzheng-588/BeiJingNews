package com.wz.beijingnews.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.wz.beijingnews.R;
import com.wz.beijingnews.ui.adapter.NewsDetailAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by wz on 17-6-2.
 * 首页新闻页面
 */

public class NewsFragment extends BaseFragment {
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


    }


    private void initFragment(List<Fragment> fragments, List<String> titles) {
        NewsDetailAdapter adapter = new NewsDetailAdapter(getChildFragmentManager(), fragments, titles);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }


}
