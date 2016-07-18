package com.xinlifm.my.main_fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

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
public class DiscoveryFragment extends Fragment implements View.OnClickListener {
    View view;
    //UI控件
    ViewPagerInFragment viewpager;
    LinearLayout ll_search, ll_kuaile, ll_beishang, ll_gudu,
            ll_yiqiliao, ll_jianya, ll_wunai,
            ll_fanzao, ll_gandong, ll_mimang,
            ll_shuiqian, ll_lvxing, ll_sanbu,
            ll_zuoche, ll_duchu, ll_shilian,
            ll_shimian, ll_suibian, ll_wuliao;


    //viewPager相关
    List<ImageView> imageViewList;
    HomepageViewPagerAdapter viewPagerAdapter;
    Handler handler;
    int viewpagerIndex = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.discovery_fragment, null);
        initView();
        initViewPagerPic();
        handler = new Handler();
        setViewpager();
        viewpagerAtuoScroll();
        setListen();
        return view;
    }

    //初始化UI
    public void initView() {
        viewpager = (ViewPagerInFragment) view.findViewById(R.id.discovery_fragment_viewpager);
        ll_search = (LinearLayout) view.findViewById(R.id.discovery_fragment_ll_search);
        ll_kuaile = (LinearLayout) view.findViewById(R.id.discovery_fragment_ll_kuaile);
        ll_beishang = (LinearLayout) view.findViewById(R.id.discovery_fragment_ll_beishang);
        ll_gudu = (LinearLayout) view.findViewById(R.id.discovery_fragment_ll_gudu);
        ll_yiqiliao = (LinearLayout) view.findViewById(R.id.discovery_fragment_ll_yiqiliao);
        ll_jianya = (LinearLayout) view.findViewById(R.id.discovery_fragment_ll_jianya);
        ll_wunai = (LinearLayout) view.findViewById(R.id.discovery_fragment_ll_wunai);
        ll_fanzao = (LinearLayout) view.findViewById(R.id.discovery_fragment_ll_fanzao);
        ll_gandong = (LinearLayout) view.findViewById(R.id.discovery_fragment_ll_gandong);
        ll_mimang = (LinearLayout) view.findViewById(R.id.discovery_fragment_ll_mimang);
        ll_shuiqian = (LinearLayout) view.findViewById(R.id.discovery_fragment_ll_shuiqian);
        ll_lvxing = (LinearLayout) view.findViewById(R.id.discovery_fragment_ll_lvxing);
        ll_sanbu = (LinearLayout) view.findViewById(R.id.discovery_fragment_ll_sanbu);
        ll_zuoche = (LinearLayout) view.findViewById(R.id.discovery_fragment_ll_zuoche);
        ll_duchu = (LinearLayout) view.findViewById(R.id.discovery_fragment_ll_duchu);
        ll_shilian = (LinearLayout) view.findViewById(R.id.discovery_fragment_ll_shilian);
        ll_shimian = (LinearLayout) view.findViewById(R.id.discovery_fragment_ll_shimian);
        ll_suibian = (LinearLayout) view.findViewById(R.id.discovery_fragment_ll_suibian);
        ll_wuliao = (LinearLayout) view.findViewById(R.id.discovery_fragment_ll_wuliao);
    }

    //初始化viewpager中内容
    public void initViewPagerPic() {
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
    public void setViewpager() {
        viewpager.setOffscreenPageLimit(5);
        viewpager.setAdapter(viewPagerAdapter);
        viewpager.setCurrentItem(0);

    }

    //设置Viewpager自动滑动
    public void viewpagerAtuoScroll() {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (viewpagerIndex > 3) {
                            viewpagerIndex = 1;
                            viewpager.setCurrentItem(1, false);
                        } else {
                            viewpagerIndex++;
                            viewpager.setCurrentItem(viewpagerIndex);
                        }
                    }
                });
            }
        }, 0, 5000, TimeUnit.MILLISECONDS);
    }

    //设置点击监听
    public void setListen() {
        viewpager.addOnPageChangeListener(new ViewPagerInFragmentListener(viewpager));
        ll_search.setOnClickListener(this);
        ll_kuaile.setOnClickListener(this);
        ll_beishang.setOnClickListener(this);
        ll_gudu.setOnClickListener(this);
        ll_yiqiliao.setOnClickListener(this);
        ll_jianya.setOnClickListener(this);
        ll_wunai.setOnClickListener(this);
        ll_fanzao.setOnClickListener(this);
        ll_gandong.setOnClickListener(this);
        ll_mimang.setOnClickListener(this);
        ll_shuiqian.setOnClickListener(this);
        ll_lvxing.setOnClickListener(this);
        ll_sanbu.setOnClickListener(this);
        ll_zuoche.setOnClickListener(this);
        ll_duchu.setOnClickListener(this);
        ll_shilian.setOnClickListener(this);
        ll_shimian.setOnClickListener(this);
        ll_suibian.setOnClickListener(this);
        ll_wuliao.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.discovery_fragment_ll_search:

                break;
            case R.id.discovery_fragment_ll_kuaile:

                break;
            case R.id.discovery_fragment_ll_beishang:

                break;
            case R.id.discovery_fragment_ll_gudu:

                break;
            case R.id.discovery_fragment_ll_yiqiliao:

                break;
            case R.id.discovery_fragment_ll_jianya:

                break;
            case R.id.discovery_fragment_ll_wunai:

                break;

            case R.id.discovery_fragment_ll_fanzao:

                break;
            case R.id.discovery_fragment_ll_gandong:

                break;
            case R.id.discovery_fragment_ll_mimang:

                break;
            case R.id.discovery_fragment_ll_shuiqian:

                break;
            case R.id.discovery_fragment_ll_lvxing:

                break;
            case R.id.discovery_fragment_ll_sanbu:

                break;

            case R.id.discovery_fragment_ll_zuoche:

                break;
            case R.id.discovery_fragment_ll_duchu:

                break;
            case R.id.discovery_fragment_ll_shilian:

                break;
            case R.id.discovery_fragment_ll_shimian:

                break;
            case R.id.discovery_fragment_ll_suibian:

                break;
            case R.id.discovery_fragment_ll_wuliao:

                break;
        }
    }
}
