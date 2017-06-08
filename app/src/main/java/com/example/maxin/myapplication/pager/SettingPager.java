package com.example.maxin.myapplication.pager;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import com.example.maxin.myapplication.base.BasePager;

/**
 * Created by shkstart on 2017/6/8.
 */

public class SettingPager extends BasePager {

    public SettingPager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        super.initData();
        tv_titile.setText("设置");
        TextView textView=new TextView(context);
        textView.setText("设置页面");
        textView.setTextColor(Color.BLACK);
        fl_content.addView(textView);
    }
}
