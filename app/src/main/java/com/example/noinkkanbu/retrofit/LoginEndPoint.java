package com.example.noinkkanbu.retrofit;



import com.example.noinkkanbu.pojo.Login;
import com.example.noinkkanbu.pojo.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginEndPoint {


    @POST("/api/v1/user/check")
    Call<User> checkUser(@Body Login login);
}
