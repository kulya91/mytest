package com.example.test.activity10;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.test.R;
import com.example.test.util.baseactivity;

public class activity10_main extends baseactivity implements View.OnClickListener {
    private IntentFilter intentFilter;
    private  LocalReceiver localReceiver;
    private LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity10_main);
        localBroadcastManager=LocalBroadcastManager.getInstance(this);
        Button button=findViewById(R.id.activty10_button);
        button.setOnClickListener(this);
        intentFilter=new IntentFilter();
        intentFilter.addAction("com.example.test.HHH");
        localReceiver=new LocalReceiver();
        localBroadcastManager.registerReceiver(localReceiver,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.activty10_button:
                Intent intent=new Intent("com.example.test.HHH");
                intent.setComponent(new ComponentName("com.example.test",
                        "com.example.test.activity10.act10_MyReceiver"));
                localBroadcastManager.sendBroadcast(intent);
        }
    }

    class LocalReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context,"收到本地广播",Toast.LENGTH_SHORT)
                    .show();
        }
    }
}
