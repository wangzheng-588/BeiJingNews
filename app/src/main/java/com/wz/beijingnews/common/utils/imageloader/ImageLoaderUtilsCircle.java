package com.wz.beijingnews.common.utils.imageloader;

import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.wz.beijingnews.R;

/**
 * Created by wz on 17-6-9.
 */

public class ImageLoaderUtilsCircle {

    public static DisplayImageOptions MyDisplayImageOptions() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ssdk_oks_classic_check_default) //设置图片在下载期间显示的图片
                .showImageForEmptyUri(R.drawable.ssdk_oks_classic_check_default)//设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.ssdk_oks_classic_check_default)  //设置图片加载/解码过程中错误时候显示的图片
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)
                .considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//
                .displayer(new RoundedBitmapDisplayer(360))//是否设置为圆角，弧度为多少
//      .displayer(new FadeInBitmapDisplayer(100))//是否图片加载好后渐入的动画时间
                .build();//构建完成

        return options;
    }

}
