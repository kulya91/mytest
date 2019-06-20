package com.example.test.activity11;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class mydatasql extends SQLiteOpenHelper {
    public static final String CREATE_BOOK = "create table book(" +
            " id integer primary key autoincrement," +
            "price real," +
            "name text)";
    public static final String CREATE_CHAT="create table chat(" +
            "id integer primary key autoincrement," +
            "name test," +
            "num integer)";
    private Context mContext;




    public mydatasql(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        db.execSQL(CREATE_CHAT);
        Toast.makeText(mContext, "创建成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
db.execSQL("drop table if exists book");
db.execSQL("drop table if exists chat");
onCreate(db);
    }
        }
