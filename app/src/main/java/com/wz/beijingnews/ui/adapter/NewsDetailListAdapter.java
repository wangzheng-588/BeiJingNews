package com.wz.beijingnews.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wz.beijingnews.R;
import com.wz.beijingnews.bean.NewsBean;
import com.wz.beijingnews.common.Constacts;
import com.wz.beijingnews.ui.activity.NewsDetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wz on 17-6-3.
 */

public class NewsDetailListAdapter extends RecyclerView.Adapter<NewsDetailListAdapter.ViewHolder> {

    private final Context mContext;

    private List<NewsBean> mNewsBeen;

    public NewsDetailListAdapter(Context context) {
        this.mContext = context;
    }

    public void setNewsBeen(List<NewsBean> newsBeen) {
        mNewsBeen = newsBeen;
        notifyItemRangeChanged(0, mNewsBeen.size());
    }

    public void addNewsBeen(List<NewsBean> newsBeen) {
        mNewsBeen.addAll(newsBeen);
        notifyItemRangeChanged(mNewsBeen.size(), newsBeen.size());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(mContext, R.layout.item_news_detail_pager, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        NewsBean newsBean = mNewsBeen.get(position);
        holder.mTvTitle.setText(newsBean.getTitle());
        holder.mTvTime.setText(newsBean.getPubdate());
        Glide.with(mContext).load(Constacts.BASE_URL + newsBean.getListimage()).error(R.mipmap.news_pic_default).into(holder.mIvIcon);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,NewsDetailActivity.class);
                intent.putExtra("newsUrl",mNewsBeen.get(position).getUrl());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNewsBeen == null ? 0 : mNewsBeen.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_icon)
        ImageView mIvIcon;
        @BindView(R.id.tv_title)
        TextView mTvTitle;
        @BindView(R.id.tv_time)
        TextView mTvTime;
        @BindView(R.id.icon_news_comment_num)
        ImageView mIconNewsCommentNum;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
