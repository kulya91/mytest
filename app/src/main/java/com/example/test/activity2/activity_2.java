package com.example.test.activity2;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.test.R;

import com.example.test.item.baseactivity;

public class activity_2 extends baseactivity {
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        TextView textView=findViewById(R.id.act2_text1);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String str = data.getStringExtra("text");
                    textView.setText(str);
                }
                break;
                default:
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_main);
        Button button = findViewById(R.id.act2_button1);
        Button button2 = findViewById(R.id.act2_button2);
        final EditText editText = findViewById(R.id.act2_edittext1);
        final TextView textView = findViewById(R.id.act2_text1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = editText.getText().toString();
                if (!str.equals("")) {
                    textView.setText(str);
                    editText.setText("");
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_2.this, act2_1.class);
                startActivityForResult(intent, 1);
            }
        });
    }
}
