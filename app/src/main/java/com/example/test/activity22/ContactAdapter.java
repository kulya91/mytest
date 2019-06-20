package com.example.test.activity22;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.test.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/*
项目名称： test
创建人：黄大神
类描述：
创建时间：2019/4/24 13:12
*/
/*自定义适配器继承自RecyclerView.Adapter泛型指定为自定义myviewgolder
myviewgolder继承自RecyclerView.ViewHolder
myviewgolder封装属性例如 name，num

*/
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.myViewHolder> {

private List<Contact> mList;
    static class myViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView num;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.contact_name);
            num = itemView.findViewById(R.id.contact_num); }
    }

    public ContactAdapter(List<Contact> list){ mList=list; }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity22_contact,
            viewGroup,false);
    myViewHolder viewHolder=new myViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder myViewHolder, int i) {
        Contact contact=mList.get(i);
        myViewHolder.name.setText(contact.getName());
        myViewHolder.num.setText(contact.getNum());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

}
