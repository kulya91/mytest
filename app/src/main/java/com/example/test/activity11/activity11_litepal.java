package com.example.test.activity11;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.test.R;
import com.example.test.item.baseactivity;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class activity11_litepal extends baseactivity implements View.OnClickListener,
        SqlAdapter.OnItemClickListener, SqlAdapter.OnItemLongClickListener {

    private List<Book> bookList = new ArrayList<>();
    private List<Book> books = new ArrayList<>();
    private List<List> selHistory = new ArrayList<>();
    private float jiage;
    private String name;

    private PopupWindow mPopWindow;
    private LinearLayoutManager layoutManager;
    private SqlAdapter adapter;
    private RecyclerView recyclerView;

    private Button adddata;
    private Button selectdata;
    private Button reselect;
    private Button backselect;
    private EditText editTextname;
    private EditText editTextjiage;

    private EditText menu_name;
    private EditText menu_jiage;
    private Button updata;
    private Button removedata;

    /**********************长按事件接口*******************************/
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity11_main3);

        editTextname = findViewById(R.id.ed_name);
        editTextjiage = findViewById(R.id.ed_jiage);
        adddata = findViewById(R.id.add_sqldata2);
        selectdata = findViewById(R.id.seclect_sqldata2);
        reselect = findViewById(R.id.updata_reseclect);
        backselect = findViewById(R.id.back_select);

        adddata.setOnClickListener(this);
        selectdata.setOnClickListener(this);
        reselect.setOnClickListener(this);
        backselect.setOnClickListener(this);
        //初始化
        LitePal.getDatabase();
        recyclerView = findViewById(R.id.sqlbook_recycleview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        init();
    }

    /**
     * recycle item点击事件
     *
     * @param view
     * @param position list id
     */
    @Override
    public void ItemClick(View view, final int position) {
        //设置contentView
        View contentView = LayoutInflater.from(this).inflate(R.layout.activity11_tips, null);
        mPopWindow = new PopupWindow(contentView,
                RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.WRAP_CONTENT, true);
        mPopWindow.setContentView(contentView);
        menu_name = contentView.findViewById(R.id.popmenu_name);
        menu_jiage = contentView.findViewById(R.id.popmenu_jiage);
        updata = contentView.findViewById(R.id.popmenu_updata);
        removedata = contentView.findViewById(R.id.popmenu_delate);
        updata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = menu_name.getText().toString();
                if (name.equals("")) {
                    Toast.makeText(activity11_litepal.this, "请输入书名", Toast.LENGTH_SHORT).show();
                    return;
                }
                /*************************捕捉异常****************************/
                try {
                    jiage = Float.parseFloat(menu_jiage.getText().toString());
                } catch (Exception e) {
                    Toast.makeText(activity11_litepal.this, "请输入正确的价格", Toast.LENGTH_SHORT).show();
                    return;
                }
                new Book(name, jiage).update(bookList.get(position).getId());
                bookList.set(position, new Book(name, jiage));
                Toast.makeText(activity11_litepal.this, "更改成功", Toast.LENGTH_SHORT).show();
                mPopWindow.dismiss();
                updateData();
            }
        });
        removedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePal.delete(Book.class, bookList.get(position).getId());
                bookList.remove(position);
                Toast.makeText(activity11_litepal.this, "删除成功", Toast.LENGTH_SHORT).show();
                mPopWindow.dismiss();
                updateData();
            }
        });
        menu_name.setText(bookList.get(position).getName());
        menu_jiage.setText(String.valueOf(bookList.get(position).getJiage()));
        //显示PopupWindow
        View rootview = LayoutInflater.from(this).inflate(R.layout.activity11_main3, null);
        mPopWindow.setAnimationStyle(R.style.contextMenuAnim);//设置动画

        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;
        getWindow().setAttributes(lp);
        mPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            //在dismiss中恢复透明度
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });

        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

    @Override
    public boolean onLongClick(View view, int position) {
        return false;
    }

    @Override
    public void onClick(View v) {
        Book book = new Book();
        switch (v.getId()) {
            case R.id.add_sqldata2:
                if (!getData())
                    break;
                book.setName(name);
                book.setJiage(jiage);
                book.save();

                bookList.add(new Book(name, jiage));
                adapter.notifyItemInserted(bookList.size() - 1);
                //跳转到最后一行
                recyclerView.smoothScrollToPosition(bookList.size() - 1);
                editTextjiage.setText("");
                editTextname.setText("");
                break;
            case R.id.seclect_sqldata2:
                name = editTextname.getText().toString();
                if (name.equals("")) {
                    Toast.makeText(this, "请输入书名", Toast.LENGTH_SHORT).show();
                    break;
                }
                books = LitePal.where("name like ?", "%" + name + "%").find(Book.class);
                if (books != null) {
                    selHistory.add(books);
                    bookList = books;
                    updateData();
                }
                editTextjiage.setText("");
                break;
            case R.id.back_select:
                if (selHistory != null && selHistory.size() >= 2) {
                    bookList = selHistory.get(selHistory.size() - 2);
                    selHistory.remove(selHistory.size() - 1);
                    updateData();
                } else
                    Toast.makeText(this, "已到最初无法再返回", Toast.LENGTH_SHORT).show();
                break;
            case R.id.updata_reseclect:
                init();
                selHistory.clear();
                break;
            default:
        }
    }

    private boolean getData() {
        name = editTextname.getText().toString();
        if (name.equals("")) {
            Toast.makeText(this, "请输入书名", Toast.LENGTH_SHORT).show();
            return false;
        }
        /*************************捕捉异常****************************/
        try {
            jiage = Float.parseFloat(editTextjiage.getText().toString());
        } catch (Exception e) {
            Toast.makeText(this, "请输入正确的价格", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void updateData() {
        layoutManager.setStackFromEnd(true);
        adapter = new SqlAdapter(bookList);
        adapter.setMOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    /*****************************************************/
    private void init() {
        List<Book> initBookList;
        selHistory.clear();
        initBookList = LitePal.findAll(Book.class);
        if (initBookList != null)
            bookList = initBookList;
        selHistory.add(bookList);
        layoutManager.setStackFromEnd(true);//初始化聊天记录出现在最底部
        adapter = new SqlAdapter(bookList);
        adapter.setMOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
    }

}
