package com.example.test.activity6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.test.R;
import com.example.test.util.baseactivity;

import java.util.ArrayList;
import java.util.List;

public class activity_6 extends baseactivity {
private List<picture> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity6_main);
        initpicture();
        act6_myadapter myadapter=new act6_myadapter(activity_6.this,R.layout.activity6_list,
                list);
        ListView listView=findViewById(R.id.act6_listview);
        listView.setAdapter(myadapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                picture pic=list.get(i);
                Toast.makeText(activity_6.this,pic.getnum(),Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(activity_6.this,act6_tips.class);
                String data="本页面的代号是"+pic.getnum();
                intent.putExtra("name",data);
                startActivity(intent);
            }
        });

    }
    private void initpicture(){
        int a[]={R.drawable.p1,R.drawable.p2,R.drawable.p3,R.drawable.p4,R.drawable.p5,
                R.drawable.p6,R.drawable.p7,R.drawable.p8,R.drawable.p9,R.drawable.p10,
                R.drawable.p11,R.drawable.p12,R.drawable.p13,R.drawable.p14,R.drawable.p15,
                R.drawable.p16,R.drawable.p14,R.drawable.p18,R.drawable.p19,R.drawable.p20,
                R.drawable.p21,R.drawable.p22,R.drawable.p23,R.drawable.p24,R.drawable.p25,
                R.drawable.p26,R.drawable.p27,R.drawable.p28,R.drawable.p29,R.drawable.p30,
                R.drawable.p31,R.drawable.p32,R.drawable.p33,R.drawable.p34,R.drawable.p35,
                R.drawable.p36,R.drawable.p37,R.drawable.p38,R.drawable.p39,R.drawable.p40,

        };
      for(int i=0;i<40;i++){
          list.add(new picture(String.valueOf(i),a[i]));

      }

    }
}
