package com.micro.tremolo.inflood.inner.execute.monitor.main_activity;

import android.content.Context;
import android.os.Bundle;

import com.micro.foreign.ForeignHook;
import com.micro.foreign.ForeignHookParam;
import com.micro.hook.config.Hook;
import com.micro.hook.plugin.Plugin;
import com.micro.tremolo.inflood.inner.execute.AutoUiControl;
import com.micro.tremolo.inflood.version.TremoloParam;
import com.micro.tremolo.sqlite.from.Author;
import com.micro.tremolo.sqlite.from.Video;

import java.util.List;

import static com.micro.tremolo.Const.monitorLogger;

/**
 * @Author KiLin
 * @Time 2020/4/3 12:16
 */
public class MainActivityOversee extends Plugin<MainActivityPresenter, MainActivityInter> implements MainActivityInter {

    private final AutoUiControl autoUiControl;

    public MainActivityOversee(Hook hook, Context context, AutoUiControl autoUiControl) throws Throwable {
        super(hook, context);
        this.autoUiControl = autoUiControl;
    }

    @Override
    protected MainActivityPresenter getPresenter() {
        return new MainActivityPresenter();
    }

    @Override
    public void monitor() {
        String MainActivity = TremoloParam.AWEME_MAIN_ACTIVITY_CLASS;
        hook.methodMonitor(MainActivity, TremoloParam.AWEME_MAIN_ACTIVITY_CHANGE_METHOD, new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                monitorLogger.d("Main Activity onCreate ");
                if (autoUiControl == null) {
                    monitorLogger.e("自动控制 未实例");
                    return;
                }
                autoUiControl.setMainActivity(param.getThisObject());
            }
        }, Bundle.class);
    }

    @Override
    public Context getIContext() {
        return presenter.getContext();
    }

    @Override
    public void profileInfo(Author author) {

    }

    @Override
    public void videoListInfo(List<Video> videos) {

    }
}