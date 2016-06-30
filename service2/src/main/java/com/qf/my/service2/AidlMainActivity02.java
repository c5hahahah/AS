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

import com.qf.my.progressservice.IMyAidlInterface02;

public class AidlMainActivity02 extends AppCompatActivity implements View.OnClickListener{
    Button btn1,btn2;
    private Intent intent;
    private IMyAidlInterface02 myAidlInterface02;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl_main02);
        btn1 = (Button) findViewById(R.id.activity02_btn1);
        btn2 = (Button) findViewById(R.id.activity02_btn2);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity02_btn1:
                intent = new Intent();
                intent.setAction("android.intent1");
                bindService(intent, new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                    myAidlInterface02 = IMyAidlInterface02.Stub.asInterface(service);
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName name) {

                    }
                }, Context.BIND_AUTO_CREATE);
                Log.i("12345","连接成功");
                break;
            case R.id.activity02_btn2:
                try {
                int result = myAidlInterface02.chengfa(5,4);
                Log.i("12345","计算的结果是"+result);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
                break;
        }
    }
}
