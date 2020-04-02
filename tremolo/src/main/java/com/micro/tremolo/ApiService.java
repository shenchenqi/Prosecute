package com.micro.tremolo;

import com.micro.network.available.model.ApiResponseBase;
import com.micro.network.available.model.EmptyEntity;
import com.micro.tremolo.sqlite.table.UserModelTable;
import com.micro.tremolo.sqlite.table.VideoModelTable;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * created by kilin on 20-3-19 上午9:59
 */
public interface ApiService {
    /**
     * 主播登录
     *
     * @return
     */
    @POST("/Media/Dy/uadd")
    Call<ApiResponseBase<EmptyEntity>> tremoloUser(@Body UserModelTable userModelTable);

    /**
     * 视频上传
     *
     * @return
     */
    @POST("/Media/Dy/vadd")
    Call<ApiResponseBase<EmptyEntity>> tremoloVideo(@Body VideoModelTable videoModelTable);
}