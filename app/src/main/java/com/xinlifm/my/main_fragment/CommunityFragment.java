package com.xinlifm.my.main_fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.xinlifm.my.adapter.CommunityPinglunAdapter;
import com.xinlifm.my.callback.NoNetBtnCallBackInCommunity;
import com.xinlifm.my.callback.PullToRefrushInPinglun;
import com.xinlifm.my.community_fragment.ListviewInCommunityofPinglun;
import com.xinlifm.my.community_fragment.NoNetFragmentInCommunity;
import com.xinlifm.my.xinlifm.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by my on 2016/7/14.
 */
public class CommunityFragment extends Fragment implements NoNetBtnCallBackInCommunity,PullToRefrushInPinglun{
    View view;
    Handler handler;
    //Ui控件
    TextView tv_zuixin,tv_jinghua;

    //碎片
    NoNetFragmentInCommunity fragment_noNet;
    ListviewInCommunityofPinglun fragment_jinghua,fragment_zuixin;


    //碎片管理
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;




    //标识此时在那一页面上
    int typeid = 1;

    public CommunityFragment(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.community_fragment,null);
        handler = new Handler();
        initView();
        initFragment();
        initListen();



        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.community_fragment_framlayout,fragment_jinghua);
        fragmentTransaction.commit();
        fragment_jinghua.callback = this;


        return view;
    }
    //初始化控件
    public void initView(){
        tv_jinghua = (TextView) view.findViewById(R.id.community_fragment_tv_jinghua);
        tv_zuixin = (TextView) view.findViewById(R.id.community_fragment_tv_zuixin);

    }
    //初始化碎片
    public void initFragment(){
        //更改这里的typeid
        fragment_jinghua = new ListviewInCommunityofPinglun(1,handler);
        fragment_zuixin = new ListviewInCommunityofPinglun(2,handler);
        fragment_noNet = new NoNetFragmentInCommunity(this);
    }

    //设置监听
    public void initListen(){
        tv_jinghua.setOnClickListener(this);
        tv_zuixin.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.community_fragment_tv_jinghua:
                setTextViewColor(v.getId());
                typeid = 1;
                //加载精华的fragment
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.community_fragment_framlayout,fragment_jinghua);
                fragmentTransaction.commit();


                break;
            case R.id.community_fragment_tv_zuixin:
                setTextViewColor(v.getId());
                typeid = 2;
                //加载最新的fragment
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.community_fragment_framlayout,fragment_zuixin);
                fragmentTransaction.commit();
                fragment_zuixin.callback = this;



                break;
            //网络不好页面下的按钮
            case R.id.community_nonet_fragment_btn:
                clickToRefresh();

                break;
        }
    }

    //设置textview颜色
    public void setTextViewColor(int id){
        resetTextViewColor();
        switch (id){
            case R.id.community_fragment_tv_jinghua:
                tv_jinghua.setTextColor(Color.parseColor("#ffffff"));
                tv_jinghua.setBackgroundResource(R.drawable.community_textview_left_choose);
                break;
            case R.id.community_fragment_tv_zuixin:
                tv_zuixin.setTextColor(Color.parseColor("#ffffff"));
                tv_zuixin.setBackgroundResource(R.drawable.community_textview_right_choose);
        }
    }
    //初始化textView颜色
    public void resetTextViewColor(){
        tv_jinghua.setBackgroundResource(R.drawable.community_textview_left);
        tv_zuixin.setBackgroundResource(R.drawable.community_textview_right);
        tv_jinghua.setTextColor(Color.parseColor("#F39E3A"));
        tv_zuixin.setTextColor(Color.parseColor("#F39E3A"));
    }
    //网络状态不好情况下的点击按钮回调
    @Override
    public void clickToRefresh() {
        if(typeid==1){
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.community_fragment_framlayout,fragment_jinghua);
            fragmentTransaction.commit();



            onPullDownToRefresh(fragment_jinghua.pullToRefreshListView);
        }
        if(typeid==2){
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.community_fragment_framlayout,fragment_zuixin);
            fragmentTransaction.commit();


           onPullDownToRefresh(fragment_zuixin.pullToRefreshListView);
        }
    }
    //延迟几秒无数据进入无网络页面
    @Override
    public void IFnoNetToNoNet() {
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.community_fragment_framlayout, fragment_noNet);
                        fragmentTransaction.commit();

        }

    //下拉刷新
    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        //判断数据是否为空，为空刷新界面

        if(typeid==1){
           fragment_jinghua.initdata();
          //  Log.i("12345","下拉执行了");
       }
       if(typeid==2){
          fragment_zuixin.initdata();
       }



    }
    //上拉刷新
    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {

    }

}
