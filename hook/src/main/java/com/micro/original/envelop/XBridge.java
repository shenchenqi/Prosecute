package com.micro.original.envelop;

import com.micro.Const;

import java.lang.reflect.Member;
import java.util.Set;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;

/**
 * created by kilin on 20-3-9 上午9:31
 */
public class XBridge {

    /**
     * hook执行
     *
     * @param hookMethod hook对象
     * @param callback   回调
     * @return
     */
    public static XC_MethodHook.Unhook hookMethod(Member hookMethod, XC_MethodHook callback) {
        XC_MethodHook.Unhook unhook = XposedBridge.hookMethod(hookMethod, callback);
        if (unhook == null) {
            Const.hookLog.e("XBridge", "监听方法失败");
            return null;
        }
        return unhook;
    }

    /**
     * unhook执行
     *
     * @param hookMethod hook对象
     * @param callback   回调
     */
    public static void unhookMethod(Member hookMethod, XC_MethodHook callback) {
        XposedBridge.unhookMethod(hookMethod, callback);
    }

    public static Set<XC_MethodHook.Unhook> hookAllMethods(Class<?> hookClass, String methodName, XC_MethodHook callback) {
        return XposedBridge.hookAllMethods(hookClass, methodName, callback);
    }

    public static Set<XC_MethodHook.Unhook> hookAllConstructors(Class<?> hookClass, XC_MethodHook callback) {
        return XposedBridge.hookAllConstructors(hookClass, callback);
    }
}