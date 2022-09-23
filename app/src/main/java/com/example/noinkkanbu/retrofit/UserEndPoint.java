package com.example.noinkkanbu.retrofit;



import com.example.noinkkanbu.pojo.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface UserEndPoint {


//    @GET("/user/list")
//    Call<List<User>> getAllData() ;
//
//    @GET("/user/{id}")
//    Call<Optional<User>> getDataById(@Path("id") int id);
//
//    @GET("/user/check")
//    Call<Map> checkUserNameAndPassword(@Body User user) throws Exception;
//
//    @POST("/user/add")
//    Call<Map> putNewDataOnDb(@Body User user) ;
//
//
//    @PUT("/user/update")
//    Call<Map> updateDataOnDb(@Body User user) throws Exception;
//
//    @DELETE("/user/delete")
//    Call<Map> deleteDataOnDb(@Body User user) throws Exception;

    @GET("/api/v1/users")
    Call<List<User>> getUsers();

    @GET("/api/v1/users/{id}")
    Call<User> getSingleUser(@Path("id") int id);

    @GET("/api/v1/users/{email}/{password}")
    Call<User> findUserbyEmail(@Path("email") String email, @Path("password") String password) throws Exception;

    @POST("/api/v1/users")
    Call<User> saveUser(@Body User user);

    @PUT("/api/v1/users/{id}")
    Call<User> updateUser(@Path("id") int id,@Body User user);

}
