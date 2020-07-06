package com.example.github.modules.network;

import com.example.github.UserData;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface IApi {

    @GET
    Single<List<UserData>> getFollowers(@Url String url);

    @GET
    Single<UserData> getUser(@Url String url);


    //    @GET("users")
//    Single<List<User>> getUsers();

    @GET("users")
    Single<List<UserData>> getUsers(@Query("since") String id);
}