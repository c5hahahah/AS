package com.qf.my.chongxieceshi3.common;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.renderscript.Sampler;

import com.qf.my.chongxieceshi3.models.News;

/**
 * Created by my on 2016/6/27.
 */
public class Newshander  {
    private Context context;
    private NewsOpenHelper helper;
    public Newshander(Context context){
        this.context = context;
        helper = new NewsOpenHelper(context);
    }
    public synchronized void insertToSQL(News news){
        String title = news.getTitle();
        String litpic = news.getLitpic();
        Cursor cursor = getSQLCursor();
        while(cursor.moveToNext()){
            String ortitle = cursor.getString(cursor.getColumnIndex("title"));
            if(title.equals(ortitle)){
                return;
            }
        }
        SQLiteDatabase db = helper.getReadableDatabase();
        db.execSQL("insert into news(title,litpic) values ('"+title+"','"+litpic+"')");
        if(db!=null){
            db.close();
        }
    }
    /*
        返回数据库中所有数据的Cursor
     */
    public Cursor getSQLCursor(){
        Cursor cursor = null;
        SQLiteDatabase db = helper.getReadableDatabase();
        cursor = db.query("news",new String[]{"title","litpic"},null,null,null,null,null);
        return cursor;
    }
    /*
        更新数据
     */
        public void updata(String url,String path){
            SQLiteDatabase db = helper.getReadableDatabase();
            db.execSQL("update news set litpic='"+path+"' where litpic = '"+url+"'");
            if(db!=null){
                db.close();
        }
    }
}
