package com.xinlifm.my.main_fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

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
public class HomepageFragment extends Fragment implements View.OnClickListener{
    View view;
   //UI控件
    ViewPagerInFragment viewpager;
    LinearLayout ll_ziwochengzhang,ll_qingxuguanli,ll_renjigoutong,ll_lianaihunxi,
                ll_zhichangguanli,ll_qinzijiating,ll_xinlikepu,ll_kechengjiangzuo,
                ll_rementuijian01,ll_rementuijian02,ll_rementuijian03,
                ll_xinlike01,ll_xinlike02,ll_xinlike03,
                ll_zuixinfm01,ll_zuixinfm02,ll_zuixinfm03,
                ll_diantaituijian01,ll_diantaituijian02,ll_diantaituijian03,ll_diantaituijian04;
    RelativeLayout rl_morexinlike,rl_morefm,rl_morediantai;
    //Viewpager相关
    List<ImageView> imageViewList ;
    HomepageViewPagerAdapter viewPagerAdapter ;
    Handler handler;
    int viewpagerIndex = 0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.homepage_fragment,null);
        handler = new Handler();
        initView();
        initViewPagerPic();
        setViewpager();
        viewpagerAtuoScroll();
        setListen();
        return view;
    }
    //初始化ui
    public void initView(){
        viewpager = (ViewPagerInFragment) view.findViewById(R.id.homepage_fragment_viewpager);
        ll_ziwochengzhang = (LinearLayout) view.findViewById(R.id.homepage_fragment_ll_ziwochengzhang);
        ll_qingxuguanli = (LinearLayout) view.findViewById(R.id.homepage_fragment_ll_qingxuguanli);
        ll_renjigoutong = (LinearLayout) view.findViewById(R.id.homepage_fragment_ll_renjigoutong);
        ll_lianaihunxi = (LinearLayout) view.findViewById(R.id.homepage_fragment_ll_lianaihunyin);
        ll_zhichangguanli = (LinearLayout) view.findViewById(R.id.homepage_fragment_ll_zhichangguanli);
        ll_qinzijiating = (LinearLayout) view.findViewById(R.id.homepage_fragment_ll_qinzijiating);
        ll_xinlikepu = (LinearLayout) view.findViewById(R.id.homepage_fragment_ll_xinlikepu);
        ll_kechengjiangzuo = (LinearLayout) view.findViewById(R.id.homepage_fragment_ll_kechengjiangzuo);
        ll_rementuijian01 = (LinearLayout) view.findViewById(R.id.homepage_ll_tuijian1);
        ll_rementuijian02 = (LinearLayout) view.findViewById(R.id.homepage_ll_tuijian2);
        ll_rementuijian03 = (LinearLayout) view.findViewById(R.id.homepage_ll_tuijian3);
        ll_xinlike01 = (LinearLayout) view.findViewById(R.id.homepage_fragment_ll_xinlike01);
        ll_xinlike02 = (LinearLayout) view.findViewById(R.id.homepage_fragment_ll_xinlike02);
        ll_xinlike03 = (LinearLayout) view.findViewById(R.id.homepage_fragment_ll_xinlike03);
        ll_zuixinfm01 = (LinearLayout) view.findViewById(R.id.homepage_fragment_ll_zuixinfm01);
        ll_zuixinfm02 = (LinearLayout) view.findViewById(R.id.homepage_fragment_ll_zuixinfm02);
        ll_zuixinfm03 = (LinearLayout) view.findViewById(R.id.homepage_fragment_ll_zuixinfm03);
        ll_diantaituijian01 = (LinearLayout) view.findViewById(R.id.homepage_fragment_ll_diantaituijian01);
        ll_diantaituijian02 = (LinearLayout) view.findViewById(R.id.homepage_fragment_ll_diantaituijian02);
        ll_diantaituijian03 = (LinearLayout) view.findViewById(R.id.homepage_fragment_ll_diantaituijian03);
        ll_diantaituijian04 = (LinearLayout) view.findViewById(R.id.homepage_fragment_ll_diantaituijian04);
        rl_morexinlike = (RelativeLayout) view.findViewById(R.id.homepage_fragment_rl_morexinlikecheng);
        rl_morefm = (RelativeLayout) view.findViewById(R.id.homepage_fragment_rl_morefm);
        rl_morediantai = (RelativeLayout) view.findViewById(R.id.homepage_fragment_rl_morediantai);
    }
    //初始化viewpager中内容
    public void initViewPagerPic(){
        imageViewList = new ArrayList<>();
        ImageView imageView1 = new ImageView(getContext());
        imageView1.setImageResource(R.drawable.homepage_viewpager03);
        imageView1.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageView imageView2 = new ImageView(getContext());
        imageView2.setImageResource(R.drawable.homepage_viewpager01);
        imageView2.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageView imageView3 = new ImageView(getContext());
        imageView3.setImageResource(R.drawable.homepage_viewpager02);
        imageView3.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageView imageView4 = new ImageView(getContext());
        imageView4.setImageResource(R.drawable.homepage_viewpager03);
        imageView4.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageView imageView5 = new ImageView(getContext());
        imageView5.setImageResource(R.drawable.homepage_viewpager01);
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
    public void setListen(){
       viewpager.addOnPageChangeListener(new ViewPagerInFragmentListener(viewpager));
        ll_ziwochengzhang.setOnClickListener(this);
        ll_qingxuguanli.setOnClickListener(this);
        ll_renjigoutong.setOnClickListener(this);
        ll_lianaihunxi.setOnClickListener(this);
        ll_zhichangguanli.setOnClickListener(this);
        ll_qinzijiating.setOnClickListener(this);
        ll_xinlikepu.setOnClickListener(this);
        ll_kechengjiangzuo.setOnClickListener(this);
        ll_rementuijian01.setOnClickListener(this);
        ll_rementuijian02.setOnClickListener(this);
        ll_rementuijian03.setOnClickListener(this);
        ll_xinlike01.setOnClickListener(this);
        ll_xinlike02.setOnClickListener(this);
        ll_xinlike03.setOnClickListener(this);
        ll_zuixinfm01.setOnClickListener(this);
        ll_zuixinfm02.setOnClickListener(this);
        ll_zuixinfm03.setOnClickListener(this);
        ll_diantaituijian01.setOnClickListener(this);
        ll_diantaituijian02.setOnClickListener(this);
        ll_diantaituijian03 .setOnClickListener(this);
        ll_diantaituijian04.setOnClickListener(this);
        rl_morexinlike .setOnClickListener(this);
        rl_morefm.setOnClickListener(this);
        rl_morediantai.setOnClickListener(this);
    }
    //点击监听
    @Override
    public void onClick(View v) {
            switch (v.getId()){

                case  R.id.homepage_fragment_ll_ziwochengzhang:

                    break;
                case R.id.homepage_fragment_ll_qingxuguanli :
                    break;
                case R.id.homepage_fragment_ll_renjigoutong:
                    break;
                case R.id.homepage_fragment_ll_lianaihunyin:
                    break;
                case R.id.homepage_fragment_ll_zhichangguanli:
                    break;
                case R.id.homepage_fragment_ll_qinzijiating:
                    break;
                case R.id.homepage_fragment_ll_xinlikepu:
                    break;
                case R.id.homepage_fragment_ll_kechengjiangzuo:
                    break;
                case R.id.homepage_ll_tuijian1:
                    break;
                case R.id.homepage_ll_tuijian2:
                    break;
                case R.id.homepage_ll_tuijian3:
                    break;
                case R.id.homepage_fragment_ll_xinlike01:
                    break;
                case R.id.homepage_fragment_ll_xinlike02:
                    break;
                case R.id.homepage_fragment_ll_xinlike03:
                    break;
                case R.id.homepage_fragment_ll_zuixinfm01:
                    break;
                case R.id.homepage_fragment_ll_zuixinfm02:
                    break;
                case R.id.homepage_fragment_ll_zuixinfm03:
                    break;
                case R.id.homepage_fragment_ll_diantaituijian01:
                    break;
                case R.id.homepage_fragment_ll_diantaituijian02:
                    break;
                case  R.id.homepage_fragment_ll_diantaituijian03:
                    break;
                case  R.id.homepage_fragment_ll_diantaituijian04:
                    break;
                case  R.id.homepage_fragment_rl_morexinlikecheng:
                    break;
                case R.id.homepage_fragment_rl_morefm:
                    break;
                case R.id.homepage_fragment_rl_morediantai:
                    break;
            }
    }
}
