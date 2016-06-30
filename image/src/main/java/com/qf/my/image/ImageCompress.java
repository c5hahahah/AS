package com.qf.my.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * Created by my on 2016/6/27.
 */
public class ImageCompress  {
    public Bitmap compress(byte[] bs,int picWidth,int picheight){
        Bitmap bit = BitmapFactory.decodeByteArray(bs,0,bs.length);
        Log.i("12345","原始的尺寸"+bit.getByteCount()+"");

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap beforbit = BitmapFactory.decodeByteArray(bs,0,bs.length,options);
        //判断缩放比例
        options.inSampleSize = getSampleSize(options,picWidth,picheight);
        Log.i("12345",options.inSampleSize+"");
        options.inJustDecodeBounds = false;
        Bitmap lastbitmap = BitmapFactory.decodeByteArray(bs,0,bs.length,options);
        return lastbitmap;
    }
    public int getSampleSize(BitmapFactory.Options options,int picWidth,int picHight){
            int width = options.outWidth;
            int hight = options.outHeight;
            int widthSampleSize = Math.round((float)width/picWidth);
            int hightSampleSize = Math.round((float)hight/picHight);
            int samplesize = widthSampleSize>hightSampleSize?hightSampleSize:widthSampleSize;

        return samplesize;
    }
}
