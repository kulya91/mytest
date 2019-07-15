package com.example.test.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/*
项目名称： test
创建人：黄大神
类描述：网络连接公共类
创建时间：2019/7/15 10:20
*/
public class HttpUtil {
    public static void sendOkHttpRequest(String address,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
