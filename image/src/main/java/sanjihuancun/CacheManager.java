package sanjihuancun;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by my on 2016/6/27.
 */
public class CacheManager {
    WebCache webCache = new WebCache();
    SDcardCache sDcardCache = new SDcardCache();
    MyLruCache myLruCache = new MyLruCache();
    Handler handler = new Handler();
    public void getBitmap(final String url, final ImageView iv){
        if(myLruCache.getBitMap(url)!=null){
            Log.i("12345","内存取出");
            Bitmap bitmap = myLruCache.getBitMap(url);
            iv.setImageBitmap(bitmap);
                }else if(sDcardCache.getFromSDcard(url)!=null){
                    Log.i("12345","sd卡取出");
                    Bitmap bitmap = sDcardCache.getFromSDcard(url);
                    iv.setImageBitmap(bitmap);
                }else{
                        webCache.getWebCache(url, new WebCache.Callback() {
                            @Override
                            public void setBitmap(byte[] bs) {
                                if(bs!=null){
                                    final Bitmap bitmap = BitmapFactory.decodeByteArray(bs,0,bs.length);
                                    sDcardCache.addToSDcard(bs,url);
                                    myLruCache.addToLru(url,bitmap);
                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            iv.setImageBitmap(bitmap);
                                        }
                                    });
                                }
                            }
                        });

        }

    }
}
