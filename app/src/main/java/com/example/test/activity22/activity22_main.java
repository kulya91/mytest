package com.example.test.activity22;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import com.example.test.R;
import com.example.test.item.baseactivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class activity22_main extends baseactivity {
    private List<Contact> contactList = new ArrayList<>();
    private static final String TAG = "activity22_main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity22_main);
        /*********************动态申请权限********************************/
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_CONTACTS}, 1);
        } else
            initContact();

        RecyclerView recyclerView = findViewById(R.id.contact_recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ContactAdapter adapter = new ContactAdapter(contactList);
        recyclerView.setAdapter(adapter);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initContact();
                } else
                    Toast.makeText(this, "无法读取到联系人\n因为你没有读取联系人权限", Toast.LENGTH_SHORT).show();
        }
    }

    private void initContact() {
        Cursor cursor = null;
        try {
            //查询table的中的数据至cursor
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null, null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                   //从cursor提取数据 name，num
                    String name = cursor.getString(cursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String num = cursor.getString(cursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER));
                    contactList.add(new Contact(name, num));
                }
            }
        } catch (Exception e) {
            Log.d(TAG, "initContact: 读取联系人失败");

        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }


}
