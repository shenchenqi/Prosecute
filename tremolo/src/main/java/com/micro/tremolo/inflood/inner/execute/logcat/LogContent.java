package com.micro.tremolo.inflood.inner.execute.logcat;

import android.content.Context;

import com.micro.foreign.ForeignHook;
import com.micro.foreign.ForeignHookParam;
import com.micro.hook.config.Hook;
import com.micro.hook.plugin.PluginInter;
import com.micro.root.Logger;
import com.micro.tremolo.inflood.version.TremoloParam;

/**
 * @Author KiLin
 * @Time 2020/4/3 9:23
 */
public class LogContent implements PluginInter {

    private final Hook hook;

    public LogContent(Hook hook) {
        this.hook = hook;
    }

    @Override
    public void monitor() {
        hook.methodMonitor(TremoloParam.LOG_CLASS, TremoloParam.LOG_SET_METHOD, new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                loadLogcat(hook, param.getArgs()[0]);
            }
        }, hook.findClass(TremoloParam.LOG_MSG_CLASS));
    }

    private void loadLogcat(Hook hook, Object msg) {
        int status = getStatus(hook, msg);
        int type = getType(hook, msg);
        String tag = getTag(hook, msg);
        String content = getContent(hook, msg);
        long threadId = getThreadId(hook, msg);
        boolean isLooperMain = isLooperMain(hook, msg);
        Object object = getMsg(hook, msg);
        Logger logger = Logger.getLogger("DouYinLog", String.format("DYLog [%s]", tag));
        logger.i(String.format("status[%s], type[%s], threadId[%s], isLooperMain[%s] \n content[%s] \n msg[%s]", status, type, threadId, isLooperMain, content, object == null ? "" : object.toString()));
    }

    @Override
    public Context getIContext() {
        return null;
    }

    private int getStatus(Hook hook, Object msg) {
        return hook.getIntegerField(msg, "a");
    }

    private int getType(Hook hook, Object msg) {
        return hook.getIntegerField(msg, "b");
    }

    private String getTag(Hook hook, Object msg) {
        return (String) hook.getField(msg, "c");
    }

    private String getContent(Hook hook, Object msg) {
        return (String) hook.getField(msg, "d");
    }

    private long getThreadId(Hook hook, Object msg) {
        return hook.getLongField(msg, "e");
    }

    private boolean isLooperMain(Hook hook, Object msg) {
        return hook.getBooleanField(msg, "f");
    }

    private Object getMsg(Hook hook, Object msg) {
        return hook.getField(msg, "h");
    }
}