package com.wz.beijingnews.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.wz.beijingnews.R;
import com.wz.beijingnews.bean.NewsBean;
import com.wz.beijingnews.bean.NewsDataBean;
import com.wz.beijingnews.bean.TopNewsBean;
import com.wz.beijingnews.common.model.NewsDetailModel;
import com.wz.beijingnews.di.component.AppComponent;
import com.wz.beijingnews.di.component.DaggerNewsDetailListComponent;
import com.wz.beijingnews.di.module.NewsDetailListModule;
import com.wz.beijingnews.presenter.NewsDetailPresenter;
import com.wz.beijingnews.presenter.contract.NewsDetailContract;
import com.wz.beijingnews.ui.adapter.NewsDetailListAdapter1;
import com.wz.beijingnews.ui.adapter.TopNewsAdapter;
import com.youth.banner.Banner;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by wz on 17-6-2.
 * 新闻列表详细信息页面
 */

public class NewsDetailListFragment extends ProgressFragment implements NewsDetailContract.View {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;


    public static final String ARGUMENT = "argument";
    public static final String ISLAZYLOAD = "islazyload";

    @BindView(R.id.material_refresh_layout)
    MaterialRefreshLayout mMaterialRefreshLayout;
//    @BindView(R.id.banner)
//    Banner mBanner;


    private String mUrl;
    private NewsDetailListAdapter1 mAdapter;
    private String mMoreUrl;
    private boolean isLazyLoad = false;

    @Inject
    NewsDetailPresenter mPresenter;
    @Inject
    NewsDetailModel mNewsDetailModel;

    private Banner mBanner;
    private ViewPager mViewPager;
    private TextView mTvTitle;
    private LinearLayout mLlPointGray;
    private TopNewsAdapter mTopNewsAdapter;
    private ImageView mRedPoint;
    private int prePosition;

    public static NewsDetailListFragment newInstance(String argument, boolean isLazyLoad) {

        Bundle args = new Bundle();
        args.putString(ARGUMENT, argument);
        args.putBoolean(ISLAZYLOAD, isLazyLoad);
        NewsDetailListFragment fragment = new NewsDetailListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            isLazyLoad = bundle.getBoolean(ISLAZYLOAD);
            mUrl = bundle.getString(ARGUMENT);
            if (!TextUtils.isEmpty(mUrl)) {
                mUrl = mUrl.substring(1);
            }
        }

    }

    @Override
    protected void setupAppComponent(AppComponent appComponent) {
        DaggerNewsDetailListComponent.builder().appComponent(appComponent)
                .newsDetailListModule(new NewsDetailListModule(this))
                .build().inject(this);
    }


    @Override
    protected void init() {
        if (!isLazyLoad) {
            mPresenter.requestDatas(mUrl);
            mPresenter.getTopNewsDetail(mUrl);
        }


        //新闻数据列表
        // mAdapter = new NewsDetailListAdapter(getActivity());
        mAdapter = new NewsDetailListAdapter1(getActivity());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // mBanner = new Banner(getActivity());

        mRecyclerView.setAdapter(mAdapter);
        View header = LayoutInflater.from(mContext).inflate(R.layout.header, mRecyclerView, false);
        mTvTitle = (TextView) header.findViewById(R.id.tv_title);
        mLlPointGray = (LinearLayout) header.findViewById(R.id.ll_point_gray);
        mViewPager = (ViewPager) header.findViewById(R.id.view_pager);

        mAdapter.setHeaderView(header);
        mMaterialRefreshLayout.setLoadMore(true);

        initListener();
    }


    @Override
    public int setLayout() {
        return R.layout.fragment_news_detail;
    }

    private void initListener() {
        mMaterialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                mPresenter.requestDatas(mUrl);
                mPresenter.getTopNewsDetail(mUrl);
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                if (!TextUtils.isEmpty(mMoreUrl)) {
                    mPresenter.loadMoreDatas(mMoreUrl);
                }

            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mTopNewsAdapter.setTitle(mTvTitle, position);
                for (int i = 0; i < mLlPointGray.getChildCount(); i++) {

                    mLlPointGray.getChildAt(prePosition).setBackgroundResource(R.drawable.topnews_bg_shape_gray);
                   mLlPointGray.getChildAt(position).setBackgroundResource(R.drawable.topnews_bg_shape_red);

                }

                prePosition = position;
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    public void showLoading() {
        showProgress();
    }

    @Override
    public void dismissLoading() {
        showContentView();
        mMaterialRefreshLayout.finishRefreshLoadMore();
        mMaterialRefreshLayout.finishRefresh();
    }

    @Override
    public void showTopNews(List<TopNewsBean> value) {
        mLlPointGray.removeAllViews();
        for (int i = 0; i < value.size(); i++) {

            ImageView imageView = new ImageView(mContext);
            imageView.setImageResource(R.drawable.topnews_bg_shape_gray);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = 5;
            imageView.setLayoutParams(params);
            mLlPointGray.addView(imageView);
        }
        mTopNewsAdapter = new TopNewsAdapter(mContext);
        mTopNewsAdapter.setData(value);
        mViewPager.setAdapter(mTopNewsAdapter);


    }

    @Override
    public void showResult(NewsDataBean<NewsBean, TopNewsBean> data) {
        mMoreUrl = data.getMore().substring(1);
        mAdapter.setDatas(data.getNews());

    }

    @Override
    public void showNoData() {
        showEmptyView();
    }

    @Override
    public void showError(String msg) {
        showEmptyView(msg);
        mMaterialRefreshLayout.finishRefreshLoadMore();
        mMaterialRefreshLayout.finishRefresh();

    }

    @Override
    public void showLoadMoreData(NewsDataBean<NewsBean, TopNewsBean> data) {
        mMoreUrl = data.getMore().substring(1);
        mAdapter.addDatas(data.getNews());
    }

    @Override
    protected void onFragmentFirstVisible() {
        if (isLazyLoad) {
            mPresenter.requestDatas(mUrl);
            mPresenter.getTopNewsDetail(mUrl);
        }
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
