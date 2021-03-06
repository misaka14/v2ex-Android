package com.wutouqishi.v2ex_android.ui.pager.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import com.wutouqishi.v2ex_android.ui.pager.BasePager;

/**
 * Created by gengjie on 16/9/7.
 */
public class NotificationPager extends BasePager
{
    public NotificationPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        super.initData();

        TextView tv_title = new TextView(mActivity);
        tv_title.setText("通知");
        tv_title.setTextColor(Color.GREEN);
        tv_title.setTextSize(30);
        tv_title.setGravity(Gravity.CENTER);

        fl_content.addView(tv_title);
    }
}
