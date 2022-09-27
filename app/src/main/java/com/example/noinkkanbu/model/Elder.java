package com.example.noinkkanbu.model;


public class Elder {

//    private String id;

    private String elderName;

    private String elderPh;

    private String elderAdr;

    private String mngPh;

    private String elderImg;

    private String managerToken;


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

    public String getManagerToken() {
        return managerToken;
    }

    public void setManagerToken(String managerToken) {
        this.managerToken = managerToken;
    }

    public Elder() {
        super();

    }
}

