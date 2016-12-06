package com.wutouqishi.v2ex_android;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.wutouqishi.v2ex_android.Util.LoginUtil;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by gengjie on 16/9/29.
 */
public class LoginActivity extends AppCompatActivity
{
    @ViewInject(R.id.et_username)
    private EditText et_username;

    @ViewInject(R.id.et_password)
    private EditText et_password;


    private LoginUtil loginUtil = new LoginUtil();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        x.view().inject(this);
    }

    @Event(R.id.btn_login)
    private void loginBtnClick(View v)
    {
        String username = et_username.getText().toString().trim();
        String password = et_password.getText().toString().trim();

        loginUtil.getLoginRequestParam(this, username, password, new LoginUtil.CompleteListener() {
            @Override
            public void success() {

            }

            @Override
            public void failed() {

            }
        });

    }

    @Event(R.id.ib_back)
    private void closeImageBtnClick(View v)
    {
        onBackPressed();
    }
}
