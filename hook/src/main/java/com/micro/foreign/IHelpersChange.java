package com.micro.foreign;

/**
 * created by kilin on 20-3-9 下午1:01
 */
public interface IHelpersChange {
    Class<?> findClass(String className, ClassLoader classLoader);

    ForeignUnHook classMonitor(Class<?> clazz, ForeignHook callback, Object... parameterTypes);

    ForeignUnHook classMonitor(String clazzName, ClassLoader classLoader, ForeignHook callback, Object... parameterTypes);

    ForeignUnHook methodMonitor(Class<?> clazz, String methodName, ForeignHook callback, Object... parameterTypes);

    ForeignUnHook methodMonitor(String clazzName, ClassLoader classLoader, String methodName, ForeignHook callback, Object... parameterTypes);

    void setField(Object treat, String fieldName, Object value);

    void setCharField(Object treat, String fieldName, char value);

    Object getField(Object treat, String fieldName);

    Boolean getBooleanField(Object treat, String fieldName);

    Byte getByteField(Object treat, String fieldName);

    Double getDoubleField(Object treat, String fieldName);

    Float getFloatField(Object treat, String fieldName);

    Integer getIntegerField(Object treat, String fieldName);

    Long getLongField(Object treat, String fieldName);

    Short getShortField(Object treat, String fieldName);

    char getCharField(Object treat, String fieldName);

    void setStaticField(String clazzName, ClassLoader classLoader, String fieldName, Object value);

    void setStaticField(Class<?> clazz, String fieldName, Object value);

    void setStaticCharField(String clazzName, ClassLoader classLoader, String fieldName, char value);

    void setStaticCharField(Class<?> clazz, String fieldName, char value);

    Object getStaticField(String clazzName, ClassLoader classLoader, String fieldName);

    Object getStaticField(Class<?> clazz, String fieldName);

    Boolean getStaticBooleanField(String clazzName, ClassLoader classLoader, String fieldName);

    Boolean getStaticBooleanField(Class<?> clazz, String fieldName);

    Byte getStaticByteField(String clazzName, ClassLoader classLoader, String fieldName);

    Byte getStaticByteField(Class<?> clazz, String fieldName);

    Double getStaticDoubleField(String clazzName, ClassLoader classLoader, String fieldName);

    Double getStaticDoubleField(Class<?> clazz, String fieldName);

    Float getStaticFloatField(String clazzName, ClassLoader classLoader, String fieldName);

    Float getStaticFloatField(Class<?> clazz, String fieldName);

    Integer getStaticIntegerField(String clazzName, ClassLoader classLoader, String fieldName);

    Integer getStaticIntegerField(Class<?> clazz, String fieldName);

    Long getStaticLongField(String clazzName, ClassLoader classLoader, String fieldName);

    Long getStaticLongField(Class<?> clazz, String fieldName);

    Short getStaticShortField(String clazzName, ClassLoader classLoader, String fieldName);

    Short getStaticShortField(Class<?> clazz, String fieldName);

    char getStaticCharField(String clazzName, ClassLoader classLoader, String fieldName);

    char getStaticCharField(Class<?> clazz, String fieldName);

    Object callMethod(Object treat, String methodName, Object... args);

    Object callStaticMethod(String clazzName, ClassLoader classLoader, String methodName, Object... args);

    Object callStaticMethod(Class<?> clazz, String methodName, Object... args);

    Object newInstance(String clazzName, ClassLoader classLoader, Object... args);

    Object newInstance(Class<?> clazz, Object... args);
}