package com.micro.tremolo.network;

import android.content.Context;

import com.micro.network.InitNetwork;
import com.micro.network.NetworkManager;
import com.micro.network.available.model.ApiResponseBase;
import com.micro.network.available.model.EmptyEntity;
import com.micro.root.Logger;
import com.micro.tremolo.inflood.inner.execute.task.WideAreaTremolo;
import com.micro.tremolo.model.params.UserIdParam;
import com.micro.tremolo.model.params.UserParam;
import com.micro.tremolo.model.params.VideoArrayParam;
import com.micro.tremolo.model.params.VideoParam;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @Author KiLin
 * @Time 2020/4/6 9:53
 */
public class UploadNet {
    private static Logger netLogger = Logger.getLogger("TremoloLog", "NetLog");

    private static Context context;

    public static void initNet(Context context) {
        UploadNet.context = context;
        InitNetwork.loadNetwork(context, ApiService.class);
    }

    public static synchronized void uploadVideo(final VideoParam videoParam) {
        NetworkManager.ModelNet modelNet = new NetworkManager.ModelNet<ApiService, EmptyEntity>(context) {
            @Override
            protected void success(EmptyEntity model, String message) {
                netLogger.d("视频上传 - 成功 " + message);
            }

            @Override
            protected void fail(String code, String message) {
                netLogger.e("视频上传 - 失败 " + code + " " + message);
            }
        };
        modelNet.setClazz(ApiService.class);
        modelNet.setCall(((ApiService) modelNet.getClazz()).tremoloVideo(videoParam));
        NetworkManager.setNetwork(modelNet);
    }

    public static synchronized void uploadUser(final UserParam userParam) {
        NetworkManager.ModelNet modelNet = new NetworkManager.ModelNet<ApiService, EmptyEntity>(context) {
            @Override
            protected void success(EmptyEntity model, String message) {
                netLogger.d("用户上传 - 成功 " + message);
            }

            @Override
            protected void fail(String code, String message) {
                netLogger.e("用户上传 - 失败 " + code + " " + message);
            }
        };
        modelNet.setClazz(ApiService.class);
        modelNet.setCall(((ApiService) modelNet.getClazz()).tremoloUser(userParam));
        NetworkManager.setNetwork(modelNet);
    }

    public static synchronized void isUserExist(final UserIdParam userIdParam, final WideAreaTremolo.NetUserCallback inter) {
        NetworkManager.ModelNet modelNet = new NetworkManager.ModelNet<ApiService, EmptyEntity>(context) {
            @Override
            protected void success(EmptyEntity model, String message) {
                netLogger.d("用户检测 - 已存在 " + message);
                inter.profileExist(userIdParam.getUserId(), userIdParam.getSceUserId(), true);
            }

            @Override
            protected void fail(String code, String message) {
                netLogger.e("用户检测 - 不存在 " + code + " " + message);
                inter.profileExist(userIdParam.getUserId(), userIdParam.getSceUserId(), false);
            }
        };
        modelNet.setClazz(ApiService.class);
        modelNet.setCall(((ApiService) modelNet.getClazz()).tremoloUserExist(userIdParam));
        NetworkManager.setNetwork(modelNet);
    }

    public static synchronized void uploadVideoList(final VideoArrayParam videoArrayParam) {
        NetworkManager.ModelNet modelNet = new NetworkManager.ModelNet<ApiService, EmptyEntity>(context) {
            @Override
            protected void success(EmptyEntity model, String message) {
                netLogger.d("视频列表上传 - 成功 " + message);
            }

            @Override
            protected void fail(String code, String message) {
                netLogger.e("视频列表上传 - 失败 " + code + " " + message);
            }
        };
        modelNet.setClazz(ApiService.class);
        modelNet.setCall(((ApiService) modelNet.getClazz()).tremoloVideoList(videoArrayParam));
        NetworkManager.setNetwork(modelNet);
    }

    private interface ApiService {
        /**
         * 主播登录
         *
         * @return
         */
        @POST("/Media/Dy/uadd")
        Call<ApiResponseBase<EmptyEntity>> tremoloUser(@Body UserParam user);

        /**
         * 检测是否已入库
         *
         * @return
         */
        @POST("/Media/Dy/uexists")
        Call<ApiResponseBase<EmptyEntity>> tremoloUserExist(@Body UserIdParam userId);

        /**
         * 视频上传
         *
         * @return
         */
        @POST("/Media/Dy/vadd")
        Call<ApiResponseBase<EmptyEntity>> tremoloVideo(@Body VideoParam video);

        /**
         * 视频上传列表
         *
         * @return
         */
        @POST("/Media/Dy/vadds")
        Call<ApiResponseBase<EmptyEntity>> tremoloVideoList(@Body VideoArrayParam videoArray);
    }
}