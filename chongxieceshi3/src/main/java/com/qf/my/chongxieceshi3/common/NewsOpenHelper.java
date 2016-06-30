package com.qf.my.chongxieceshi3.common;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by my on 2016/6/27.
 */
public class NewsOpenHelper extends SQLiteOpenHelper{
    public NewsOpenHelper(Context context) {
        super(context, "news3.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists news(_id integer primarry , title varchar(25)," +
                "litpic varchar(50))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
