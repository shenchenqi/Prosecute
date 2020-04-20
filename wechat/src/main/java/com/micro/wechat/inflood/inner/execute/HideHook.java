package com.micro.wechat.inflood.inner.execute;

import android.content.Context;

import com.micro.foreign.ForeignHook;
import com.micro.foreign.ForeignHookParam;
import com.micro.hook.config.Hook;
import com.micro.hook.plugin.PluginInter;
import com.micro.wechat.inflood.version.WeChatParam;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static com.micro.wechat.Const.monitorLogger;

/**
 * @Author: Kirin
 * @CreateDate: 2020/4/20 11:06
 */
public class HideHook implements PluginInter {

    private final Hook hook;

    public HideHook(Hook hook) {
        this.hook = hook;
    }

    @Override
    public void monitor() {
        hook.methodMonitor(WeChatParam.LOGIC_NATIVE_CLASS, WeChatParam.LOGIC_NATIVE_JAR_METHOD, new ForeignHook() {
            @Override
            public void beforeHookedMethod(ForeignHookParam param) throws Throwable {
                Set<String> args = (Set<String>) param.getArgs()[1];
                for (Iterator<String> iterator = args.iterator(); iterator.hasNext(); ) {
                    String line = iterator.next();
                    monitorLogger.d("wxLog line : %s ", line);
                    if (line.contains("/data@app@")) {
                        iterator.remove();
                    } else if (line.contains("XposedBridge.jar")) {
                        iterator.remove();
                    } else if (line.contains("xposed-89.jar")) {
                        iterator.remove();
                    }
                }
            }
        }, Context.class, Set.class, boolean.class);

        hook.methodMonitor(java.lang.Throwable.class, "getInternalStackTrace", foreignHook);
        hook.methodMonitor(java.lang.Throwable.class, "getOurStackTrace", foreignHook);
        hook.methodMonitor(java.lang.Thread.class, "getStackTrace", foreignHook);
    }

    private ForeignHook foreignHook = new ForeignHook(){
        @Override
        public void beforeHookedMethod(ForeignHookParam param) throws Throwable {
            StackTraceElement[] traceElements = (StackTraceElement[]) param.getResult();
            if (null == traceElements || traceElements.length < 1) {
                return;
            }
            List<StackTraceElement> elementList = new LinkedList<StackTraceElement>();
            for (StackTraceElement element : traceElements) {
                if ("<Xposed>".equals(element.getFileName())) {
                    hook.setField(element, "fileName", null);
                }
                if (!element.getClassName().startsWith("de.robv.android.xposed")) {
                    elementList.add(element);
                } else if (!element.getClassName().startsWith("com.micro.prosecute")) {
                    elementList.add(element);
                }
            }
            param.setResult(elementList.toArray(new StackTraceElement[0]));
        }
    };

    @Override
    public Context getIContext() {
        return null;
    }
}