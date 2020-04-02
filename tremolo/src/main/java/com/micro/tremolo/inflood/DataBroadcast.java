package com.micro.tremolo.inflood;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.micro.tremolo.rep.AppApiResponseBase;
import com.micro.tremolo.rep.entity.EmptyEntity;
import com.micro.root.Logger;
import com.micro.root.broadcast.BaseBroadcastReceiver;
import com.micro.root.utils.Lang;
import com.micro.tremolo.ApiService;
import com.micro.tremolo.sqlite.table.UserModelTable;
import com.micro.tremolo.sqlite.table.VideoModelTable;
import com.so1spms.module.rpc.RPCApiFactory;
import com.so1spms.module.rpc.callback.BaseCallback;

import retrofit2.Call;
import retrofit2.Response;

import static com.micro.tremolo.inflood.inner.execute.Deploy.monitorLogger;

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
        //logger.d("视频发送成功");
    }

    public static void sendUser(Context context, UserModelTable userTable) {
        Intent intent = new Intent();
        intent.setAction(ACTION_TREMOLO_USER);
        intent.putExtra(NAME_TREMOLO_USER, userTable);
        context.sendBroadcast(intent);
        //logger.d("用户发送成功");
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
        //DataTable.saveOrUpdate(videoTable, "id = ?", videoTable.getId());
        //logger.d("视频入库成功");

        uploadVideo(videoTable);
    }

    private synchronized void saveTremoloUser(UserModelTable userTable) {
        //DataTable.saveOrUpdate(userTable, "userId = ?", userTable.getUserId());
        //logger.d("用户入库成功");

        uploadUser(userTable);
    }

    private void uploadVideo(final VideoModelTable videoModelTable) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ApiService api = RPCApiFactory.create(ApiService.class);
                Call<AppApiResponseBase<EmptyEntity>> call = api.tremoloVideo(videoModelTable);
                call.enqueue(new BaseCallback<AppApiResponseBase<EmptyEntity>>() {
                    @Override
                    public void onResponse(Call<AppApiResponseBase<EmptyEntity>> call, Response<AppApiResponseBase<EmptyEntity>> response) {
                        super.onResponse(call, response);
                        monitorLogger.d("视频上传 - 成功");
                    }

                    @Override
                    public void onFailure(Call<AppApiResponseBase<EmptyEntity>> call, Throwable t) {
                        super.onFailure(call, t);
                        monitorLogger.e(t, "视频上传 - 失败");
                    }
                });
            }
        }).start();
    }

    private void uploadUser(final UserModelTable userModelTable) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ApiService api = RPCApiFactory.create(ApiService.class);
                Call<AppApiResponseBase<EmptyEntity>> call = api.tremoloUser(userModelTable);
                call.enqueue(new BaseCallback<AppApiResponseBase<EmptyEntity>>() {
                    @Override
                    public void onResponse(Call<AppApiResponseBase<EmptyEntity>> call, Response<AppApiResponseBase<EmptyEntity>> response) {
                        super.onResponse(call, response);
                        monitorLogger.d("用户上传 - 成功");
                    }

                    @Override
                    public void onFailure(Call<AppApiResponseBase<EmptyEntity>> call, Throwable t) {
                        super.onFailure(call, t);
                        monitorLogger.e(t, "用户上传 - 失败");
                    }
                });
            }
        }).start();
    }
}