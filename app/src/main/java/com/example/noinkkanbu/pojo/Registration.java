package com.example.noinkkanbu.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Registration {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("loginId")
    @Expose
    private int loginId;

    @SerializedName("fullname")
    @Expose
    private String fullname;

    @SerializedName("address1")
    @Expose
    private String address1;

    @SerializedName("address2")
    @Expose
    private String address2;

    @SerializedName("state")
    @Expose
    private String state;

    @SerializedName("city")
    @Expose
    private String city;

    @SerializedName("pincode")
    @Expose
    private String pincode;

    @SerializedName("gender")
    @Expose
    private String gender;

    @SerializedName("dob")
    @Expose
    private String dob;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLoginId() {
        return loginId;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Registration [id=" + id + ", loginId=" + loginId + ", fullname=" + fullname + ", address1=" + address1
                + ", address2=" + address2 + ", state=" + state + ", city=" + city + ", pincode=" + pincode
                + ", gender=" + gender + ", dob=" + dob + "]";
    }

    public Registration() {
        super();
    }



}

