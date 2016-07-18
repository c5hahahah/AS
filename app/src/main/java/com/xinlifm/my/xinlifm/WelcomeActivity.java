package com.xinlifm.my.xinlifm;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity {
    private ImageView backgroud;
    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome);
        backgroud = (ImageView) findViewById(R.id.welcome_backgroud);
        animation();
        //判断是否首次加载  是  进入引导界面   否  直接进入主界面

        //动画监听
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //开启服务请求数据
                if(isFirst()){


                }

            }
            @Override
            public void onAnimationEnd(Animation animation) {
                if(isConnect()){
                    if(isFirst()){
                        Intent toGuide = new Intent(WelcomeActivity.this,GuideActivity.class);
                        startActivity(toGuide);
                        finish();
                    }else{
                        //進入主界面
                    Intent toMain = new Intent(WelcomeActivity.this,MainActivity.class);
                    startActivity(toMain);
                    finish();
                }
                }else{
                    Toast.makeText(WelcomeActivity.this,"请链接网络",Toast.LENGTH_LONG).show();

                }

            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
    //背景放大图片
    public void animation(){
        animation = AnimationUtils.loadAnimation(this,R.anim.welcome_animation);
        animation.setFillAfter(true);
        backgroud.setAnimation(animation);
    }

    //判断是否第一次连接
    public boolean isFirst(){
        SharedPreferences preferences = getSharedPreferences("isFirstUse", Context.MODE_PRIVATE);
        boolean flag = preferences.getBoolean("isFirstUse",true);
        return flag;
    }
    //判断是否有 网络
    public boolean isConnect(){
        boolean flag = false;
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if(info.isAvailable()||info!=null){
            flag = true;
        }
        return  flag;
    }
}
