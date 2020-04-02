package com.micro.network.available.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author KiLin
 * @Time 2020/4/2 13:12
 */
public class ReflectionUtil {
    /***
     * 获取私有成员变量的值
     *
     */
    public static Object getValue(Object instance, String fieldName)
            throws IllegalAccessException, NoSuchFieldException {

        Field field = instance.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);

        return field.get(instance);
    }

    /***
     * 设置私有成员变量的值
     *
     */
    public static void setValue(Object instance, String fileName, Object value)
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

        Field field = instance.getClass().getDeclaredField(fileName);
        field.setAccessible(true);
        field.set(instance, value);
    }

    /***
     * 访问私有方法
     *
     */
    public static Object callMethod(Object instance, String methodName, Class[] classes, Object[] objects)
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {

        Method method = instance.getClass().getDeclaredMethod(methodName, classes);
        method.setAccessible(true);
        return method.invoke(instance, objects);
    }

    /**
     * 调用静态方法
     * @return
     */
    public static Object callClassMethod(String className,String methodName, Class[] classes, Object[] objects) throws  Exception{
        Class<?> threadClazz = Class.forName(className);
        Method method = threadClazz.getMethod(methodName, classes);
        return  method.invoke(null,objects);
    }
}