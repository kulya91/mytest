package com.example.test.activity27;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.test.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class activity27_main extends AppCompatActivity implements View.OnClickListener {

    private Button act27_start;
    private Button act27_pause;
    private Button act27_cancel;

    private DownloadService.DownloadBinder downloadBinder;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (DownloadService.DownloadBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    Toast.makeText(this, "没有权限无法使用", Toast.LENGTH_SHORT).show();
                    finish();
                }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity27_main);
        initView();
        Intent startIntent = new Intent(this, DownloadService.class);
        startService(startIntent);
        bindService(startIntent, connection, BIND_AUTO_CREATE);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

    }

    private void initView() {
        act27_start = (Button) findViewById(R.id.act27_start);
        act27_pause = (Button) findViewById(R.id.act27_pause);
        act27_cancel = (Button) findViewById(R.id.act27_cancel);

        act27_start.setOnClickListener(this);
        act27_pause.setOnClickListener(this);
        act27_cancel.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }

    /**
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.act27_start:
                downloadBinder.startDownload("https://minecraft.azureedge.net/bin-linux/bedrock-server-1.12.0.28.zipAhzj");
                break;
            case R.id.act27_pause:
                downloadBinder.pauseDownload();
                break;
            case R.id.act27_cancel:
                downloadBinder.cancelDownload();
                break;
        }
    }
}
