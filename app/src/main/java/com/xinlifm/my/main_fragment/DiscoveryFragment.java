package com.xinlifm.my.main_fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xinlifm.my.adapter.HomepageViewPagerAdapter;
import com.xinlifm.my.costemView.ViewPagerInFragment;
import com.xinlifm.my.listener.ViewPagerInFragmentListener;
import com.xinlifm.my.xinlifm.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by my on 2016/7/14.
 */
public class DiscoveryFragment extends Fragment {
    View view;
    //UI控件
    ViewPagerInFragment viewpager;

    //viewPager相关
    List<ImageView> imageViewList ;
    HomepageViewPagerAdapter viewPagerAdapter ;
    Handler handler;
    int viewpagerIndex = 0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.discovery_fragment,null);
        initView();
        initViewPagerPic();
        handler = new Handler();
        setViewpager();
        viewpagerAtuoScroll();
        setListen();
        return view;
    }
    //初始化UI
    public void initView(){
        viewpager = (ViewPagerInFragment) view.findViewById(R.id.discovery_fragment_viewpager);

    }
    //初始化viewpager中内容
    public void initViewPagerPic(){
        imageViewList = new ArrayList<>();
        ImageView imageView1 = new ImageView(getContext());
        imageView1.setImageResource(R.drawable.discovery_viewpager03);
        imageView1.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageView imageView2 = new ImageView(getContext());
        imageView2.setImageResource(R.drawable.discovery_viewpage01);
        imageView2.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageView imageView3 = new ImageView(getContext());
        imageView3.setImageResource(R.drawable.discovery_viewpager02);
        imageView3.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageView imageView4 = new ImageView(getContext());
        imageView4.setImageResource(R.drawable.discovery_viewpager03);
        imageView4.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageView imageView5 = new ImageView(getContext());
        imageView5.setImageResource(R.drawable.discovery_viewpage01);
        imageView5.setScaleType(ImageView.ScaleType.FIT_XY);
        imageViewList.add(imageView1);
        imageViewList.add(imageView2);
        imageViewList.add(imageView3);
        imageViewList.add(imageView4);
        imageViewList.add(imageView5);
        viewPagerAdapter = new HomepageViewPagerAdapter(imageViewList);
    }
    //设置viewpager
    public void setViewpager(){
        viewpager.setOffscreenPageLimit(5);
        viewpager.setAdapter(viewPagerAdapter);
        viewpager.setCurrentItem(0);

    }
    //设置Viewpager自动滑动
    public void viewpagerAtuoScroll(){
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(viewpagerIndex>3){
                            viewpagerIndex=1;
                            viewpager.setCurrentItem(1,false);
                        }else{
                            viewpagerIndex++;
                            viewpager.setCurrentItem(viewpagerIndex);
                        }
                    }
                });
            }
        },0,5000, TimeUnit.MILLISECONDS);
    }
    //设置点击监听
    public void setListen() {
        viewpager.addOnPageChangeListener(new ViewPagerInFragmentListener(viewpager));

    }
}
