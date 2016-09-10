package com.wutouqishi.v2ex_android.pager.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import com.wutouqishi.v2ex_android.pager.BasePager;

/**
 * Created by gengjie on 16/9/7.
 */
public class MessagePager extends BasePager
{
    public MessagePager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        super.initData();

        TextView tv_title = new TextView(mActivity);
        tv_title.setText("消息");
        tv_title.setTextColor(Color.GREEN);
        tv_title.setTextSize(30);
        tv_title.setGravity(Gravity.CENTER);

        fl_content.addView(tv_title);
    }
}
