package com.game3d.my.service;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.util.Log;

import com.android.volley.RequestQueue;

import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.game3d.my.cache.FileCache;
import com.game3d.my.cache.MemoryCache;
import com.game3d.my.cache.WebCache;
import com.game3d.my.modles.MyBander;
import com.game3d.my.modles.News;
import com.game3d.my.sqlitehelper.SqliteHander;
import com.game3d.my.utils.BitmapTobyte;
import com.game3d.my.utils.CampressPic;
import com.game3d.my.utils.JsonUtils;

import java.util.List;

public class DownloadService extends Service {
    RequestQueue requestQueue;
    StringRequest stringRequest;
    ImageRequest imageRequest;


//    int types[] = {181,182,183,184,2,151,152,153,154,196,197,199,26};
//    int type = 181;
//    int page = 1;
//    String url = "http://www.3dmgame.com/sitemap/api.php?row=10&typeid="+type+"&paging=1&page=";
    String[] urls = {"http://www.3dmgame.com/sitemap/api.php?row=10&typeid=181&paging=1&page=",
            "http://www.3dmgame.com/sitemap/api.php?row=10&typeid=182&paging=1&page=",
            "http://www.3dmgame.com/sitemap/api.php?row=10&typeid=183&paging=1&page=",
            "http://www.3dmgame.com/sitemap/api.php?row=10&typeid=184&paging=1&page=",
            "http://www.3dmgame.com/sitemap/api.php?row=10&typeid=2&paging=1&page=",
    "http://www.3dmgame.com/sitemap/api.php?row=10&typeid=151&paging=1&page=",
    "http://www.3dmgame.com/sitemap/api.php?row=10&typeid=152&paging=1&page=",
    "http://www.3dmgame.com/sitemap/api.php?row=10&typeid=153&paging=1&page=",
    "http://www.3dmgame.com/sitemap/api.php?row=10&typeid=154&paging=1&page=",
    "http://www.3dmgame.com/sitemap/api.php?row=10&typeid=196&paging=1&page=",
    "http://www.3dmgame.com/sitemap/api.php?row=10&typeid=197&paging=1&page=",
"http://www.3dmgame.com/sitemap/api.php?row=10&typeid=199&paging=1&page=",
    "http://www.3dmgame.com/sitemap/api.php?row=10&typeid=26&paging=1&page="};
    WebCache webCache;
    FileCache fileCache;
    MemoryCache memoryCache;
    SqliteHander hander;
    public DownloadService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        webCache = new WebCache();
        fileCache = new FileCache();
        memoryCache = new MemoryCache();
        hander = new SqliteHander(getApplicationContext());
        requestQueue = Volley.newRequestQueue(getApplicationContext());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBander();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//          getdataByVolley(urls[0]);
//          getdataByVolley(urls[1]);
//          getdataByVolley(urls[2]);
//          getdataByVolley(urls[3]);
//          getdataByVolley(urls[4]);
//          getdataByVolley(urls[5]);
//          getdataByVolley(urls[6]);
//          getdataByVolley(urls[7]);
//          getdataByVolley(urls[8]);
//          getdataByVolley(urls[9]);
//          getdataByVolley(urls[10]);
//          getdataByVolley(urls[11]);
//          getdataByVolley(urls[12]);
        int type  = intent.getIntExtra("type",1);
        int pageindex = intent.getIntExtra("pageindex",1);
        Log.i("aaa",type+"--------------"+pageindex);
        if(pageindex==1&&type==1) {
            download(urls[0] + pageindex,pageindex);
            download(urls[1] + pageindex,pageindex);
            download(urls[2] + pageindex,pageindex);
            download(urls[3] + pageindex,pageindex);
            download(urls[4] + pageindex,pageindex);
            download(urls[5] + pageindex,pageindex);
            download(urls[6] + pageindex,pageindex);
            download(urls[7] + pageindex,pageindex);
            download(urls[8] + pageindex,pageindex);
            download(urls[9] + pageindex,pageindex);
            download(urls[10] + pageindex,pageindex);
            download(urls[11] + pageindex,pageindex);
            download(urls[12] + pageindex,pageindex);
        }
        switch (type){
            case 181:
                download(urls[0]+pageindex,pageindex);
                break;
            case 182:
                download(urls[1]+pageindex,pageindex);
                break;
            case 183:
                download(urls[2]+pageindex,pageindex);
                break;
            case 184:
                download(urls[3]+pageindex,pageindex);
                break;
            case 2:
                download(urls[4]+pageindex,pageindex);
                break;
            case 151:
                download(urls[5]+pageindex,pageindex);
                break;
            case 152:
                download(urls[6]+pageindex,pageindex);
                break;
            case 153:
                download(urls[7]+pageindex,pageindex);
                break;
            case 154:
                download(urls[8]+pageindex,pageindex);
                break;
            case 196:
                download(urls[9]+pageindex,pageindex);
                break;
            case 197:
                download(urls[10]+pageindex,pageindex);
                break;
            case 199:
                download(urls[11]+pageindex,pageindex);
                break;
            case 26:
                download(urls[12]+pageindex,pageindex);
                break;
            default:
                break;
        }
        return START_REDELIVER_INTENT;
    }
    public void download(final String url,final int pageindex){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    byte[] bs = webCache.getFromNet(url);
                    if (bs == null) {
                        Log.i("12345", "网络解析的第一步是空");
                        return;
                    }
                    String json = new String(bs, "utf-8");
                    List<News> newsList = JsonUtils.getList(json, 10);
                    Log.i("12345", "解析出的news的个数" + newsList.size() + "--------");
                    //下载图片
                    for (News news : newsList) {
                        String litpic = news.getLitpic();
                        //下载图片
                        if (!litpic.equals("www.3dmgame.com")) {
                            byte[] data = webCache.getFromNet(litpic);
                            if (data != null) {
                                //压缩
                                Bitmap aftercampress = CampressPic.compress(data, 80, 80);
                                if (aftercampress != null) {
                                    byte[] afterdata = BitmapTobyte.bitmapToByte(aftercampress);
                                    //保存图片
                                    String filename = fileCache.saveToFile(afterdata, litpic);
                                    memoryCache.insertToMemory(filename, aftercampress);
                                    //将数据加入数据库
                                    news.setLitpic(filename);
                                    hander.insertToNewstable(news, filename);
                                    // Log.i("12345", "完成一个数据");
                                } else {
                                    //  Log.i("12345", "压缩出来是空");
                                }
                            } else {
                                //  Log.i("12345", "图片下载为空");
                            }
                        }
                    }
                    Intent intent = new Intent("ok");
                    intent.putExtra("ok", "ok");
                    sendBroadcast(intent);
                    if(pageindex!=1){
                        Log.i("12345","关掉自己");
                        stopSelf();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
//
//    public void getdataByVolley(String url){
//            stringRequest = new StringRequest(url, new Response.Listener<String>() {
//                @Override
//                public void onResponse(String s) {
//                    if(s!=null) {
//                        try {
//                            List<News> list = JsonUtils.getList(s,10);
//                            for(News news:list){
//
//                                if(news.getLitpic()!=null){
//                                    News afterupdatenews = savePicGetFilename(news);
//                                    hander.insertToNewstable(afterupdatenews,news.getLitpic());
//                                    }
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError volleyError) {
//                    Log.i("12345","下载数据出问题");
//                }
//            });
//        requestQueue.add(stringRequest);
//    }
//    public News savePicGetFilename(final News news){
//        imageRequest = new ImageRequest(news.getLitpic(), new Response.Listener<Bitmap>() {
//            @Override
//            public void onResponse(Bitmap bitmap) {
//            byte[] bs = BitmapTobyte.bitmapToByte(bitmap);
//                try {
//                    String name = fileCache.saveToFile(bs,news.getLitpic());
//                    memoryCache.insertToMemory(name,bitmap);
//                    news.setLitpic(name);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }, 80, 80, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//                Log.i("12345","图片问题");
//            }
//
//        });
//
//
//        return news;
//    }

}
