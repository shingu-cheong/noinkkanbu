package com.example.noinkkanbu.model;


import androidx.navigation.NavType;

import java.io.Serializable;

public class Elder implements Serializable {

//    private String id;

    private String elderName;

    private String elderPh;

    private String elderAdr;

    private String mngPh;

    private String bloodtype;

    private String et_managerRel;

    private String et_managername;

    private String elderImg;

    private String managerToken;

    private String elderUniq;

    public String getElderImg() {
        return elderImg;
    }

    public void setElderImg(String elderImg) {
        this.elderImg = elderImg;
    }

//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }

    public String getElderName() {
        return elderName;
    }

    public void setElderName(String elderName) {
        this.elderName = elderName;
    }

    public String getElderPh() {
        return elderPh;
    }

    public void setElderPh(String elderPh) {
        this.elderPh = elderPh;
    }

    public String getElderAdr() {
        return elderAdr;
    }

    public void setElderAdr(String elderAdr) {
        this.elderAdr = elderAdr;
    }

    public String getMngPh() {
        return mngPh;
    }

    public void setMngPh(String mngPh) {
        this.mngPh = mngPh;
    }

    public String getbloodtype() {
        return bloodtype;
    }

    public void setBloodtype(String bloodtype) {
        this.bloodtype = bloodtype;
    }

    public String getEt_managerRel() {
        return et_managerRel;
    }

    public void setEt_managerRel(String et_managerRel) {
        this.et_managerRel = et_managerRel;
    }

    public String getEt_managername() {
        return et_managername;
    }

    public void setEt_managername(String et_managername) {
        this.et_managername = et_managername;
    }

    public String getManagerToken() {
        return managerToken;
    }

    public void setManagerToken(String managerToken) {
        this.managerToken = managerToken;
    }

    public String getElderUniq() {
        return elderUniq;
    }

    public void setElderUniq(String elderUniq) {
        this.elderUniq = elderUniq;
    }

    public Elder() {
        super();

    }
}

