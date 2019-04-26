package com.example.test.activity3;


import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.test.R;
import com.example.test.item.baseactivity;

public class activity_3 extends baseactivity implements View.OnClickListener {
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    ProgressBar progressBar;
    ImageView imageView;
    boolean isVisibility = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity3_main);
        button1 = findViewById(R.id.act3_button1);
        button3 = findViewById(R.id.act3_button3);
        button4 = findViewById(R.id.act3_button4);
        progressBar = findViewById(R.id.act3_progressbar);
        imageView = findViewById(R.id.act3_image);
        button1.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.act3_button1:
                if (isVisibility) {
                    imageView.setAdjustViewBounds(true);
                    imageView.setImageResource(R.drawable.pp1);
                    isVisibility = false;
                } else {
                    imageView.setImageResource(0);
                    isVisibility=true;
                }
                break;
            case R.id.act3_button3:
                if (progressBar.getVisibility() == View.GONE)
                    progressBar.setVisibility(View.VISIBLE);
                else
                    progressBar.setVisibility(View.GONE);
                break;
            case R.id.act3_button4:
                AlertDialog.Builder tips = new AlertDialog.Builder(activity_3.this);
                tips.setTitle("嘤嘤嘤");
                tips.setMessage("我是tips");
                tips.show();
                break;
            default:
        }
    }
}
