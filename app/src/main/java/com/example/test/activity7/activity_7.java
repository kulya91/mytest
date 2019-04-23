package com.example.test.activity7;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.test.R;
import com.example.test.activity6.picture;
import com.example.test.item.baseactivity;

import java.util.ArrayList;
import java.util.List;

public class activity_7 extends baseactivity {
private List<picture> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity7_main);
        initpicture(); //初始化子项
        RecyclerView recyclerView=findViewById(R.id.act7_recyclerview);
        /********************创建布局格式***************************/
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        //linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        activity7_myadapter myadapter=new activity7_myadapter(list);//创建适配器
        recyclerView.setAdapter(myadapter);              //导入适配器
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
