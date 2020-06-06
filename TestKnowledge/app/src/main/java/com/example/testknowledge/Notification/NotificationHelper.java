package com.example.testknowledge.Notification;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.example.testknowledge.MainActivity;
import com.example.testknowledge.QuizActivity;
import com.example.testknowledge.R;

public class NotificationHelper extends ContextWrapper {
    public static final String channelID = "channelID";
    public static final String channelName = "Channel Name";
    private NotificationManager mManager;
    Intent intent;
    PendingIntent contentIntent;
    public NotificationHelper(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
        }
    }
    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);
        getManager().createNotificationChannel(channel);
    }
    public NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }
    public NotificationCompat.Builder getChannelNotification(String name,String difficulty,String category,int idcategory,Context context) {
        Log.e("CONTEXT",context.getClass().getSimpleName());
        intent=new Intent(context, QuizActivity.class);
        intent.putExtra(MainActivity.EXTRA_CATEGORY_ID,idcategory);
        intent.putExtra(MainActivity.EXTRA_CATEGORY_NAME,category);
        intent.putExtra(MainActivity.EXTRA_DIFFICULTY,difficulty);
        intent.putExtra(MainActivity.EXTRA_NOTIFICATION,"TUANCUTE");
        intent.putExtra(MainActivity.EXTRA_NAME_NOTIFICATION,name);
        contentIntent=PendingIntent.getActivity(context,MainActivity.REQUEST_CODE_QUIZ,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        return new NotificationCompat.Builder(getApplicationContext(), channelID)
                .setContentTitle("ĐÃ ĐẾN GIỜ THI RỒI:")
                .setContentText("")
                .setStyle(new NotificationCompat.BigTextStyle()
                .bigText("Tên: "+name+
                        "\n"+"Lĩnh vực: "+category+
                        "\n"+"Độ khó: "+difficulty))
                .setContentIntent(contentIntent)
                .setSmallIcon(R.drawable.choice_icon)
                .setAutoCancel(true);
    }
}
