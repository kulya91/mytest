package com.example.test.activity11;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.test.R;
import com.example.test.item.baseactivity;

import org.litepal.LitePal;

import java.util.List;
import java.util.ListIterator;

public class activity11_litepal extends baseactivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity11_main3);
        Button addsql = findViewById(R.id.create_sql_book2);
        Button adddata = findViewById(R.id.add_sqldata2);
        Button removedata = findViewById(R.id.remove_sqldata2);
        Button updata = findViewById(R.id.updata_sqldata2);
        Button selectdata = findViewById(R.id.seclect_sqldata2);
        adddata.setOnClickListener(this);
        addsql.setOnClickListener(this);
        removedata.setOnClickListener(this);
        updata.setOnClickListener(this);
        selectdata.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Book book = new Book();
        List<Book> books = null;
        switch (v.getId()) {
            case R.id.create_sql_book2:
                LitePal.getDatabase();
                break;
            case R.id.add_sqldata2:

                book.setName("666");
                book.setJiage((float) (Math.random() * 100.0));
                book.save();
                break;
            case R.id.updata_sqldata2:
                books = LitePal.where("jiage>=?", "50").find(Book.class);
                if (books != null) {
                    ListIterator<Book> lit = books.listIterator();
                    while (lit.hasNext()) {
                        Book book1 = lit.next();
                        book1.setJiage(99.99f);
                        book1.save();
                    }
                }
                break;
            case R.id.remove_sqldata2:
                books = LitePal.where("jiage<?", "50").find(Book.class);
                if (books != null) {
                    ListIterator<Book> lit = books.listIterator();
                    while (lit.hasNext()) {
                        Book book1 = lit.next();
                        book1.delete();
                    }
                }
                break;
            case R.id.seclect_sqldata2:

                break;
            default:
        }
    }
}
