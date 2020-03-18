package com.micro.hook.plugin;

import android.content.Context;

/**
 * created by kilin on 20-3-18 上午9:49
 */
public abstract class PluginPresenter<Inter extends PluginInter> {
    private Inter clazz;

    public Inter getClazz() {
        return clazz;
    }

    void setClazz(Inter clazz) throws Throwable {
        if (clazz == null) {
            throw new Throwable("clazz cannot be null");
        }
        this.clazz = clazz;
        this.onAttached();
    }

    private Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public abstract void onAttached();
}