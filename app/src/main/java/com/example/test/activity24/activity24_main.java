package com.example.test.activity24;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.test.MainActivity;
import com.example.test.R;
import com.example.test.util.baseactivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

public class activity24_main extends baseactivity implements View.OnClickListener {

    private Button act24_sendnotice;
    private Button act24_camera;
    private Uri imageUri;
    private static final int TAKE_PHOTO = 1;
    private static final int CHOOSE_PHOTO = 2;
    private ImageView act24_picture;
    private Button act24_photo;


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
                break;
            case CHOOSE_PHOTO:

                if (resultCode == RESULT_OK) {

                   // getpicture(data);
                    Uri uri = data.getData();
                    act24_picture.setImageURI(uri);
                }
                break;
            default:
                break;
        }
    }

    private void getpicture(Intent data) {
        String imagepath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this, uri)) {
            String docid = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docid.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagepath = getimgepath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contenturi = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docid));
                imagepath = getimgepath(contenturi, null);
            }else if("content".equalsIgnoreCase(uri.getScheme())){
                imagepath=getimgepath(uri,null);
            }else if("file".equalsIgnoreCase(uri.getScheme())){
                imagepath=uri.getPath();
            }
            if(imagepath!=null){
                Bitmap bp=BitmapFactory.decodeFile(imagepath);
                act24_picture.setImageBitmap(bp);
            }
            else
                Toast.makeText(this, "777", Toast.LENGTH_SHORT).show();
        }
    }

    private String getimgepath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst())
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            cursor.close();
        }
        return path;
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
                //创建一个file对象，存放图片，存放在关联缓存目录下，
                //  /sdcard/Android/data/<package name>/cache。否则就要动态申请权限
                File outputimage = new File(getExternalCacheDir(), "66.jpg");
                try {
                    if (outputimage.exists()) {
                        outputimage.delete();
                    }
                    outputimage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
//转化uri
                if (Build.VERSION.SDK_INT >= 24) {
                    imageUri = FileProvider.getUriForFile(activity24_main.this, "com.example.test.fileprovider", outputimage);
                } else {
                    imageUri = Uri.fromFile(outputimage);
                }
                //启动摄像头
                Intent intent1 = new Intent("android.media.action.IMAGE_CAPTURE");
                intent1.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent1, TAKE_PHOTO);
                break;
            case R.id.act24_picture:
                act24_picture.setImageResource(0);
                break;
            case R.id.act24_photo:
                if (ContextCompat.checkSelfPermission(activity24_main.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                        PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(activity24_main.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    openalbum();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openalbum();
                } else {
                    Toast.makeText(this, "你没有权限", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    private void openalbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);
    }

    private void initView() {
        act24_sendnotice = (Button) findViewById(R.id.act24_sendnotice);
        act24_sendnotice.setOnClickListener(this);
        act24_camera = (Button) findViewById(R.id.act24_camera);
        act24_camera.setOnClickListener(this);
        act24_picture = (ImageView) findViewById(R.id.act24_picture);
        act24_picture.setOnClickListener(this);
        act24_photo = (Button) findViewById(R.id.act24_photo);
        act24_photo.setOnClickListener(this);
    }
}
