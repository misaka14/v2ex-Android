package com.wutouqishi.v2ex_android.ui.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wutouqishi.v2ex_android.R;
import com.wutouqishi.v2ex_android.domain.Topic;
import com.wutouqishi.v2ex_android.domain.TopicDetail;
import com.wutouqishi.v2ex_android.util.HomeUtil;
import com.wutouqishi.v2ex_android.util.UIUtils;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by wutouqishigj on 2016/12/7.
 */

public class HomeDetailHeaderHolder extends BaseHolder<Topic>
{
    @ViewInject(R.id.iv_avatar)
    private ImageView iv_avatar;

    @ViewInject(R.id.tv_author)
    private TextView tv_author;

    @ViewInject(R.id.tv_title)
    private TextView tv_title;

    @ViewInject(R.id.tv_node)
    private TextView tv_node;

    @Override
    public View initView()
    {
        View view = UIUtils.inflate(R.layout.layout_home_detail_header);

        iv_avatar = (ImageView) view.findViewById(R.id.iv_avatar);
        tv_author = (TextView) view.findViewById(R.id.tv_author);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        tv_node = (TextView) view.findViewById(R.id.tv_node);
//        x.view().inject(view);

        return view;
    }

    @Override
    public void refreshView(Topic data)
    {
        tv_author.setText(data.getAuthor());
        tv_title.setText(data.getTitle());
        tv_node.setText(data.getNode());

        ImageOptions options = new ImageOptions.Builder().setRadius(DensityUtil.dip2px(3)).setLoadingDrawableId(R.mipmap.topic_avatar_normal).build();
        x.image().bind(iv_avatar, data.getAvatarURL(), options);

    }
}
