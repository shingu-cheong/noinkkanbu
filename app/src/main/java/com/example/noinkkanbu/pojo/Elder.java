package com.example.noinkkanbu.pojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//@Getter
//@Setter
//@ToString

public class Elder {
    @SerializedName(value = "id")
    @Expose
    private Integer id;

    @SerializedName(value = "elderName")
    @Expose
    private String elderName;

    @SerializedName(value = "elderPh")
    @Expose
    private String elderPh;

    @SerializedName(value = "elderAdr")
    @Expose
    private String elderAdr;

    @SerializedName(value = "mngPh")
    @Expose
    private String mngPh;

    @SerializedName(value = "elderImg")
    @Expose
    private String elderImg;

//    @SerializedName(value = "users")
//    @Expose
//    private List<UserElder> users = new ArrayList<>();

    public String getElderImg() {
        return elderImg;
    }

    public void setElderImg(String elderImg) {
        this.elderImg = elderImg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

//    public List<UserElder> getUsers() {
//        return users;
//    }
//
//    public void setUsers(List<UserElder> users) {
//        this.users = users;
//    }

    @Override
    public String toString() {
        return "Elder{" +
                "id=" + id +
                ", elderName='" + elderName + '\'' +
                ", elderPh='" + elderPh + '\'' +
                ", elderAdr='" + elderAdr + '\'' +
                ", mngPh='" + mngPh + '\'' +
                ", elderImg='" + elderImg + '\'' +
//                ", users=" + users +
                '}';
    }

    public Elder() {
        super();
    }



}
