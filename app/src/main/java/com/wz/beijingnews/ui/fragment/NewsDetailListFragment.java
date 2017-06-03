package com.wz.beijingnews.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wz.beijingnews.R;
import com.wz.beijingnews.bean.NewsBean;
import com.wz.beijingnews.bean.NewsDataBean;
import com.wz.beijingnews.bean.TopNewsBean;
import com.wz.beijingnews.common.model.NewsDetailModel;
import com.wz.beijingnews.presenter.NewsDetailPresenter;
import com.wz.beijingnews.presenter.contract.NewsDetailContract;
import com.wz.beijingnews.ui.adapter.NewsDetailListAdapter;

import butterknife.BindView;

/**
 * Created by wz on 17-6-2.
 * 新闻列表详细信息页面
 */

public class NewsDetailListFragment extends BaseFragment implements NewsDetailContract.View {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;


    public static final String ARGUMENT = "argument";
    private String mUrl;
    private NewsDetailListAdapter mAdapter;

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
        if (bundle != null)
        {
            mUrl = bundle.getString(ARGUMENT);
            mUrl = mUrl.substring(1);
        }

    }

    @Override
    protected void initData() {
        NewsDetailModel newsDetailModel = new NewsDetailModel();
        NewsDetailPresenter presenter = new NewsDetailPresenter(newsDetailModel, this);
        presenter.requestDatas(mUrl);

        mAdapter = new NewsDetailListAdapter(getActivity());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected int setLayoutResID() {
        return R.layout.fragment_news_detail;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showResult(NewsDataBean<NewsBean, TopNewsBean> data) {
        mAdapter.setNewsBeen(data.getNews());
    }

    @Override
    public void showNoData() {

    }

    @Override
    public void showError() {

    }
}
