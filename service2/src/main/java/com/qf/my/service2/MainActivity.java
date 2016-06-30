package com.qf.my.service2;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.qf.my.progressservice.IMyAidlIinterface;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button btn1;
    Button btn2;
    IMyAidlIinterface myAidlInterface;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("12345","Oncreate执行了");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button) findViewById(R.id.aidl_btn1);
        btn2 = (Button) findViewById(R.id.aidl_btn2);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        intent = new Intent();
        intent.setAction("android.qf.progress");


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.aidl_btn1:

                bindService(intent, new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        myAidlInterface = IMyAidlIinterface.Stub.asInterface(service);
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName name) {

                    }
                }, Context.BIND_AUTO_CREATE);
                break;
            case R.id.aidl_btn2:
                try {
                    int result =  myAidlInterface.jia(1,2);
                    Log.i("12345",result+"-----");
                    List<String> data = new ArrayList<String>();
                    List<String> list = myAidlInterface.getList(data);
                    for(String s:list){
                        Log.i("12345","来自服务端的"+s);
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;



        }
    }
}
