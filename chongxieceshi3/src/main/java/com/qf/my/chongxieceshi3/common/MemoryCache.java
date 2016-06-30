package com.qf.my.chongxieceshi3.common;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by my on 2016/6/28.
 */
public class MemoryCache {
    private LruCache<String,Bitmap> lruCache;

    public MemoryCache(){
        int size = (int) Runtime.getRuntime().freeMemory()/8;
        lruCache = new LruCache<String,Bitmap>(size){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getHeight()*value.getRowBytes()/1024;
            }
        };
    }
    /*
        加入缓存
     */
    public synchronized void addtoCache(String url,Bitmap bitmap){
        if(url!=null){
            if(bitmap!=null){
                lruCache.put(url,bitmap);
            }
        }
    }
    /*
        从缓存中取出
     */
    public Bitmap getBitmapFromCache(String url){
        if(url!=null){
            lruCache.get(url);
        }
        return null;
    }
}
