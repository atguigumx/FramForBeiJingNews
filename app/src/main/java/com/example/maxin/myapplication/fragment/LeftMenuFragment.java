package com.example.maxin.myapplication.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.maxin.myapplication.R;
import com.example.maxin.myapplication.activity.MainActivity;
import com.example.maxin.myapplication.base.BaseFragment;
import com.example.maxin.myapplication.bean.AllPagerBean;
import com.example.maxin.myapplication.pager.AllPager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.List;

/**
 * Created by shkstart on 2017/6/8.
 */

public class LeftMenuFragment extends BaseFragment {

    private ListView listView;
    private int prePosition;
    private List<AllPagerBean.DataBean> datas;
    private MyAdapter adapter;

    @Override
    public View initView() {
        listView=new ListView(context);
        listView.setPadding(0,70,0,0);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                prePosition=i;

                adapter.notifyDataSetChanged();

                MainActivity mainActivity= (MainActivity) context;
                SlidingMenu slidingMenu = mainActivity.getSlidingMenu();
                slidingMenu.toggle();

                switchPagers(prePosition);
            }
        });
        return listView;
    }

    public void switchPagers(int prePosition) {
        MainActivity main= (MainActivity) context;
        ContentFragment contentFragment = main.getContentFragment();
        AllPager allPager = contentFragment.getAllPager();
        allPager.switchPager(prePosition);
    }

    @Override
    public void initData() {
        super.initData();
    }

    public void setDatas(List<AllPagerBean.DataBean> datas) {
        this.datas=datas;
        adapter=new MyAdapter();
        listView.setAdapter(adapter);
    }
    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int i) {
            return datas.get(i);
        }

        @Override
        public long getItemId(int i) {
            return datas.get(i).getId();
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            TextView textView= (TextView) View.inflate(context, R.layout.item_leftmenu,null);
            if(prePosition==i) {
                textView.setEnabled(true);
            }else {
                textView.setEnabled(false);
            }

            textView.setText(datas.get(i).getTitle());;
            return textView;
        }
    }
}
