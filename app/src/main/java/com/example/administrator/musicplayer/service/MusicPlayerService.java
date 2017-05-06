package com.example.administrator.musicplayer.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.administrator.musicplayer.bean.Music;
import com.example.administrator.musicplayer.utils.GetMusic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/5.
 */

public class MusicPlayerService extends Service{
    private MediaPlayer mediaPlayer;
    private List<Music> musics;
    private final int defaultvalue=-1;
    private int currentposition=-1;
    MyBroadcastReceiver receiver;
    @Override
    public void onCreate() {
        super.onCreate();
        if(mediaPlayer==null){
            mediaPlayer=new MediaPlayer();
        }
        musics= new GetMusic(MusicPlayerService.this).loadmusic();
        receiver=new MyBroadcastReceiver();
        IntentFilter filter=new IntentFilter();
        filter.addAction("play");
        filter.addAction("pause");
        filter.addAction("prev");
        filter.addAction("next");
        registerReceiver(receiver,filter);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                next();
            }
        });
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
    private void play(int position){
        if(position!=currentposition){
            currentposition=position;
        }
        try {
            Log.d("控制帧",currentposition+"");
            Log.d("控制帧",currentposition+musics.size()+"");
           // Log.d("控制帧",currentposition+musics.get(position).getPath()+"");

            mediaPlayer.reset();
            mediaPlayer.setDataSource(musics.get(currentposition).getPath());
            mediaPlayer.prepare();

            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void pause(){
        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
    }
    public void next(){
        currentposition+=1;
        if(currentposition==musics.size()){
            currentposition=0;
        }
        play(currentposition);
    }
    public void prev(){
        currentposition-=1;
        if(currentposition<0){
            currentposition=musics.size()-1;
        }
        play(currentposition);
    }
    class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action=intent.getAction();
            int position=intent.getIntExtra("position",defaultvalue);
            Log.d("position",position+"");
            switch (action){
                case "play":
                    play(position);
                    break;
                case "pause":
                    pause();
                    break;
                case "next":
                    next();
                    break;
                case "prev":
                    prev();
                    break;
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
