package sanjihuancun;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * SDcard 缓存
 */
public class SDcardCache {
    private static final File SD_ROOT = Environment.getExternalStorageDirectory();
    private static boolean isMOUNTED = false ;
    private String string = "file_cache";
    private File file = null;
    public SDcardCache(){
        if(getMounted()){
            file = new File(SD_ROOT,string);
            if(!file.exists()){
                file.mkdirs();
            }
        }
    }
    //判断是否挂在
    public static boolean getMounted(){
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
            isMOUNTED = true;
        }
        return isMOUNTED;
    }
    //插入数据
    public synchronized boolean addToSDcard(byte[] data,String urlpath){
        boolean flag = false;
        FileOutputStream outputStream = null;
        if(getMounted()){
            if(urlpath!=null){
                String filename = getFilenameFromUrlpath(urlpath);
                File filedown = new File(file,filename);
                if (filedown.exists()){
                    filedown.delete();
                }
                try {

                    outputStream = new FileOutputStream(filedown);
                    outputStream.write(data);
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if (outputStream!=null){
                        try {
                            outputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return flag;
    }
    //得到其中的数据
    public Bitmap getFromSDcard(String urlpath){
        if(getMounted()){
            if(urlpath!=null) {
                String filename = getFilenameFromUrlpath(urlpath);
                File filedown  = new File(file,filename);
                if(filedown.exists()){
                   Bitmap bitmap = BitmapFactory.decodeFile(filedown.getAbsolutePath());
                    return bitmap;
                }
            }
        }
        return null;
    }

    //删除数据
    public synchronized boolean delet(String urlpath){
        boolean flag = false;
        if (getMounted()){
            String filename = getFilenameFromUrlpath(urlpath);
            File filedown = new File(file,filename);
            if(filedown.exists()){
                filedown.delete();
                flag = true;
            }
        }
        return flag;
    }
    //清空文件夹
    public void clear(){
        if(getMounted()){
            File[] files = file.listFiles();
            for (File filee:files){
                filee.delete();
            }
        }
    }

    //将URLpath中把文件名拆分出来
    public String getFilenameFromUrlpath(String urlpath){
        Log.i("12345",urlpath);
        return urlpath.substring(urlpath.lastIndexOf("/")+1);
    }
}
