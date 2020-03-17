package com.micro.original.change;

import com.micro.original.envelop.XHelpers;
import com.micro.foreign.ForeignHook;
import com.micro.foreign.ForeignUnHook;
import com.micro.foreign.IHelpersChange;

import de.robv.android.xposed.XC_MethodHook;

/**
 * created by kilin on 20-3-9 下午1:50
 */
public class ChangeHelpers implements IHelpersChange {

    @Override
    public Class<?> findClass(String className, ClassLoader classLoader) {
        return XHelpers.findClass(className, classLoader);
    }

    @Override
    public ForeignUnHook classMonitor(Class<?> clazz, ForeignHook callback, Object... parameterTypes) {
        XC_MethodHook.Unhook unhook = XHelpers.classMonitor(clazz, new NativeMethod(callback), parameterTypes);
        return new NativeUnhook(unhook);
    }

    @Override
    public ForeignUnHook classMonitor(String clazzName, ClassLoader classLoader, ForeignHook callback, Object... parameterTypes) {
        XC_MethodHook.Unhook unhook = XHelpers.classMonitor(clazzName, classLoader, new NativeMethod(callback), parameterTypes);
        return new NativeUnhook(unhook);
    }

    @Override
    public ForeignUnHook methodMonitor(Class<?> clazz, String methodName, ForeignHook callback, Object... parameterTypes) {
        XC_MethodHook.Unhook unhook = XHelpers.methodMonitor(clazz, methodName, new NativeMethod(callback), parameterTypes);
        return new NativeUnhook(unhook);
    }

    @Override
    public ForeignUnHook methodMonitor(String clazzName, ClassLoader classLoader, String methodName, ForeignHook callback, Object... parameterTypes) {
        XC_MethodHook.Unhook unhook = XHelpers.methodMonitor(clazzName, classLoader, methodName, new NativeMethod(callback), parameterTypes);
        return new NativeUnhook(unhook);
    }

    @Override
    public void setField(Object treat, String fieldName, Object value) {
        XHelpers.setField(treat, fieldName, value);
    }

    @Override
    public void setCharField(Object treat, String fieldName, char value) {
        XHelpers.setCharField(treat, fieldName, value);
    }

    @Override
    public Object getField(Object treat, String fieldName) {
        return XHelpers.getField(treat, fieldName);
    }

    @Override
    public Boolean getBooleanField(Object treat, String fieldName) {
        return XHelpers.getBooleanField(treat, fieldName);
    }

    @Override
    public Byte getByteField(Object treat, String fieldName) {
        return XHelpers.getByteField(treat, fieldName);
    }

    @Override
    public Double getDoubleField(Object treat, String fieldName) {
        return XHelpers.getDoubleField(treat, fieldName);
    }

    @Override
    public Float getFloatField(Object treat, String fieldName) {
        return XHelpers.getFloatField(treat, fieldName);
    }

    @Override
    public Integer getIntegerField(Object treat, String fieldName) {
        return XHelpers.getIntegerField(treat, fieldName);
    }

    @Override
    public Long getLongField(Object treat, String fieldName) {
        return XHelpers.getLongField(treat, fieldName);
    }

    @Override
    public Short getShortField(Object treat, String fieldName) {
        return XHelpers.getShortField(treat, fieldName);
    }

    @Override
    public char getCharField(Object treat, String fieldName) {
        return XHelpers.getCharField(treat, fieldName);
    }

    @Override
    public void setStaticField(String clazzName, ClassLoader classLoader, String fieldName, Object value) {
        XHelpers.setStaticField(clazzName, classLoader, fieldName, value);
    }

    @Override
    public void setStaticField(Class<?> clazz, String fieldName, Object value) {
        XHelpers.setStaticField(clazz, fieldName, value);
    }

    @Override
    public void setStaticCharField(String clazzName, ClassLoader classLoader, String fieldName, char value) {
        XHelpers.setStaticCharField(clazzName, classLoader, fieldName, value);
    }

    @Override
    public void setStaticCharField(Class<?> clazz, String fieldName, char value) {
        XHelpers.setStaticCharField(clazz, fieldName, value);
    }

    @Override
    public Object getStaticField(String clazzName, ClassLoader classLoader, String fieldName) {
        return XHelpers.getStaticField(clazzName, classLoader, fieldName);
    }

    @Override
    public Object getStaticField(Class<?> clazz, String fieldName) {
        return XHelpers.getStaticField(clazz, fieldName);
    }

    @Override
    public Boolean getStaticBooleanField(String clazzName, ClassLoader classLoader, String fieldName) {
        return XHelpers.getStaticBooleanField(clazzName, classLoader, fieldName);
    }

    @Override
    public Boolean getStaticBooleanField(Class<?> clazz, String fieldName) {
        return XHelpers.getStaticBooleanField(clazz, fieldName);
    }

    @Override
    public Byte getStaticByteField(String clazzName, ClassLoader classLoader, String fieldName) {
        return XHelpers.getStaticByteField(clazzName, classLoader, fieldName);
    }

    @Override
    public Byte getStaticByteField(Class<?> clazz, String fieldName) {
        return XHelpers.getStaticByteField(clazz, fieldName);
    }

    @Override
    public Double getStaticDoubleField(String clazzName, ClassLoader classLoader, String fieldName) {
        return XHelpers.getStaticDoubleField(clazzName, classLoader, fieldName);
    }

    @Override
    public Double getStaticDoubleField(Class<?> clazz, String fieldName) {
        return XHelpers.getStaticDoubleField(clazz, fieldName);
    }

    @Override
    public Float getStaticFloatField(String clazzName, ClassLoader classLoader, String fieldName) {
        return XHelpers.getStaticFloatField(clazzName, classLoader, fieldName);
    }

    @Override
    public Float getStaticFloatField(Class<?> clazz, String fieldName) {
        return XHelpers.getStaticFloatField(clazz, fieldName);
    }

    @Override
    public Integer getStaticIntegerField(String clazzName, ClassLoader classLoader, String fieldName) {
        return XHelpers.getStaticIntegerField(clazzName, classLoader, fieldName);
    }

    @Override
    public Integer getStaticIntegerField(Class<?> clazz, String fieldName) {
        return XHelpers.getStaticIntegerField(clazz, fieldName);
    }

    @Override
    public Long getStaticLongField(String clazzName, ClassLoader classLoader, String fieldName) {
        return XHelpers.getStaticLongField(clazzName, classLoader, fieldName);
    }

    @Override
    public Long getStaticLongField(Class<?> clazz, String fieldName) {
        return XHelpers.getStaticLongField(clazz, fieldName);
    }

    @Override
    public Short getStaticShortField(String clazzName, ClassLoader classLoader, String fieldName) {
        return XHelpers.getStaticShortField(clazzName, classLoader, fieldName);
    }

    @Override
    public Short getStaticShortField(Class<?> clazz, String fieldName) {
        return XHelpers.getStaticShortField(clazz, fieldName);
    }

    @Override
    public char getStaticCharField(String clazzName, ClassLoader classLoader, String fieldName) {
        return XHelpers.getStaticCharField(clazzName, classLoader, fieldName);
    }

    @Override
    public char getStaticCharField(Class<?> clazz, String fieldName) {
        return XHelpers.getStaticCharField(clazz, fieldName);
    }

    @Override
    public Object callMethod(Object treat, String methodName, Object... args) {
        return XHelpers.callMethod(treat, methodName, args);
    }

    @Override
    public Object callStaticMethod(String clazzName, ClassLoader classLoader, String methodName, Object... args) {
        return XHelpers.callStaticMethod(clazzName, classLoader, methodName, args);
    }

    @Override
    public Object callStaticMethod(Class<?> clazz, String methodName, Object... args) {
        return XHelpers.callStaticMethod(clazz, methodName, args);
    }

    @Override
    public Object newInstance(String clazzName, ClassLoader classLoader, Object... args) {
        return XHelpers.newInstance(clazzName, classLoader, args);
    }

    @Override
    public Object newInstance(Class<?> clazz, Object... args) {
        return XHelpers.newInstance(clazz, args);
    }
}