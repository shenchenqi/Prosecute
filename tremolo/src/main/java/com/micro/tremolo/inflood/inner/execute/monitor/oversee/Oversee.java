package com.micro.tremolo.inflood.inner.execute.monitor.oversee;

import android.content.Context;

import com.micro.hook.config.Hook;
import com.micro.hook.plugin.Plugin;
import com.micro.tremolo.inflood.inner.execute.task.WideAreaTask;

/**
 * @Author KiLin
 * @Time 2020/4/9 11:15
 */
public abstract class Oversee extends Plugin<OverseePresenter, OverseeInter> implements OverseeInter {

    protected Oversee(Hook hook, Context context) throws Throwable {
        super(hook, context);
        presenter.setHook(hook);
        presenter.initCreate();
        WideAreaTask.setOversee(this);
    }

    @Override
    protected OverseePresenter getPresenter() {
        return OverseePresenter.getInstance();
    }

    @Override
    public Context getIContext() {
        return presenter.getContext();
    }

    public void nextVideo() {
        presenter.nextVideo();
    }
}