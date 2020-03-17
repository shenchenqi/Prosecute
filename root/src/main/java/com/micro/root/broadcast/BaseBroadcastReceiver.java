package com.micro.root.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * @CreateDate: 2019/5/11 16:38
 */
public abstract class BaseBroadcastReceiver extends BroadcastReceiver {

    protected Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        receive(intent);
    }

    protected abstract void receive(Intent intent);
}