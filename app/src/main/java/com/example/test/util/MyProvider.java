package com.example.test.util;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.test.activity23.MyDatabaseHelper;

public class MyProvider extends ContentProvider {
    public static final int STUDENT_DIR = 0;
    public static final int STUDENT_ITEM = 1;
    public static final String AUTHORITY = "com.example.test.provider";
    private static UriMatcher uriMatcher;
    private MyDatabaseHelper dbHelper;

    /*********************静态代码块初始化********************************/
    /*
    uriMatcher的adduri方法，接受三个参数，分别将authority，path和一个自定义代码传进去；
    uriMatcher的match方法可以将一个uri传入，返回值是某个能匹配这个uri的自定义代码；
     */

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "student", STUDENT_DIR);
        uriMatcher.addURI(AUTHORITY, "student/#", STUDENT_ITEM);
    }

    public MyProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int deleterows = 0;
        switch (uriMatcher.match(uri)) {
            case STUDENT_DIR:
                deleterows = db.delete("student", selection, selectionArgs);
                break;
            case STUDENT_ITEM:
                String bookid = uri.getPathSegments().get(1);
                deleterows=db.delete("student","id=?",new String[]{bookid});
                break;
            default:
                break;
        }
        return deleterows;
        // Implement this to handle requests to delete one or more rows.
        //throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
            case STUDENT_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.test.provider.student";
            case STUDENT_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.test.provider.student";
                default:break;
        }
        return null;
       // throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Uri urireturn = null;
        switch (uriMatcher.match(uri)) {
            case STUDENT_DIR:
            case STUDENT_ITEM:
                long studentid = db.insert("student", null, values);
                urireturn = Uri.parse("content://" + AUTHORITY + "/student/" + studentid);
                break;
            default:
                break;
        }
        return urireturn;

    }

    /*
    初始化
     */
    @Override
    public boolean onCreate() {
        dbHelper = new MyDatabaseHelper(getContext(), "student.db", null, 2);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        //查询数据
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        switch (uriMatcher.match(uri)) {
            case STUDENT_DIR:
                cursor = db.query("student", projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case STUDENT_ITEM:
                String bookid = uri.getPathSegments().get(1);
                cursor = db.query("student", projection, "id=?", new String[]{bookid}, null, null, sortOrder);
                break;
            default:
                break;
        }
        return cursor;
        // TODO: Implement this to handle query requests from clients.
        //throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int updaterows = 0;
        switch (uriMatcher.match(uri)) {
            case STUDENT_DIR:
                updaterows = db.update("student", values, selection, selectionArgs);
                break;
            case STUDENT_ITEM:
                String bookid = uri.getPathSegments().get(1);
                updaterows = db.update("student", values, "id=?", new String[]{bookid});
                break;
            default:
                break;
        }
        return updaterows;
        //throw new UnsupportedOperationException("Not yet implemented");
    }
}
