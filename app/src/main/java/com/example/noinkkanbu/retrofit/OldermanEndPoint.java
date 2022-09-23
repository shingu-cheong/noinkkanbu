package com.example.noinkkanbu.retrofit;


import com.example.noinkkanbu.pojo.Olderman;

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

public interface OldermanEndPoint {



    @GET("/olderman/list")
    Call <List<Olderman>> getAllData();

    @GET("/olderman/{manId}")
    Call <Optional<Olderman>> getDataById(@Path("manId") int manId);

    @GET("/olderman/myman/{manManager}")
    Call<List<Olderman>> getmyolderman(@Path("manManager") String manManager);

    @POST("/olderman/add")
    Call<Map> addOlderman(@Body Olderman olderman) ;

    @PUT("/olderman/update")
    Call<Map> updateOlderman(@Body Olderman olderman) throws Exception;

    @DELETE("/olderman/delete")
    Call<Map> deleteOlderman(@Body Olderman olderman) throws Exception;
}
