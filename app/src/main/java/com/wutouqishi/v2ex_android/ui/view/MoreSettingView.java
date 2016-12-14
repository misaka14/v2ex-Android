package com.wutouqishi.v2ex_android.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wutouqishi.v2ex_android.util.UIUtils;

import org.w3c.dom.Text;

/**
 * Created by wutouqishigj on 2016/12/13.
 */

public class MoreSettingView extends LinearLayout {

    private TextView tv_title;

    public MoreSettingView(Context context)
    {
        super(context, null);
    }

    public MoreSettingView(Context context, AttributeSet attrs)
    {
        super(context, attrs, 0);
    }

    public MoreSettingView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // 获取控件的长、宽
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }
}
