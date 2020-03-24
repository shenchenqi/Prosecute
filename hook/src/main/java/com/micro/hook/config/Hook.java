package com.micro.hook.config;

import com.micro.original.InitHook;
import com.micro.foreign.ForeignHook;
import com.micro.foreign.ForeignUnHook;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * created by kilin on 20-3-17 下午1:49
 */
public class Hook {

    public static Hook getInstance(ClassLoader classLoader) {
        return new Hook(classLoader);
    }

    private final ClassLoader classLoader;

    private Hook(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }

    public String getCurrentProcessName() {
        return InitHook.currentInfo("currentProcessName", classLoader);
    }

    public String getCurrentPackageName() {
        return InitHook.currentInfo("currentPackageName", classLoader);
    }

    public Class<?> findClass(String className) {
        return InitHook.findClass(className, classLoader);
    }

    public ForeignUnHook classMonitor(Class<?> clazz, ForeignHook callback, Object... parameterTypes) {
        return wrapper(InitHook.classMonitor(clazz, callback, parameterTypes));
    }

    public ForeignUnHook classMonitor(String clazzName, ForeignHook callback, Object... parameterTypes) {
        return wrapper(InitHook.classMonitor(clazzName, classLoader, callback, parameterTypes));
    }

    public ForeignUnHook methodMonitor(Class<?> clazz, String methodName, ForeignHook callback, Object... parameterTypes) {
        return wrapper(InitHook.methodMonitor(clazz, methodName, callback, parameterTypes));
    }

    public ForeignUnHook methodMonitor(String clazzName, String methodName, ForeignHook callback, Object... parameterTypes) {
        return wrapper(InitHook.methodMonitor(clazzName, classLoader, methodName, callback, parameterTypes));
    }

    public void setField(Object treat, String fieldName, Object value) {
        InitHook.setField(treat, fieldName, value);
    }

    public void setCharField(Object treat, String fieldName, char value) {
        InitHook.setCharField(treat, fieldName, value);
    }

    public Object getField(Object treat, String fieldName) {
        return InitHook.getField(treat, fieldName);
    }

    public Boolean getBooleanField(Object treat, String fieldName) {
        return InitHook.getBooleanField(treat, fieldName);
    }

    public Byte getByteField(Object treat, String fieldName) {
        return InitHook.getByteField(treat, fieldName);
    }

    public Double getDoubleField(Object treat, String fieldName) {
        return InitHook.getDoubleField(treat, fieldName);
    }

    public Float getFloatField(Object treat, String fieldName) {
        return InitHook.getFloatField(treat, fieldName);
    }

    public Integer getIntegerField(Object treat, String fieldName) {
        return InitHook.getIntegerField(treat, fieldName);
    }

    public Long getLongField(Object treat, String fieldName) {
        return InitHook.getLongField(treat, fieldName);
    }

    public Short getShortField(Object treat, String fieldName) {
        return InitHook.getShortField(treat, fieldName);
    }

    public char getCharField(Object treat, String fieldName) {
        return InitHook.getCharField(treat, fieldName);
    }

    public void setStaticField(String clazzName, String fieldName, Object value) {
        InitHook.setStaticField(clazzName, classLoader, fieldName, value);
    }

    public void setStaticField(Class<?> clazz, String fieldName, Object value) {
        InitHook.setStaticField(clazz, fieldName, value);
    }

    public void setStaticCharField(String clazzName, String fieldName, char value) {
        InitHook.setStaticCharField(clazzName, classLoader, fieldName, value);
    }

    public void setStaticCharField(Class<?> clazz, String fieldName, char value) {
        InitHook.setStaticCharField(clazz, fieldName, value);
    }

    public Object getStaticField(String clazzName, String fieldName) {
        return InitHook.getStaticField(clazzName, classLoader, fieldName);
    }

    public Object getStaticField(Class<?> clazz, String fieldName) {
        return InitHook.getStaticField(clazz, fieldName);
    }

    public Boolean getStaticBooleanField(String clazzName, String fieldName) {
        return InitHook.getStaticBooleanField(clazzName, classLoader, fieldName);
    }

    public Boolean getStaticBooleanField(Class<?> clazz, String fieldName) {
        return InitHook.getStaticBooleanField(clazz, fieldName);
    }

    public Byte getStaticByteField(String clazzName, String fieldName) {
        return InitHook.getStaticByteField(clazzName, classLoader, fieldName);
    }

    public Byte getStaticByteField(Class<?> clazz, String fieldName) {
        return InitHook.getStaticByteField(clazz, fieldName);
    }

    public Double getStaticDoubleField(String clazzName, String fieldName) {
        return InitHook.getStaticDoubleField(clazzName, classLoader, fieldName);
    }

    public Double getStaticDoubleField(Class<?> clazz, String fieldName) {
        return InitHook.getStaticDoubleField(clazz, fieldName);
    }

    public Float getStaticFloatField(String clazzName, String fieldName) {
        return InitHook.getStaticFloatField(clazzName, classLoader, fieldName);
    }

    public Float getStaticFloatField(Class<?> clazz, String fieldName) {
        return InitHook.getStaticFloatField(clazz, fieldName);
    }

    public Integer getStaticIntegerField(String clazzName, String fieldName) {
        return InitHook.getStaticIntegerField(clazzName, classLoader, fieldName);
    }

    public Integer getStaticIntegerField(Class<?> clazz, String fieldName) {
        return InitHook.getStaticIntegerField(clazz, fieldName);
    }

    public Long getStaticLongField(String clazzName, String fieldName) {
        return InitHook.getStaticLongField(clazzName, classLoader, fieldName);
    }

    public Long getStaticLongField(Class<?> clazz, String fieldName) {
        return InitHook.getStaticLongField(clazz, fieldName);
    }

    public Short getStaticShortField(String clazzName, String fieldName) {
        return InitHook.getStaticShortField(clazzName, classLoader, fieldName);
    }

    public Short getStaticShortField(Class<?> clazz, String fieldName) {
        return InitHook.getStaticShortField(clazz, fieldName);
    }

    public char getStaticCharField(String clazzName, String fieldName) {
        return InitHook.getStaticCharField(clazzName, classLoader, fieldName);
    }

    public char getStaticCharField(Class<?> clazz, String fieldName) {
        return InitHook.getStaticCharField(clazz, fieldName);
    }

    public Object callMethod(Object treat, String methodName, Object... args) {
        return InitHook.callMethod(treat, methodName, args);
    }

    public Object callStaticMethod(String clazzName, String methodName, Object... args) {
        return InitHook.callStaticMethod(clazzName, classLoader, methodName, args);
    }

    public static Object method(Class<?> clazz, String methodName, Object... args) {
        return InitHook.callStaticMethod(clazz, methodName, args);
    }

    public Object callStaticMethod(Class<?> clazz, String methodName, Object... args) {
        return InitHook.callStaticMethod(clazz, methodName, args);
    }

    public Object newInstance(String clazzName, Object... args) {
        return InitHook.newInstance(clazzName, classLoader, args);
    }

    public Object newInstance(Class<?> clazz, Object... args) {
        return InitHook.newInstance(clazz, args);
    }

    public void destroy() {
        Set<Integer> hookIds = new HashSet<>(unhookWrapperConcurrentHashMap.keySet());
        for (Integer hookId : hookIds) {
            ForeignUnHook unhook = unhookWrapperConcurrentHashMap.get(hookId);
            if (null != unhook) {
                unhook.unhook();
            }
        }
    }

    private static final Map<Integer, UnhookWrapper> unhookWrapperConcurrentHashMap = new ConcurrentHashMap<>();
    private static final AtomicInteger id = new AtomicInteger(0);

    private static class UnhookWrapper implements ForeignUnHook {
        private final int hookId;
        private final ForeignUnHook delegate;

        private UnhookWrapper(ForeignUnHook delegate) {
            this.hookId = id.incrementAndGet();
            this.delegate = delegate;
            unhookWrapperConcurrentHashMap.put(hookId, this);
        }

        @Override
        public void unhook() {
            try {
                this.delegate.unhook();
            } finally {
                unhookWrapperConcurrentHashMap.remove(hookId);
            }
        }
    }

    private ForeignUnHook wrapper(ForeignUnHook unhook) {
        if (unhook instanceof UnhookWrapper) {
            return unhook;
        } else {
            return new UnhookWrapper(unhook);
        }
    }
}