package com.example.noinkkanbu.model;

import androidx.annotation.NonNull;

import com.google.firebase.Timestamp;
import com.google.type.DateTime;

public class Video {
    String url;
    Timestamp createDate;

    public String getUrl() {

        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Video(String url, Timestamp createDate) {
        this.url = url;
        this.createDate = createDate;
    }

    public Video() {
        super();
    }
}
