package com.wutouqishi.v2ex_android.ui.pager.impl;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.viewpagerindicator.TabPageIndicator;
import com.wutouqishi.v2ex_android.R;
import com.wutouqishi.v2ex_android.domain.Node;
import com.wutouqishi.v2ex_android.domain.Topic;
import com.wutouqishi.v2ex_android.ui.pager.BasePager;
import com.wutouqishi.v2ex_android.ui.pager.BaseTopicPager;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by gengjie on 16/9/7.
 */
public class HomePager extends BasePager
{
    // 控件
    private TabPageIndicator tpi_title;
    private ViewPager vp_topics;

    // 数据
    private ArrayList<Node> nodes;
    private ArrayList<BaseTopicPager> topicPagers;

    public HomePager(Activity activity)
    {
        super(activity);
    }



    @Override
    public void initData() {
        super.initData();

        View view = View.inflate(mActivity, R.layout.pager_home, null);
        tpi_title = (TabPageIndicator) view.findViewById(R.id.tpi_title);
        vp_topics = (ViewPager) view.findViewById(R.id.vp_topics);
        fl_content.addView(view);

        // 标题数组
        nodes = new ArrayList<Node>();
        nodes.add(new Node("最近", "/?tab=recent"));
        nodes.add(new Node("技术", "/?tab=tech"));
        nodes.add(new Node("创意", "/?tab=creative"));
        nodes.add(new Node("好玩", "/?tab=play"));
        nodes.add(new Node("Apple", "/?tab=apple"));
        nodes.add(new Node("酷工作", "/?tab=jobs"));
        nodes.add(new Node("交易", "/?tab=deals"));
        nodes.add(new Node("城市", "/?tab=city"));
        nodes.add(new Node("问与答", "/?tab=qna"));
        nodes.add(new Node("最热", "/?tab=hot"));
        nodes.add(new Node("R2", "/?tab=r2"));
        nodes.add(new Node("全部", "/?tab=all"));

        topicPagers = new ArrayList<BaseTopicPager>();
        for (int i = 0; i < nodes.size(); i++)
        {
            HomeTopicPager pager = new HomeTopicPager(mActivity, nodes.get(i).getNodeUrl());
            topicPagers.add(pager);
        }

        vp_topics.setAdapter(new TopicPagerAdapter());
        tpi_title.setViewPager(vp_topics);


    }

    class TopicPagerAdapter extends PagerAdapter
    {
        @Override
        public CharSequence getPageTitle(int position) {
            return nodes.get(position).getName();
        }

        @Override
        public int getCount() {
            return nodes.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            BaseTopicPager pager = topicPagers.get(position);

            View view = pager.mRootView;
            container.addView(view);


            pager.initData();


            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
