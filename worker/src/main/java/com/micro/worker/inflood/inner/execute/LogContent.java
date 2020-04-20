package com.micro.worker.inflood.inner.execute;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.micro.foreign.ForeignHook;
import com.micro.foreign.ForeignHookParam;
import com.micro.hook.config.Hook;
import com.micro.hook.plugin.PluginInter;
import com.micro.root.Logger;
import com.micro.worker.inflood.version.WorkerParam;

/**
 * @Author KiLin
 * @Time 2020/4/13 20:18
 */
public class LogContent implements PluginInter {

    private final Hook hook;

    public LogContent(Hook hook) {
        this.hook = hook;
    }

    @Override
    public void monitor() {
        String log = WorkerParam.LOG_CLASS;
        hook.methodMonitor(log, WorkerParam.LOG_VERBOSE_METHOD, new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                getLogger(JSON.toJSONString(param.getArgs()[0])).d((String) param.getArgs()[1]);
            }
        }, String.class, String.class);
        hook.methodMonitor(log, WorkerParam.LOG_DEBUG_METHOD, new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                getLogger(JSON.toJSONString(param.getArgs()[0])).d((String) param.getArgs()[1]);
            }
        }, String.class, String.class);
        hook.methodMonitor(log, WorkerParam.LOG_INFO_METHOD, new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                getLogger(JSON.toJSONString(param.getArgs()[0])).d((String) param.getArgs()[1]);
            }
        }, String.class, String.class);
        hook.methodMonitor(log, WorkerParam.LOG_WARN_METHOD, new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                getLogger(JSON.toJSONString(param.getArgs()[0])).d((String) param.getArgs()[1]);
            }
        }, String.class, String.class);
        hook.methodMonitor(log, WorkerParam.LOG_ERROR_METHOD, new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                getLogger(JSON.toJSONString(param.getArgs()[0])).d((String) param.getArgs()[1]);
            }
        }, String.class, String.class);
        hook.methodMonitor(log, WorkerParam.LOG_WARN_THROWABLE_1_METHOD, new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                getLogger(JSON.toJSONString(param.getArgs()[0])).e((Throwable) param.getArgs()[1], "");
            }
        }, String.class, Throwable.class);
        hook.methodMonitor(log, WorkerParam.LOG_ERROR_THROWABLE_2_METHOD, new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                getLogger(JSON.toJSONString(param.getArgs()[0])).e((Throwable) param.getArgs()[1], "");
            }
        }, String.class, Throwable.class);
        hook.methodMonitor(log, WorkerParam.LOG_ERROR_THROWABLE_3_METHOD, new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                getLogger(JSON.toJSONString(param.getArgs()[0])).e((Throwable) param.getArgs()[1], "");
            }
        }, String.class, Throwable.class);
        hook.methodMonitor(log, WorkerParam.LOG_VERBOSE_THROWABLE_METHOD, new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                getLogger(JSON.toJSONString(param.getArgs()[0])).e((Throwable) param.getArgs()[2], (String) param.getArgs()[1]);
            }
        }, String.class, String.class, Throwable.class);
        hook.methodMonitor(log, WorkerParam.LOG_DEBUG_THROWABLE_METHOD, new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                getLogger(JSON.toJSONString(param.getArgs()[0])).e((Throwable) param.getArgs()[2], (String) param.getArgs()[1]);
            }
        }, String.class, String.class, Throwable.class);
        hook.methodMonitor(log, WorkerParam.LOG_INFO_THROWABLE_METHOD, new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                getLogger(JSON.toJSONString(param.getArgs()[0])).e((Throwable) param.getArgs()[2], (String) param.getArgs()[1]);
            }
        }, String.class, String.class, Throwable.class);
        hook.methodMonitor(log, WorkerParam.LOG_WARN_THROWABLE_METHOD, new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                getLogger(JSON.toJSONString(param.getArgs()[0])).e((Throwable) param.getArgs()[2], (String) param.getArgs()[1]);
            }
        }, String.class, String.class, Throwable.class);
        hook.methodMonitor(log, WorkerParam.LOG_ERROR_THROWABLE_METHOD, new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                getLogger(JSON.toJSONString(param.getArgs()[0])).e((Throwable) param.getArgs()[2], (String) param.getArgs()[1]);
            }
        }, String.class, String.class, Throwable.class);
    }

    @Override
    public Context getIContext() {
        return null;
    }

    private synchronized Logger getLogger(String tag) {
        return Logger.getLogger("WorkerLog", String.format("KSLog-%s", tag));
    }
}