package com.wutouqishi.v2ex_android.ui.pager;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.wutouqishi.v2ex_android.R;

/**
 * Created by gengjie on 16/9/7.
 */
public class BasePager<T>
{
    public Activity mActivity;

    public View mRootView;

    public FrameLayout fl_content;

    public RelativeLayout rl_nav;

    public boolean hasData;

    public BasePager(Activity activity)
    {
        mActivity = activity;
        mRootView = initView();
    }

    public View initView()
    {
        View view = View.inflate(mActivity, R.layout.pager_base, null);

        fl_content = (FrameLayout) view.findViewById(R.id.fl_content);
        rl_nav = (RelativeLayout) view.findViewById(R.id.rl_nav);
        System.out.println("fl_content:" + fl_content);
        return view;
    }

    public void initData()
    {
        hasData = true;
    }
}
