package com.example.noinkkanbu.pojo;


import java.io.Serializable;
import java.util.Objects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GroupmemberId implements Serializable {
    private static final long serialVersionUID = -3539146564183794740L;

    private Integer elderNum;


    private Integer userNum;

    public Integer getElderNum() {
        return elderNum;
    }

    public void setElderNum(Integer elderNum) {
        this.elderNum = elderNum;
    }

    public Integer getUserNum() {
        return userNum;
    }

    public void setUserNum(Integer userNum) {
        this.userNum = userNum;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
//        GroupmemberId entity = (GroupmemberId) o;
//        return Objects.equals(this.userNum, entity.userNum) &&
//                Objects.equals(this.elderNum, entity.elderNum);
//    }

    @Override
    public int hashCode() {
        return Objects.hash(userNum, elderNum);
    }

}