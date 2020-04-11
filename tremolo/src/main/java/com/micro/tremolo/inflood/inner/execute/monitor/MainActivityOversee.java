package com.micro.tremolo.inflood.inner.execute.monitor;

import android.content.Context;
import android.os.Bundle;

import com.micro.foreign.ForeignHook;
import com.micro.foreign.ForeignHookParam;
import com.micro.hook.config.Hook;
import com.micro.tremolo.inflood.inner.execute.monitor.oversee.Oversee;
import com.micro.tremolo.inflood.inner.execute.task.NarrowAreaTask;
import com.micro.tremolo.inflood.inner.execute.task.WideAreaTask;
import com.micro.tremolo.inflood.version.TremoloParam;

import static com.micro.tremolo.Const.monitorLogger;

/**
 * @Author KiLin
 * @Time 2020/4/3 12:16
 */
public class MainActivityOversee extends Oversee {

    public MainActivityOversee(Hook hook, Context context) throws Throwable {
        super(hook, context);
    }

    @Override
    public void monitor() {
        String MainActivity = TremoloParam.AWEME_MAIN_ACTIVITY_CLASS;
        hook.methodMonitor(MainActivity, TremoloParam.AWEME_MAIN_ACTIVITY_CHANGE_METHOD, new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                monitorLogger.d("Main Activity onCreate ");
                presenter.setMainActivity(param.getThisObject());
                WideAreaTask.canTremoloData(getIContext());
                presenter.statusMonitor();
            }
        }, Bundle.class);
        hook.methodMonitor(MainActivity, TremoloParam.AWEME_MAIN_ACTIVITY_VIDEO_CHANGE_METHOD, new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                monitorLogger.i("Main Activity onVideoPageChangeEvent");
                NarrowAreaTask.requestData("淘宝");
            }
        }, hook.findClass(TremoloParam.AWEME_FEED_VIDEO_CLASS));
    }
}