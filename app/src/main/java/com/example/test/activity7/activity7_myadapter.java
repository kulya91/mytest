package com.example.test.activity7;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.R;
import com.example.test.activity6.picture;

import java.util.List;
/*********************自定义适配器，继承自RecyclerView.Adapter，泛型为自定义内部类**************************/
public class activity7_myadapter extends RecyclerView.Adapter<activity7_myadapter.ViewHolder> {
    private List<picture> act7_list;
    /****************创建一个内部类，用作缓存*******************************/
    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView; //缓存类属性

        public ViewHolder(View view) { //构造方法，传入的view继承属性
            super(view);
            imageView = view.findViewById(R.id.act7_list_image);
            textView = view.findViewById(R.id.act7_list_text);
        }
    }
/******************act7adapter,将传入的list转存入act7_list*****************************/
    public activity7_myadapter(List<picture> list) {
        act7_list = list;
    }
    @NonNull
    @Override
    /*****************创建viewholder实例，在这里加载布局******************************/
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity7_list,
                viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"hhh",Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }
/********************对recyclerview子项数据进行赋值***************************/
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        picture picture = act7_list.get(i);
        viewHolder.imageView.setImageResource(picture.getsrc());
        viewHolder.textView.setText(picture.getnum());
    }
/*******************告诉recyclerview有多少子项****************************/
    @Override
    public int getItemCount() {
        return act7_list.size();
    }



}