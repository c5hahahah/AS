package com.qf.my.image;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by my on 2016/6/24.
 */
public class HttpUtiles {
    public static byte[] getByte(String urlpath){
        try {
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
                    baos.flush();

                }
                is.close();
                return baos.toByteArray();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }
}
