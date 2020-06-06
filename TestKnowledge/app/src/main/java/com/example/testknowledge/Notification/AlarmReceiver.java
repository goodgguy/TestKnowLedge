package com.example.testknowledge.Notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class AlarmReceiver extends BroadcastReceiver {
    private NotificationManagerCompat notificationManagerCompat;
    String name;
    String difficulty;
    int categoryId;
    String categoryName;
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("Broadcast:   ", "Đây là broadcast");
        name = intent.getStringExtra("NameClock");
        difficulty = intent.getStringExtra("idDifficulty");
        categoryId = Integer.parseInt(intent.getStringExtra("idCate_Clock"));
        categoryName = intent.getStringExtra("Category_Name");
        Log.e("Data from broadCast", name + " " + difficulty + " " + categoryId);
        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification(name,difficulty,categoryName,categoryId,context);
        notificationHelper.getManager().notify(1, nb.build());
    }
}
