package com.wutouqishi.v2ex_android.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.EditText;
import android.widget.ImageView;

import com.wutouqishi.v2ex_android.R;
import com.wutouqishi.v2ex_android.http.protocol.LoginProtocol;
import com.wutouqishi.v2ex_android.util.LoginUtil;
import com.wutouqishi.v2ex_android.util.UIUtils;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by gengjie on 16/9/29.
 */
public class LoginActivity extends Activity
{
    @ViewInject(R.id.iv_bg)
    private ImageView iv_bg;

    @ViewInject(R.id.et_username)
    private EditText et_username;

    @ViewInject(R.id.et_password)
    private EditText et_password;

    private LoginProtocol loginProtocol;

    private ScaleAnimation scaleAnimation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        x.view().inject(this);

        loginProtocol = new LoginProtocol();

        initAnim();
    }

    private void initAnim()
    {
        scaleAnimation = new ScaleAnimation(1.0f, 1.35f, 1.0f, 1.35f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(1000 * 30);
        scaleAnimation.setRepeatMode(Animation.REVERSE);
        scaleAnimation.setRepeatCount(1);
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startAnim();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    private void startAnim()
    {
        iv_bg.startAnimation(scaleAnimation);



    }

    @Event(R.id.btn_login)
    private void loginBtnClick(View v)
    {
        String username = et_username.getText().toString().trim();
        String password = et_password.getText().toString().trim();


        loginProtocol.login("misaka20", "misaka20", new LoginProtocol.LoginCallBack() {
            @Override
            public void success() {

            }

            @Override
            public void failure(String errorMsg) {

            }
        });
    }

    @Event(R.id.ib_back)
    private void closeImageBtnClick(View v)
    {
        onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        startAnim();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        iv_bg.clearAnimation();
    }
}
