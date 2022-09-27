package com.example.noinkkanbu.model;



public class User {

    private String idToken;

    private String userName;

    private String userPh;

//    private String userImg;


    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
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


//    public String getUserImg() {
//        return userImg;
//    }
//
//    public void setUserImg(String userImg) {
//        this.userImg = userImg;
//    }


    public User() {
        super();
    }
}
