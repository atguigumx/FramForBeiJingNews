package com.example.maxin.myapplication.fragment;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.maxin.myapplication.R;
import com.example.maxin.myapplication.activity.MainActivity;
import com.example.maxin.myapplication.base.BaseFragment;
import com.example.maxin.myapplication.base.BasePager;
import com.example.maxin.myapplication.pager.AllPager;
import com.example.maxin.myapplication.pager.CommetsPager;
import com.example.maxin.myapplication.pager.SettingPager;
import com.example.maxin.myapplication.view.NoScrollViewPager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by shkstart on 2017/6/8.
 */

public class ContentFragment extends BaseFragment {

    @InjectView(R.id.content_fragment_viewpager)
    NoScrollViewPager viewPager;
    @InjectView(R.id.rb_one)
    RadioButton rbOne;
    @InjectView(R.id.rb_two)
    RadioButton rbTwo;
    @InjectView(R.id.rb_three)
    RadioButton rbThree;
    @InjectView(R.id.rg_main)
    RadioGroup rgMain;

    private List<BasePager> pagers;
    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.content_fragment, null);
        ButterKnife.inject(this, view);
        rgMain.check(R.id.rb_two);//默认选中页面
        return view;
    }

    @Override
    public void initData() {
        super.initData();

        pagers=new ArrayList<>();
        pagers.add(new CommetsPager(context));
        pagers.add(new AllPager(context));
        pagers.add(new SettingPager(context));
        viewPager.setAdapter(new MyPagerAdapter());
        viewPager.setCurrentItem(1);
        pagers.get(1).initData();//不执行initData方法不会有子类viewPager数据,先加载选中页面数据
        setToutchMode(SlidingMenu.LEFT); //默认可以侧滑
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_one  :
                        viewPager.setCurrentItem(0);
                        pagers.get(0).initData();
                        setToutchMode(SlidingMenu.TOUCHMODE_NONE);
                        break;
                    case R.id.rb_two  :
                        viewPager.setCurrentItem(1);
                        pagers.get(1).initData();
                        setToutchMode(SlidingMenu.LEFT); //只有中心页面可以侧滑
                        break;
                    case R.id.rb_three  :
                        viewPager.setCurrentItem(2);
                        pagers.get(2).initData();
                        setToutchMode(SlidingMenu.TOUCHMODE_NONE);
                        break;
                }
            }
        });
    }

    public AllPager getAllPager() {
        return (AllPager) pagers.get(1);
    }

    class MyPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return pagers.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BasePager basePager = pagers.get(position);
            View rootView = basePager.rootView;
            container.addView(rootView);
            return rootView;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
    public void setToutchMode(int mode){
        MainActivity main= (MainActivity) context;
        SlidingMenu slidingMenu = main.getSlidingMenu();
        slidingMenu.setTouchModeAbove(mode);
    }
}
