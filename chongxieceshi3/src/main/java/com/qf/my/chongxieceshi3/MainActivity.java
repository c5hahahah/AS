package com.qf.my.chongxieceshi3;

import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

import com.qf.my.chongxieceshi3.common.MyLoader;

public class MainActivity extends AppCompatActivity {
    Button btn1,btn2,btn3;
    private String url = "http://www.3dmgame.com/sitemap/api.php?row=10&typeid=1&paging=1&page=";
    private DownLoadService.MyBinder binder;
    private LoaderManager manager;
    private LoaderManager.LoaderCallbacks<Boolean> callbacks;
    private MyLoader loader;
    private NotificationManager nm;
    private NotificationCompat.Builder builder;
    private int getpage;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3= (Button) findViewById(R.id.btn3);
        manager = getSupportLoaderManager();
        nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        builder = new NotificationCompat.Builder(this);
        callbacks = new LoaderManager.LoaderCallbacks<Boolean>() {
            @Override
            public Loader<Boolean> onCreateLoader(int id, Bundle args) {
                getpage = args.getInt("page");
                loader = new MyLoader(getApplicationContext(),url+getpage);
                return loader;
            }

            @Override
            public void onLoadFinished(Loader<Boolean> loader, Boolean data) {
                //loader执行结束之后需要进行处理
                if(getpage==4) {
                    builder.setTicker("加载完成了");
                    builder.setSmallIcon(R.mipmap.ic_launcher);
                    builder.setContentText("下载完成了");
                    nm.notify(100,builder.build());
                }
            }

            @Override
            public void onLoaderReset(Loader<Boolean> loader) {

            }
        };

        //设置开启服务的监听
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(MainActivity.this,DownLoadService.class);
                intent.putExtra("url",url);
                bindService(intent, new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        binder = (DownLoadService.MyBinder) service;
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName name) {

                    }
                }, Context.BIND_AUTO_CREATE);
            }
        });
        //设置开启下载服务
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binder.downLoad(manager,callbacks);
            }
        });
        //设置开启跳转页面
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
