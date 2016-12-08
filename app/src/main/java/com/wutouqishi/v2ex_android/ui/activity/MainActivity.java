package com.wutouqishi.v2ex_android.ui.activity;


import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RadioGroup;

import com.wutouqishi.v2ex_android.R;
import com.wutouqishi.v2ex_android.ui.pager.BasePager;
import com.wutouqishi.v2ex_android.ui.pager.impl.HomePager;
import com.wutouqishi.v2ex_android.ui.pager.impl.MessagePager;
import com.wutouqishi.v2ex_android.ui.pager.impl.MorePager;
import com.wutouqishi.v2ex_android.ui.pager.impl.NodePager;
import com.wutouqishi.v2ex_android.ui.pager.impl.NotificationPager;
import com.wutouqishi.v2ex_android.ui.view.NoScrollViewPager;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

public class MainActivity extends Activity {

    @ViewInject(R.id.nsvp_content)
    private NoScrollViewPager nsvp_content;

    @ViewInject(R.id.rg_tabbar)
    private RadioGroup rg_tabbar;

    private ArrayList<BasePager> pagers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        x.view().inject(this);
        
        initData();
    }

    private void initData()
    {
        pagers = new ArrayList<BasePager>();
        pagers.add(new HomePager(this));
        pagers.add(new NodePager(this));
        pagers.add(new MessagePager(this));
        pagers.add(new NotificationPager(this));
        pagers.add(new MorePager(this));
        nsvp_content.setAdapter(new MyAdapter());

        nsvp_content.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                BasePager pager = pagers.get(position);
                if(pager.hasData == false)
                {
                    pager.initData();
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        rg_tabbar.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i)
                {
                    case R.id.rb_home:
                        nsvp_content.setCurrentItem(0, false);
                        break;
                    case R.id.rb_node:
                        nsvp_content.setCurrentItem(1, false);
                        break;

                    case R.id.rb_message:
                        nsvp_content.setCurrentItem(2, false);
                        break;
                    case R.id.rb_notification:
                        nsvp_content.setCurrentItem(3, false);
                        break;

                    case R.id.rb_more:
                        nsvp_content.setCurrentItem(4, false);
                        break;
                }
            }
        });

        pagers.get(0).initData();
    }

    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return pagers.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            BasePager pager = pagers.get(position);

            View view = pager.mRootView;

            container.addView(view);


            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
