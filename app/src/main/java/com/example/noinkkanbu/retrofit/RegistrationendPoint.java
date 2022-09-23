package com.example.noinkkanbu.retrofit;


import com.example.noinkkanbu.pojo.LoginRegistration;
import com.example.noinkkanbu.pojo.Registration;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RegistrationendPoint {

    @GET("/registration/list")
    Call<List<Registration>> getAllData();

    @GET("/Registration/{id}")
    Call<Optional<Registration>>  getDataById(@Path("id") int id);

    @POST("/registration/add")
    Call<Map> putNewDataOnDb(@Body Registration registration);

    @POST("/registration/login/add")
    Call<Map> putNewDataOnDb(@Body LoginRegistration loginRegistration);

    @PUT("/Registration/update")
    Call<Map> updateDataOnDb(@Body Registration registration);

    @DELETE("/Registration/delete")
    Call<Map> deleteDataOnDb(@Body Registration registration);




}
