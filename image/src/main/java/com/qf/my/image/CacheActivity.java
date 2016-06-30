package com.qf.my.image;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import sanjihuancun.CacheManager;

public class CacheActivity extends AppCompatActivity {
    Button btn1,btn2;
    ImageView iv1,iv2;
    CacheManager manager;
    String url = "https://www.baidu.com/img/bd_logo1.png";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cache);
        btn1 = (Button) findViewById(R.id.cache_btn1);
        btn2 = (Button) findViewById(R.id.cache_btn2);
        iv1 = (ImageView) findViewById(R.id.cache_iv1);
        iv2 = (ImageView) findViewById(R.id.cache_iv2);
        manager = new CacheManager();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.getBitmap(url,iv1);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.getBitmap(url,iv2);
            }
        });
    }
}
