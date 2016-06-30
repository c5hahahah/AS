package com.qf.my.chongxieceshi3.common;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 进行网络缓存
 */
public class WebCache {
    /*
           从网络获取数据
     */
    public byte[] getByteFromInternet(String urlpath) throws Exception {
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


    /*
        从网络获取图片
     */
    public Bitmap getImageFrom(String url){
        try {
            byte[] bs = getByteFromInternet(url);
            Bitmap bitmap = BitmapFactory.decodeByteArray(bs,0,bs.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
