package com.wz.beijingnews.ui.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.wz.beijingnews.R;
import com.wz.beijingnews.common.Constacts;

import butterknife.BindView;

/**
 * Created by wz on 17-6-4.
 */

public class NewsDetailActivity extends BaseActivity {
    @BindView(R.id.web_view)
    WebView mWebView;
    private Dialog mProgressDialog;

    @Override
    protected int setLayoutRedID() {
        return R.layout.activity_news_detail;
    }

    @Override
    protected void init() {
        String newsUrl = getIntent().getStringExtra("newsUrl");

        initWebView(newsUrl);

    }

    private void initWebView(String newsUrl) {
        newsUrl = Constacts.BASE_URL+(newsUrl.substring(1));
        Log.e("TAG",newsUrl);
        mProgressDialog = ProgressDialog.show(this, "请稍后", "页面正在加载中");
        //设置WebView属性，能够执行Javascript脚本
        WebSettings settings = mWebView.getSettings();
        //支持js脚本
        settings.setJavaScriptEnabled(true);
        //设置启动缓存
        settings.setAppCacheEnabled(true);
        //设置缓存的大小
        settings.setAppCacheMaxSize(1024 * 10);
        //设置缓存模式
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//优先使用缓存
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);//不使用缓存

        //设置缓存，离线应用
        settings.setAppCacheEnabled(true);
        settings.setAppCacheMaxSize(1024 * 1024 * 8);
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

}
