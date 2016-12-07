package com.wutouqishi.v2ex_android.util;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by gengjie on 16/9/14.
 */
public class AssetsUtil
{
    public static String getAssetsFile(String fileName, Context context)
    {
        try {
            InputStreamReader isr = new InputStreamReader(context.getResources().getAssets().open("light.css"));

            BufferedReader br = new BufferedReader(isr);

            String result = "";
            String line = "";

            while ((line = br.readLine()) != null){
                result += line;
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
