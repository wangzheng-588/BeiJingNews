package com.wz.beijingnews.ui.fragment;

import android.support.v7.widget.RecyclerView;

import com.wz.beijingnews.R;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by wz on 17-6-2.
 * 新闻列表详细信息页面
 */

public class NewsDetailFragment extends BaseFragment {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    Unbinder unbinder;

    @Override
    protected int setLayoutResID() {
        return R.layout.fragment_news_detail;
    }

}
