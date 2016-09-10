package com.wutouqishi.v2ex_android;

import android.app.Application;

import org.xutils.x;

/**
 * Created by gengjie on 16/9/7.
 */
public class V2exApplication extends Application
{
    @Override
    public void onCreate() {
        super.onCreate();

        x.Ext.init(this);
//        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志,开启debug会影响性能
    }
}
