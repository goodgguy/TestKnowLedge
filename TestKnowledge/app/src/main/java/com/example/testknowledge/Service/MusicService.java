package com.example.testknowledge.Service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.testknowledge.R;

import java.io.IOException;

public class MusicService extends Service {
    MediaPlayer mediaPlayer;
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mediaPlayer=MediaPlayer.create(this, R.raw.music);
        mediaPlayer.start();
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        mediaPlayer.stop();
        mediaPlayer.release();
        super.onDestroy();
    }
}
