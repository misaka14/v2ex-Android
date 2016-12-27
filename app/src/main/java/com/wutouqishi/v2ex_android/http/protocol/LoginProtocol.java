package com.wutouqishi.v2ex_android.http.protocol;

import com.orhanobut.logger.Logger;
import com.wutouqishi.v2ex_android.global.GlobalConstants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.cookie.CookieJarImpl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Cookie;

/**
 * Created by wutouqishigj on 2016/12/15.
 */

public class LoginProtocol
{
    private String next = "/";

    private String once;

    private static String onceKey = "once";
    private static String nextKey = "next";

    private String usernameKey;

    private String passwordKey;



    public void login(String username, String password, LoginCallBack loginCallBack)
    {
        loginPrepare(loginCallBack, username, password);
    }

    public void loginPrepare(final LoginCallBack loginCallBack, final String username, final String password)
    {
        OkHttpUtils.get().url(GlobalConstants.LOGINURL).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                if (loginCallBack != null)
                {
                    loginCallBack.failure("服务器异常，请稍候重试");
                }
            }

            @Override
            public void onResponse(String response, int id) {
                System.out.println("response:" + response);
                parseLoginParamWithResponse(response);

                logining(loginCallBack, username, password);
            }
        });
    }

    public void logining(final LoginCallBack loginCallBack, String username, String password)
    {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put(usernameKey, username);
        params.put(passwordKey, password);
        params.put(nextKey, next);
        params.put(onceKey, once);
        System.out.println("paramslogining:"+ params);

        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Referer", GlobalConstants.LOGINURL);
        headers.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.12; rv:50.0) Gecko/20100101 Firefox/50.0");
        headers.put("Upgrade-Insecure-Requests", "1");

        CookieJarImpl cookieJar1 = (CookieJarImpl) OkHttpUtils.getInstance().getOkHttpClient().cookieJar();
        List<Cookie> cookies = cookieJar1.getCookieStore().getCookies();
        System.out.println("cookies:" + cookies);

        OkHttpUtils.post().url(GlobalConstants.LOGINURL).params(params).headers(headers).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                if (loginCallBack != null)
                {
                    loginCallBack.failure("");
                }
            }

            @Override
            public void onResponse(String response, int id)
            {
                parseLoginAfterHTMLWithResponse(response);

                if (loginCallBack != null)
                {
                    loginCallBack.success();
                }
            }
        });
    }

    public void parseLoginParamWithResponse(String response)
    {
        Document doc = Jsoup.parse(response);

        once = doc.select("input[name=once]").get(0).attr("value");
        System.out.println("once:" + once);

        Elements sls = doc.select("input.sl");

        if (sls.size() > 1)
        {
            usernameKey = sls.get(0).attr("name");
            passwordKey = sls.get(1).attr("name");
        }
    }

    public void parseLoginAfterHTMLWithResponse(String response)
    {
        System.out.println("loginHTML:" + response);
    }

    public abstract static class LoginCallBack
    {
        public abstract void success();

        public abstract void failure(String errorMsg);
    }
}
