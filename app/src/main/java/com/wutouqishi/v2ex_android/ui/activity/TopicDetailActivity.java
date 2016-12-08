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
    private ArrayList<TopicDetail> topicComments = new ArrayList();

    private TopicDetail topicDetail;

    private Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            ArrayList topicDetails = (ArrayList) msg.obj;
            topicDetail = (TopicDetail) topicDetails.get(0);
//            System.out.println("topicDetails:" + topicDetails);

            homeDetailContentHolder.setData(topicDetail);
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

    private HomeDetailContentHolder homeDetailContentHolder;

    private Topic mTopic;

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

//    @Override
    public void initView() {
//        super.initView();

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

    }

//    @Override
    public void initData()
    {
//        String url = "https://www.v2ex.com/t/326059";
        String url = "https://www.v2ex.com/t/326182#reply119";  // 超过1百条评论
//        String url = mTopic.getDetailUrl();
        Logger.i("detailUrl", url);
        HomeUtil.parseTopicWithDetailUrl(url, myHandler, this);
    }
}
