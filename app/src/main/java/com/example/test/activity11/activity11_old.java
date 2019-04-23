package com.example.test.activity11;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.test.R;
import com.example.test.item.baseactivity;

public class activity11_old extends baseactivity implements View.OnClickListener {
    private mydatasql mydatasql;
    private static final String TAG = "activity11_old";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity11_main2);
        mydatasql = new mydatasql(this, "book.db", null, 3);
        Button addsql = findViewById(R.id.create_sql_book);
        Button adddata = findViewById(R.id.add_sqldata);
        Button removedata = findViewById(R.id.remove_sqldata);
        Button updata = findViewById(R.id.updata_sqldata);
        Button selectdata = findViewById(R.id.seclect_sqldata);
        adddata.setOnClickListener(this);
        addsql.setOnClickListener(this);
        removedata.setOnClickListener(this);
        updata.setOnClickListener(this);
        selectdata.setOnClickListener(this);
        Log.d(TAG, "onCreate: rerewrwe");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create_sql_book:
                mydatasql.getWritableDatabase();
                break;
            case R.id.add_sqldata:
                SQLiteDatabase db = mydatasql.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("name", "黄大神");
                values.put("num", "12");
                db.insert("chat", null, values);
                values.clear();
                values.put("name", "黄大神2");
                values.put("num", "20");
                db.insert("chat", null, values);
                break;
            case R.id.updata_sqldata:
                SQLiteDatabase db2 = mydatasql.getWritableDatabase();
                ContentValues values2 = new ContentValues();
                values2.put("num", "9999");
                db2.update("chat", values2, "name=?", new String[]{
                        "黄大神"
                });
                break;
            case R.id.remove_sqldata:
                SQLiteDatabase db3 = mydatasql.getWritableDatabase();
                db3.delete("chat", "num>?", new String[]{"21"});
            case R.id.seclect_sqldata:
                SQLiteDatabase db4 = mydatasql.getWritableDatabase();
                Cursor cursor = db4.query("chat", null, null, null, null, null, null);
                while (cursor.moveToNext()) {
                    Log.d("666", "name=" + cursor.getString(cursor.getColumnIndex("name"))
                            + "num=" + cursor.getInt(cursor.getColumnIndex("num")));
                }

                cursor.close();
                break;
            default:

        }
    }
}