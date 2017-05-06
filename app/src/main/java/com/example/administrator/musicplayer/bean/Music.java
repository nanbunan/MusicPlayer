package com.example.administrator.musicplayer.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/5.
 */

public class Music implements Serializable{
    private String title;
    private String artist;
    private String path;
    private Long duration;
    private String size;

    public Music() {
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getPath() {
        return path;
    }

    public Long getDuration() {
        return duration;
    }

    public String getSize() {
        return size;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
