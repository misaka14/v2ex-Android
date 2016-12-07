package com.wutouqishi.v2ex_android.ui.holder;

import android.view.View;

import com.wutouqishi.v2ex_android.R;
import com.wutouqishi.v2ex_android.util.UIUtils;

/**
 * Created by wutouqishigj on 2016/12/7.
 */

public class HomeDetailCommentHolder extends BaseHolder {
    @Override
    public View initView() {

        View view = UIUtils.inflate(R.layout.layout_home_detail_comment);

        return view;
    }

    @Override
    public void refreshView(Object data) {

    }
}
