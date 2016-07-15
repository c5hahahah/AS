package com.xinlifm.my.costemView;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by my on 2016/7/14.
 */
public class ViewPagerInFragment extends ViewPager {
    public ViewPagerInFragment(Context context) {
        super(context);
    }

    public ViewPagerInFragment(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return true;
    }
}
