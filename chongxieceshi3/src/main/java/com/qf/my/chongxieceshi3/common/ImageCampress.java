package com.qf.my.chongxieceshi3.common;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by my on 2016/6/28.
 */
public class ImageCampress {
    public static Bitmap imageCampress(byte[] bs,int picWidth,int picHeight){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap beforbit = BitmapFactory.decodeByteArray(bs,0,bs.length,options);
        //计算压缩比
        options.inSampleSize = sampleSize(options,picWidth,picHeight);
        options.inJustDecodeBounds = false;
        Bitmap afterbit = BitmapFactory.decodeByteArray(bs,0,bs.length,options);
        return afterbit;
    }
    public static int sampleSize(BitmapFactory.Options options,int picWidth,int picHeight){
        int width = options.outWidth;
        int height = options.outHeight;
        int sampleSize = 1;
        int widthRate = Math.round((float)width/picWidth);
        int heightRate = Math.round((float)height/picHeight);
        sampleSize = widthRate<heightRate?widthRate:heightRate;
        return sampleSize;
    }
}
