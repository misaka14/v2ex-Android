package com.wutouqishi.v2ex_android.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.wutouqishi.v2ex_android.R;
import com.wutouqishi.v2ex_android.ui.holder.HomeDetailContentHolder;
import com.wutouqishi.v2ex_android.ui.holder.HomeDetailFooterHolder;
import com.wutouqishi.v2ex_android.ui.holder.HomeDetailHeaderHolder;
import com.wutouqishi.v2ex_android.util.HomeUtil;
import com.wutouqishi.v2ex_android.domain.Topic;
import com.wutouqishi.v2ex_android.domain.TopicDetail;
import com.wutouqishi.v2ex_android.util.LogUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by gengjie on 16/9/10.
 */
public class TopicDetailActivity extends BaseAcitivy
{
    private TopicDetail topicDetail;

    private Topic mTopic;

    private String mTopicDetailUrl;

    private Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            topicDetail = (TopicDetail) msg.obj;
            topicDetail.setDetailUrl(mTopic.getDetailUrl());
            homeDetailContentHolder.setData(topicDetail);
            homeDetailFooterHolder.setData(topicDetail);
            fl_loading.setVisibility(View.GONE);

        }
    };

    @ViewInject(R.id.ib_back)
    private ImageButton ib_back;

    @ViewInject(R.id.tv_tabbar_title)
    private TextView tv_tabbar_title;

    @ViewInject(R.id.fl_header)
    private FrameLayout fl_header;

    @ViewInject(R.id.fl_content)
    private FrameLayout fl_content;

    @ViewInject(R.id.fl_loading)
    private FrameLayout fl_loading;

    @ViewInject(R.id.fl_footer)
    private FrameLayout fl_footer;

    private HomeDetailContentHolder homeDetailContentHolder;
    private HomeDetailFooterHolder homeDetailFooterHolder;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_topic_detail);
        x.view().inject(this);

        mTopic = (Topic) getIntent().getSerializableExtra("topic");
        LogUtils.i("topicDetailUrl:" + mTopic.getDetailUrl());

        initView();
        initData();
    }

    public void initView() {

        // 标题栏
        tv_tabbar_title.setText("阅读话题");
        ib_back.setVisibility(View.VISIBLE);
        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        // 头部
        HomeDetailHeaderHolder homeDetailHeaderHolder = new HomeDetailHeaderHolder();
        homeDetailHeaderHolder.setData(mTopic);
        fl_header.addView(homeDetailHeaderHolder.getMRootView());

        // 正文
        homeDetailContentHolder = new HomeDetailContentHolder();
        fl_content.addView(homeDetailContentHolder.getMRootView());

        // 底部工具栏
        homeDetailFooterHolder = new HomeDetailFooterHolder() {

            @Override
            public void refresh() {
                homeDetailContentHolder.refresh();
            }

            @Override
            public void prevPage(int currentPage)
            {
                initData(currentPage);
            }

            @Override
            public void nextPage(int currentPage)
            {
                initData(currentPage);
            }
        };

        homeDetailFooterHolder.setCurrentPage(mTopic.getDetailUrl());
        fl_footer.addView(homeDetailFooterHolder.getMRootView());

    }

    public void initData()
    {
//        String url = "https://www.v2ex.com/t/326059";
        String url = "https://www.v2ex.com/t/326182#reply119";  // 超过1百条评论
//        String url = mTopic.getDetailUrl();

//        Logger.i("detailUrl", url);
//        mTopic.setDetailUrl(url);
        mTopicDetailUrl = url.substring(0, url.indexOf("#reply"));
        initData(0);
    }

    private void initData(int currentPage)
    {
        String url;
        if (currentPage > 0)
        {
            url = getDetailUrl(currentPage);
        }
        else
        {
            url = "https://www.v2ex.com/t/326182#reply119";
        }

        HomeUtil.parseTopicWithDetailUrl(url, myHandler, this);
    }

    private String getDetailUrl(int currentPage)
    {
        String url = mTopicDetailUrl + "?p=" + currentPage;
        mTopic.setDetailUrl(url);
        return url;
    }
}