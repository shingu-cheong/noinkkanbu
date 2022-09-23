package com.example.noinkkanbu.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserElder {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("user")
    @Expose
    private User user;

    @SerializedName("elder")
    @Expose
    private Elder elder;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Elder getElder() {
        return elder;
    }

    public void setElder(Elder elder) {
        this.elder = elder;
    }

    @Override
    public String toString() {
        return "UserElder{" +
                "id=" + id +
                ", user=" + user +
                ", elder=" + elder +
                '}';
    }
}
