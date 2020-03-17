package com.micro.original.change;

import com.micro.foreign.ForeignUnHook;

import de.robv.android.xposed.XC_MethodHook;

/**
 * created by kilin on 19-12-13 下午1:19
 */
public class NativeUnhook implements ForeignUnHook {

    private final XC_MethodHook.Unhook unhook;

    public NativeUnhook(XC_MethodHook.Unhook unhook) {
        this.unhook = unhook;
    }

    @Override
    public void unhook() {
        this.unhook.unhook();
    }
}