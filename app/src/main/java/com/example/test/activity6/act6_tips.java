package com.example.test.activity6;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.test.R;
import com.example.test.util.baseactivity;

public class act6_tips extends baseactivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity6_tips);
        Intent intent=getIntent();
        String str =intent.getStringExtra("name");
        TextView textView= findViewById(R.id.act6_tips_text);
        textView.setText(str);
    }
}
