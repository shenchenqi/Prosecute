package com.micro.original;

import com.micro.original.change.ChangeBridge;
import com.micro.original.change.ChangeHelpers;
import com.micro.foreign.ForeignHook;
import com.micro.foreign.ForeignUnHook;

import java.lang.reflect.Member;
import java.util.Set;

/**
 * created by kilin on 20-3-17 下午1:42
 */
public class InitHook {
    private static final ChangeBridge bridge;
    private static final ChangeHelpers helpers;

    static {
        bridge = new ChangeBridge();
        helpers = new ChangeHelpers();
    }

    public static ForeignUnHook hookMethod(Member hookMethod, ForeignHook callback) {
        return bridge.hookMethod(hookMethod, callback);
    }

    public static void unhookMethod(Member hookMethod, ForeignHook callback) {
        bridge.unhookMethod(hookMethod, callback);
    }

    public static Set<ForeignUnHook> hookAllMethods(Class<?> hookClass, String methodName, ForeignHook callback) {
        return bridge.hookAllMethods(hookClass, methodName, callback);
    }

    public static Set<ForeignUnHook> hookAllConstructors(Class<?> hookClass, ForeignHook callback) {
        return bridge.hookAllConstructors(hookClass, callback);
    }

    public static Class<?> findClass(String className, ClassLoader classLoader) {
        return helpers.findClass(className, classLoader);
    }

    public static ForeignUnHook classMonitor(Class<?> clazz, ForeignHook callback, Object... parameterTypes) {
        return helpers.classMonitor(clazz, callback, parameterTypes);
    }

    public static ForeignUnHook classMonitor(String clazzName, ClassLoader classLoader, ForeignHook callback, Object... parameterTypes) {
        return helpers.classMonitor(clazzName, classLoader, callback, parameterTypes);
    }

    public static ForeignUnHook methodMonitor(Class<?> clazz, String methodName, ForeignHook callback, Object... parameterTypes) {
        return helpers.methodMonitor(clazz, methodName, callback, parameterTypes);
    }

    public static ForeignUnHook methodMonitor(String clazzName, ClassLoader classLoader, String methodName, ForeignHook callback, Object... parameterTypes) {
        return helpers.methodMonitor(clazzName, classLoader, methodName, callback, parameterTypes);
    }

    public static void setField(Object treat, String fieldName, Object value) {
        helpers.setField(treat, fieldName, value);
    }

    public static void setCharField(Object treat, String fieldName, char value) {
        helpers.setCharField(treat, fieldName, value);
    }

    public static Object getField(Object treat, String fieldName) {
        return helpers.getField(treat, fieldName);
    }

    public static Boolean getBooleanField(Object treat, String fieldName) {
        return helpers.getBooleanField(treat, fieldName);
    }

    public static Byte getByteField(Object treat, String fieldName) {
        return helpers.getByteField(treat, fieldName);
    }

    public static Double getDoubleField(Object treat, String fieldName) {
        return helpers.getDoubleField(treat, fieldName);
    }

    public static Float getFloatField(Object treat, String fieldName) {
        return helpers.getFloatField(treat, fieldName);
    }

    public static Integer getIntegerField(Object treat, String fieldName) {
        return helpers.getIntegerField(treat, fieldName);
    }

    public static Long getLongField(Object treat, String fieldName) {
        return helpers.getLongField(treat, fieldName);
    }

    public static Short getShortField(Object treat, String fieldName) {
        return helpers.getShortField(treat, fieldName);
    }

    public static char getCharField(Object treat, String fieldName) {
        return helpers.getCharField(treat, fieldName);
    }

    public static void setStaticField(String clazzName, ClassLoader classLoader, String fieldName, Object value) {
        helpers.setStaticField(clazzName, classLoader, fieldName, value);
    }

    public static void setStaticField(Class<?> clazz, String fieldName, Object value) {
        helpers.setStaticField(clazz, fieldName, value);
    }

    public static void setStaticCharField(String clazzName, ClassLoader classLoader, String fieldName, char value) {
        helpers.setStaticCharField(clazzName, classLoader, fieldName, value);
    }

    public static void setStaticCharField(Class<?> clazz, String fieldName, char value) {
        helpers.setStaticCharField(clazz, fieldName, value);
    }

    public static Object getStaticField(String clazzName, ClassLoader classLoader, String fieldName) {
        return helpers.getStaticField(clazzName, classLoader, fieldName);
    }

    public static Object getStaticField(Class<?> clazz, String fieldName) {
        return helpers.getStaticField(clazz, fieldName);
    }

    public static Boolean getStaticBooleanField(String clazzName, ClassLoader classLoader, String fieldName) {
        return helpers.getStaticBooleanField(clazzName, classLoader, fieldName);
    }

    public static Boolean getStaticBooleanField(Class<?> clazz, String fieldName) {
        return helpers.getStaticBooleanField(clazz, fieldName);
    }

    public static Byte getStaticByteField(String clazzName, ClassLoader classLoader, String fieldName) {
        return helpers.getStaticByteField(clazzName, classLoader, fieldName);
    }

    public static Byte getStaticByteField(Class<?> clazz, String fieldName) {
        return helpers.getStaticByteField(clazz, fieldName);
    }

    public static Double getStaticDoubleField(String clazzName, ClassLoader classLoader, String fieldName) {
        return helpers.getStaticDoubleField(clazzName, classLoader, fieldName);
    }

    public static Double getStaticDoubleField(Class<?> clazz, String fieldName) {
        return helpers.getStaticDoubleField(clazz, fieldName);
    }

    public static Float getStaticFloatField(String clazzName, ClassLoader classLoader, String fieldName) {
        return helpers.getStaticFloatField(clazzName, classLoader, fieldName);
    }

    public static Float getStaticFloatField(Class<?> clazz, String fieldName) {
        return helpers.getStaticFloatField(clazz, fieldName);
    }

    public static Integer getStaticIntegerField(String clazzName, ClassLoader classLoader, String fieldName) {
        return helpers.getStaticIntegerField(clazzName, classLoader, fieldName);
    }

    public static Integer getStaticIntegerField(Class<?> clazz, String fieldName) {
        return helpers.getStaticIntegerField(clazz, fieldName);
    }

    public static Long getStaticLongField(String clazzName, ClassLoader classLoader, String fieldName) {
        return helpers.getStaticLongField(clazzName, classLoader, fieldName);
    }

    public static Long getStaticLongField(Class<?> clazz, String fieldName) {
        return helpers.getStaticLongField(clazz, fieldName);
    }

    public static Short getStaticShortField(String clazzName, ClassLoader classLoader, String fieldName) {
        return helpers.getStaticShortField(clazzName, classLoader, fieldName);
    }

    public static Short getStaticShortField(Class<?> clazz, String fieldName) {
        return helpers.getStaticShortField(clazz, fieldName);
    }

    public static char getStaticCharField(String clazzName, ClassLoader classLoader, String fieldName) {
        return helpers.getStaticCharField(clazzName, classLoader, fieldName);
    }

    public static char getStaticCharField(Class<?> clazz, String fieldName) {
        return helpers.getStaticCharField(clazz, fieldName);
    }

    public static Object callMethod(Object treat, String methodName, Object... args) {
        return helpers.callMethod(treat, methodName, args);
    }

    public static Object callStaticMethod(String clazzName, ClassLoader classLoader, String methodName, Object... args) {
        return helpers.callStaticMethod(clazzName, classLoader, methodName, args);
    }

    public static Object callStaticMethod(Class<?> clazz, String methodName, Object... args) {
        return helpers.callStaticMethod(clazz, methodName, args);
    }

    public static Object newInstance(String clazzName, ClassLoader classLoader, Object... args) {
        return helpers.newInstance(clazzName, classLoader, args);
    }

    public static Object newInstance(Class<?> clazz, Object... args) {
        return helpers.newInstance(clazz, args);
    }

    public static String currentInfo(String method, ClassLoader classLoader) {
        try {
            Class<?> activityThreadClass = findClass("android.app.ActivityThread", classLoader);
            return (String) callStaticMethod(activityThreadClass, method, new Object[0]);
        } catch (Throwable e) {
            return "";
        }
    }
}