package com.wutouqishi.v2ex_android.Util;

import android.content.Context;
import android.os.AsyncTask;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.wutouqishi.v2ex_android.global.GlobalConstants;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import javax.security.cert.X509Certificate;

/**
 * Created by gengjie on 16/10/7.
 */

public class LoginUtil
{
    private String mUsernameKey;
    private String mPasswordKey;
    private String mNextKey = "next";
    private String mOnceKey = "once";

    private String mUsernameValue;
    private String mPasswordValue;
    private String mNextValue;
    private String mOnceValue;
    private Context mContext;

    public void getLoginRequestParam(Context context, String username, String password, CompleteListener comleteListener)
    {
        mContext = context;
        mCompleteListener = comleteListener;
        mUsernameValue = username;
        mPasswordValue = password;
        System.out.println("getLoginRequestParam");
        new GetLoginRequestParamAsyncTask().execute();
    }

    class GetLoginRequestParamAsyncTask extends AsyncTask<Void, Void, Void>{

//        System.out.println("GetLoginRequestParamAsyncTask");

        @Override
        protected Void doInBackground(Void... voids) {

            System.out.println("GetLoginRequestParamAsyncTask");
            Document doc = null;
            try {
                doc = Jsoup.connect(GlobalConstants.LOGINURL).get();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Elements inputEs = doc.select("input.sl");
            Element nextInput = doc.getElementsByAttributeValue("name", mNextKey).get(0);
            Element onceInput = doc.getElementsByAttributeValue("name", mOnceKey).get(0);

            mUsernameKey = inputEs.first().attr("name");
            mPasswordKey = inputEs.last().attr("name");
            mOnceValue = onceInput.attr("value");
            mNextValue = nextInput.attr("value");
            System.out.println("mUsernameKey:" + mUsernameKey + "mPasswordValue:" + mPasswordValue +"nextInput:" + nextInput.attr("value") + ", onceInput:" + onceInput.attr("value"));

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            InputStream inputStream = null;
            try {
                inputStream = mContext.getAssets().open("server.cer");
            } catch (IOException e) {
                e.printStackTrace();
            }
            SSLSocketFactory sslSocketFactory =  HTTPSManager.buildSSLSocketFactory(mContext, inputStream);
            HttpsStack httpsStack = new HttpsStack(null,sslSocketFactory);
            RequestQueue requestQueue = Volley.newRequestQueue(mContext, httpsStack);
            StringRequest request = new StringRequest(Request.Method.POST, GlobalConstants.LOGINURL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    loginCompleteOperation(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> map = new HashMap<String,String>();
                    map.put(mUsernameKey, mUsernameValue);
                    map.put(mPasswordKey, mPasswordValue);
                    map.put(mOnceKey, mOnceValue);
                    map.put(mNextKey, mNextValue);
                    return map;
                }
            };
            requestQueue.add(request);
        }
    }

    private void loginCompleteOperation(String data)
    {
        System.out.println("data:" + data);
    }

    // 请求完成的回调
    private CompleteListener mCompleteListener;

    public void setCompleteListener(CompleteListener completeListener) {
        mCompleteListener = completeListener;
    }

    public interface CompleteListener
    {
        public void success();

        public void failed();
    }


}



