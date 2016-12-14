package com.wutouqishi.v2ex_android.ui.holder;

import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.wutouqishi.v2ex_android.R;
import com.wutouqishi.v2ex_android.domain.TopicDetail;
import com.wutouqishi.v2ex_android.util.UIUtils;

/**
 * Created by wutouqishigj on 2016/12/9.
 */

public abstract class HomeDetailFooterHolder extends BaseHolder<TopicDetail> implements View.OnClickListener
{

    private ImageButton ib_collection;
    private ImageButton ib_love;
    private ImageButton ib_prev;
    private ImageButton ib_next;
    private ImageButton ib_refresh;
    private ImageButton ib_reply;

    private TextView tv_page;

    // 最大页
    private int maxPage;

    @Override
    public View initView() {

        View view = UIUtils.inflate(R.layout.layout_home_detail_footer);

        ib_collection = (ImageButton) view.findViewById(R.id.ib_collection);
        ib_love = (ImageButton) view.findViewById(R.id.ib_love);
        ib_prev = (ImageButton) view.findViewById(R.id.ib_prev);
        ib_next = (ImageButton) view.findViewById(R.id.ib_next);
        ib_refresh = (ImageButton) view.findViewById(R.id.ib_refresh);
        ib_reply = (ImageButton) view.findViewById(R.id.ib_reply);
        tv_page = (TextView) view.findViewById(R.id.tv_page);

        ib_collection.setOnClickListener(this);
        ib_love.setOnClickListener(this);
        ib_prev.setOnClickListener(this);
        ib_next.setOnClickListener(this);
        ib_refresh.setOnClickListener(this);
        ib_reply.setOnClickListener(this);

        return view;
    }

    @Override
    public void refreshView(TopicDetail data)
    {

        String url = data.getDetailUrl();

        setCurrentPage(url);
    }

    public void setCurrentPage(String url)
    {
        String countStr = url.substring(url.indexOf("#reply") + 6, url.length());
        System.out.println("url:" + url + " ---countStr:" + countStr);
        int count = Integer.parseInt(url.substring(url.indexOf("#reply") + 6, url.length()));

        int currentPage = count / 100 + 1;
        if (maxPage == 0)
        {
            maxPage = currentPage;
            ib_next.setEnabled(false);
        }


        //System.out.println("maxPage:" + maxPage);
        //Logger.i("maxpage1:", maxPage);
        tv_page.setText(currentPage + "");
    }

    @Override
    public void onClick(View view)
    {
        ImageButton ib = (ImageButton) view;
        switch (ib.getId())
        {
            case R.id.ib_collection:

                break;

            case R.id.ib_love:

                break;

            case R.id.ib_prev:
            {

                int currentPage = Integer.parseInt(tv_page.getText().toString()) - 1;
                if (currentPage > 0)
                {
                    prevPage(currentPage);
                    tv_page.setText(currentPage + "");
                    ib_next.setEnabled(true);

                }
                else
                {
                    ib_prev.setEnabled(false);
                }


                break;
            }
            case R.id.ib_next:
            {

                int currentPage = Integer.parseInt(tv_page.getText().toString()) + 1;
                if (currentPage <= maxPage)
                {
                    nextPage(currentPage);
                    tv_page.setText(currentPage + "");
                    ib_prev.setEnabled(true);
                }
                else
                {
                    ib_next.setEnabled(false);
                }


                break;
            }
            case R.id.ib_refresh:
                refresh();
                break;

            case R.id.ib_reply:

                break;
        }
    }

    public abstract void refresh();

    public abstract void prevPage(int currentPage);

    public abstract void nextPage(int currentPage);

}
