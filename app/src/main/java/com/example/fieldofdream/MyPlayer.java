package com.example.fieldofdream;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MyPlayer extends Service {
    private MediaPlayer player;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate(){
        player.setLooping(true);
    }

    @Override
    public void onDestroy(){
        player.stop();
    }

    public void onStart(){
        player.start();
    }
}
