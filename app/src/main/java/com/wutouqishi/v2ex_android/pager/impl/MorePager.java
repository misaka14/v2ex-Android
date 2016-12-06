package com.wutouqishi.v2ex_android.pager.impl;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wutouqishi.v2ex_android.LoginActivity;
import com.wutouqishi.v2ex_android.R;
import com.wutouqishi.v2ex_android.pager.BasePager;

/**
 * Created by gengjie on 16/9/7.
 */
public class MorePager extends BasePager implements View.OnClickListener
{
    public MorePager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        super.initData();

        View view = View.inflate(mActivity, R.layout.pager_more, null);

        Button btn_login = (Button) view.findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);

        fl_content.addView(view);
    }

    @Override
    public void onClick(View view)
    {
        Intent intent = new Intent(mActivity, LoginActivity.class);
        mActivity.startActivity(intent);
    }
}
