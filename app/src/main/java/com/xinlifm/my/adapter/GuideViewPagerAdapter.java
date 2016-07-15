package com.xinlifm.my.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by my on 2016/7/14.
 */
public class GuideViewPagerAdapter extends PagerAdapter{
    List<View> list;

    public GuideViewPagerAdapter(List<View> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(list.get(position));
        return list.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
        container.removeView(list.get(position));
    }

    @Override
    public
    boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}