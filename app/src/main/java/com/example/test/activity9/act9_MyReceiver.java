package com.example.test.activity9;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class act9_MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
Toast.makeText(context,"我开机了",Toast.LENGTH_SHORT)
        .show();
    }
}
