package com.micro.tremolo.inflood;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.alibaba.fastjson.JSON;
import com.micro.network.NetworkManager;
import com.micro.network.available.model.EmptyEntity;
import com.micro.root.Logger;
import com.micro.root.broadcast.BaseBroadcastReceiver;
import com.micro.root.utils.Lang;
import com.micro.tremolo.ApiService;
import com.micro.tremolo.sqlite.table.UserModelTable;
import com.micro.tremolo.sqlite.table.VideoModelTable;

import static com.micro.tremolo.inflood.inner.execute.Deploy.netLogger;

public class DataBroadcast extends BaseBroadcastReceiver {

    private static final Logger logger = Logger.getLogger("Execute-Log-Broadcast");

    private static final String ACTION_TREMOLO_VIDEO = "com.prosecute.tremolo.video";
    private static final String NAME_TREMOLO_VIDEO = "prosecute.tremolo.video";
    private static final String ACTION_TREMOLO_USER = "com.prosecute.tremolo.user";
    private static final String NAME_TREMOLO_USER = "prosecute.tremolo.user";

    public static void registerReceiver(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_TREMOLO_VIDEO);
        intentFilter.addAction(ACTION_TREMOLO_USER);
        context.registerReceiver(new DataBroadcast(), intentFilter);
        logger.i("广播注册成功");
    }

    public static void sendVideo(Context context, VideoModelTable videoTable) {
        Intent intent = new Intent();
        intent.setAction(ACTION_TREMOLO_VIDEO);
        intent.putExtra(NAME_TREMOLO_VIDEO, videoTable);
        context.sendBroadcast(intent);
        //logger.d("视频 发送成功");
    }

    public static void sendUser(Context context, UserModelTable userTable) {
        Intent intent = new Intent();
        intent.setAction(ACTION_TREMOLO_USER);
        intent.putExtra(NAME_TREMOLO_USER, userTable);
        context.sendBroadcast(intent);
        //logger.d("用户 发送成功");
    }

    @Override
    protected void receive(Intent intent) {
        String action = intent.getAction();
        if (Lang.isEquals(action, ACTION_TREMOLO_VIDEO)) {
            saveTremoloVideo((VideoModelTable) intent.getSerializableExtra(NAME_TREMOLO_VIDEO));
        } else if (Lang.isEquals(action, ACTION_TREMOLO_USER)) {
            saveTremoloUser((UserModelTable) intent.getSerializableExtra(NAME_TREMOLO_USER));
        }
    }

    private synchronized void saveTremoloVideo(VideoModelTable videoTable) {
        uploadVideo(videoTable);
    }

    private synchronized void saveTremoloUser(UserModelTable userTable) {
        uploadUser(userTable);
    }

    private synchronized void uploadVideo(final VideoModelTable videoModelTable) {
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

    private synchronized void uploadUser(final UserModelTable userModelTable) {
        netLogger.i("用户上传 - " + JSON.toJSONString(userModelTable));
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
}