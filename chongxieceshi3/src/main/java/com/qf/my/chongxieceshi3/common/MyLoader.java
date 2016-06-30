package com.qf.my.chongxieceshi3.common;

import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.qf.my.chongxieceshi3.models.News;

import java.util.List;

/**
 * Created by my on 2016/6/27.
            */
    public class MyLoader extends AsyncTaskLoader<Boolean> {
        private  String url ;
        private Newshander newshander;
        WebCache webCache = new WebCache();
        FileCache fileCache = new FileCache();
        MemoryCache memoryCache = new MemoryCache();
    public MyLoader(Context context,String url) {
        super(context);
        this.url = url;
        newshander = new Newshander(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public Boolean loadInBackground() {
        boolean flag = false;
        //进行开启下载任务

        try {
            byte[] bs = webCache.getByteFromInternet(url);
            if(bs!=null){
                String json = new String(bs,"utf-8");
                List<News> list = JsonUtils.turnToJson(json);
                for (News news:list){
                    //将list加入数据库中
                    newshander.insertToSQL(news);
                    //下载图片
                    String url = news.getLitpic();
                    byte[] bytes = webCache.getByteFromInternet(url);
                    //保存图片
                    Bitmap compress = Compress.ImageCompress(bytes,60,60);
                    String filename =  fileCache.addToSdcard(url,BitmapToByte.bitmapToByte(compress));
                    newshander.updata(url,filename);
                    memoryCache.addtoCache(filename, compress);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }
}
