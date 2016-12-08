package com.wutouqishi.v2ex_android.ui.pager;

import android.app.Activity;
import android.view.View;

import com.wutouqishi.v2ex_android.domain.Topic;

import java.util.ArrayList;

/**
 * Created by gengjie on 16/9/7.
 */
public abstract  class BaseTopicPager
{
    public Activity mActivity;

    public View mRootView;

    public ArrayList<Topic> topics;

    public BaseTopicPager(Activity activity)
    {
        mActivity = activity;
        mRootView = initView();
    }

    public abstract View initView();

    public void initData(){}
}
