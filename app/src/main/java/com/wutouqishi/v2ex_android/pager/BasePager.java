package com.wutouqishi.v2ex_android.pager;

import android.app.Activity;
import android.provider.Settings;
import android.view.View;
import android.widget.FrameLayout;

import com.wutouqishi.v2ex_android.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by gengjie on 16/9/7.
 */
public class BasePager
{
    public Activity mActivity;

    public View mRootView;

    public FrameLayout fl_content;

    public BasePager(Activity activity)
    {
        mActivity = activity;
        mRootView = initView();
    }

    public View initView()
    {
        View view = View.inflate(mActivity, R.layout.pager_base, null);

        fl_content = (FrameLayout) view.findViewById(R.id.fl_content);
        System.out.println("fl_content:" + fl_content);
        return view;
    }

    public void initData(){}
}
