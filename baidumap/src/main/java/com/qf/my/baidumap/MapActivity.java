package com.qf.my.baidumap;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.MapView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MapActivity extends AppCompatActivity {


    private MapView mMapView;
    FrameLayout layout;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        SDKInitializer.initialize(getApplicationContext());
       // MapView.setMapCustomEnable(true);
        super.onCreate(savedInstanceState);
       // setMapCustomFile(this);
        mMapView = new MapView(this, new BaiduMapOptions());
        layout = new FrameLayout(this);
        layout.addView(mMapView);
       // initView(this);
        setContentView(layout);
    }

//    // 初始化View
//    private void initView(Context context) {
//
//        RadioGroup group = new RadioGroup(context);
//        group.setBackgroundColor(Color.BLACK);
//        final RadioButton openBtn = new RadioButton(context);
//        openBtn.setText("开启个性化地图");
//        openBtn.setId(OPEN_ID);
//        openBtn.setTextColor(Color.WHITE);
//        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,
//                FrameLayout.LayoutParams.WRAP_CONTENT);
//        openBtn.setChecked(true);
//
//        group.addView(openBtn, params);
//        final RadioButton closeBtn = new RadioButton(context);
//        closeBtn.setText("关闭个性化地图");
//        closeBtn.setTextColor(Color.WHITE);
//        closeBtn.setId(CLOSE_ID);
//        group.addView(closeBtn, params);
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT);
//        layout.addView(group, layoutParams);
//
//        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                if (checkedId == OPEN_ID) {
//                    MapView.setMapCustomEnable(true);
//                } else if (checkedId == CLOSE_ID) {
//                    MapView.setMapCustomEnable(false);
//                }
//            }
//        });
//    }
//
//    // 设置个性化地图config文件路径
//    private void setMapCustomFile(Context context) {
//        FileOutputStream out = null;
//        InputStream inputStream = null;
//        String moduleName = null;
//        try {
//            inputStream = context.getAssets()
//                    .open("customConfigdir/custom_config.txt");
//            byte[] b = new byte[inputStream.available()];
//            inputStream.read(b);
//
//            moduleName = context.getFilesDir().getAbsolutePath();
//            File f = new File(moduleName + "/" + "custom_config.txt");
//            if (f.exists()) {
//                f.delete();
//            }
//            f.createNewFile();
//            out = new FileOutputStream(f);
//            out.write(b);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (inputStream != null) {
//                    inputStream.close();
//                }
//                if (out != null) {
//                    out.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        MapView.setCustomMapStylePath(moduleName + "/custom_config.txt");
//
//    }

    @Override
    protected void onPause() {
        super.onPause();
        // activity 暂停时同时暂停地图控件
        mMapView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // activity 恢复时同时恢复地图控件
        mMapView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // activity 销毁时同时销毁地图控件
        mMapView.onDestroy();
//        MapView.setMapCustomEnable(false);
    }

}
