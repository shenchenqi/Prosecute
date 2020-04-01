package com.micro.tremolo.inflood.inner.execute.monitor.dialog;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.micro.foreign.ForeignHook;
import com.micro.foreign.ForeignHookParam;
import com.micro.hook.config.Hook;
import com.micro.hook.plugin.PluginInter;
import com.micro.tremolo.inflood.version.TremoloParam;

import static com.micro.tremolo.inflood.inner.execute.Deploy.monitorLogger;

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
    public void monitor(final Hook hook) {
        /*hook.methodMonitor(TremoloParam.DIALOG_CLASS, "a", new ForeignHook(){
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                monitorLogger.d("" + JSON.toJSONString(param.getArgs()));
            }
        }, View.class);*/
        hook.methodMonitor(TremoloParam.DIALOG_UPDATE_CLASS, TremoloParam.DIALOG_UPDATE_CREATE_METHOD, new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                monitorLogger.d("update view");
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        TextView view = (TextView) hook.callMethod(param.getThisObject(), "findViewById", 2131168732);
                        if (view == null) {
                            monitorLogger.e("版本更新提示框，自动点击失败");
                        } else {
                            view.performClick();
                            monitorLogger.d("版本更新提示框，自动点击取消");
                        }
                    }
                }, second);
            }
        }, Bundle.class);
    }

    @Override
    public Context getIContext() {
        return context;
    }
}