package com.wz.beijingnews.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.wz.beijingnews.R;
import com.wz.beijingnews.bean.NewsBean;
import com.wz.beijingnews.bean.NewsDataBean;
import com.wz.beijingnews.bean.TopNewsBean;
import com.wz.beijingnews.common.model.NewsDetailModel;
import com.wz.beijingnews.presenter.NewsDetailPresenter;
import com.wz.beijingnews.presenter.contract.NewsDetailContract;
import com.wz.beijingnews.ui.adapter.NewsDetailListAdapter;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by wz on 17-6-2.
 * 新闻列表详细信息页面
 */

public class NewsDetailListFragment extends BaseFragment implements NewsDetailContract.View {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;


    public static final String ARGUMENT = "argument";
    @BindView(R.id.material_refresh_layout)
    MaterialRefreshLayout mMaterialRefreshLayout;
    Unbinder unbinder;
    private String mUrl;
    private NewsDetailListAdapter mAdapter;
    private String mMoreUrl;
    private NewsDetailPresenter mPresenter;

    public static NewsDetailListFragment newInstance(String argument) {

        Bundle args = new Bundle();
        args.putString(ARGUMENT, argument);
        NewsDetailListFragment fragment = new NewsDetailListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            mUrl = bundle.getString(ARGUMENT);
            mUrl = mUrl.substring(1);
        }

    }

    @Override
    protected int setLayoutResID() {
        return R.layout.fragment_news_detail;
    }


    @Override
    protected void initData() {
        NewsDetailModel newsDetailModel = new NewsDetailModel();
        mPresenter = new NewsDetailPresenter(newsDetailModel, this);
        mPresenter.requestDatas(mUrl);

        mAdapter = new NewsDetailListAdapter(getActivity());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);

        mMaterialRefreshLayout.setLoadMore(true);
        initListener();
    }

    private void initListener() {
        mMaterialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {

            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                if (!TextUtils.isEmpty(mMoreUrl)){
                    mPresenter.loadMoreDatas(mMoreUrl);
                }

            }
        });
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {
        mMaterialRefreshLayout.finishRefreshLoadMore();
    }

    @Override
    public void showResult(NewsDataBean<NewsBean, TopNewsBean> data) {
        mMoreUrl = data.getMore().substring(1);
        mAdapter.setNewsBeen(data.getNews());
    }

    @Override
    public void showNoData() {

    }

    @Override
    public void showError() {
        mMaterialRefreshLayout.finishRefreshLoadMore();

    }

    @Override
    public void showLoadMoreData(NewsDataBean<NewsBean, TopNewsBean> data) {
        mMoreUrl = data.getMore().substring(1);
        mAdapter.addNewsBeen(data.getNews());
    }

}
