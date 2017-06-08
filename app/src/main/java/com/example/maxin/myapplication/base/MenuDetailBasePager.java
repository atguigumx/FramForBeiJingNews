package com.example.maxin.myapplication.base;

import android.content.Context;
import android.view.View;

/**
 * Created by shkstart on 2017/6/8.
 */

public abstract class MenuDetailBasePager {
    public final Context context;
    public View rootView;
    public MenuDetailBasePager(Context context) {
        this.context=context;
        rootView=initView();
    }
    //由子类实现
    public abstract View initView();
    public void initData(){

    };
}
