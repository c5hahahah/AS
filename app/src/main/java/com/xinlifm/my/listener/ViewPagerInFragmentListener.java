package com.xinlifm.my.listener;

import android.support.v4.view.ViewPager;

/**
 * Created by my on 2016/7/15.
 */
public class ViewPagerInFragmentListener implements ViewPager.OnPageChangeListener{
    private ViewPager viewPager;
    private int currentPosition;

    public ViewPagerInFragmentListener(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == ViewPager.SCROLL_STATE_IDLE) {
            if (currentPosition == viewPager.getAdapter().getCount() - 1) {
                viewPager.setCurrentItem(1, false);
            }
            else if (currentPosition == 0) {
                viewPager.setCurrentItem(viewPager.getAdapter().getCount() - 2, false);
            }
        }
    }

    @Override
    public void onPageScrolled(int scrolledPosition, float percent, int pixels) {
        //empty
    }

    @Override
    public void onPageSelected(int position) {
        currentPosition = position;
    }


}
