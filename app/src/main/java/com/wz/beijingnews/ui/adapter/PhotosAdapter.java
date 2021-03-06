package com.wz.beijingnews.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.wz.beijingnews.R;
import com.wz.beijingnews.bean.PhotosDataBean;
import com.wz.beijingnews.bean.PhotosNewsBean;
import com.wz.beijingnews.common.Constacts;
import com.wz.beijingnews.common.utils.imageloader.ImageLoaderUtilsCircle;
import com.wz.beijingnews.ui.activity.PhotoViewActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wz on 17-6-5.
 */

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ViewHolder>{

    private final Context mContext;
    private ArrayList<PhotosNewsBean> mPhotosNewsBeen;

    public PhotosAdapter(Context context) {
        this.mContext = context;
    }

    public void setPhotosNewsBeen(ArrayList<PhotosNewsBean> photosNewsBeen) {
        mPhotosNewsBeen = photosNewsBeen;
        notifyDataSetChanged();
    }

    @Override
    public PhotosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_photos,parent,false));
    }

    @Override
    public void onBindViewHolder(PhotosAdapter.ViewHolder holder, int position) {
        PhotosNewsBean photosNewsBean = mPhotosNewsBeen.get(position);
        String title = photosNewsBean.getTitle();
        holder.mTvTitle.setText(title);
        String largeimageUrl = photosNewsBean.getLargeimage().substring(1);
        largeimageUrl = Constacts.BASE_URL+largeimageUrl;


        //使用glide圆角图片加载
//        Glide.with(mContext).load(largeimageUrl)
//                .transform(new GlideRoundTransform(mContext,20))
//               .placeholder(R.mipmap.news_pic_default)
//                .into(holder.mImageView);

        ImageLoader.getInstance().displayImage(largeimageUrl, holder.mImageView , ImageLoaderUtilsCircle.MyDisplayImageOptions());


//
//        RxImageLoader.with(mContext).load(largeimageUrl)
//                .into(holder.mImageView);

      // loadIntoUseFitWidth(mContext, largeimageUrl, R.mipmap.news_pic_default, holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mPhotosNewsBeen==null?0:mPhotosNewsBeen.size();
    }

    public void addPhotosNewsBeen(PhotosDataBean<PhotosNewsBean> value) {
        mPhotosNewsBeen.addAll(value.getNews());
        notifyItemRangeChanged(mPhotosNewsBeen.size(),mPhotosNewsBeen.size()+value.getNews().size());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_photos_icon)
        ImageView mImageView;
        @BindView(R.id.tv_title)
        TextView mTvTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext,PhotoViewActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("photos",mPhotosNewsBeen);
                    bundle.putInt("position",getLayoutPosition());
                    intent.putExtras(bundle);
                    mContext.startActivity(intent);
                }
            });
        }
    }


    public static void loadIntoUseFitWidth(Context context, final String imageUrl, int errorImageId, final ImageView imageView) {
        Glide.with(context)
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        if (imageView == null) {
                            return false;
                        }
                        if (imageView.getScaleType() != ImageView.ScaleType.FIT_XY) {
                            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        }
                        ViewGroup.LayoutParams params = imageView.getLayoutParams();
                        int vw = imageView.getWidth() - imageView.getPaddingLeft() - imageView.getPaddingRight();
                        float scale = (float) vw / (float) resource.getIntrinsicWidth();
                        int vh = Math.round(resource.getIntrinsicHeight() * scale);
                        params.height = vh + imageView.getPaddingTop() + imageView.getPaddingBottom();
                        imageView.setLayoutParams(params);
                        return false;
                    }
                })
                .placeholder(errorImageId)
                .error(errorImageId)
                .into(imageView);
    }







}
