package com.qf.my.baidumap;

import android.location.LocationListener;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

public class LocationDemo extends AppCompatActivity {
    MapView mapView;
    BaiduMap baiduMap;

    LocationClient locationClient;
    MyLocationListener locationListener ;

//    AlertDialog.Builder builder ;

    boolean isFirst = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // SDKInitializer.initialize(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_demo);
        //地图初始化
        mapView = (MapView) findViewById(R.id.mapview_location);
        baiduMap = mapView.getMap();
        //开启定位图层
        baiduMap.setMyLocationEnabled(true);

        locationClient = new LocationClient(getApplicationContext());
        locationListener = new MyLocationListener();
//        builder = new AlertDialog.Builder(this);
//        builder.setIcon(R.mipmap.ic_launcher);
//        builder.setTitle("你好");
//        builder.setNegativeButton("谢谢",null);

        locationClient.registerLocationListener(locationListener);
        baiduMap.setMyLocationConfigeration(new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL,true,null));
        LocationClientOption option = new LocationClientOption();
        option.setScanSpan(1000);
        option.setIsNeedAddress(true);
        option.setOpenGps(true);
        option.setCoorType("bd09ll");
        locationClient.setLocOption(option);
        locationClient.start();
    }
    class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            if(bdLocation==null||mapView==null){
                Toast.makeText(getApplication(),"出错啦",Toast.LENGTH_LONG).show();
            }
            MyLocationData locationData = new MyLocationData.Builder()
                    .accuracy(bdLocation.getRadius())
                    .direction(100).latitude(bdLocation.getLatitude())
                    .longitude(bdLocation.getLongitude()).build();
            baiduMap.setMyLocationData(locationData);
            if(isFirst){
                isFirst= false;
                LatLng latLng = new LatLng(bdLocation.getLatitude(),bdLocation.getLongitude());
                MapStatus.Builder statusbuilder = new MapStatus.Builder();
                statusbuilder.target(latLng).zoom(18.0f);
                baiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(statusbuilder.build()));
                String local = bdLocation.getAddrStr();
//                builder.setMessage(local);
//                builder.create();
            }

        }
    }
}
