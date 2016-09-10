package com.wutouqishi.v2ex_android.pager.impl;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.wutouqishi.v2ex_android.R;
import com.wutouqishi.v2ex_android.Util.HomeUtil;
import com.wutouqishi.v2ex_android.domain.Topic;
import com.wutouqishi.v2ex_android.pager.BaseTopicPager;

import org.w3c.dom.Text;
import org.xutils.common.Callback;
import org.xutils.common.util.DensityUtil;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by gengjie on 16/9/7.
 */
public class HomeTopicPager extends BaseTopicPager
{
    private ListView lv_topic;

    private HomeTopicAdapter homeTopicAdapter;

    private int currentPage;

    private ArrayList<Topic> topics;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            topics = (ArrayList<Topic>) msg.obj;
            homeTopicAdapter.notifyDataSetChanged();
        }
    };

    public HomeTopicPager(Activity activity) {
        super(activity);
    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.home_topic_pager, null);

        lv_topic = (ListView) view.findViewById(R.id.lv_topic);
        topics = new ArrayList<Topic>();
        homeTopicAdapter = new HomeTopicAdapter();
        return view;
    }

    @Override
    public void initData() {
        super.initData();

        loadDataFromServer();

        lv_topic.setAdapter(homeTopicAdapter);
    }

    private void loadDataFromServer()
    {
        HomeUtil.parseTopicWithHTML("", handler);
//        RequestParams params = new RequestParams("https://www.v2ex.com/?tab=creative");
//        x.http().get(params, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
////                System.out.println("reuslt:" + result);
//                topics = HomeUtil.parseTopicWithHTML(result);
////                System.out.println("topics:" + topics);
//                homeTopicAdapter.notifyDataSetChanged();
//            }
//
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//        });
    }

    class HomeTopicAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return topics.size();
        }

        @Override
        public Object getItem(int i) {
            return topics.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            ViewHolder viewHolder;
            if (view == null)
            {
                view = View.inflate(mActivity, R.layout.listview_home_topic_item, null);

                viewHolder = new ViewHolder();

                viewHolder.tv_author = (TextView) view.findViewById(R.id.tv_author);
                viewHolder.iv_avatar = (ImageView) view.findViewById(R.id.iv_avatar);
                viewHolder.btn_node = (Button) view.findViewById(R.id.btn_node);
                viewHolder.tv_replyTime = (TextView) view.findViewById(R.id.tv_replyTime);
                viewHolder.tv_commentCount = (TextView) view.findViewById(R.id.tv_commentCount);
                viewHolder.tv_title = (TextView) view.findViewById(R.id.tv_title);
                viewHolder.ll_comment = (LinearLayout) view.findViewById(R.id.ll_comment);
                view.setTag(viewHolder);
            }
            else
            {
                viewHolder = (ViewHolder) view.getTag();
            }

            Topic topic = topics.get(i);
//            System.out.println("topic:" + topic);
            ImageOptions options = new ImageOptions.Builder().setRadius(DensityUtil.dip2px(3)).build();
            x.image().bind(viewHolder.iv_avatar, topic.getAvatarURL(), options);
            viewHolder.tv_author.setText(topic.getAuthor());
            viewHolder.btn_node.setText(topic.getNode());
            viewHolder.tv_replyTime.setText(topic.getLastReplyTime());
            if (TextUtils.isEmpty(topic.getCommentCount()))
            {
                viewHolder.ll_comment.setVisibility(View.INVISIBLE);

            }
            else
            {
                viewHolder.ll_comment.setVisibility(View.VISIBLE);
                viewHolder.tv_commentCount.setText(topic.getCommentCount());
            }

            viewHolder.tv_title.setText(topic.getTitle());

            return view;
        }
    }

    static class ViewHolder
    {
        TextView tv_author;
        ImageView iv_avatar;
        Button btn_node;
        TextView tv_replyTime;
        TextView tv_commentCount;
        TextView tv_title;
        LinearLayout ll_comment;
    }
}
