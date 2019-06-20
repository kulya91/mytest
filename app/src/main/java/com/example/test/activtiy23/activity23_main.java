package com.example.test.activtiy23;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.test.R;
import com.example.test.item.baseactivity;

public class activity23_main extends baseactivity implements View.OnClickListener {
    private Button insert;
    private Button updata;
    private Button delete;
    private Button query;

    private String newid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity23_main);
        insert = findViewById(R.id.act23_insert);
        updata = findViewById(R.id.act23_updata);
        delete = findViewById(R.id.act23_delete);
        query = findViewById(R.id.act23_query);
        insert.setOnClickListener(this);
        updata.setOnClickListener(this);
        delete.setOnClickListener(this);
        query.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.act23_insert:
                Uri uri = Uri.parse("content://com.example.test.provider/student");
                ContentValues values = new ContentValues();
                values.put("score",95);
                values.put("name","黄大神");
                Uri newuri=getContentResolver().insert(uri,values);
                newid=newuri.getPathSegments().get(1);
                break;
            case R.id.act23_updata:
                Uri uri2 = Uri.parse("content://com.example.test.provider/student/"+newid);
                ContentValues values2 = new ContentValues();
                values2.put("score",94);
                values2.put("name","黄大神2");
                getContentResolver().update(uri2,values2,null,null);
                break;
            case R.id.act23_delete:
                Uri uri3 = Uri.parse("content://com.example.test.provider/student/"+newid);
                getContentResolver().delete(uri3,null,null);
                break;
            case R.id.act23_query:
                Uri uri4 = Uri.parse("content://com.example.test.provider/student");
                Cursor cursor=getContentResolver().query(uri4,null,null,null,null);
                if(cursor!=null){
                    while(cursor.moveToNext()){
                        String name=cursor.getString(cursor.getColumnIndex("name"));
                        String score=cursor.getString(cursor.getColumnIndex("score"));
                        String id=cursor.getString(cursor.getColumnIndex("id"));
                        Log.d("66", id);
                        Log.d("66", name);
                        Log.d("66", score);
                    }
                    cursor.close();
                }
                break;
            default:
                break;
        }
    }
}
