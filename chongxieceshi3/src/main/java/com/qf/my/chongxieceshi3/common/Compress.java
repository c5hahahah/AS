package com.qf.my.chongxieceshi3.common;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by my on 2016/6/27.
 */
public class Compress {
    public static Bitmap ImageCompress(byte[] data,int picWidth,int picHeight){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap beforbit = BitmapFactory.decodeByteArray(data,0,data.length,options);
        //计算压缩比
        options.inSampleSize = getSampleSize(options,picWidth,picHeight);
        options.inJustDecodeBounds = false;
        Bitmap afterbit = BitmapFactory.decodeByteArray(data,0,data.length,options);
        return afterbit;
    }
    public static  int getSampleSize(BitmapFactory.Options options,int picWidth,int picHeight){
        int SampleSize = 1;
        int width = options.outWidth;
        int height = options.outHeight;
        int widthrate = Math.round ((float)width/picWidth);
        int heightrate = Math.round ((float)height/picHeight);
        SampleSize = widthrate>heightrate?heightrate:widthrate;
        return SampleSize;
    }
}
