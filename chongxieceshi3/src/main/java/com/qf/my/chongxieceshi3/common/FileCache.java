package com.qf.my.chongxieceshi3.common;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.renderscript.FieldPacker;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by my on 2016/6/27.
 */
public class FileCache {
    private static final File SD_ROOT = Environment.getExternalStorageDirectory();
    private static final String NAME = "file_cache";
    private File file = null;
    private static boolean ISMOUNTED = false;
    public FileCache(){
        if(getIsMounted()) {
            file = new File(SD_ROOT, NAME);
            if(!file.exists()){
                file.mkdirs();
            }
        }
    }

    /*
        向文件中添加
     */
    public synchronized String addToSdcard(String url,byte[] data) throws Exception {
        if(getIsMounted()){
            String filename = url.substring(url.lastIndexOf("/")+1);
            File savefile = new File(file,filename);
            if(savefile.exists()){
                savefile.delete();
            }
            String filepath = savefile.getAbsolutePath();
            FileOutputStream outputStream = new FileOutputStream(savefile);
            outputStream.write(data);
            if(outputStream!=null){
                outputStream.close();
            }
            return filepath;
        }
        return null;
    }

    /*
        从文件中获取图片
     */
    public Bitmap getFromFile(String url) throws Exception {
        if(getIsMounted()) {
            String filename = url.substring(url.lastIndexOf("/") + 1);
            File getfile = new File(file, filename);
            FileInputStream inputStream = new FileInputStream(getfile);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] bs = new byte[1024*4];
            int len = -1;
            while((len = inputStream.read(bs))!=-1){
                baos.write(bs,0,len);
                baos.flush();
            }
            return BitmapFactory.decodeByteArray(baos.toByteArray(),0,baos.toByteArray().length);
        }

        return null;
    }
    /*
        静态判断SDcard 是否挂载
    */
    public static boolean getIsMounted(){
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
            ISMOUNTED = true;
        }
        return ISMOUNTED;
    }
}
