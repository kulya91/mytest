package com.example.test.activity3;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.test.R;

import com.example.test.item.baseactivity;

public class activity_3 extends baseactivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity3_main);
        Button button1 = findViewById(R.id.act3_button1);
        Button button2 = findViewById(R.id.act3_button2);
        Button button3 = findViewById(R.id.act3_button3);
        Button button4 = findViewById(R.id.act3_button4);
        final ProgressBar progressBar = findViewById(R.id.act3_progressbar);
        final ImageView imageView = findViewById(R.id.act3_image);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView.setAdjustViewBounds(true);
                imageView.setImageResource(R.drawable.pp1);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  imageView.setImageResource(0);
                imageView.setImageDrawable(null);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (progressBar.getVisibility() == View.GONE)
                    progressBar.setVisibility(View.VISIBLE);
                else
                    progressBar.setVisibility(View.GONE);

            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                AlertDialog.Builder tips=new AlertDialog.Builder(activity_3.this);
//                tips.setTitle("嘤嘤嘤");
//                tips.setMessage("我是tips");
//                tips.show();
                Toast.makeText(view.getContext(),"999",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
