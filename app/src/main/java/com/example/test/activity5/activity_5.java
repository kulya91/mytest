package com.example.test.activity5;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.test.R;
import com.example.test.item.baseactivity;

public class activity_5 extends baseactivity {
    private String[] data={"1","2","3","4","5","6"
            ,"7","8","9","10","11","12","13","14"
            ,"14","15","16","17","18"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity5_main);
        ArrayAdapter<String> list=new ArrayAdapter<>
                (activity_5.this, android.R.layout.simple_list_item_1,data);
        ListView listView=findViewById(R.id.act5_listview);
       listView.setAdapter(list);
    }
}
