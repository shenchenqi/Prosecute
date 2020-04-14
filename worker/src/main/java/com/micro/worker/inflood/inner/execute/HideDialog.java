package com.micro.worker.inflood.inner.execute;

import android.content.Context;
import android.os.Handler;

import com.micro.hook.config.Hook;
import com.micro.hook.plugin.PluginInter;

/**
 * @Author KiLin
 * @Time 2020/4/14 11:11
 */
public class HideDialog implements PluginInter {

    private final Hook hook;
    private final Context context;
    private final Handler handler;

    public HideDialog(Hook hook, Context context) {
        this.hook = hook;
        this.context = context;
        this.handler = new Handler(getIContext().getMainLooper());
    }

    @Override
    public void monitor() {

    }

    @Override
    public Context getIContext() {
        return context;
    }
}