package com.example.test.activity26;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.test.R;
import com.example.test.util.baseactivity;

public class activity26_main extends baseactivity implements View.OnClickListener {

    private Button act26_download;
    private Button act26_start;
    private Button act26_top;
    private Button act26_bind;
    private Button act26_unbind;
    private MyService.DownloadBinder downloadBinder;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (MyService.DownloadBinder) service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    private Button act26_intentservice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity26_main);
        initView();
    }

    private void initView() {
        act26_download = (Button) findViewById(R.id.act26_download);
        act26_download.setOnClickListener(this);
        act26_start = (Button) findViewById(R.id.act26_start);
        act26_start.setOnClickListener(this);
        act26_top = (Button) findViewById(R.id.act26_top);
        act26_top.setOnClickListener(this);
        act26_bind = (Button) findViewById(R.id.act26_bind);
        act26_bind.setOnClickListener(this);
        act26_unbind = (Button) findViewById(R.id.act26_unbind);
        act26_unbind.setOnClickListener(this);
        act26_intentservice = (Button) findViewById(R.id.act26_intentservice);
        act26_intentservice.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.act26_download:
                new DownloadTask(this).execute();
                break;
            case R.id.act26_start:
                Intent startIntent = new Intent(this, MyService.class);
                startService(startIntent);
                break;
            case R.id.act26_top:
                Intent stopIntent = new Intent(this, MyService.class);
                startService(stopIntent);
                break;
            case R.id.act26_bind:
                Intent bindIntent = new Intent(this, MyService.class);
                bindService(bindIntent, connection, BIND_AUTO_CREATE);
                break;
            case R.id.act26_unbind:
                unbindService(connection);
                break;
            case R.id.act26_intentservice:
                Log.d("intentservice", "当前线程:："+Thread.currentThread().getId());
                Intent intentServiceIntent = new Intent(this, MyIntentService.class);
                startService(intentServiceIntent);
                break;
            default:
                break;
        }
    }

}
