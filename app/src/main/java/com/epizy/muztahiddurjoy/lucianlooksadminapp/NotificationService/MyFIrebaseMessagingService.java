package com.epizy.muztahiddurjoy.lucianlooksadminapp.NotificationService;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.FileUriExposedException;

import com.epizy.muztahiddurjoy.lucianlooksadminapp.MainActivity;
import com.epizy.muztahiddurjoy.lucianlooksadminapp.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

public class MyFIrebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull @NotNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        setNotification(remoteMessage);
    }

    private void setNotification(RemoteMessage remoteMessage) {
        Map<String,String> data = remoteMessage.getData();
        String title = data.get("title");
        String content = data.get("content");
        Uri defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Intent resultIntent = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntentWithParentStack(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "Lucian Looks";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            @SuppressLint("WrongConstant") NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                    "Lucian Looks",
                    NotificationManager.IMPORTANCE_MAX);
            AudioAttributes attributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .build();
            channel.setDescription("Lucian Looks Admin App");
            channel.enableVibration(true);
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.setVibrationPattern(new long[]{0,1000,500,1000});
            channel.setSound(defaultSound,attributes);
            manager.createNotificationChannel(channel);
        }
        Random random = new Random();
        NotificationCompat.Builder  builder = new NotificationCompat.Builder(this,NOTIFICATION_CHANNEL_ID);
        builder.setAutoCancel(false)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("Lucian Looks")
                .setStyle(new NotificationCompat.BigTextStyle())
                .setContentTitle(title)
                .setContentText(content)
                .setSound(defaultSound)
                .setContentIntent(resultPendingIntent);
        if (!title.equals("Hello")) {
            manager.notify(random.nextInt(), builder.build());
        }
    }
}
