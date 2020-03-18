package com.micro.hook.plugin;

import com.micro.hook.config.Hook;

/**
 * created by kilin on 20-3-18 上午9:48
 */
public abstract class Plugin<P extends PluginPresenter<I>, I extends PluginInter> {

    protected final Hook hook;
    private final P presenter;

    protected Plugin(Hook hook) throws Throwable {
        this.hook = hook;
        this.presenter = getPresenter();
        if (this.presenter != null) {
            this.presenter.setClazz((I) this);
        }
    }

    protected abstract P getPresenter();
}