package com.micro.tremolo.inflood.inner.execute;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;

import com.micro.foreign.ForeignHook;
import com.micro.foreign.ForeignHookParam;
import com.micro.hook.config.Hook;
import com.micro.hook.plugin.PluginInter;
import com.micro.tremolo.inflood.version.TremoloParam;

import static com.micro.tremolo.Const.monitorLogger;

public class HideDialog implements PluginInter {

    private final Hook hook;
    private final Context context;
    private final Handler handler;

    public HideDialog(Hook hook, Context context) {
        this.hook = hook;
        this.context = context;
        this.handler = new Handler(getIContext().getMainLooper());
    }

    @Override
    public void monitor() {
        hook.methodMonitor(TremoloParam.DIALOG_CLASS, TremoloParam.DIALOG_ONE_METHOD, new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                monitorLogger.d("underage view a()");
                handler.postDelayed(() -> {
                    hide(param.getResult());
                }, second);
            }
        });
        hook.methodMonitor(TremoloParam.DIALOG_CLASS, TremoloParam.DIALOG_TWO_METHOD, new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                monitorLogger.d("underage view b()");
                handler.postDelayed(() -> {
                    hide(param.getResult());
                }, second);
            }
        });
        hook.methodMonitor(TremoloParam.DIALOG_INFO_CLASS, TremoloParam.DIALOG_INFO_CREATE_METHOD, new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                monitorLogger.d("my content view");
                handler.postDelayed(() -> {
                    hide(param.getThisObject());
                }, second);
            }
        }, Bundle.class);
        hook.methodMonitor(TremoloParam.DIALOG_UPDATE_CLASS, TremoloParam.DIALOG_UPDATE_CREATE_METHOD, new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                monitorLogger.d("update view");
                handler.postDelayed(() -> {
                    hide(param.getThisObject());
                }, second);
            }
        }, Bundle.class);
    }

    @Override
    public Context getIContext() {
        return context;
    }

    private synchronized void hide(Object object) {
        if (object instanceof Dialog) {
            ((Dialog) object).hide();
            /*hook.callMethod(object, TremoloParam.DIALOG_HIDE_METHOD);*/
        }
    }
}