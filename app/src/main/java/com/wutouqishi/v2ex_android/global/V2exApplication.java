package com.wutouqishi.v2ex_android.global;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import org.xutils.x;

/**
 * Created by gengjie on 16/9/7.
 */
public class V2exApplication extends Application
{
    private static Context context;
    private static Handler handler;
    private static int mainThreadId;

    @Override
    public void onCreate() {
        super.onCreate();

        x.Ext.init(this);
//        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志,开启debug会影响性能

        context = getApplicationContext();
        handler = new Handler();
        mainThreadId = android.os.Process.myTid();

    }

    public static Context getContext()
    {
        return context;
    }

    public static Handler getHandler()
    {
        return handler;
    }
    public static int getMainThreadId()
    {
        return mainThreadId;
    }
}
