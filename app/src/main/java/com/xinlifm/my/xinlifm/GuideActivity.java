package com.xinlifm.my.xinlifm;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.xinlifm.my.adapter.GuideViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity implements View.OnClickListener,ViewPager.OnPageChangeListener{
    ViewPager viewpager;
    View view1,view2,view3;
    List<View> views;
    ImageView iv1,iv2,iv3;
    GuideViewPagerAdapter viewPagerAdapter;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_guide);
        initView();
        initlistener();
        viewpager.setOffscreenPageLimit(3);
        setdot(0);
    }

    //初始化View
    public void initView(){
        views = new ArrayList<>();
        viewpager = (ViewPager) this.findViewById(R.id.guide_viewpager);
        view1 = LayoutInflater.from(this).inflate(R.layout.guide01,null);
        view2 = LayoutInflater.from(this).inflate(R.layout.guide02,null);
        view3 = LayoutInflater.from(this).inflate(R.layout.guide03,null);
        btn = (Button) view3.findViewById(R.id.guide3_btn_tiaozhuan);
        views.add(view1);
        views.add(view2);
        views.add(view3);
        iv1 = (ImageView) findViewById(R.id.guide_dot1);
        iv2 = (ImageView) findViewById(R.id.guide_dot2);
        iv3 = (ImageView) findViewById(R.id.guide_dot3);
        viewPagerAdapter = new GuideViewPagerAdapter(views);
        viewpager.setAdapter(viewPagerAdapter);
    }
    //初始化监听
    public void initlistener(){
        iv1.setOnClickListener(this);
        iv2.setOnClickListener(this);
        iv3.setOnClickListener(this);
        viewpager.addOnPageChangeListener(this);
        btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.guide_dot1:
                viewpager.setCurrentItem(0);
                break;
            case R.id.guide_dot2:
                viewpager.setCurrentItem(1);
                break;
            case R.id.guide_dot3:
                viewpager.setCurrentItem(2);
                break;
            case R.id.guide3_btn_tiaozhuan:
                //记录登录信息
                //跳转主界面
                SharedPreferences preferences = getSharedPreferences("isFirstUse", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("isFirstUse",false);
                editor.commit();
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
        }
    }
    //Viewpager监听
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        setdot(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
    //初始化dot
    public void initdot(){
        iv1.setImageResource(R.drawable.dot_dark);
        iv2.setImageResource(R.drawable.dot_dark);
        iv3.setImageResource(R.drawable.dot_dark);
    }
    //设置对应的点击变色
    public void setdot(int position){
        initdot();
        switch (position){
            case 0:
                iv1.setImageResource(R.drawable.dot_white);
                break;
            case 1:
                iv2.setImageResource(R.drawable.dot_white);
                break;
            case 2:
                iv3.setImageResource(R.drawable.dot_white);
                break;
        }
    }
}
