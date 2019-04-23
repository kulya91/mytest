package com.example.test.activity4;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.test.R;

public class titlelayout extends LinearLayout {

    public titlelayout(Context context,  AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.activity4_title,this);
        Button button1=findViewById(R.id.title_button1);
        Button button2=findViewById(R.id.title_button2);
        TextView textView=findViewById(R.id.title_text);
        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity)getContext()).finish();
            }
        });

    }
}
