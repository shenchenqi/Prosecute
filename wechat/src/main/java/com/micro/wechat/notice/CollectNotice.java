package com.micro.wechat.notice;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.micro.root.notice.BaseNoticeBar;
import com.micro.root.utils.Lang;
import com.micro.wechat.R;

/**
 * created by kilin on 20-1-2 下午2:39
 */
public class CollectNotice extends BaseNoticeBar {

    public static void createShowNotice(Context context, String title, String content) {
        new CollectNotice(context, String.format(Lang.isNotEmpty(title) ? "微信助手-(%s)" : "微信助手", title), content, null).createNotice();
    }

    public static void sendBroadcastNotice(Context context, String title, String content, Intent intent) {
        CollectNotice collectNotice = new CollectNotice(context, String.format(Lang.isNotEmpty(title) ? "微信助手-(%s)" : "微信助手", title), content, intent);
        collectNotice.createNotice();
    }

    private final String title;
    private final String content;
    private final Intent intent;

    private CollectNotice(Context context, String title, String content, Intent intent) {
        super(context);
        this.title = title;
        this.content = content;
        this.intent = intent;
        init();
    }

    @Override
    protected void initBar() {
        if (intent != null) {
            setContentIntent(getBroadcastIntent(intent));
        }
        setWhen(System.currentTimeMillis());
    }

    @Override
    protected int getLayoutID() {
        return 0;
    }

    @Override
    protected void initView(RemoteViews views) {
    }

    @Override
    protected String getTitle() {
        return title;
    }

    @Override
    protected String getText() {
        return content;
    }

    @Override
    protected int getIcon() {
        return R.drawable.ic_working;
    }

    @Override
    protected boolean isOngoing() {
        return true;
    }
}