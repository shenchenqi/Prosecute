package com.micro.tremolo;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * created by kilin on 20-3-19 上午9:59
 */
public interface ApiService {

    @FormUrlEncoded
    @POST("/uaa/post")
    Observable<Object> post(@Field("username") String username, @Field("password") String password);


    @FormUrlEncoded
    @GET("/uaa/post")
    Observable<Object> get(@Field("username") String username, @Field("password") String password);
}