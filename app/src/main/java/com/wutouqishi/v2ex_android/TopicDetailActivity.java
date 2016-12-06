package com.wutouqishi.v2ex_android;

import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.wutouqishi.v2ex_android.Util.HomeUtil;
import com.wutouqishi.v2ex_android.domain.Topic;
import com.wutouqishi.v2ex_android.domain.TopicComment;
import com.wutouqishi.v2ex_android.domain.TopicDetail;
import com.wutouqishi.v2ex_android.global.GlobalConstants;

import org.w3c.dom.Text;
import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gengjie on 16/9/10.
 */
public class TopicDetailActivity extends AppCompatActivity
{
    private List topicComments = new ArrayList();

    private TopicDetail topicDetail = new TopicDetail();

    private Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            ArrayList topicDetails = (ArrayList) msg.obj;
            topicDetail = (TopicDetail) topicDetails.get(0);
            System.out.println("topicDetails:" + topicDetails);

//            if (topicDetails.size() > 1)
//            {
//                topicComments = topicDetails.subList(1, topicDetails.size() - 1);
//            }

            System.out.println("topicDetailHTML:" + topicDetail.getContent());
            wv_content.loadDataWithBaseURL(GlobalConstants.SERVER_URL, topicDetail.getContent(), "text/html", "utf-8", null);
            //commentAdapter.notifyDataSetChanged();
        }
    };

    @ViewInject(R.id.ib_back)
    private ImageButton ib_back;

    @ViewInject(R.id.tv_tabbar_title)
    private TextView tv_tabbar_title;

    @ViewInject(R.id.wv_content)
    private WebView wv_content;

    @ViewInject(R.id.iv_avatar)
    private ImageView iv_avatar;

    @ViewInject(R.id.tv_author)
    private TextView tv_author;

    @ViewInject(R.id.tv_title)
    private TextView tv_title;

    @ViewInject(R.id.tv_node)
    private TextView tv_node;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_topic_detail);
        x.view().inject(this);

        initView();
        initData();

        InputStreamReader inputReader = null;
        try {
            inputReader = new InputStreamReader(
                    getResources().getAssets().open("light.css"));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String Result = "";
            while ((line = bufReader.readLine()) != null)
                Result += line;
            System.out.println("result:" + Result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initView()
    {
        // 标题栏
        tv_tabbar_title.setText("阅读话题");
        ib_back.setVisibility(View.VISIBLE);
        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        // 话题详情
//        tv_author.setText();
    }

    private void initData()
    {
        Topic topic = (Topic) getIntent().getSerializableExtra("topic");
        System.out.println("topicDetailUrl:" + topic.getDetailUrl());

        tv_author.setText(topic.getAuthor());
        tv_title.setText(topic.getTitle());
        tv_node.setText(topic.getNode());

        ImageOptions options = new ImageOptions.Builder().setRadius(DensityUtil.dip2px(3)).setLoadingDrawableId(R.mipmap.topic_avatar_normal).build();
        x.image().bind(iv_avatar, topic.getAvatarURL(), options);

        HomeUtil.parseTopicWithDetailUrl(topic.getDetailUrl(), myHandler, this);
    }
}
