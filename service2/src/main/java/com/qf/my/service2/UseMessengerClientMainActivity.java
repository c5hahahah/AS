package com.qf.my.service2;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class UseMessengerClientMainActivity extends AppCompatActivity implements View.OnClickListener{
    Button btn1;
    Button btn2;
    Intent intent;
    Messenger client;
    Messenger send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_messenger_client_main);
        btn1 = (Button) findViewById(R.id.messenger_btn1);
        btn2 = (Button) findViewById(R.id.messenger_btn2);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.messenger_btn1:
                client = new Messenger(new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        Log.i("12345","来自服务端的"+msg.arg1);
                    }
                });
                intent = new Intent();
                intent.setAction("android.messenger");

                bindService(intent, new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        send = new Messenger(service);
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName name) {

                    }
                }, Context.BIND_AUTO_CREATE);
                Log.i("12345","进行了连接");

                break;
            case R.id.messenger_btn2:

                Message msg = Message.obtain();
                msg.arg1 = 10;
                msg.arg2 = 1;
                msg.replyTo = client;
                try {
                    send.send(msg);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;


        }
    }
}
