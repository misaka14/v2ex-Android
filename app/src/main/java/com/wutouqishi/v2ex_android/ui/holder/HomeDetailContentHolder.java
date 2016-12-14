package com.wutouqishi.v2ex_android.ui.holder;


import android.content.Context;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.wutouqishi.v2ex_android.R;
import com.wutouqishi.v2ex_android.domain.TopicDetail;
import com.wutouqishi.v2ex_android.global.GlobalConstants;
import com.wutouqishi.v2ex_android.util.UIUtils;

/**
 * Created by wutouqishigj on 2016/12/7.
 */

public class HomeDetailContentHolder extends BaseHolder<TopicDetail>
{
    private WebView webView;

    @Override
    public View initView()
    {

        webView = (WebView) UIUtils.inflate(R.layout.layout_home_detail_content);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);


        return webView;
    }

    @Override
    public void refreshView(TopicDetail data)
    {
        webView.scrollTo(0, 0);
        webView.loadDataWithBaseURL(GlobalConstants.SERVER_URL, data.getContent(), "text/html", "utf-8", null);
    }

    // 刷新页面
    public void refresh()
    {
        webView.reload();
    }


    public class JavascriptInterface {

        private Context context;

        public JavascriptInterface(Context context) {
            this.context = context;
        }

        public void openImage(String img) {
            System.out.println("openImage");
            //
//            Intent intent = new Intent();
//            intent.putExtra("image", img);
//            intent.setClass(context, ShowWebImageActivity.class);
//            context.startActivity(intent);
//            System.out.println(img);
        }
    }
}
