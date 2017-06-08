package com.example.maxin.myapplication.pager;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.example.maxin.myapplication.activity.MainActivity;
import com.example.maxin.myapplication.base.BasePager;
import com.example.maxin.myapplication.base.MenuDetailBasePager;
import com.example.maxin.myapplication.bean.AllPagerBean;
import com.example.maxin.myapplication.detailpager.InteractMenuDetailPager;
import com.example.maxin.myapplication.detailpager.NewsMenuDetailPager;
import com.example.maxin.myapplication.detailpager.PhotosMenuDetailPager;
import com.example.maxin.myapplication.detailpager.TopicMenuDetailPager;
import com.example.maxin.myapplication.detailpager.VoteMenuDetailPager;
import com.example.maxin.myapplication.fragment.LeftMenuFragment;
import com.example.maxin.myapplication.utils.CacheUtils;
import com.example.maxin.myapplication.utils.ConstantUtils;
import com.google.gson.Gson;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by shkstart on 2017/6/8.
 */

public class AllPager extends BasePager {

    private List<AllPagerBean.DataBean> datas;
    private List<MenuDetailBasePager> pagers;

    public AllPager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        super.initData();
        ibMenu.setVisibility(View.VISIBLE);
        ibMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity mainActivity= (MainActivity) context;
                SlidingMenu slidingMenu = mainActivity.getSlidingMenu();
                slidingMenu.toggle();
            }
        });
        //添加到布局上
        String json= CacheUtils.getString(context,ConstantUtils.NEWSCENTER_PAGER_URL);
        if(!TextUtils.isEmpty(json)) {
            process(json);
        }
        getDateFromNet();
    }

    private void getDateFromNet() {
        String Url= ConstantUtils.NEWSCENTER_PAGER_URL;
        RequestParams parms=new RequestParams(Url);
        x.http().get(parms, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String json) {
                CacheUtils.putString(context,ConstantUtils.NEWSCENTER_PAGER_URL,json);
                process(json);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void process(String json) {
        AllPagerBean allPagerBean = new Gson().fromJson(json, AllPagerBean.class);
        datas = allPagerBean.getData();

        pagers=new ArrayList<>();
        pagers.add(new NewsMenuDetailPager(context));
        pagers.add(new TopicMenuDetailPager(context));
        pagers.add(new PhotosMenuDetailPager(context));
        pagers.add(new InteractMenuDetailPager(context));
        pagers.add(new VoteMenuDetailPager(context));

        MainActivity main= (MainActivity) context;
        LeftMenuFragment leftFragment = main.getLeftFragment();
        leftFragment.setDatas(datas);
        switchPager(0);
    }

    public void switchPager(int position) {
        tv_titile.setText(datas.get(position).getTitle());
        MenuDetailBasePager pager = pagers.get(position);
        View rootView = pager.rootView;
        fl_content.removeAllViews();
        fl_content.addView(rootView);
        pager.initData();
    }
}
