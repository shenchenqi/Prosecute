package com.micro.wechat.inflood.inner.execute.monitor.oversee;

import com.micro.hook.config.Hook;
import com.micro.hook.plugin.PluginPresenter;

/**
 * @Author KiLin
 * @Time 2020/4/7 13:08
 */
public class OverseePresenter extends PluginPresenter<OverseeInter> {
    private static OverseePresenter mOverseePresenter;

    public static OverseePresenter getInstance() {
        if (mOverseePresenter == null) {
            mOverseePresenter = new OverseePresenter();
        }
        return mOverseePresenter;
    }

    @Override
    public void onAttached() {
    }

    private Hook hook;

    public void setHook(Hook hook) {
        this.hook = hook;
    }
}