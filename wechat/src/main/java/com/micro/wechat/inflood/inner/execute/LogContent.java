package com.micro.wechat.inflood.inner.execute;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.micro.foreign.ForeignHook;
import com.micro.foreign.ForeignHookParam;
import com.micro.hook.config.Hook;
import com.micro.hook.plugin.PluginInter;
import com.micro.root.Logger;
import com.micro.wechat.inflood.version.WeChatParam;

import java.lang.reflect.Proxy;

/**
 * @Description: java类作用描述
 * @Author: Kirin
 * @CreateDate: 2020/4/20 10:09
 */
public class LogContent implements PluginInter {

    private final Hook hook;

    public LogContent(Hook hook) {
        this.hook = hook;
    }

    @Override
    public void monitor() {
        try {
            Class loggerInterface = hook.getClassLoader().loadClass(WeChatParam.TOOLS_LOGGER_INTER_CLASS);
            final Object logHandler = Proxy.newProxyInstance(hook.getClassLoader(), new Class[]{loggerInterface}, (proxy, method, args) -> {
                String name = method.getName();
                switch (name) {
                    case "logD":
                        getLogger().d(String.format("%s %s %s %s %s %s %s %s", args));
                        break;
                    case "logE":
                    case "logF":
                        getLogger().e(String.format("%s %s %s %s %s %s %s %s", args));
                        break;
                    case "logI":
                    case "logV":
                        getLogger().i(String.format("%s %s %s %s %s %s %s %s", args));
                        break;
                    case "logW":
                        getLogger().w(String.format("%s %s %s %s %s %s %s %s", args));
                        break;
                    case "getLogLevel":
                        return 0;
                }
                return null;
            });
            hook.callStaticMethod(WeChatParam.TOOLS_LOG_CLASS, WeChatParam.TOOLS_LOG_SDK_INIT_METHOD, new Class[]{loggerInterface}, logHandler);
            hook.methodMonitor(WeChatParam.TOOLS_LOG_CLASS, WeChatParam.TOOLS_LOG_SDK_INIT_METHOD, new ForeignHook() {
                @Override
                public void beforeHookedMethod(ForeignHookParam param) throws Throwable {
                    getLogger().i("正在设置微信日志：%s", param.getArgs()[0].getClass().getName());
                    param.getArgs()[0] = logHandler;
                }
            }, loggerInterface);
            getLogger().i("微信日志设置成功");
        } catch (Throwable e) {
            getLogger().e(e, "微信日志设置失败");
        }
        //isLogcatOpen
        hook.methodMonitor(WeChatParam.LOG_SETUP_CLASS, WeChatParam.LOG_SETUP_KEEP_METHOD, new ForeignHook() {
            @Override
            public void beforeHookedMethod(ForeignHookParam param) throws Throwable {
                param.getArgs()[5] = true;
                getLogger().i("强制开启Xlog", JSON.toJSONString(param));
            }
        }, boolean.class, String.class, String.class, Integer.class, Boolean.class, Boolean.class, String.class);

        hook.methodMonitor(WeChatParam.LOG_MARS_CLASS, WeChatParam.LOG_MARS_WRITE_METHOD, new ForeignHook() {
            @Override
            public void afterHookedMethod(ForeignHookParam param) throws Throwable {
                getLogger().d(String.format("XLog:  %s %s %s %s %s %s %s %s", param.getArgs()));
            }
        }, int.class, String.class, String.class, String.class, int.class, int.class, long.class, long.class, String.class);
        hook.methodMonitor(WeChatParam.LOG_MARS_CLASS, WeChatParam.LOG_MARS_LEVEL_METHOD, new ForeignHook() {
            @Override
            public void beforeHookedMethod(ForeignHookParam param) throws Throwable {
                param.setResult(1);
            }
        });
    }

    @Override
    public Context getIContext() {
        return null;
    }

    private synchronized Logger getLogger() {
        return Logger.getLogger("WeChatLog", "WXLog");
    }
}