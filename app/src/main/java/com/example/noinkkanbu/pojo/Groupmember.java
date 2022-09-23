package com.example.noinkkanbu.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Groupmember {
    @SerializedName("id")
    @Expose
    private GroupmemberId id;

    public GroupmemberId getId() {
        return id;
    }

    public void setId(GroupmemberId id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Groupmember{" +
                "id=" + id +
                '}';
    }

    public Groupmember() {
        super();
    }
}
