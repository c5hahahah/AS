package com.qf.my.image;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by my on 2016/6/27.
 */
public class MyLru {
    LruCache<String,Bitmap> lruCache;
    public MyLru(){
        int size = (int) Runtime.getRuntime().freeMemory();
        lruCache = new LruCache<String,Bitmap>(size){
                @Override
    protected int sizeOf(String key, Bitmap value) {
        return value.getRowBytes()*value.getHeight()/1024;
    }
};
}

}
