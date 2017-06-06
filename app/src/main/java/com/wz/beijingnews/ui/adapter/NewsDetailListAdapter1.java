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
import com.wz.beijingnews.common.utils.SPUtil;
import com.wz.beijingnews.ui.activity.NewsDetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wz on 17-6-5.
 */

public class NewsDetailListAdapter1 extends BaseRecyclerAdapter<NewsBean> {

    private final Context mContext;

    public NewsDetailListAdapter1(Context context) {
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        return new MyViewHolder(View.inflate(mContext, R.layout.item_news_detail_pager, null));
    }

    @Override
    public void onBind(RecyclerView.ViewHolder holder, final int RealPosition, final NewsBean newsBean) {
        if (holder instanceof MyViewHolder){

            ((MyViewHolder)holder).mTvTitle.setText(newsBean.getTitle());
            ((MyViewHolder)holder).mTvTime.setText(newsBean.getPubdate());
            Glide.with(mContext).load(Constacts.BASE_URL + newsBean.getListimage()).error(R.mipmap.news_pic_default).into(((MyViewHolder)holder).mIvIcon);

            String read_id = SPUtil.getString(mContext, "READ_ID", "");
            if (read_id.contains(newsBean.getId()+"")){
                ((MyViewHolder) holder).mTvTitle.setTextColor(mContext.getResources().getColor(R.color.gray));
            } else {
                ((MyViewHolder) holder).mTvTitle.setTextColor(mContext.getResources().getColor(R.color.black));


            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext,NewsDetailActivity.class);
                    intent.putExtra("newsUrl",newsBean.getUrl());
                    mContext.startActivity(intent);

                    String read_id = SPUtil.getString(mContext, "READ_ID", "");
                    if (!read_id.contains(newsBean.getId()+"")){
                        read_id = read_id+newsBean.getId()+",";
                        SPUtil.putString(mContext,"READ_ID",read_id);
                        notifyItemChanged(RealPosition+1);
                    }


                }
            });
        }
    }


    class MyViewHolder extends BaseRecyclerAdapter.Holder {

        @BindView(R.id.iv_icon)
        ImageView mIvIcon;
        @BindView(R.id.tv_title)
        TextView mTvTitle;
        @BindView(R.id.tv_time)
        TextView mTvTime;
        @BindView(R.id.icon_news_comment_num)
        ImageView mIconNewsCommentNum;

        MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
