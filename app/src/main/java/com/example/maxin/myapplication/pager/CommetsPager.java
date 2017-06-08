package com.example.maxin.myapplication.pager;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import com.example.maxin.myapplication.base.BasePager;

/**
 * Created by shkstart on 2017/6/8.
 */

public class CommetsPager extends BasePager {

    public CommetsPager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        super.initData();
        tv_titile.setText("Comments");
        TextView textView=new TextView(context);
        textView.setText("Comments页面");
        textView.setTextColor(Color.BLACK);
        fl_content.addView(textView);
    }
}
