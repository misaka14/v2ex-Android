package com.wutouqishi.v2ex_android.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import org.xutils.x;

/**
 * Created by wutouqishigj on 2016/12/7.
 */

public class BaseAcitivy extends Activity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);


//        initView();
//        initData();
    }

//    public void initView(){}

//    public void initData(){}
}
