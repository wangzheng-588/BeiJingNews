package com.wz.beijingnews.ui.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.wz.beijingnews.R;
import com.wz.beijingnews.common.Constacts;

import butterknife.BindView;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by wz on 17-6-4.
 */

public class NewsDetailActivity extends BaseActivity {
    @BindView(R.id.web_view)
    WebView mWebView;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.iv_news_share)
    ImageView mIvNewsShare;
    @BindView(R.id.iv_news_textsize)
    ImageView mIvNewsTextsize;
    private Dialog mProgressDialog;
    private int mTempWhich = 1;
    private int mRealWhich = mTempWhich;

    String[] items = {"小号字体","普通字体","大号字体","超大字体"};

    @Override
    protected int setLayoutRedID() {
        return R.layout.activity_news_detail;
    }

    @Override
    protected void init() {
        String newsUrl = getIntent().getStringExtra("newsUrl");
        initWebView(newsUrl);
        initListener();
    }

    private void initListener() {
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mIvNewsShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare();
            }
        });

        mIvNewsTextsize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(NewsDetailActivity.this)
                       .setTitle("设置文字大小")
                       .setSingleChoiceItems(items, mRealWhich, new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                               mTempWhich = which;
                           }
                       })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mRealWhich = mTempWhich;
                                changeTextSize(mTempWhich);
                            }
                        })
                       .show();
            }
        });
    }

    private void changeTextSize(int which) {
        WebSettings settings = mWebView.getSettings();
        switch (which){
            case 0:
                settings.setTextZoom(75);
                break;
            case 1:
                settings.setTextZoom(100);
                break;
            case 2:
                settings.setTextZoom(150);
                break;
            case 3:
                settings.setTextZoom(200);
                break;
        }
    }

    private void initWebView(String newsUrl) {
        newsUrl = Constacts.BASE_URL + (newsUrl.substring(1));
        Log.e("TAG", newsUrl);
        mProgressDialog = ProgressDialog.show(this, "请稍后", "页面正在加载中");
        //设置WebView属性，能够执行Javascript脚本
        WebSettings settings = mWebView.getSettings();
        //支持js脚本
        settings.setJavaScriptEnabled(true);
        //设置启动缓存
        settings.setAppCacheEnabled(true);

        //设置缓存模式
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//优先使用缓存
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);//不使用缓存

        //设置缓存，离线应用
        settings.setAppCacheEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);


        //设置可以自动加载图片
        settings.setLoadsImagesAutomatically(true);
        //设置可以访问文件
        settings.setAllowFileAccess(true);
        //设置图片调整到适合webView的方法
        settings.setUseWideViewPort(false);
        //设置支持多窗口的
        settings.setSupportMultipleWindows(true);
        //支持缩放，设置成拖动放大缩小
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        //支持内容从新布局
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        //加载需要显示的网页
        mWebView.loadUrl(newsUrl);

        mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // return super.shouldOverrideUrlLoading(view, url);
                mWebView.loadUrl(url);
                return true;
            }

            //当开始载入页面的时候调用
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                //显示正在加载中的对话框
                mProgressDialog.show();
            }

            //当一个页面加载完成时候调用
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //隐藏正在加载的圣诞框的提示信息
                mProgressDialog.dismiss();

            }


        });

        mWebView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mWebView.requestFocus();
                return false;
            }
        });
    }


   /* @Override
    //设置回退
    //覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack(); //goBack()表示返回WebView的上一页面
            return true;
        }
        return false;
    }*/


    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle("标题");
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("ShareSDK");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(this);
    }

}
