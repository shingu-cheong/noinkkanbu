package com.example.noinkkanbu.pojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Login {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("userEmail")
    @Expose
    private String userEmail;

    @SerializedName("userPassword")
    @Expose
    private String userPassword;


//    @SerializedName("loginAt")
//    @Expose
//    private LocalDateTime loginAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserEmail() {
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

//    public LocalDateTime getLoginAt() {
//        return loginAt;
//    }
//
//    public void setLoginAt(LocalDateTime loginAt) {
//        this.loginAt = loginAt;
//    }

    @Override
    public String toString() {
        return "Login{" +
                "id=" + id +
                ", userEmail='" + userEmail + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }

    public Login() {
        super();
    }


}
