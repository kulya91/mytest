package com.example.test.activity24;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.test.MainActivity;
import com.example.test.R;
import com.example.test.item.baseactivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.content.FileProvider;

public class activity24_main extends baseactivity implements View.OnClickListener {

    private Button act24_sendnotice;
    private Button act24_camera;
    private Uri imageUri;
    private static final int TAKE_PHOTO = 1;
    private ImageView act24_picture;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        act24_picture.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity24_main);
        initView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.act24_sendnotice:
                Toast.makeText(this, "666", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                //高版本需要渠道
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    //只在Android O之上需要渠道，这里的第一个参数要和下面的channelId一样
                    // id：String类型，NotificationChannel的id ，每个包必须是唯一的。如果值太长，可能会被截断
                    // name：CharSequence类型，NotificationChannel的用户可见名称，当系统区域（System locale）设置更改时，可以通过侦听Intent.ACTION_LOCALE_CHANGED广播，最大建议长度为40个字符，如果太长可能会被截断
                    // importance：int类型，NotificationChannel的重要性，它控制发送到这个通道的通知中断的方式，具体取值如下表所示
                    NotificationChannel notificationChannel = new NotificationChannel("default", "name", NotificationManager.IMPORTANCE_HIGH);
                    //如果这里用IMPORTANCE_NOENE就需要在系统的设置里面开启渠道，通知才能正常弹出
                    manager.createNotificationChannel(notificationChannel);
                }
                //创建Notification对象
                Notification notification = new NotificationCompat.Builder(this, "default")
                        .setContentTitle("我是标题！")//设置标题
                        .setContentText("我是内容")//设置内容
                        .setWhen(System.currentTimeMillis())//设置显示通知被创建的时间
                        .setSmallIcon(R.mipmap.ic_launcher)//设置通知的小图标
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))//设置通知的大图标
                        .setContentIntent(pendingIntent)//设置intent
                        .setAutoCancel(true)//通知自动取消
                        .build();
                //显示通知，每条通知的id都要不同
                manager.notify(1, notification);
                break;
            case R.id.act24_camera:
                File outputimage = new File(getExternalCacheDir(), "66.jpg");
                try {
                    if (outputimage.exists()) {
                        outputimage.delete();
                    }
                    outputimage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT >= 24) {
                    imageUri = FileProvider.getUriForFile(activity24_main.this, "com.example.test.fileprovider", outputimage);
                } else {
                    imageUri = Uri.fromFile(outputimage);
                }
                Intent intent1 = new Intent("android.media.action.IMAGE_CAPTURE");
                intent1.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent1, TAKE_PHOTO);
                break;
            case R.id.act24_picture:
                act24_picture.setImageResource(0);
                break;
            default:
                break;
        }
    }

    private void initView() {
        act24_sendnotice = (Button) findViewById(R.id.act24_sendnotice);
        act24_sendnotice.setOnClickListener(this);
        act24_camera = (Button) findViewById(R.id.act24_camera);
        act24_camera.setOnClickListener(this);

        act24_picture = (ImageView) findViewById(R.id.act24_picture);
        act24_picture.setOnClickListener(this);
    }
}
