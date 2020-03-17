package com.micro.original.change;

import android.os.Bundle;

import com.micro.foreign.ForeignHookParam;

import java.lang.reflect.Member;

import de.robv.android.xposed.XC_MethodHook;

/**
 * created by kilin on 19-12-13 下午1:55
 */
public class NativeParam implements ForeignHookParam {

    private final XC_MethodHook.MethodHookParam param;

    public NativeParam(XC_MethodHook.MethodHookParam param) {
        this.param = param;
    }

    @Override
    public Object getThisObject() {
        return param.thisObject;
    }

    @Override
    public Object[] getArgs() {
        return param.args;
    }

    @Override
    public Member getMethod() {
        return param.method;
    }

    @Override
    public Object getResult() {
        return param.getResult();
    }

    @Override
    public void setResult(Object result) {
        param.setResult(result);
    }

    @Override
    public Throwable getThrowable() {
        return param.getThrowable();
    }

    @Override
    public void setThrowable(Throwable throwable) {
        param.setThrowable(throwable);
    }

    @Override
    public boolean hasThrowable() {
        return param.hasThrowable();
    }

    @Override
    public Object getResultOrThrowable() throws Throwable {
        return param.getResultOrThrowable();
    }

    @Override
    public Bundle getExtra() {
        return param.getExtra();
    }

    @Override
    public Object getObjectExtra(String key) {
        return param.getObjectExtra(key);
    }

    @Override
    public void setObjectExtra(String key, Object value) {
        param.setObjectExtra(key, value);
    }
}