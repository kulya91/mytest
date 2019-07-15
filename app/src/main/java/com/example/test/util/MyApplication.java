package com.example.test.util;

import android.app.Application;
import android.content.Context;

import org.litepal.LitePal;

/*
项目名称： test
创建人：黄大神
类描述：
创建时间：2019/7/15 11:20
*/
public class MyApplication extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        LitePal.initialize(context);
    }
    public static Context getContext(){
        return context;
    }
}
