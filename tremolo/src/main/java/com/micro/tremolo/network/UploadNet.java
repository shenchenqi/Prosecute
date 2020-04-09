package com.micro.tremolo.network;

import android.content.Context;

import com.micro.network.InitNetwork;
import com.micro.network.NetworkManager;
import com.micro.network.available.model.ApiResponseBase;
import com.micro.network.available.model.EmptyEntity;
import com.micro.root.Logger;
import com.micro.tremolo.inflood.inner.execute.monitor.oversee.OverseeInter;
import com.micro.tremolo.sqlite.table.UserIdModelTable;
import com.micro.tremolo.sqlite.table.UserModelTable;
import com.micro.tremolo.sqlite.table.VideoListModelTable;
import com.micro.tremolo.sqlite.table.VideoModelTable;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @Author KiLin
 * @Time 2020/4/6 9:53
 */
public class UploadNet {
    private static Logger netLogger = com.micro.root.Logger.getLogger("tremoloLog", "NetLog");

    private static Context context;

    public static void initNet(Context context) {
        UploadNet.context = context;
        InitNetwork.loadNetwork(context, ApiService.class);
    }

    public static synchronized void uploadVideo(final VideoModelTable videoModelTable) {
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
        modelNet.setCall(((ApiService) modelNet.getClazz()).tremoloVideo(videoModelTable));
        NetworkManager.setNetwork(modelNet);
    }

    public static synchronized void uploadUser(final UserModelTable userModelTable) {
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
        modelNet.setCall(((ApiService) modelNet.getClazz()).tremoloUser(userModelTable));
        NetworkManager.setNetwork(modelNet);
    }

    public static synchronized void isUserExist(UserIdModelTable userId, final OverseeInter inter) {
        NetworkManager.ModelNet modelNet = new NetworkManager.ModelNet<ApiService, EmptyEntity>(context) {
            @Override
            protected void success(EmptyEntity model, String message) {
                netLogger.d("用户检测 - 已存在 " + message);
                inter.profileExist(true);
            }

            @Override
            protected void fail(String code, String message) {
                netLogger.e("用户检测 - 不存在 " + code + " " + message);
                inter.profileExist(false);
            }
        };
        modelNet.setClazz(ApiService.class);
        modelNet.setCall(((ApiService) modelNet.getClazz()).tremoloUserExist(userId));
        NetworkManager.setNetwork(modelNet);
    }

    public static synchronized void uploadVideoList(final VideoListModelTable videoListTable) {
        NetworkManager.ModelNet modelNet = new NetworkManager.ModelNet<ApiService, EmptyEntity>(context) {
            @Override
            protected void success(EmptyEntity model, String message) {
                netLogger.d("视频列表上传 - 成功 " + message);
            }

            @Override
            protected void fail(String code, String message) {
                for (VideoModelTable videoModelTable : videoListTable.getVideoModelTableList()) {
                    netLogger.e("视频列表上传 数据 " + videoModelTable.toString());
                }
                netLogger.e("视频列表上传 - 失败 " + code + " " + message);
            }
        };
        modelNet.setClazz(ApiService.class);
        modelNet.setCall(((ApiService) modelNet.getClazz()).tremoloVideoList(videoListTable));
        NetworkManager.setNetwork(modelNet);
    }

    private interface ApiService {
        /**
         * 主播登录
         *
         * @return
         */
        @POST("/Media/Dy/uadd")
        Call<ApiResponseBase<EmptyEntity>> tremoloUser(@Body UserModelTable userModelTable);

        /**
         * 检测是否已入库
         *
         * @return
         */
        @POST("/Media/Dy/uexists")
        Call<ApiResponseBase<EmptyEntity>> tremoloUserExist(@Body UserIdModelTable user);

        /**
         * 视频上传
         *
         * @return
         */
        @POST("/Media/Dy/vadd")
        Call<ApiResponseBase<EmptyEntity>> tremoloVideo(@Body VideoModelTable videoModelTable);

        /**
         * 视频上传列表
         *
         * @return
         */
        @POST("/Media/Dy/vadds")
        Call<ApiResponseBase<EmptyEntity>> tremoloVideoList(@Body VideoListModelTable videoListModelTable);
    }
}