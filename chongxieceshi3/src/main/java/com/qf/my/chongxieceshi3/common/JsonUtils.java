package com.qf.my.chongxieceshi3.common;


import com.qf.my.chongxieceshi3.models.News;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by my on 2016/6/26.
 */
public class JsonUtils {
    public static List<News> turnToJson(String json){
        try {
            JSONObject jo1 = new JSONObject(json);
            JSONObject jo2 = jo1.getJSONObject("data");
            List<News> list = new ArrayList<>();
            for (int i = 0;i<10;i++){
                JSONObject jo3 = jo2.getJSONObject(i+"");
                String title = jo3.getString("title");
                String litpic = "http://www.3dmgame.com"+jo3.getString("litpic");
                News news = new News(title,litpic);
                list.add(news);
               // Log.i("12345",news.toString());
            }
            return list;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
