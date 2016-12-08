package com.wutouqishi.v2ex_android.ui.holder;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.webkit.WebView;

import com.wutouqishi.v2ex_android.R;
import com.wutouqishi.v2ex_android.domain.Topic;
import com.wutouqishi.v2ex_android.domain.TopicDetail;
import com.wutouqishi.v2ex_android.global.GlobalConstants;
import com.wutouqishi.v2ex_android.util.HomeUtil;
import com.wutouqishi.v2ex_android.util.UIUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by wutouqishigj on 2016/12/7.
 */

public class HomeDetailContentHolder extends BaseHolder<TopicDetail> {



    @Override
    public View initView() {

        WebView webView = (WebView) UIUtils.inflate(R.layout.layout_home_detail_content);


        return webView;
    }

    @Override
    public void refreshView(TopicDetail data)
    {
        ((WebView)getMRootView()).loadDataWithBaseURL(GlobalConstants.SERVER_URL, data.getContent(), "text/html", "utf-8", null);
    }
}
