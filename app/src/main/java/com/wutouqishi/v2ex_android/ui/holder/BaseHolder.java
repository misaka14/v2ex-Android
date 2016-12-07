package com.wutouqishi.v2ex_android.ui.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wutouqishi.v2ex_android.R;

import org.xutils.view.annotation.ViewInject;

/**
 * Created by wutouqishigj on 2016/12/7.
 */

public abstract class BaseHolder<T>
{
    private View mRootView;

    private T data;

    public BaseHolder()
    {
        mRootView = initView();
        mRootView.setTag(this);
    }

    public View getMRootView()
    {
        return this.mRootView;
    }

    public void setData(T data)
    {
        this.data = data;
        refreshView(data);
    }

    public abstract View initView();

    public abstract void refreshView(T data);

}
