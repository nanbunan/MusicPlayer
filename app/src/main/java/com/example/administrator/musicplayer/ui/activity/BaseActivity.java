package com.example.administrator.musicplayer.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2017/5/5.
 */

public abstract class BaseActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResultId());
        initView();
        initData();
        initListener();
    }
    public abstract int getResultId() ;
    public abstract void initView() ;
    public abstract void initData() ;
    public abstract void initListener() ;


}
