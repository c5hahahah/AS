package sanjihuancun;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by my on 2016/6/27.
 */
public class MyLruCache {
    private LruCache<String,Bitmap> lruCache;

    public MyLruCache(){
        int size = (int) Runtime.getRuntime().freeMemory();
        lruCache = new LruCache<String,Bitmap>(size){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes()*value.getHeight()/1024;
            }
        };
    }

    //增加一个数据进入缓存
    public synchronized boolean addToLru(String url,Bitmap bitmap){
        boolean flag = false;
        if(url!=null){
            if(bitmap!=null){
                lruCache.put(url,bitmap);
                flag = true;
            }
        }
        return flag;
    }
    //得到数据
    public Bitmap getBitMap(String urlpath){
        if(urlpath!=null){
            Bitmap bitmap = lruCache.get(urlpath);
            return bitmap;
        }
        return null;
    }
    //移除数据
    public boolean remove(String urlpath){
        boolean flag = false;
        if(urlpath!=null){
              Bitmap bitmap = lruCache.remove(urlpath);
            if(bitmap!=null){
                bitmap.recycle();
            }
            flag = true;
        }
        return flag;
    }
    //移除所有的数据
        public void clear(){
            if(lruCache.size()>0){
                lruCache.evictAll();
            }
        }
}
