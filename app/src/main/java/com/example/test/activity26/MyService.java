package com.example.test.activity26;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import com.example.test.MainActivity;
import com.example.test.R;

import androidx.core.app.NotificationCompat;

public class MyService extends Service {
    private DownloadBinder mBinder = new DownloadBinder();

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mBinder;
        // throw new UnsupportedOperationException("Not yet implemented");

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("service", "onCreate: ");
        Intent intent=new Intent(this, MainActivity.class);
        PendingIntent pi=PendingIntent.getActivity(this,0,intent,0);
        //创建渠道
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
           NotificationChannel notificationChannel = new NotificationChannel("default", "name", NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(notificationChannel);
        }
        Notification notification = new NotificationCompat.Builder(this, "default")
                .setContentTitle("我是标题！")//设置标题
                .setContentText("我是内容")//设置内容
                .setWhen(System.currentTimeMillis())//设置显示通知被创建的时间
                .setSmallIcon(R.mipmap.ic_launcher)//设置通知的小图标
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))//设置通知的大图标
                .setContentIntent(pi)//设置intent
                .setAutoCancel(true)//通知自动取消
                .build();
        //显示通知，每条通知的id都要不同
      startForeground(1,notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("service", "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("service", "onDestroy: ");
    }

    class DownloadBinder extends Binder {
        public void startDownload() {
            Log.d("service", "startDownload: ");
        }

        public int getProgress() {
            Log.d("service", "getProgress: ");
            return 0;
        }
    }
}
