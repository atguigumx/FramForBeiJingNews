package com.example.maxin.myapplication.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.maxin.myapplication.R;
import com.example.maxin.myapplication.fragment.ContentFragment;
import com.example.maxin.myapplication.fragment.LeftMenuFragment;
import com.example.maxin.myapplication.utils.DensityUtil;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setBehindContentView(R.layout.leftmenu);
        //3.设置右侧菜单
        SlidingMenu slidingMenu = getSlidingMenu();
        //4.设置支持滑动的模式：全屏滑动，边缘滑动，不可以滑动
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //5.设置页面模式：左侧菜单+主页面；左侧菜单+主页面+右侧菜单； 主页面+右侧菜单
        slidingMenu.setMode(SlidingMenu.LEFT);
        //6.设置主页面占的宽度
        slidingMenu.setBehindOffset(DensityUtil.dip2px(this,200));
        initFragment();
    }

    private void initFragment() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = supportFragmentManager.beginTransaction();
        ft.replace(R.id.activity_main,new ContentFragment(),"main");
        ft.replace(R.id.fl_leftMenu,new LeftMenuFragment(),"left");
        ft.commit();//提交事务,此方法不可少
    }

    public LeftMenuFragment getLeftFragment() {
        return (LeftMenuFragment) getSupportFragmentManager().findFragmentByTag("left");
    }

    public ContentFragment getContentFragment() {
        return (ContentFragment) getSupportFragmentManager().findFragmentByTag("main");
    }
}
