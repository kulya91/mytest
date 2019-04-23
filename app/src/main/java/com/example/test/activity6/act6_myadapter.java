package com.example.test.activity6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.test.R;

import java.util.List;

public class act6_myadapter extends ArrayAdapter<picture> {
    private int resourceid;

    public act6_myadapter(Context context, int textViewResourceId,
                          List<picture> objects) {
        super(context, textViewResourceId, objects);
        resourceid = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        picture pic = getItem(position); //获取到具体某一行布局，即某一行的文字图片封装
        View view;
        viewholder viewholder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceid, parent, false);
            viewholder = new viewholder();
            viewholder.picture_src = view.findViewById(R.id.act6_list_image);
            viewholder.picture_num = view.findViewById(R.id.act6_list_text);
            view.setTag(viewholder);
        } else {
            view = convertView;
            viewholder = (viewholder) view.getTag();
        }
        viewholder.picture_num.setText(pic.getnum());
        viewholder.picture_src.setImageResource(pic.getsrc());

        return view;
    }

    private class viewholder {
        ImageView picture_src;
        TextView picture_num;
    }
}
/*
1、getView(int position,View converView,ViewGroup parent)


    int position位置，一般BaseAdapter都是很多类型一样的数据展示在界面，
        该属性是判断显示在界面上的是第几个，通过position在BaseAdapter自定义的数组或者集合中取值。并展示在界面上。
        View converView 展示在界面上的一个item。因为手机屏幕就那么大，
        所以一次展示给用户看见的内容是固定的，如果你List中有1000条数据，
        不应该new1000个converView，那样内存肯定不足，应该学会控件重用，
        滑出屏幕的converView就在下面新进来的item中重新使用，只是修改下他展示的值
        */
