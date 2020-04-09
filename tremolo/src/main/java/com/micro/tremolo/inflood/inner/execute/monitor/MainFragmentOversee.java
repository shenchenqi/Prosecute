package com.micro.tremolo.inflood.inner.execute.monitor;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.micro.foreign.ForeignHook;
import com.micro.foreign.ForeignHookParam;
import com.micro.hook.config.Hook;
import com.micro.tremolo.inflood.inner.execute.monitor.oversee.Oversee;
import com.micro.tremolo.inflood.inner.replace.Aweme;
import com.micro.tremolo.inflood.version.TremoloParam;

import static com.micro.tremolo.Const.monitorLogger;

/**
 * @Author KiLin
 * @Time 2020/4/3 12:18
 */
public class MainFragmentOversee extends Oversee {

    public MainFragmentOversee(Hook hook, Context context) throws Throwable {
        super(hook, context);
    }

    @Override
    public void monitor() {
        String MainFragment = TremoloParam.AWEME_MAIN_FRAGMENT_CLASS;
        hook.methodMonitor(MainFragment, TremoloParam.AWEME_MAIN_FRAGMENT_VIEW_CREATE_METHOD, new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                monitorLogger.i("Main Fragment onViewCreated");
                presenter.setMainFragmentView((View) param.getArgs()[0]);
            }
        }, View.class, Bundle.class);
        hook.methodMonitor(MainFragment, TremoloParam.AWEME_MAIN_FRAGMENT_VIDEO_CHANGE_METHOD, new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                monitorLogger.i("Main Fragment onVideoPageChangeEvent");
                Object videoInfo = hook.getField(param.getArgs()[0], TremoloParam.AWEME_FEED_MODEL_AWEME_FIELD);
                final Aweme aweme = new Aweme(hook, videoInfo);
                presenter.getClazz().videoInfo(aweme);
                presenter.setProfile(false);
                presenter.setData(aweme.getAid(), aweme.getAuthor().getUid(), aweme.getAuthor().getSecUid());
                presenter.apiLoadProfile();
                presenter.autoMoveUser();
            }
        }, hook.findClass(TremoloParam.AWEME_FEED_VIDEO_CLASS));
    }
}