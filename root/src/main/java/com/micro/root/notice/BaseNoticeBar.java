package com.micro.root.notice;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.RemoteViews;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;


/**
 * @Description: BaseNoticeBar
 * @Author: ALin
 * @CreateDate: 19-9-29
 */
public abstract class BaseNoticeBar {

    protected final Context context;
    private final NotificationManager manager;
    private final Builder builder;

    protected BaseNoticeBar(Context context) {
        this.context = context;
        this.builder = new Builder(context);
        this.manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    protected void init() {
        initContext();
        initPriority();
        initTitle();
        initText();
        initOngoing();
        initSmallIcon();
        initBar();
        initDefaults();
    }

    protected abstract void initBar();

    private RemoteViews getViews() {
        return new RemoteViews(context.getPackageName(), getLayoutID());
    }

    protected abstract int getLayoutID();

    protected abstract void initView(RemoteViews views);

    private void initContext() {
        if (getLayoutID() == 0) {
            return;
        }
        RemoteViews remoteViews = getViews();
        initView(remoteViews);
        builder.setContent(remoteViews);
    }

    protected int getPriority() {
        return NotificationCompat.PRIORITY_DEFAULT;
    }

    private void initPriority() {
        builder.setPriority(getPriority());
    }

    protected abstract String getTitle();

    private void initTitle() {
        builder.setTitle(getTitle());
    }

    protected abstract String getText();

    private void initText() {
        builder.setText(getText());
    }

    protected void setSubText(String subText) {
        builder.setSubText(subText);
    }

    protected void setWhen(long when) {
        builder.setWhen(when);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void setLargeIcon(Icon icon) {
        builder.setLargeIcon(icon);
    }

    protected abstract int getIcon();

    private void initSmallIcon() {
        builder.setSmallIcon(getIcon());
    }

    protected int getDefaults() {
        return Notification.DEFAULT_ALL;
    }

    private void initDefaults() {
        builder.setDefaults(getDefaults());
    }

    protected void setVibrate(long[] pattern) {
        builder.setVibrate(pattern);
    }

    protected void setLights(int argb, int onMs, int offMs) {
        builder.setLights(argb, onMs, offMs);
    }

    protected void setSound(Uri sound) {
        builder.setSound(sound);
    }

    protected boolean isOngoing() {
        return false;
    }

    private void initOngoing() {//持续的通知
        builder.setOngoing(isOngoing());
    }

    protected void setProgress(int max, int progress, boolean indeterminate) {//进度条
        builder.setProgress(max, progress, indeterminate);
    }

    protected int getPendingFlags() {
        return PendingIntent.FLAG_CANCEL_CURRENT;
    }

    protected int getCode() {
        return 0;
    }

    protected PendingIntent getBroadcastIntent(Intent intent) {
        return PendingIntent.getBroadcast(context, getCode(), intent, getPendingFlags());
    }

    protected PendingIntent getActivityIntent(Intent intent, Bundle options) {
        return PendingIntent.getActivity(context, getCode(), intent, getPendingFlags(), options);
    }

    protected PendingIntent getActivityIntent(Intent intent) {
        return PendingIntent.getService(context, getCode(), intent, getPendingFlags());
    }

    protected void addAction(int icon, CharSequence title, PendingIntent intent) {//按钮
        builder.addAction(icon, title, intent);
    }

    protected void setContentIntent(PendingIntent intent) {
        builder.setContentIntent(intent);
    }

    protected String getChannelID() {
        return this.getClass().getSimpleName();
    }

    protected String getChannelName() {
        return this.getClass().getName();
    }

    protected void createNotice() {
        manager.notify(getChannelName(), 1, builder.build(getChannelID(), getChannelName(), manager));
    }

    protected void cancelNotice() {
        if (isOngoing()) {
            return;
        }
        manager.cancel(getChannelName(), 1);
    }

    private class Builder {
        private final Notification.Builder builder;

        Builder(Context context) {
            this.builder = new Notification.Builder(context);
        }

        void setContent(RemoteViews views) {
            builder.setContent(views);
        }

        void setPriority(int priority) {
            builder.setPriority(priority);
        }

        void setTitle(String title) {
            builder.setContentTitle(title);
        }

        void setText(String text) {
            builder.setContentText(text);
        }

        void setSubText(String subText) {
            builder.setSubText(subText);
        }

        void setWhen(long when) {
            builder.setWhen(when);
        }

        void setSmallIcon(int icon) {
            builder.setSmallIcon(icon);
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        void setLargeIcon(Icon icon) {
            builder.setLargeIcon(icon);
        }

        void setDefaults(int defaults) {
            builder.setDefaults(defaults);
        }

        void setVibrate(long[] pattern) {
            builder.setVibrate(pattern);
        }

        void setLights(int argb, int onMs, int offMs) {
            builder.setLights(argb, onMs, offMs);
        }

        void setSound(Uri sound) {
            builder.setSound(sound);
        }

        void setOngoing(boolean ongoing) {//持续的通知
            builder.setOngoing(ongoing);
        }

        void setProgress(int max, int progress, boolean indeterminate) {//进度条
            builder.setProgress(max, progress, indeterminate);
        }

        void addAction(int icon, CharSequence title, PendingIntent intent) {//按钮
            builder.addAction(icon, title, intent);
        }

        void setContentIntent(PendingIntent intent) {
            builder.setContentIntent(intent);
        }

        Notification build(String channelID, String channelName, NotificationManager manager) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);
                manager.createNotificationChannel(channel);
                builder.setChannelId(channelID);
                return builder.build();
            } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                return builder.getNotification();
            } else {
                return builder.build();
            }
        }
    }
}