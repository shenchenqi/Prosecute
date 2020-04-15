package com.micro.tremolo.broad;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.micro.root.Logger;
import com.micro.root.broadcast.BaseBroadcastReceiver;
import com.micro.root.utils.Lang;
import com.micro.tremolo.model.from.Video;
import com.micro.tremolo.model.from.VideoArray;
import com.micro.tremolo.model.table.DataTable;
import com.micro.tremolo.model.table.UserTable;
import com.micro.tremolo.model.table.VideoTable;

/**
 * @Author KiLin
 * @Time 2020/4/15 11:36
 */
public class DataBroadcast extends BaseBroadcastReceiver {
    private static final Logger logger = Logger.getLogger("tremoloLog", "BroadcastLog");

    private static final String ACTION_TREMOLO_VIDEO_LIST = "com.prosecute.tremolo.video.list";
    private static final String NAME_TREMOLO_VIDEO_LIST = "prosecute.tremolo.video.list";
    private static final String ACTION_TREMOLO_USER = "com.prosecute.tremolo.user";
    private static final String NAME_TREMOLO_USER = "prosecute.tremolo.user";

    public static void registerReceiver(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_TREMOLO_VIDEO_LIST);
        intentFilter.addAction(ACTION_TREMOLO_USER);
        context.registerReceiver(new DataBroadcast(), intentFilter);
        logger.i("广播注册成功");
    }

    public static void sendVideoList(Context context, VideoArray videoArray) {
        Intent intent = new Intent();
        intent.setAction(ACTION_TREMOLO_VIDEO_LIST);
        intent.putExtra(NAME_TREMOLO_VIDEO_LIST, videoArray);
        context.sendBroadcast(intent);
    }

    public static void sendUser(Context context, UserTable userTable) {
        Intent intent = new Intent();
        intent.setAction(ACTION_TREMOLO_USER);
        intent.putExtra(NAME_TREMOLO_USER, userTable);
        context.sendBroadcast(intent);
    }

    @Override
    protected void receive(Intent intent) {
        String action = intent.getAction();
        if (Lang.isEquals(action, ACTION_TREMOLO_USER)) {
            saveTremoloUser((UserTable) intent.getSerializableExtra(NAME_TREMOLO_USER));
        } else if (Lang.isEquals(action, ACTION_TREMOLO_VIDEO_LIST)) {
            saveTremoloVideoList((VideoArray) intent.getSerializableExtra(NAME_TREMOLO_VIDEO_LIST));
        }
    }

    private synchronized void saveTremoloUser(UserTable userTable) {
        if (DataTable.saveOrUpdate(userTable, "userId=?", userTable.getUserId())) {
            logger.i(String.format("用户[%s %s] 保存 数据表 成功", userTable.getUserId(), userTable.getNickname()));
        } else {
            logger.i(String.format("用户[%s %s] 保存 数据表 失败", userTable.getUserId(), userTable.getNickname()));
        }
    }

    private synchronized void saveTremoloVideoList(VideoArray videoArray) {
        for (VideoTable videoTable : videoArray.getVideoTables()) {
            if (DataTable.saveOrUpdate(videoTable, "videoId=?", videoTable.getVideoId())) {
                logger.i(String.format("用户[%s %s] 视频[%s] 保存 数据表 成功", videoTable.getUserId(), videoTable.getNickname(), videoTable.getVideoId()));
            } else {
                logger.i(String.format("用户[%s %s] 视频[%s] 保存 数据表 失败", videoTable.getUserId(), videoTable.getNickname(), videoTable.getVideoId()));
            }
        }
    }
}