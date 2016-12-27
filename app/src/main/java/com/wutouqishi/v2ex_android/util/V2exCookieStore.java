package com.wutouqishi.v2ex_android.util;



import com.zhy.http.okhttp.cookie.store.CookieStore;
import com.zhy.http.okhttp.cookie.store.SerializableHttpCookie;


import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.Cookie;
import okhttp3.HttpUrl;

/**
 * Created by wutouqishigj on 2016/12/28.
 */

public class V2exCookieStore implements CookieStore
{
    private static final String LOG_TAG = "V2exCookieStore";
    private static final String COOKIE_PREFS = "CookiePrefsFile";
    private static final String COOKIE_NAME_PREFIX = "cookie_";

    private final HashMap<String, ConcurrentHashMap<String, Cookie>> cookies;
    private final SharedPreferences cookiePrefs;

    /**
     * Construct a persistent cookie store.
     *
     * @param context Context to attach cookie store to
     */
    public V2exCookieStore(Context context)
    {
        cookiePrefs = context.getSharedPreferences(COOKIE_PREFS, 0);
        cookies = new HashMap<String, ConcurrentHashMap<String, Cookie>>();

        // Load any previously stored cookies into the store
        Map<String, ?> prefsMap = cookiePrefs.getAll();
        for (Map.Entry<String, ?> entry : prefsMap.entrySet())
        {
            if (((String) entry.getValue()) != null && !((String) entry.getValue()).startsWith(COOKIE_NAME_PREFIX))
            {
                String[] cookieNames = TextUtils.split((String) entry.getValue(), ",");
                for (String name : cookieNames)
                {
                    String encodedCookie = cookiePrefs.getString(COOKIE_NAME_PREFIX + name, null);
                    if (encodedCookie != null)
                    {
                        Cookie decodedCookie = decodeCookie(encodedCookie);
                        if (decodedCookie != null)
                        {
                            if (!cookies.containsKey(entry.getKey()))
                                cookies.put(entry.getKey(), new ConcurrentHashMap<String, Cookie>());
                            cookies.get(entry.getKey()).put(name, decodedCookie);
                        }
                    }
                }

            }
        }
    }



    protected void add(HttpUrl uri, Cookie cookie){
        String name = getCookieToken(cookie);

//        if (!cookie.persistent())
//        {
            if (!cookies.containsKey(uri.host()))
            {
                cookies.put(uri.host(), new ConcurrentHashMap<String, Cookie>());
            }
            cookies.get(uri.host()).put(name, cookie);
//        } else
//        {
//            if (cookies.containsKey(uri.host()))
//            {
//                cookies.get(uri.host()).remove(name);
//            }else
//            {
//                return ;
//            }
//        }
    }

    protected String getCookieToken(Cookie cookie)
    {
        return cookie.name() + cookie.domain();
    }


    @Override
    public void add(HttpUrl uri, List<Cookie> cookies) {
        for (Cookie cookie : cookies)
        {
            add(uri, cookie);
        }
    }

    @Override
    public List<Cookie> get(HttpUrl uri) {
        ArrayList<Cookie> ret = new ArrayList<Cookie>();
        if (cookies.containsKey(uri.host()))
        {
            Collection<Cookie> cookies = this.cookies.get(uri.host()).values();
            for (Cookie cookie : cookies)
            {
                if (isCookieExpired(cookie))
                {
                    remove(uri, cookie);
                } else
                {
                    ret.add(cookie);
                }
            }
        }

        return ret;
    }
    private static boolean isCookieExpired(Cookie cookie)
    {
        return cookie.expiresAt() < System.currentTimeMillis();
    }
    @Override
    public List<Cookie> getCookies() {
        return null;
    }

    @Override
    public boolean remove(HttpUrl uri, Cookie cookie) {
        String name = getCookieToken(cookie);

        if (cookies.containsKey(uri.host()) && cookies.get(uri.host()).containsKey(name))
        {
            cookies.get(uri.host()).remove(name);

            SharedPreferences.Editor prefsWriter = cookiePrefs.edit();
            if (cookiePrefs.contains(COOKIE_NAME_PREFIX + name))
            {
                prefsWriter.remove(COOKIE_NAME_PREFIX + name);
            }
            prefsWriter.putString(uri.host(), TextUtils.join(",", cookies.get(uri.host()).keySet()));
            prefsWriter.apply();

            return true;
        } else
        {
            return false;
        }
    }

    @Override
    public boolean removeAll() {
        SharedPreferences.Editor prefsWriter = cookiePrefs.edit();
        prefsWriter.clear();
        prefsWriter.apply();
        cookies.clear();
        return true;
    }

    protected Cookie decodeCookie(String cookieString)
    {
        byte[] bytes = hexStringToByteArray(cookieString);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        Cookie cookie = null;
        try
        {
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            cookie = ((SerializableHttpCookie) objectInputStream.readObject()).getCookie();
        } catch (IOException e)
        {
            Log.d(LOG_TAG, "IOException in decodeCookie", e);
        } catch (ClassNotFoundException e)
        {
            Log.d(LOG_TAG, "ClassNotFoundException in decodeCookie", e);
        }

        return cookie;
    }

    /**
     * Converts hex values from strings to byte arra
     *
     * @param hexString string of hex-encoded values
     * @return decoded byte array
     */
    protected byte[] hexStringToByteArray(String hexString)
    {
        int len = hexString.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2)
        {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4) + Character.digit(hexString.charAt(i + 1), 16));
        }
        return data;
    }
}
