package sanjihuancun;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 网络缓存
 */
public class WebCache {
    public  void getWebCache(final String url,final Callback callback){
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        byte[] bs = download(url);
                        callback.setBitmap(bs);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public byte[] download(String urlpath) throws Exception {
        URL url = new URL(urlpath);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoInput(true);
        connection.connect();
        if(connection.getResponseCode()==200){
            InputStream is = connection.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] bs = new byte[1024*4];
        int len = -1;
        while((len = is.read(bs))!=-1){
            baos.write(bs,0,len);
        }
        if(is!=null){
            is.close();
        }
        return baos.toByteArray();
    }
        return null;
    }

public interface Callback{
      void setBitmap(byte[] bs);
}

}
