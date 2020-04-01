package com.micro.tremolo.inflood.inner.execute.control;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.micro.foreign.ForeignHook;
import com.micro.foreign.ForeignHookParam;
import com.micro.hook.config.Hook;
import com.micro.tremolo.inflood.version.TremoloParam;

import static com.micro.tremolo.inflood.inner.execute.Deploy.controlLogger;

public class AutoControl implements ControlInter {

    private final ControlPresenter presenter;
    private final Hook hook;

    public AutoControl(Hook hook, Context context) {
        this.hook = hook;
        this.presenter = getPresenter();
        if (this.presenter != null) {
            this.presenter.setContext(context);
            this.presenter.setClass(this);
            this.presenter.setHook(hook);
        }
    }

    private ControlPresenter getPresenter() {
        return new ControlPresenter();
    }

    private Hook getHook() {
        return hook;
    }

    @Override
    public void autoControl(final Hook hook) {
        hook.methodMonitor(TremoloParam.AWEME_MAIN_ACTIVITY_CLASS, TremoloParam.AWEME_MAIN_ACTIVITY_CHANGE_METHOD, new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                if (presenter == null) {
                    controlLogger.e("当前工厂未实例");
                    return;
                }
                controlLogger.i("Load Main Activity");
                View view = (View) hook.callMethod(hook.callMethod(param.getThisObject(), "getWindow"), "getDecorView");
                presenter.setMainActivityView(view);
            }
        }, Bundle.class);
        hook.methodMonitor(TremoloParam.AWEME_MAIN_FRAGMENT_CLASS, TremoloParam.AWEME_MAIN_FRAGMENT_VIEW_CREATE_METHOD, new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                if (presenter == null) {
                    controlLogger.e("当前工厂未实例");
                    return;
                }
                controlLogger.i("Load Main Fragment View");
                View view = (View) param.getArgs()[0];
                presenter.setMainFragmentView(view);
            }
        }, View.class, Bundle.class);
        hook.methodMonitor(TremoloParam.AWEME_MAIN_PAGE_FRAGMENT_CLASS, TremoloParam.AWEME_MAIN_PAGE_FRAGMENT_VIEW_CREATE_METHOD, new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                if (presenter == null) {
                    controlLogger.e("当前工厂未实例");
                    return;
                }
                controlLogger.i("Load Main Page Fragment View");
                View view = (View) param.getArgs()[0];
                //presenter.setMainPageFragmentView(view);
            }
        }, View.class, Bundle.class);
        hook.methodMonitor(TremoloParam.AWEME_PROFILE_USER_FRAGMENT_CLASS, TremoloParam.AWEME_PROFILE_USER_FRAGMENT_VIEW_CREATE_METHOD, new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                if (presenter == null) {
                    controlLogger.e("当前工厂未实例");
                    return;
                }
                controlLogger.i("Load User Fragment View");
                View view = (View) param.getArgs()[0];
                presenter.setProfileFragmentView(view);
            }
        }, View.class, Bundle.class);
        hook.methodMonitor(TremoloParam.AWEME_PROFILE_USER_FRAGMENT_CLASS, TremoloParam.AWEME_PROFILE_USER_FRAGMENT_LOAD_USER_METHOD, new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                if (presenter == null) {
                    controlLogger.e("当前工厂未实例");
                    return;
                }
            }
        }, TremoloParam.AWEME_PROFILE_USER_CLASS);
        hook.methodMonitor(TremoloParam.AWEME_MAIN_FRAGMENT_CLASS, TremoloParam.AWEME_MAIN_FRAGMENT_VIDEO_CHANGE_METHOD, new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                if (presenter == null) {
                    controlLogger.e("当前工厂未实例");
                    return;
                }
                controlLogger.i("Video Change");
                presenter.autoMoveUser();
            }
        }, hook.findClass(TremoloParam.AWEME_FEED_VIDEO_CLASS));
        hook.methodMonitor(TremoloParam.AWEME_PROFILE_USER_FRAGMENT_CLASS, TremoloParam.AWEME_PROFILE_USER_FRAGMENT_LOAD_USER_METHOD, new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                if (presenter == null) {
                    controlLogger.e("当前工厂未实例");
                    return;
                }
                controlLogger.i("User Load");
                int count = hook.getIntegerField(param.getArgs()[0], "awemeCount");
                presenter.autoLoadMoreVideo(count);
            }
        }, TremoloParam.AWEME_PROFILE_USER_CLASS);
    }

    @Override
    public Context getIContext() {
        if (presenter != null) {
            return presenter.getContext();
        } else {
            return null;
        }
    }
}
