package com.xinlifm.my.xinlifm;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.xinlifm.my.adapter.MainViewPagerAdapter;
import com.xinlifm.my.main_fragment.CommunityFragment;
import com.xinlifm.my.main_fragment.DiscoveryFragment;
import com.xinlifm.my.main_fragment.HomepageFragment;
import com.xinlifm.my.main_fragment.MineFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener,ViewPager.OnPageChangeListener{
    List<Fragment> fragmentList;
    Fragment f_homepage,f_discovery,f_community,f_mine;
    ViewPager viewPager;
    LinearLayout ll_homepage,ll_discovery,ll_community,ll_mine;
    ImageView iv_homepage,iv_discovery,iv_community,iv_mine;
    MainViewPagerAdapter viewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();
        initView();
        initListener();
        setClickChange(0);
    }
    public void initFragment(){
        fragmentList = new ArrayList<>();
        f_homepage = new HomepageFragment();
        f_community = new CommunityFragment();
        f_discovery = new DiscoveryFragment();
        f_mine = new MineFragment();
        fragmentList.add(f_homepage);
        fragmentList.add(f_discovery);
        fragmentList.add(f_community);
        fragmentList.add(f_mine);
    }
    public void initView(){
        viewPager = (ViewPager) findViewById(R.id.main_viewpager);
        ll_community = (LinearLayout) findViewById(R.id.ll_community);
        ll_homepage = (LinearLayout) findViewById(R.id.ll_homepage);
        ll_discovery = (LinearLayout) findViewById(R.id.ll_discovery);
        ll_mine = (LinearLayout) findViewById(R.id.ll_mine);
        iv_community = (ImageView) findViewById(R.id.iv_community);
        iv_homepage = (ImageView) findViewById(R.id.iv_homepage);
        iv_discovery  = (ImageView) findViewById(R.id.iv_discovery );
        iv_mine = (ImageView) findViewById(R.id.iv_mine);
        viewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(viewPagerAdapter);
    }
    public void initListener(){
        ll_community.setOnClickListener(this);
        ll_homepage.setOnClickListener(this);
        ll_discovery.setOnClickListener(this);
        ll_mine.setOnClickListener(this);
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_community:
                viewPager.setCurrentItem(2);
                break;
            case R.id.ll_discovery:
                viewPager.setCurrentItem(1);
                break;
            case R.id.ll_homepage:
                viewPager.setCurrentItem(0);
                break;
            case R.id.ll_mine:
                viewPager.setCurrentItem(3);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setClickChange(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void resetChange(){
        ll_homepage.setBackgroundColor(Color.parseColor("#373B3D"));
        iv_homepage.setImageResource(R.drawable.main_homepage_false);
        ll_community.setBackgroundColor(Color.parseColor("#373B3D"));
        iv_community.setImageResource(R.drawable.main_community_false);
        ll_mine.setBackgroundColor(Color.parseColor("#373B3D"));
        iv_mine.setImageResource(R.drawable.main_mine_false);
        ll_discovery.setBackgroundColor(Color.parseColor("#373B3D"));
        iv_discovery.setImageResource(R.drawable.main_discovery_false);
    }
    public void setClickChange(int position){
        resetChange();
        switch (position){
            case 0:
                ll_homepage.setBackgroundColor(Color.parseColor("#282C2E"));
                iv_homepage.setImageResource(R.drawable.main_homepage_true);
                break;
            case 1:
                ll_discovery.setBackgroundColor(Color.parseColor("#282C2E"));
                iv_discovery.setImageResource(R.drawable.main_discovery_true);
                break;
            case 2:
                ll_community.setBackgroundColor(Color.parseColor("#282C2E"));
                iv_community.setImageResource(R.drawable.main_community_true);
                break;
            case 3:
                ll_mine.setBackgroundColor(Color.parseColor("#282C2E"));
                iv_mine.setImageResource(R.drawable.main_mine_true);
                break;
        }
    }
}
