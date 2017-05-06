package com.example.administrator.musicplayer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.musicplayer.R;
import com.example.administrator.musicplayer.bean.Music;
import com.example.administrator.musicplayer.utils.GetMusic;

import java.util.List;

/**
 * Created by Administrator on 2017/5/6.
 */

public class MusicAdapter extends BaseAdapter{
    private List<Music> musics;
    private Context context;
    public MusicAdapter(){};
    public MusicAdapter(Context context,List<Music> musics){
        this.context=context;
        this.musics=musics;
    }
    @Override
    public int getCount() {
        return musics.size();
    }

    @Override
    public Object getItem(int i) {
        return musics.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View myview;
        MusicAdapter.viewHolder holder;
        if(view==null){
            holder=new MusicAdapter.viewHolder();
            myview= LayoutInflater.from(context).inflate(R.layout.item_music_list,null);
            holder.textView1_title= (TextView) myview.findViewById(R.id.textView1_title);
            holder.textView2_singer= (TextView) myview.findViewById(R.id.textView2_singer);
            holder.textView3_timer= (TextView) myview.findViewById(R.id.textView3_timer);
            myview.setTag(holder);

        }
        else {
            myview= view;
            holder=(MusicAdapter.viewHolder)myview.getTag();
        }
        holder.textView1_title.setText(musics.get(i).getTitle());
        holder.textView3_timer.setText(GetMusic.formattime(musics.get(i).getDuration()));
        holder.textView2_singer.setText(musics.get(i).getArtist());
        return myview;
    }
    class viewHolder{
        TextView textView1_title,textView2_singer,textView3_timer;
    }
}
