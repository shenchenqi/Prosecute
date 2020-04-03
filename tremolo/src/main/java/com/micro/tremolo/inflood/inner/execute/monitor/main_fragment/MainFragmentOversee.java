package com.micro.tremolo.inflood.inner.execute.monitor.main_fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.micro.foreign.ForeignHook;
import com.micro.foreign.ForeignHookParam;
import com.micro.hook.config.Hook;
import com.micro.hook.plugin.Plugin;
import com.micro.tremolo.inflood.inner.execute.control.AutoUiControl;
import com.micro.tremolo.inflood.inner.replace.Aweme;
import com.micro.tremolo.inflood.version.TremoloParam;

import static com.micro.tremolo.Const.controlLogger;
import static com.micro.tremolo.Const.monitorLogger;

/**
 * @Author KiLin
 * @Time 2020/4/3 12:18
 */
public class MainFragmentOversee extends Plugin<MainFragmentPresenter, MainFragmentInter> implements MainFragmentInter {

    private final AutoUiControl autoUiControl;

    public MainFragmentOversee(Hook hook, Context context, AutoUiControl autoUiControl) throws Throwable {
        super(hook, context);
        this.autoUiControl = autoUiControl;
    }

    @Override
    protected MainFragmentPresenter getPresenter() {
        return new MainFragmentPresenter();
    }

    @Override
    public void monitor() {
        String MainFragment = TremoloParam.AWEME_MAIN_FRAGMENT_CLASS;
        hook.methodMonitor(MainFragment, TremoloParam.AWEME_MAIN_FRAGMENT_VIEW_CREATE_METHOD, new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                monitorLogger.i("Main Fragment onViewCreated");
                if (autoUiControl == null) {
                    controlLogger.e("自动控制 未实例");
                    return;
                }
                controlLogger.i("Load Main Fragment View");
                View view = (View) param.getArgs()[0];
                autoUiControl.setMainFragmentView(view);
            }
        }, View.class, Bundle.class);
        hook.methodMonitor(MainFragment, TremoloParam.AWEME_MAIN_FRAGMENT_VIDEO_CHANGE_METHOD, new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                monitorLogger.i("Main Fragment onVideoPageChangeEvent");
                if (presenter == null) {
                    monitorLogger.e("当前工厂 未实例");
                    return;
                }
                Object videoInfo = hook.getField(param.getArgs()[0], TremoloParam.AWEME_FEED_MODEL_AWEME_FIELD);
                presenter.obtainVideo(new Aweme(hook, videoInfo));

                controlLogger.i("Video Change");
                if (autoUiControl == null) {
                    controlLogger.e("自动控制 未实例");
                    return;
                }
                autoUiControl.autoMoveUser();
            }
        }, hook.findClass(TremoloParam.AWEME_FEED_VIDEO_CLASS));
    }

    @Override
    public Context getIContext() {
        return presenter.getContext();
    }
}