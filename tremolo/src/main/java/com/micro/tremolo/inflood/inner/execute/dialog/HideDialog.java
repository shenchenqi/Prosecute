package com.micro.tremolo.inflood.inner.execute.dialog;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

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
        hook.methodMonitor(TremoloParam.DIALOG_CLASS, TremoloParam.DIALOG_VIEW_METHOD, new ForeignHook(){
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                monitorLogger.d("underage view");
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        TextView view = (TextView) hook.callMethod(param.getThisObject(), TremoloParam.BIND_VIEW, TremoloParam.UNDERAGE_CANCEL_INTEGER);
                        if (view == null) {
                            monitorLogger.e("未成年 提示框，自动点击失败");
                        } else {
                            view.performClick();
                            monitorLogger.d("未成年 提示框，自动点击取消");
                        }
                    }
                }, second);
            }
        }, View.class);
        hook.methodMonitor(TremoloParam.DIALOG_INFO_CLASS, TremoloParam.DIALOG_INFO_CREATE_METHOD, new ForeignHook(){
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                monitorLogger.d("my content view");
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        TextView view = (TextView) hook.callMethod(param.getThisObject(), TremoloParam.BIND_VIEW, TremoloParam.INFO_CANCEL_INTEGER);
                        if (view == null) {
                            monitorLogger.e("个性信息 提示框，自动点击失败");
                        } else {
                            view.performClick();
                            monitorLogger.d("个性信息 提示框，自动点击取消");
                        }
                    }
                }, second);
            }
        }, Bundle.class);
        hook.methodMonitor(TremoloParam.DIALOG_UPDATE_CLASS, TremoloParam.DIALOG_UPDATE_CREATE_METHOD, new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                monitorLogger.d("update view");
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        TextView view = (TextView) hook.callMethod(param.getThisObject(), TremoloParam.BIND_VIEW, 2131168732);
                        if (view == null) {
                            monitorLogger.e("版本更新 提示框，自动点击失败");
                        } else {
                            view.performClick();
                            monitorLogger.d("版本更新 提示框，自动点击取消");
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