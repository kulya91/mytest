package com.example.test.activity11;

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
创建时间：2019/4/25 19:07
*/
public class SqlAdapter extends RecyclerView.Adapter<SqlAdapter.myViewHolder>
        implements View.OnLongClickListener, View.OnClickListener {
    List<Book> mList;
    public OnItemClickListener mOnItemClickListener;
    public OnItemLongClickListener mOnItemLongClickListener;
    public interface OnItemClickListener {
        void ItemClick(View view, int position);
    }
    public interface OnItemLongClickListener {

        boolean onLongClick(View view, int position);
    }

public void setMOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener=mOnItemClickListener;

}
    public void setMOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener){
        this.mOnItemLongClickListener=mOnItemLongClickListener;

    }
    @Override
    public boolean onLongClick(View v) {
        return mOnItemLongClickListener != null &&
                mOnItemLongClickListener.onLongClick(v, (Integer) v.getTag());
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.ItemClick(v, (Integer) v.getTag());
        }
    }


    public SqlAdapter(List<Book> list) {
        mList = list;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.activity11_book, viewGroup, false);
        myViewHolder myViewHolder = new myViewHolder(view);
        view.setOnLongClickListener(this);
        view.setOnClickListener(this);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder myViewHolder, int i) {
        Book book = mList.get(i);
        myViewHolder.name.setText(book.getName());
        myViewHolder.jiage.setText(String.valueOf(book.getJiage()));
        myViewHolder.itemView.setTag(i);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView jiage;
        View itemView;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            name = itemView.findViewById(R.id.sqlbook_name);
            jiage = itemView.findViewById(R.id.sqlbook_jiage);
        }
    }
}
