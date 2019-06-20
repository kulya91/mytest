package com.example.test.activity21;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.test.R;
import com.example.test.item.baseactivity;

import java.util.ArrayList;
import java.util.List;

public class activity21_main extends baseactivity implements View.OnClickListener {
    private static final String TAG = "activity21_main";

    private List<String> mPermissionList = new ArrayList<>();

    private String[] mPermission = new String[]{
            Manifest.permission.CALL_PHONE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity21_main);
        Button button = findViewById(R.id.act21_button);
        button.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.act21_button:
                mPermissionList.clear();
                for (String a : mPermission) {
                    if (ContextCompat.checkSelfPermission(activity21_main.this,
                            a) != PackageManager.PERMISSION_GRANTED) {
                        mPermissionList.add(a);
                    }
                }
                if (mPermissionList.size() > 0)
                    ActivityCompat.requestPermissions(activity21_main.this, mPermission, 100);
                else
                    Toast.makeText(this, "权限全被授权", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
    }

    private void call() {
        try {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:10086"));
            startActivity(intent);
        } catch (SecurityException e) {
            Log.d(TAG, "唤起通话失败");
        }
    }

    /**
     * 用户点击授权窗口后回调此方法
     *
     * @param requestCode  是我们自己定义的权限请求码
     * @param permissions  是我们请求的权限名称数组
     * @param grantResults 是我们在弹出页面后是否允许权限的标识数组，数组的长度对应的是权限名称数组的长度，数组的数据0表示允许权限，-1表示我们点击了禁止权限
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 100:
                if (grantResults.length == 2) {
                    for (int i : grantResults) {
                        if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this, permissions[i] + "权限授权成功", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(this, permissions[i] + "权限被拒绝", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            default:
        }
    }
}
