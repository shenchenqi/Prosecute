package com.micro.tremolo;

import com.micro.network.http3.filter.BaseBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

/**
 * created by kilin on 20-3-19 上午9:59
 */
public interface ApiService {

    @FormUrlEncoded
    @POST("/Media/Dy/uadd")
    Observable<BaseBean<Object>> uploadTremolo(@FieldMap Map<String, Object> userModelTable);


    @FormUrlEncoded
    @POST("/Media/Dy/vadd")
    Observable<BaseBean<Object>> uploadVideo(@Field("videoModelTable") String videoModelTable);
}