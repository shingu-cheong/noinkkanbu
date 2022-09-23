package com.example.noinkkanbu.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class User {

    @SerializedName("id")
    @Expose
    private Integer id;


    @SerializedName("userName")
    @Expose
    private String userName;


    @SerializedName("userPh")
    @Expose
    private String userPh;

    @SerializedName("userEmail")
    @Expose
    private String userEmail;

    @SerializedName("userPassword")
    @Expose
    private String userPassword;

    @SerializedName("userImg")
    @Expose
    private String userImg;


//    @JoinTable(name = "user_elder",
//            joinColumns = { @JoinColumn(name = "user_num")},
//            inverseJoinColumns = {@JoinColumn(name = "elder_num")})
//    @SerializedName("userElders")
//    @Expose
//    private List<UserElder> userElders = new ArrayList<>();
//
//    public List<UserElder> getUserElders() {
//        return userElders;
//    }
//
//    public void setUserElders(List<UserElder> userElders) {
//        this.userElders = userElders;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPh() {
        return userPh;
    }

    public void setUserPh(String userPh) {
        this.userPh = userPh;
    }

    public String getUserEmail(String s) {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }
//
//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(LocalDateTime createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public LocalDateTime getUpdatedAt() {
//        return updatedAt;
//    }
//
//    public void setUpdatedAt(LocalDateTime updatedAt) {
//        this.updatedAt = updatedAt;
//    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userPh='" + userPh + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userImg='" + userImg + '\'' +
//                ", userElders=" + userElders +
                '}';
    }

    public User() {
        super();
    }


}
