package com.micro.hook.plugin;

import android.content.Context;

import com.micro.hook.config.Hook;

/**
 * created by kilin on 20-3-18 上午9:48
 */
public abstract class Plugin<P extends PluginPresenter<I>, I extends PluginInter> {

    private final Hook hook;
    protected final P presenter;

    protected Plugin(Hook hook, Context context) throws Throwable {
        this.hook = hook;
        this.presenter = getPresenter();
        if (this.presenter != null) {
            this.presenter.setContext(context);
            this.presenter.setClass((I) this);
        }
    }

    protected Hook getHook() {
        return hook;
    }

    protected abstract P getPresenter();
}