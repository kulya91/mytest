package com.example.test.activity8;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.test.R;
import com.example.test.util.baseactivity;

public class activity8_main extends baseactivity implements View.OnClickListener {
    String str;
    static Fragment fragment1;
    static Fragment fragment2;
    private boolean isTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity8_main);
        Button button=findViewById(R.id.activity8_left_button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.activity8_left_button:
                 if(str==null){
                     fragment1= new activity8_rightfragment();
               // replaceFragment(fragment1);
                str="fragment1";
            }
               else if(str.equals("fragment1")){
                    fragment2= new activity8_right2();
                   // replaceFragment(fragment2);
                   // removeFragment(fragment1);
                    str="fragment2";
                }
                else if(str.equals("fragment2")){
                    //fragment1= new activity8_rightfragment();
                   // replaceFragment(fragment1);
                  // removeFragment(fragment2);
                    str="fragment1";
                }
                break;
                default:
                    break;
        }
    }
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager=getSupportFragmentManager();//创建碎片实例
        FragmentTransaction transaction=fragmentManager.beginTransaction();//开启一个事务
        transaction.replace(R.id.activity8_main_layout,fragment);//向容器内添加碎片
        transaction.addToBackStack(null);//将事务添加到返回栈中
        transaction.commit();//提交事务
    }
//    private void removeFragment(Fragment fragment) {
//        FragmentManager fragmentManager=getSupportFragmentManager();//创建碎片实例
//        FragmentTransaction transaction=fragmentManager.beginTransaction();//开启一个事务
//        transaction.remove(fragment);//向容器内添加碎片
//        transaction.addToBackStack(null);//将事务添加到返回栈中
//        transaction.commit();//提交事务
//    }
}
