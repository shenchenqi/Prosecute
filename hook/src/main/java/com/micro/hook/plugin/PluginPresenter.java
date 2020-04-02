package com.micro.hook.plugin;

import android.content.Context;

import com.micro.root.mvp.BasePresenter;

/**
 * created by kilin on 20-3-18 上午9:49
 */
public abstract class PluginPresenter<Inter extends PluginInter> extends BasePresenter<Inter> {

    private Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}