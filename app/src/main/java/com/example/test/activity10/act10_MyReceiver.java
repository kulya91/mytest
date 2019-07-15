package com.example.test.activity10;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.test.util.activitycollector;


public class act10_MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"666",Toast.LENGTH_SHORT)
                .show();
        activitycollector.finishall();
    }}
