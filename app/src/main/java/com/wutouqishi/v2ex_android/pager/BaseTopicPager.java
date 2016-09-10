package com.wutouqishi.v2ex_android.pager;

import android.app.Activity;
import android.view.View;

/**
 * Created by gengjie on 16/9/7.
 */
public abstract  class BaseTopicPager
{
    public Activity mActivity;

    public View mRootView;

    public BaseTopicPager(Activity activity)
    {
        mActivity = activity;
        mRootView = initView();
    }

    public abstract View initView();

    public void initData(){}
}
