package com.up.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;

public class MyService extends Service {

    final String LOG_TAG = "myLogs";

    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "onCreate");
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, "onStartCommand");
        someTask();
        return START_STICKY;
        //return super.onStartCommand(intent, flags, startId);
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