package com.wutouqishi.v2ex_android.global;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.orhanobut.logger.Logger;
import com.wutouqishi.v2ex_android.util.V2exCookieStore;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.HeadBuilder;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.PersistentCookieStore;
import com.zhy.http.okhttp.https.HttpsUtils;

import org.xutils.x;

import java.util.concurrent.TimeUnit;

import okhttp3.CookieJar;
import okhttp3.OkHttpClient;

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
        Logger.init("v2ex");                 // default PRETTYLOGGER or use just init()



        context = getApplicationContext();
        handler = new Handler();
        mainThreadId = android.os.Process.myTid();

        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
        V2exCookieStore cookieStore = new V2exCookieStore(getApplicationContext());
        CookieJarImpl cookieJar = new CookieJarImpl(cookieStore);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .cookieJar(cookieJar)
                //其他配置
                .build();





        OkHttpUtils.initClient(okHttpClient);
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
