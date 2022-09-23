package com.example.noinkkanbu.retrofit;


import com.example.noinkkanbu.pojo.Elder;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ElderEndPoint {

//    @GET("/elder/list")
//    Call<List<Elder>> getAllData();
//
//    @GET("/elder/{id}")
//    Call<Optional<Elder>> getDataById(@Path("id") int id);
//
//    @POST("/elder/add")
//    Call <Map> putNewDataOnDb(@Body Elder elder);
//
//    @PUT("/elder/update")
//    Call <Map> updateDataOnDb(@Body Elder elder) throws Exception;
//
//    @DELETE("/elder/delete")
//    Call <Map> deleteDataOnDb(@Body Elder elder) throws Exception;
//
//    @GET("/userelder/{userid}")
//    Call<List<Elder>> getUserelder(@Path("userid") int userid);
    @GET("/api/v1/elders")
    Call<List<Elder>> getElders();

    @GET("/api/v1/{userid}/elders")
    Call<List<Elder>> getUserElders(@Path("userid") int userid);

    @POST("/api/v1/{userid}/elders")
    Call<String> saveElder(@Path("userid") int userid, @Body Elder elder);

//    @POST("/user_elder")
//    public UserElder addUserElder(@RequestBody UserElder userElder) {
//        return elderService.addUserElder(userElder);
//    }
}
