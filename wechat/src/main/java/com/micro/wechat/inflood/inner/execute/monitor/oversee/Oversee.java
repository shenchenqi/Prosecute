package com.micro.wechat.inflood.inner.execute.monitor.oversee;

import android.content.Context;

import com.micro.hook.config.Hook;
import com.micro.hook.plugin.Plugin;

/**
 * @Author KiLin
 * @Time 2020/4/9 11:15
 */
public abstract class Oversee extends Plugin<OverseePresenter, OverseeInter> implements OverseeInter {

    protected Oversee(Hook hook, Context context) throws Throwable {
        super(hook, context);
        presenter.setHook(hook);
    }

    @Override
    protected OverseePresenter getPresenter() {
        return OverseePresenter.getInstance();
    }

    @Override
    public Context getIContext() {
        return presenter.getContext();
    }
}