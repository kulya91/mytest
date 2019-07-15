package com.example.test.activity9;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.test.R;
import com.example.test.util.baseactivity;

public class activity9_main extends baseactivity implements View.OnClickListener {
private IntentFilter intentFilter;
private MyReceiver myReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);setContentView(R.layout.activity9_main);
        Button button =findViewById(R.id.activity9_button);
        button.setOnClickListener(this);
        intentFilter=new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        myReceiver=new MyReceiver();
        registerReceiver(myReceiver,intentFilter);//注册广播接收器，将诶收两个参数
                                                  //第一个参数继承自broadcastreceiver,响应广播事件
                                                  //第二个参数注册要响应的事件
    }
    @Override
    public void onClick(View view) {
switch (view.getId()){
    case R.id.activity9_button:
        Intent intent=new Intent("com.example.test.MY_BROADCAST");
        intent.setComponent(new ComponentName("com.example.test",
                "com.example.test.activity9.act9_MyReceiver"));  //安卓8新特性，第一个参数广播接收者的包名
        sendBroadcast(intent);                         //第二个参数广播接收者的路径
}
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);//取消注册
    }

    /********************当网络发生变化就会执行onreceiver（）***************************/
    class MyReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager=(ConnectivityManager)  //系统服务类，专用于管理网络
                    getSystemService(context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
            if(networkInfo!=null&&networkInfo.isAvailable()){
                Toast.makeText(context,"网络开",Toast.LENGTH_SHORT)
                        .show();
            }
            else{
                Toast.makeText(context,"网络关",Toast.LENGTH_SHORT)
                        .show();
            }

        }
    }
}
