package com.wutouqishi.v2ex_android.ui.pager.impl;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.wutouqishi.v2ex_android.ui.activity.LoginActivity;
import com.wutouqishi.v2ex_android.R;
import com.wutouqishi.v2ex_android.ui.pager.BasePager;
import com.wutouqishi.v2ex_android.util.UIUtils;

/**
 * Created by gengjie on 16/9/7.
 */
public class MorePager extends BasePager
{

    public MorePager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        super.initData();

        rl_nav.setVisibility(View.GONE);

        View view = View.inflate(mActivity, R.layout.pager_more, null);
        fl_content.addView(view);


        Button btn_login = (Button) view.findViewById(R.id.btn_login);
        Button btn_register = (Button) view.findViewById(R.id.btn_register);
        btn_login.setOnClickListener(new MoreButtonListener());
        btn_register.setOnClickListener(new MoreButtonListener());

        // 设置Button图片大小
        LinearLayout ll_personal = (LinearLayout) view.findViewById(R.id.ll_personal);
        initSettingButton(ll_personal);

        LinearLayout ll_setting = (LinearLayout) view.findViewById(R.id.ll_setting);
        initSettingButton(ll_setting);

    }


    public class MoreButtonListener implements View.OnClickListener
    {

        @Override
        public void onClick(View view)
        {
            switch (view.getId())
            {
                case R.id.btn_register:
                {
                    Intent intent = new Intent(UIUtils.getContext(), LoginActivity.class);
                    UIUtils.getContext().startActivity(intent);
                    break;
                }
                case R.id.btn_login:
                {
                    Intent intent = new Intent(UIUtils.getContext(), LoginActivity.class);
                    // Context中有一个startActivity方法，Activity继承自Context，重载了startActivity方法。
                    // 如果使用Activity的startActivity方法，不会有任何限制，而如果使用Context的startActivity方法的话
                    // 就需要开启一个新的task。
                    // intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    // UIUtils.getContext().startActivity(intent);

                    mActivity.startActivity(intent);
                    break;
                }

                default:

                    Toast.makeText(UIUtils.getContext(), "其他按钮", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    // 统一设置图片大小
    private void initSettingButton(LinearLayout ll_root)
    {
        ll_root.measure(0, 0);
        int width = ll_root.getMeasuredWidth();
        int btnWidth = width / 4;
        int drawableW = UIUtils.dip2px(20);
        int drawableLeft = (int) ((btnWidth - drawableW) * 0.5);
        int drawableTop = UIUtils.dip2px(5);
        int drawableH = drawableW + drawableTop;

        int count = ll_root.getChildCount();
        for (int i = 0; i < count; i++)
        {
            View subView = ll_root.getChildAt(i);
            if (subView instanceof LinearLayout)
            {
                LinearLayout ll_sub = (LinearLayout) subView;

                int ll_subCount = ll_sub.getChildCount();
                for (int j = 0; j < ll_subCount; j++)
                {
                    View childView = ll_sub.getChildAt(j);
                    if (childView instanceof Button)
                    {
                        Button btn = (Button) childView;
                        btn.setOnClickListener(new MoreButtonListener());
//                        LinearLayout.LayoutParams btnParams = (LinearLayout.LayoutParams) btn.getLayoutParams();
//                        btnParams.height = btnWidth;
//                        btn.setLayoutParams(btnParams);

                        System.out.println("width:" + width);

                        Drawable[] drawables = btn.getCompoundDrawables();
                        drawables[1].setBounds(drawableLeft, drawableTop, drawableW, drawableH);
                        btn.setCompoundDrawables(null, drawables[1], null, null);
                    }
                }
            }
        }
    }
}
