package com.example.maxin.myapplication.base;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.maxin.myapplication.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by shkstart on 2017/6/8.
 */

public class BasePager {
    public View rootView;
    public Context context;
    @InjectView(R.id.tv_titile)
    public TextView tv_titile;
    @InjectView(R.id.fl_content)
    public FrameLayout fl_content;
    @InjectView(R.id.ib_menu)
    public ImageButton ibMenu;

    public BasePager(Context context) {
        this.context = context;
        rootView = View.inflate(context, R.layout.base_pager, null);
        ButterKnife.inject(this, rootView);
    }

    public void initData() {

    }
}
