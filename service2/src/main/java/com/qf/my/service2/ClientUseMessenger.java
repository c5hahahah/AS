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

public class ClientUseMessenger extends AppCompatActivity {
    Messenger client;
    Messenger send;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_use_messenger);
        btn = (Button) findViewById(R.id.usemessenger_btn);
        Intent intent = new Intent();
        intent.setAction("aaaaa");
        client = new Messenger(new Handler(){
            @Override
            public void handleMessage(Message msg) {
                Log.i("12345","arg1="+msg.arg1);
            }
        });
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                send = new Messenger(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, Context.BIND_AUTO_CREATE);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message msg = Message.obtain();
                msg.arg1 = 10;
                msg.arg2 = 1;
                msg.replyTo = client;
                try {
                    send.send(msg);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
