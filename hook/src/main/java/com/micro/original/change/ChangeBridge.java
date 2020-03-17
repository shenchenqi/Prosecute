package com.micro.original.change;

import com.micro.original.envelop.XBridge;
import com.micro.foreign.ForeignHook;
import com.micro.foreign.ForeignUnHook;
import com.micro.foreign.IBridgeChange;

import java.lang.reflect.Member;
import java.util.HashSet;
import java.util.Set;

import de.robv.android.xposed.XC_MethodHook;

/**
 * created by kilin on 20-3-9 下午1:32
 */
public class ChangeBridge implements IBridgeChange {

    @Override
    public ForeignUnHook hookMethod(Member hookMethod, ForeignHook callback) {
        XC_MethodHook.Unhook unhook = XBridge.hookMethod(hookMethod, new NativeMethod(callback));
        return new NativeUnhook(unhook);
    }

    @Override
    public void unhookMethod(Member hookMethod, ForeignHook callback) {
        XBridge.unhookMethod(hookMethod, new NativeMethod(callback));
    }

    @Override
    public Set<ForeignUnHook> hookAllMethods(Class<?> hookClass, String methodName, ForeignHook callback) {
        Set<XC_MethodHook.Unhook> unhookSet = XBridge.hookAllMethods(hookClass, methodName, new NativeMethod(callback));
        return wrapUnHooks(unhookSet);
    }

    @Override
    public Set<ForeignUnHook> hookAllConstructors(Class<?> hookClass, ForeignHook callback) {
        Set<XC_MethodHook.Unhook> unhookSet = XBridge.hookAllConstructors(hookClass, new NativeMethod(callback));
        return wrapUnHooks(unhookSet);
    }

    private Set<ForeignUnHook> wrapUnHooks(Set<XC_MethodHook.Unhook> unhooks) {
        Set<ForeignUnHook> xUnhooks = new HashSet<>(unhooks.size());
        for (XC_MethodHook.Unhook unhook : unhooks) {
            xUnhooks.add(new NativeUnhook(unhook));
        }
        return xUnhooks;
    }
}