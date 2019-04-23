package com.example.test.activity4;

import android.os.Bundle;

import com.example.test.R;
import com.example.test.item.baseactivity;

public class activity_4 extends baseactivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity4_main);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
    }
}
