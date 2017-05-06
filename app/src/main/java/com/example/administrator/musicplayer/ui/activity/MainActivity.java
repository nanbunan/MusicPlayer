package com.example.administrator.musicplayer.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.administrator.musicplayer.R;
import com.example.administrator.musicplayer.adapter.MusicAdapter;
import com.example.administrator.musicplayer.bean.Music;
import com.example.administrator.musicplayer.service.MusicPlayerService;
import com.example.administrator.musicplayer.utils.GetMusic;

import java.util.List;

/**
 * Created by Administrator on 2017/5/5.
 */

public class MainActivity extends BaseActivity implements View.OnClickListener{
    Button btn_play_pause,btn_prev,btn_next;
    ListView lv_music;
    MusicAdapter adapter;
    List<Music> musics;
    private boolean isPlaying=false;
    private int position=0;
    @Override
    public int getResultId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        btn_next= (Button) findViewById(R.id.bt_next);
        btn_play_pause= (Button) findViewById(R.id.bt_play_pause);
        btn_prev= (Button) findViewById(R.id.bt_prev);
        lv_music= (ListView) findViewById(R.id.lv_music);

    }

    @Override
    public void initData() {
       musics=new GetMusic(this).loadmusic();
    }

    @Override
    public void initListener() {
        Intent intent=new Intent(this,MusicPlayerService.class);
        startService(intent);
        btn_prev.setOnClickListener(this);
        btn_play_pause.setOnClickListener(this);
        btn_next.setOnClickListener(this);
        adapter=new MusicAdapter(this,musics);
        lv_music.setAdapter(adapter);
        lv_music.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent();
                intent.setAction("play");
                intent.putExtra("position",i);
                isPlaying=true;
                btn_play_pause.setBackgroundResource(R.drawable.pause);
                sendBroadcast(intent);
                position=i;
            }
        });

    }

    @Override
    public void onClick(View view) {
        Intent intent=new Intent();
        switch (view.getId()){
            case R.id.bt_next:
                intent.setAction("next");
                sendBroadcast(intent);
                break;
            case R.id.bt_play_pause:
                if(isPlaying){
                    intent.setAction("pause");
                    sendBroadcast(intent);
                    btn_play_pause.setBackgroundResource(R.drawable.play);
                    isPlaying=false;

                }
                else {
                    intent.setAction("play");
                    intent.putExtra("position",position);
                    sendBroadcast(intent);

                    btn_play_pause.setBackgroundResource(R.drawable.pause);
                    isPlaying=true;
                }

                break;
            case R.id.bt_prev:
                intent=new Intent("prev");
                sendBroadcast(intent);
                break;
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            new MaterialDialog.Builder(MainActivity.this)
                    .title(getResources().getString(R.string.tip))
                    .content(getResources().getString(R.string.exit))
                    .negativeText(getResources().getString(R.string.cancel))
                    .onNegative((dialog, which) -> dialog.dismiss()).positiveText(getResources().getString(R.string.ok))
                    .onPositive((dialog, which) -> {
                        stopService(new Intent(MainActivity.this,MusicPlayerService.class));
                        finish();
                        dialog.dismiss();
                    }).show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
