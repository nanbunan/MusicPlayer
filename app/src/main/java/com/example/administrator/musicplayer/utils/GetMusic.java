package com.example.administrator.musicplayer.utils;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import com.example.administrator.musicplayer.bean.Music;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/5.
 */

public class GetMusic {
    private  List<Music> musics;
    private Music music;
    private Context context;
    public GetMusic(Context context){
        this.context=context;
    }
    public  List<Music> loadmusic(){
        musics=new ArrayList<>();
        Cursor cursor=context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,null,null,null,null);
        if(cursor!=null){
            while (cursor.moveToNext()){
                music=new Music();
                String title=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                long duration=cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
                String path=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                String artist=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                String size=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE));
                music.setTitle(title);
                music.setArtist(artist);
                music.setDuration(duration);
                music.setPath(path);
                musics.add(music);
                Log.d("信息",title+duration+path+artist+size+"");
            }
        }
        return musics;
    }
    //传入的数据为毫秒数
    public static String formattime(long time){
        String min=  (time/(1000*60))+"";
        String second= (time%(1000*60)/1000)+"";
        if(min.length()<2){
            min=0+min;
        }
        if(second.length()<2){
            second=0+second;
        }
       return min+":"+second;
    }
}
