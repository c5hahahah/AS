package com.qf.my.chongxieceshi3;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.LoaderManager;
import android.widget.Toast;

public class DownLoadService extends Service {


    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(getApplicationContext(),"连接建立",Toast.LENGTH_LONG).show();
        return new MyBinder();
    }

    class MyBinder extends Binder{
        public void downLoad(LoaderManager manager, LoaderManager.LoaderCallbacks<Boolean> callbacks){
            //调用loadermanager的init方法
            for(int i = 1;i<=4;i++){
                Bundle bundle = new Bundle();
                bundle.putInt("page",i);
                manager.initLoader(i,bundle,callbacks);
            }
        }
    }
}
