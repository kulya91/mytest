package com.example.test.activity2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.test.R;
import com.example.test.item.baseactivity;

public class act2_1 extends baseactivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_1);
        final EditText editText=findViewById(R.id.act2_1text);
        Button button=findViewById(R.id.act2_1button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str=editText.getText().toString();
                if(!str.equals("")){
                    Intent intent=new Intent();
                    intent.putExtra("text","返回值："+str);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            }
        });
    }
}
