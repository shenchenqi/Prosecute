package com.micro.original.change;

import com.micro.foreign.ForeignHook;

import de.robv.android.xposed.XC_MethodHook;

/**
 * created by kilin on 19-12-13 下午1:52
 */
public class NativeMethod extends XC_MethodHook {

    private final ForeignHook callback;

    public NativeMethod(ForeignHook callback) {
        this.callback = callback;
    }

    @Override
    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
        callback.beforeHookedMethod(new NativeParam(param));
    }

    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        callback.afterHookedMethod(new NativeParam(param));
    }
}