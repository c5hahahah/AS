package com.qf.my.chongxieceshi3.common;

import android.graphics.Bitmap;
import java.io.ByteArrayOutputStream;

/**
 * Created by my on 2016/6/28.
 */
public class BitmapToByte {
    public static  byte[] bitmapToByte(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,baos);
        return baos.toByteArray();
    }
}
