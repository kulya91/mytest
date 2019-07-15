package com.example.test.util;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class baseactivity extends AppCompatActivity {
    private  long lastClickTime;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitycollector.addactivity(this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        activitycollector.removeactivity(this);
    }

}
