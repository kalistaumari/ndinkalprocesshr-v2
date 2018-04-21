package com.andinazn.sensordetectionv2;

/**
 * Created by calista on 18/04/2018.
 */

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.IBinder;



public class NotificationService extends Service {


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        notifiy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return super.onStartCommand(intent, flags, startId);
    }



    public void onDestroy() {

        Intent intent = new Intent("com.example.sample");
        intent.putExtra("yourvalue", "torestore");
        sendBroadcast(intent);
    }

    public void notifiy() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("RSSPullService");

        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(""));
        PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(), 0, myIntent, Intent.FLAG_ACTIVITY_NEW_TASK);
        Context context = getApplicationContext();

        Notification.Builder builder;
        builder = new Notification.Builder(context)
                .setContentTitle("Alert")
                .setContentText("Abnormality Detected")
                .setContentIntent(pendingIntent)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_launcher);

        Notification notification = builder.build();

        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);

        stopService(new Intent(this, MainActivity.class));

    }
}
