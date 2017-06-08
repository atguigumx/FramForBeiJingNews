package com.example.maxin.myapplication.detailpager;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.example.maxin.myapplication.base.MenuDetailBasePager;

/**
 * Created by shkstart on 2017/6/8.
 */

public class TopicMenuDetailPager extends MenuDetailBasePager {
    private TextView textView;

    public TopicMenuDetailPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        textView = new TextView(context);
        textView.setTextColor(Color.RED);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("topic");
    }
}
