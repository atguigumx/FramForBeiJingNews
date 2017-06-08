package com.example.maxin.myapplication.application;

import android.app.Application;

import org.xutils.x;

/**
 * Created by shkstart on 2017/6/8.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.setDebug(true);
        x.Ext.init(this);
    }
}
