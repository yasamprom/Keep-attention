package com.up.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;

public class MyService extends Service {

    final String LOG_TAG = "myLogs";

    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "onCreate");
    }
    /*public void createNotification(){
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        int notifyId = 1;
        String channelId = "some_channel_id";

        Notification notification = new Notification.Builder(MyService.this)
                .setContentTitle("Shit")
                .setContentText("Notify you!")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setChannel(channelId)
                .build();

        notificationManager.notify(notifyId, notification);
    }*/

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, "onStartCommand");


        int importance = NotificationManager.IMPORTANCE_HIGH;
        String NOTIFICATION_CHANNEL_ID = "channel_id";
        String CHANNEL_NAME = "Notification Channel";

//        NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, CHANNEL_NAME, importance);
        if (android.os.Build.VERSION.SDK_INT < 26) {
            Intent notificationIntent = new Intent(this, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this,
                    0, notificationIntent, 0);

            Notification notification = new NotificationCompat.Builder(this, CHANNEL_NAME)
                    .setContentTitle("Example Service")
                    .setContentText("Notify you")
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentIntent(pendingIntent)
                    .build();

            startForeground(1, notification);
        }                                                                                           // write same code for sdk >= 26
        return START_NOT_STICKY;
    }



    public void onDestroy() {
        //super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
        Log.d(LOG_TAG, "Started again!");



    }

    public IBinder onBind(Intent intent) {
        Log.d(LOG_TAG, "onBind");
        return null;
    }

    void someTask() {
        while (true) {
            Date currentTime = Calendar.getInstance().getTime();
            Log.d(LOG_TAG, currentTime.toString());
            try
            {
                Thread.sleep(3000);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
        }
    }

}
