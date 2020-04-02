package com.micro.tremolo;

import com.micro.tremolo.rep.AppApiResponseBase;
import com.micro.tremolo.rep.entity.EmptyEntity;
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
    Call<AppApiResponseBase<EmptyEntity>> tremoloUser(@Body UserModelTable userModelTable);

    /**
     * 视频上传
     *
     * @return
     */
    @POST("/Media/Dy/vadd")
    Call<AppApiResponseBase<EmptyEntity>> tremoloVideo(@Body VideoModelTable videoModelTable);
}