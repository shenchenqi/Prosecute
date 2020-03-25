package com.micro.original.envelop;

import com.micro.Const;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

/**
 * created by kilin on 20-3-9 上午9:30
 */
public class XHelpers {

    /**
     * 查询类
     *
     * @param className   类名
     * @param classLoader app加载对象
     * @return 类
     */
    public static Class<?> findClass(String className, ClassLoader classLoader) {
        Class<?> clazz = XposedHelpers.findClassIfExists(className, classLoader);
        if (clazz == null) {
            Const.hookLog.e("XHelpers", String.format("类[%s]-未找到", className));
            return null;
        }
        return clazz;
    }

    /**
     * 查询方法
     *
     * @param clazz          类
     * @param methodName     方法名
     * @param parameterTypes 传入值类型
     * @return 方法
     */
    private static Method findMethod(Class<?> clazz, String methodName, Object... parameterTypes) {
        Method method = XposedHelpers.findMethodExactIfExists(clazz, methodName, parameterTypes);
        if (method == null) {
            Const.hookLog.e("XHelpers", String.format("类[%s]方法[%s]-未找到", clazz.getSimpleName(), methodName));
            return null;
        }
        return method;
    }

    /**
     * 查询对象
     *
     * @param clazz     类
     * @param fieldName 对象名
     * @return 对象
     */
    private static Field findField(Class<?> clazz, String fieldName) {
        Field field = XposedHelpers.findFieldIfExists(clazz, fieldName);
        if (field == null) {
            Const.hookLog.e("XHelpers", String.format("类[%s]对象[%s]-未找到", clazz.getSimpleName(), fieldName));
            return null;
        }
        return field;
    }

    /**
     * 创建类
     *
     * @param clazz 类
     * @param args  传入值类型
     * @return 类
     */
    private static Constructor<?> newClass(Class<?> clazz, Object... args) {
        Constructor<?> constructor = XposedHelpers.findConstructorBestMatch(clazz, args);
        if (constructor == null) {
            Const.hookLog.e("XHelpers", String.format("类[%s]-匹配未成功", clazz.getSimpleName()));
            return null;
        }
        return constructor;
    }

    /**
     * 创建方法
     *
     * @param clazz          类
     * @param methodName     方法名
     * @param parameterTypes 传入值类型
     * @return 方法
     */
    private static Method newMethod(Class<?> clazz, String methodName, Object... parameterTypes) {
        Method method = XposedHelpers.findMethodExactIfExists(clazz, methodName, parameterTypes);
        if (method == null) {
            Const.hookLog.e("XHelpers", String.format("类[%s]方法[%s]-匹配未成功", clazz.getSimpleName(), methodName));
            return null;
        }
        return method;
    }

    /**
     * 监听类
     *
     * @param clazz          类
     * @param callback       回调
     * @param parameterTypes 传入值类型
     * @return 监听类
     */
    private static XC_MethodHook.Unhook monitorClass(Class<?> clazz, XC_MethodHook callback, Object... parameterTypes) {
        Constructor<?> constructor = newClass(clazz, parameterTypes);
        if (constructor == null) {
            XC_MethodHook.Unhook unhook = XBridge.hookMethod(constructor, callback);
            if (unhook != null) {
                return unhook;
            }
            Const.hookLog.e("XHelpers", String.format("类[%s]监听-监听失败", clazz.getSimpleName()));
        }
        Const.hookLog.e("XHelpers", String.format("类[%s]监听-匹配未成功", clazz.getSimpleName()));
        return null;
    }

    public static XC_MethodHook.Unhook classMonitor(Class<?> clazz, XC_MethodHook callback, Object... parameterTypes) {
        return monitorClass(clazz, callback, parameterTypes);
    }

    public static XC_MethodHook.Unhook classMonitor(String clazzName, ClassLoader classLoader, XC_MethodHook callback, Object... parameterTypes) {
        Object[] parameterTypesAndCallback;
        if (parameterTypes.length == 0) {
            parameterTypesAndCallback = new Object[1];
            parameterTypesAndCallback[0] = callback;
        } else {
            parameterTypesAndCallback = new Object[parameterTypes.length + 1];
            for (int i = 0; i < parameterTypes.length; i++) {
                parameterTypesAndCallback[i] = parameterTypes[i];
            }
            parameterTypesAndCallback[parameterTypes.length] = callback;
        }
        return XposedHelpers.findAndHookConstructor(clazzName, classLoader, parameterTypesAndCallback);
        /*Class clazz = findClass(clazzName, classLoader);
        if (clazz != null) {
            return monitorClass(clazz, callback, parameterTypes);
        }
        return null;*/
    }

    /**
     * 监听方法
     *
     * @param clazz          类
     * @param methodName     方法名
     * @param callback       回调
     * @param parameterTypes 传入值类型
     * @return 监听方法
     */
    private static XC_MethodHook.Unhook monitorMethod(Class<?> clazz, String methodName, XC_MethodHook callback, Object... parameterTypes) {
        Object[] parameterTypesAndCallback;
        if (parameterTypes.length == 0) {
            parameterTypesAndCallback = new Object[1];
            parameterTypesAndCallback[0] = callback;
        } else {
            parameterTypesAndCallback = new Object[parameterTypes.length + 1];
            for (int i = 0; i < parameterTypes.length; i++) {
                parameterTypesAndCallback[i] = parameterTypes[i];
            }
            parameterTypesAndCallback[parameterTypes.length] = callback;
        }
        Method method = findMethod(clazz, methodName, parameterTypesAndCallback);
        if (method != null) {
            XC_MethodHook.Unhook unhook = XBridge.hookMethod(method, callback);
            if (unhook != null) {
                return unhook;
            }
            Const.hookLog.e("XHelpers", String.format("类[%s]方法[%s]监听-监听失败", clazz.getSimpleName(), methodName));
        }
        Const.hookLog.e("XHelpers", String.format("类[%s]方法[%s]监听-匹配未成功", clazz.getSimpleName(), methodName));
        return null;
    }

    public static XC_MethodHook.Unhook methodMonitor(Class<?> clazz, String methodName, XC_MethodHook callback, Object... parameterTypes) {
        return monitorMethod(clazz, methodName, callback, parameterTypes);
    }

    public static XC_MethodHook.Unhook methodMonitor(String clazzName, ClassLoader classLoader, String methodName, XC_MethodHook callback, Object... parameterTypes) {
        Object[] parameterTypesAndCallback;
        if (parameterTypes.length == 0) {
            parameterTypesAndCallback = new Object[1];
            parameterTypesAndCallback[0] = callback;
        } else {
            parameterTypesAndCallback = new Object[parameterTypes.length + 1];
            for (int i = 0; i < parameterTypes.length; i++) {
                parameterTypesAndCallback[i] = parameterTypes[i];
            }
            parameterTypesAndCallback[parameterTypes.length] = callback;
        }
        return XposedHelpers.findAndHookMethod(clazzName, classLoader, methodName, parameterTypesAndCallback);
        /*Class clazz = findClass(clazzName, classLoader);
        if (clazz != null) {
            return monitorMethod(clazz, methodName, callback, parameterTypes);
        }
        return null;*/
    }

    public static void setField(Object treat, String fieldName, Object value) {
        if (value instanceof Boolean) {
            XposedHelpers.setBooleanField(treat, fieldName, (boolean) value);
        } else if (value instanceof Byte) {
            XposedHelpers.setByteField(treat, fieldName, (byte) value);
        } else if (value instanceof Double) {
            XposedHelpers.setDoubleField(treat, fieldName, (double) value);
        } else if (value instanceof Float) {
            XposedHelpers.setFloatField(treat, fieldName, (float) value);
        } else if (value instanceof Integer) {
            XposedHelpers.setIntField(treat, fieldName, (int) value);
        } else if (value instanceof Long) {
            XposedHelpers.setLongField(treat, fieldName, (long) value);
        } else if (value instanceof Short) {
            XposedHelpers.setLongField(treat, fieldName, (short) value);
        } else {
            XposedHelpers.setObjectField(treat, fieldName, value);
        }
        /*Field field = findField(treat.getClass(), fieldName);
        if (field != null) {
            try {
                if (value instanceof Boolean) {
                    field.setBoolean(treat, (boolean) value);
                } else if (value instanceof Byte) {
                    field.setByte(treat, (byte) value);
                } else if (value instanceof Double) {
                    field.setDouble(treat, (double) value);
                } else if (value instanceof Float) {
                    field.setFloat(treat, (float) value);
                } else if (value instanceof Integer) {
                    field.setInt(treat, (int) value);
                } else if (value instanceof Long) {
                    field.setLong(treat, (long) value);
                } else if (value instanceof Short) {
                    field.setLong(treat, (short) value);
                } else {
                    field.set(treat, value);
                }
            } catch (Throwable e) {
                Const.hookLog.e(e, "XHelpers", String.format("类[%s]私有对象[%s]-设置值报错", treat.getClass().getSimpleName(), fieldName));
            }
        }
        Const.hookLog.e("XHelpers", String.format("类[%s]私有对象[%s]-匹配未成功", treat.getClass().getSimpleName(), fieldName));*/
    }

    public static void setCharField(Object treat, String fieldName, char value) {
        XposedHelpers.setCharField(treat, fieldName, value);
        /*Field field = findField(treat.getClass(), fieldName);
        if (field != null) {
            try {
                field.setChar(treat, value);
            } catch (Throwable e) {
                Const.hookLog.e(e, "XHelpers", String.format("类[%s]私有对象[%s]-设置值报错", treat.getClass().getSimpleName(), fieldName));
            }
        }
        Const.hookLog.e("XHelpers", String.format("类[%s]私有对象[%s]-匹配未成功", treat.getClass().getSimpleName(), fieldName));*/
    }

    public static Object getField(Object treat, String fieldName) {
        return XposedHelpers.getObjectField(treat, fieldName);
        /*Field field = findField(treat.getClass(), fieldName);
        if (field != null) {
            try {
                return field.get(treat);
            } catch (Throwable e) {
                Const.hookLog.e(e, "XHelpers", String.format("类[%s]私有对象[%s]-拿取值报错", treat.getClass().getSimpleName(), fieldName));
            }
        }
        Const.hookLog.e("XHelpers", String.format("类[%s]私有对象[%s]-匹配未成功", treat.getClass().getSimpleName(), fieldName));
        return null;*/
    }

    public static Boolean getBooleanField(Object treat, String fieldName) {
        return XposedHelpers.getBooleanField(treat, fieldName);
        /*Field field = findField(treat.getClass(), fieldName);
        if (field != null) {
            try {
                return field.getBoolean(treat);
            } catch (Throwable e) {
                Const.hookLog.e(e, "XHelpers", String.format("类[%s]私有对象[%s]-拿取值报错", treat.getClass().getSimpleName(), fieldName));
            }
        }
        Const.hookLog.e("XHelpers", String.format("类[%s]私有对象[%s]-匹配未成功", treat.getClass().getSimpleName(), fieldName));
        return null;*/
    }

    public static Byte getByteField(Object treat, String fieldName) {
        return XposedHelpers.getByteField(treat, fieldName);
        /*Field field = findField(treat.getClass(), fieldName);
        if (field != null) {
            try {
                return field.getByte(treat);
            } catch (Throwable e) {
                Const.hookLog.e(e, "XHelpers", String.format("类[%s]私有对象[%s]-拿取值报错", treat.getClass().getSimpleName(), fieldName));
            }
        }
        Const.hookLog.e("XHelpers", String.format("类[%s]私有对象[%s]-匹配未成功", treat.getClass().getSimpleName(), fieldName));
        return null;*/
    }

    public static Double getDoubleField(Object treat, String fieldName) {
        return XposedHelpers.getDoubleField(treat, fieldName);
        /*Field field = findField(treat.getClass(), fieldName);
        if (field != null) {
            try {
                return field.getDouble(treat);
            } catch (Throwable e) {
                Const.hookLog.e(e, "XHelpers", String.format("类[%s]私有对象[%s]-拿取值报错", treat.getClass().getSimpleName(), fieldName));
            }
        }
        Const.hookLog.e("XHelpers", String.format("类[%s]私有对象[%s]-匹配未成功", treat.getClass().getSimpleName(), fieldName));
        return null;*/
    }

    public static Float getFloatField(Object treat, String fieldName) {
        return XposedHelpers.getFloatField(treat, fieldName);
        /*Field field = findField(treat.getClass(), fieldName);
        if (field != null) {
            try {
                return field.getFloat(treat);
            } catch (Throwable e) {
                Const.hookLog.e(e, "XHelpers", String.format("类[%s]私有对象[%s]-拿取值报错", treat.getClass().getSimpleName(), fieldName));
            }
        }
        Const.hookLog.e("XHelpers", String.format("类[%s]私有对象[%s]-匹配未成功", treat.getClass().getSimpleName(), fieldName));
        return null;*/
    }

    public static Integer getIntegerField(Object treat, String fieldName) {
        return XposedHelpers.getIntField(treat, fieldName);
        /*Field field = findField(treat.getClass(), fieldName);
        if (field != null) {
            try {
                return field.getInt(treat);
            } catch (Throwable e) {
                Const.hookLog.e(e, "XHelpers", String.format("类[%s]私有对象[%s]-拿取值报错", treat.getClass().getSimpleName(), fieldName));
            }
        }
        Const.hookLog.e("XHelpers", String.format("类[%s]私有对象[%s]-匹配未成功", treat.getClass().getSimpleName(), fieldName));
        return null;*/
    }

    public static Long getLongField(Object treat, String fieldName) {
        return XposedHelpers.getLongField(treat, fieldName);
        /*Field field = findField(treat.getClass(), fieldName);
        if (field != null) {
            try {
                return field.getLong(treat);
            } catch (Throwable e) {
                Const.hookLog.e(e, "XHelpers", String.format("类[%s]私有对象[%s]-拿取值报错", treat.getClass().getSimpleName(), fieldName));
            }
        }
        Const.hookLog.e("XHelpers", String.format("类[%s]私有对象[%s]-匹配未成功", treat.getClass().getSimpleName(), fieldName));
        return null;*/
    }

    public static Short getShortField(Object treat, String fieldName) {
        return XposedHelpers.getShortField(treat, fieldName);
        /*Field field = findField(treat.getClass(), fieldName);
        if (field != null) {
            try {
                return field.getShort(treat);
            } catch (Throwable e) {
                Const.hookLog.e(e, "XHelpers", String.format("类[%s]私有对象[%s]-拿取值报错", treat.getClass().getSimpleName(), fieldName));
            }
        }
        Const.hookLog.e("XHelpers", String.format("类[%s]私有对象[%s]-匹配未成功", treat.getClass().getSimpleName(), fieldName));
        return null;*/
    }

    public static char getCharField(Object treat, String fieldName) {
        return XposedHelpers.getCharField(treat, fieldName);
        /*Field field = findField(treat.getClass(), fieldName);
        if (field != null) {
            try {
                return field.getChar(treat);
            } catch (Throwable e) {
                Const.hookLog.e(e, "XHelpers", String.format("类[%s]私有对象[%s]-拿取值报错", treat.getClass().getSimpleName(), fieldName));
            }
        }
        Const.hookLog.e("XHelpers", String.format("类[%s]私有对象[%s]-匹配未成功", treat.getClass().getSimpleName(), fieldName));
        return 0;*/
    }

    public static void setStaticField(String clazzName, ClassLoader classLoader, String fieldName, Object value) {
        Class clazz = findClass(clazzName, classLoader);
        if (clazz != null) {
            setStaticField(clazz, fieldName, value);
        }
    }

    public static void setStaticField(Class<?> clazz, String fieldName, Object value) {
        if (value instanceof Boolean) {
            XposedHelpers.setStaticBooleanField(clazz, fieldName, (boolean) value);
        } else if (value instanceof Byte) {
            XposedHelpers.setStaticByteField(clazz, fieldName, (byte) value);
        } else if (value instanceof Double) {
            XposedHelpers.setStaticDoubleField(clazz, fieldName, (double) value);
        } else if (value instanceof Float) {
            XposedHelpers.setStaticFloatField(clazz, fieldName, (float) value);
        } else if (value instanceof Integer) {
            XposedHelpers.setStaticIntField(clazz, fieldName, (int) value);
        } else if (value instanceof Long) {
            XposedHelpers.setStaticLongField(clazz, fieldName, (long) value);
        } else if (value instanceof Short) {
            XposedHelpers.setStaticLongField(clazz, fieldName, (short) value);
        } else {
            XposedHelpers.setStaticObjectField(clazz, fieldName, value);
        }
        /*Field field = findField(clazz, fieldName);
        if (field != null) {
            try {
                if (value instanceof Boolean) {
                    field.setBoolean(null, (boolean) value);
                } else if (value instanceof Byte) {
                    field.setByte(null, (byte) value);
                } else if (value instanceof Double) {
                    field.setDouble(null, (double) value);
                } else if (value instanceof Float) {
                    field.setFloat(null, (float) value);
                } else if (value instanceof Integer) {
                    field.setInt(null, (int) value);
                } else if (value instanceof Long) {
                    field.setLong(null, (long) value);
                } else if (value instanceof Short) {
                    field.setLong(null, (short) value);
                } else {
                    field.set(null, value);
                }
            } catch (Throwable e) {
                Const.hookLog.e(e, "XHelpers", String.format("类[%s]公有对象[%s]-设置值报错", clazz.getSimpleName(), fieldName));
            }
        }
        Const.hookLog.e("XHelpers", String.format("类[%s]公有对象[%s]-匹配未成功", clazz.getSimpleName(), fieldName));*/
    }

    public static void setStaticCharField(String clazzName, ClassLoader classLoader, String fieldName, char value) {
        Class clazz = findClass(clazzName, classLoader);
        if (clazz != null) {
            setStaticCharField(clazz, fieldName, value);
        }
    }

    public static void setStaticCharField(Class<?> clazz, String fieldName, char value) {
        XposedHelpers.setStaticCharField(clazz, fieldName, value);
        /*Field field = findField(clazz, fieldName);
        if (field != null) {
            try {
                field.setChar(null, value);
            } catch (Throwable e) {
                Const.hookLog.e(e, "XHelpers", String.format("类[%s]公有对象[%s]-设置值报错", clazz.getSimpleName(), fieldName));
            }
        }
        Const.hookLog.e("XHelpers", String.format("类[%s]公有对象[%s]-匹配未成功", clazz.getSimpleName(), fieldName));*/
    }

    public static Object getStaticField(String clazzName, ClassLoader classLoader, String fieldName) {
        Class clazz = findClass(clazzName, classLoader);
        if (clazz != null) {
            return getStaticField(clazz, fieldName);
        }
        return null;
    }

    public static Object getStaticField(Class<?> clazz, String fieldName) {
        return XposedHelpers.getStaticObjectField(clazz, fieldName);
        /*Field field = findField(clazz, fieldName);
        if (field != null) {
            try {
                return field.get(null);
            } catch (Throwable e) {
                Const.hookLog.e(e, "XHelpers", String.format("类[%s]公有对象[%s]-拿取值报错", clazz.getSimpleName(), fieldName));
            }
        }
        Const.hookLog.e("XHelpers", String.format("类[%s]公有对象[%s]-匹配未成功", clazz.getSimpleName(), fieldName));
        return null;*/
    }

    public static Boolean getStaticBooleanField(String clazzName, ClassLoader classLoader, String fieldName) {
        Class clazz = findClass(clazzName, classLoader);
        if (clazz != null) {
            return getStaticBooleanField(clazz, fieldName);
        }
        return null;
    }

    public static Boolean getStaticBooleanField(Class<?> clazz, String fieldName) {
        return XposedHelpers.getStaticBooleanField(clazz, fieldName);
        /*Field field = findField(clazz, fieldName);
        if (field != null) {
            try {
                return field.getBoolean(null);
            } catch (Throwable e) {
                Const.hookLog.e(e, "XHelpers", String.format("类[%s]公有对象[%s]-拿取值报错", clazz.getSimpleName(), fieldName));
            }
        }
        Const.hookLog.e("XHelpers", String.format("类[%s]公有对象[%s]-匹配未成功", clazz.getSimpleName(), fieldName));
        return null;*/
    }

    public static Byte getStaticByteField(String clazzName, ClassLoader classLoader, String fieldName) {
        Class clazz = findClass(clazzName, classLoader);
        if (clazz != null) {
            return getStaticByteField(clazz, fieldName);
        }
        return null;
    }

    public static Byte getStaticByteField(Class<?> clazz, String fieldName) {
        return XposedHelpers.getStaticByteField(clazz, fieldName);
        /*Field field = findField(clazz, fieldName);
        if (field != null) {
            try {
                return field.getByte(null);
            } catch (Throwable e) {
                Const.hookLog.e(e, "XHelpers", String.format("类[%s]公有对象[%s]-拿取值报错", clazz.getSimpleName(), fieldName));
            }
        }
        Const.hookLog.e("XHelpers", String.format("类[%s]公有对象[%s]-匹配未成功", clazz.getSimpleName(), fieldName));
        return null;*/
    }

    public static Double getStaticDoubleField(String clazzName, ClassLoader classLoader, String fieldName) {
        Class clazz = findClass(clazzName, classLoader);
        if (clazz != null) {
            return getStaticDoubleField(clazz, fieldName);
        }
        return null;
    }

    public static Double getStaticDoubleField(Class<?> clazz, String fieldName) {
        return XposedHelpers.getStaticDoubleField(clazz, fieldName);
        /*Field field = findField(clazz, fieldName);
        if (field != null) {
            try {
                return field.getDouble(null);
            } catch (Throwable e) {
                Const.hookLog.e(e, "XHelpers", String.format("类[%s]公有对象[%s]-拿取值报错", clazz.getSimpleName(), fieldName));
            }
        }
        Const.hookLog.e("XHelpers", String.format("类[%s]公有对象[%s]-匹配未成功", clazz.getSimpleName(), fieldName));
        return null;*/
    }

    public static Float getStaticFloatField(String clazzName, ClassLoader classLoader, String fieldName) {
        Class clazz = findClass(clazzName, classLoader);
        if (clazz != null) {
            return getStaticFloatField(clazz, fieldName);
        }
        return null;
    }

    public static Float getStaticFloatField(Class<?> clazz, String fieldName) {
        return XposedHelpers.getStaticFloatField(clazz, fieldName);
        /*Field field = findField(clazz, fieldName);
        if (field != null) {
            try {
                return field.getFloat(null);
            } catch (Throwable e) {
                Const.hookLog.e(e, "XHelpers", String.format("类[%s]公有对象[%s]-拿取值报错", clazz.getSimpleName(), fieldName));
            }
        }
        Const.hookLog.e("XHelpers", String.format("类[%s]公有对象[%s]-匹配未成功", clazz.getSimpleName(), fieldName));
        return null;*/
    }

    public static Integer getStaticIntegerField(String clazzName, ClassLoader classLoader, String fieldName) {
        Class clazz = findClass(clazzName, classLoader);
        if (clazz != null) {
            return getStaticIntegerField(clazz, fieldName);
        }
        return null;
    }

    public static Integer getStaticIntegerField(Class<?> clazz, String fieldName) {
        return XposedHelpers.getStaticIntField(clazz, fieldName);
        /*Field field = findField(clazz, fieldName);
        if (field != null) {
            try {
                return field.getInt(null);
            } catch (Throwable e) {
                Const.hookLog.e(e, "XHelpers", String.format("类[%s]公有对象[%s]-拿取值报错", clazz.getSimpleName(), fieldName));
            }
        }
        Const.hookLog.e("XHelpers", String.format("类[%s]公有对象[%s]-匹配未成功", clazz.getSimpleName(), fieldName));
        return null;*/
    }

    public static Long getStaticLongField(String clazzName, ClassLoader classLoader, String fieldName) {
        Class clazz = findClass(clazzName, classLoader);
        if (clazz != null) {
            return getStaticLongField(clazz, fieldName);
        }
        return null;
    }

    public static Long getStaticLongField(Class<?> clazz, String fieldName) {
        return XposedHelpers.getStaticLongField(clazz, fieldName);
        /*Field field = findField(clazz, fieldName);
        if (field != null) {
            try {
                return field.getLong(null);
            } catch (Throwable e) {
                Const.hookLog.e(e, "XHelpers", String.format("类[%s]公有对象[%s]-拿取值报错", clazz.getSimpleName(), fieldName));
            }
        }
        Const.hookLog.e("XHelpers", String.format("类[%s]公有对象[%s]-匹配未成功", clazz.getSimpleName(), fieldName));
        return null;*/
    }

    public static Short getStaticShortField(String clazzName, ClassLoader classLoader, String fieldName) {
        Class clazz = findClass(clazzName, classLoader);
        if (clazz != null) {
            return getStaticShortField(clazz, fieldName);
        }
        return null;
    }

    public static Short getStaticShortField(Class<?> clazz, String fieldName) {
        return XposedHelpers.getStaticShortField(clazz, fieldName);
        /*Field field = findField(clazz, fieldName);
        if (field != null) {
            try {
                return field.getShort(null);
            } catch (Throwable e) {
                Const.hookLog.e(e, "XHelpers", String.format("类[%s]公有对象[%s]-拿取值报错", clazz.getSimpleName(), fieldName));
            }
        }
        Const.hookLog.e("XHelpers", String.format("类[%s]公有对象[%s]-匹配未成功", clazz.getSimpleName(), fieldName));
        return null;*/
    }

    public static char getStaticCharField(String clazzName, ClassLoader classLoader, String fieldName) {
        Class clazz = findClass(clazzName, classLoader);
        if (clazz != null) {
            return getStaticCharField(clazz, fieldName);
        }
        return 0;
    }

    public static char getStaticCharField(Class<?> clazz, String fieldName) {
        return XposedHelpers.getStaticCharField(clazz, fieldName);
        /*Field field = findField(clazz, fieldName);
        if (field != null) {
            try {
                return field.getChar(null);
            } catch (Throwable e) {
                Const.hookLog.e(e, "XHelpers", String.format("类[%s]公有对象[%s]-拿取值报错", clazz.getSimpleName(), fieldName));
            }
        }
        Const.hookLog.e("XHelpers", String.format("类[%s]公有对象[%s]-匹配未成功", clazz.getSimpleName(), fieldName));
        return 0;*/
    }

    public static Object callMethod(Object treat, String methodName, Object... args) {
        return XposedHelpers.callMethod(treat, methodName, args);
        /*Method method = newMethod(treat.getClass(), methodName, args);
        if (method != null) {
            try {
                return method.invoke(treat, args);
            } catch (Throwable e) {
                Const.hookLog.e(e, "XHelpers", String.format("类[%s]私有方法[%s]-创建报错", treat.getClass().getSimpleName(), methodName));
            }
        }
        Const.hookLog.e("XHelpers", String.format("类[%s]私有方法[%s]-匹配未成功", treat.getClass().getSimpleName(), methodName));
        return null;*/
    }

    public static Object callStaticMethod(String clazzName, ClassLoader classLoader, String methodName, Object... args) {
        Class clazz = findClass(clazzName, classLoader);
        if (clazz != null) {
            return callStaticMethod(clazz, methodName, args);
        }
        return null;
    }

    public static Object callStaticMethod(Class<?> clazz, String methodName, Object... args) {
        return XposedHelpers.callStaticMethod(clazz, methodName, args);
        /*Method method = newMethod(clazz, methodName, args);
        if (method != null) {
            try {
                return method.invoke(null, args);
            } catch (Throwable e) {
                Const.hookLog.e(e, "XHelpers", String.format("类[%s]公有方法[%s]-创建报错", clazz.getSimpleName(), methodName));
            }
        }
        Const.hookLog.e("XHelpers", String.format("类[%s]公有方法[%s]-匹配未成功", clazz.getSimpleName(), methodName));
        return null;*/
    }

    public static Object newInstance(String clazzName, ClassLoader classLoader, Object... args) {
        Class clazz = findClass(clazzName, classLoader);
        if (clazz != null) {
            return newInstance(clazz, args);
        }
        return null;
    }

    public static Object newInstance(Class<?> clazz, Object... args) {
        return XposedHelpers.newInstance(clazz, args);
        /*Constructor constructor = newClass(clazz, args);
        if (constructor != null) {
            try {
                return constructor.newInstance(args);
            } catch (Throwable e) {
                Const.hookLog.e(e, "XHelpers", String.format("类[%s]-创建报错", clazz.getSimpleName()));
            }
        }
        Const.hookLog.e("XHelpers", String.format("类[%s]-匹配未成功", clazz.getSimpleName()));
        return null;*/
    }
}