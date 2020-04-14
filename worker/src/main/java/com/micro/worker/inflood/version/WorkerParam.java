package com.micro.worker.inflood.version;

import static com.micro.worker.Const.monitorLogger;

/**
 * @Author KiLin
 * @Time 2020/4/11 16:24
 */
public abstract class WorkerParam {
    public static void init(String version) {
        String impl = "com.micro.worker.inflood.version.impl.V" + version.replaceAll("\\.", "");
        try {
            monitorLogger.i("PARAM", String.format("初始化参数：%s", impl));
            Class implClazz = Class.forName(impl);
            implClazz.newInstance();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract void logClass();

    public static String LOG_CLASS;
    public static String LOG_VERBOSE_METHOD;
    public static String LOG_VERBOSE_THROWABLE_METHOD;
    public static String LOG_DEBUG_METHOD;
    public static String LOG_DEBUG_THROWABLE_METHOD;
    public static String LOG_INFO_METHOD;
    public static String LOG_INFO_THROWABLE_METHOD;
    public static String LOG_WARN_METHOD;
    public static String LOG_WARN_THROWABLE_METHOD;
    public static String LOG_ERROR_METHOD;
    public static String LOG_ERROR_THROWABLE_METHOD;
    public static String LOG_WARN_THROWABLE_1_METHOD;
    public static String LOG_ERROR_THROWABLE_2_METHOD;
    public static String LOG_ERROR_THROWABLE_3_METHOD;

    protected abstract void apiClass();

    public static String API_APP_CLASS;
    public static String API_APP_SERVICE_METHOD;
    public static String API_SERVICE_CLASS;
    public static String API_SERVICE_USER_PROFILE_METHOD;
    public static String API_ENUM_CONFIG_CLASS;
    public static String API_ENUM_CONFIG_DEFAULT;
    public static String API_ENUM_CONFIG_COLD_START;
    public static String API_ENUM_CONFIG_ON_HOME_PAGE_CREATED;
    public static String API_ENUM_CONFIG_ON_FOREGROUND;
    public static String API_ENUM_CONFIG_ON_BACKGROUND;
    public static String API_ENUM_CONFIG_LOGIN;
    public static String API_ENUM_CONFIG_LOGOUT;
    public static String API_ENUM_CONFIG_AFTER_STARTUP;
    public static String API_ENUM_CONFIG_AFTER_ABTEST;
    public static String API_ENUM_CONFIG_NETWORK_CHANGED;
    public static String API_ENUM_CONFIG_BUSY_TIME;
    public static String API_ENUM_CONFIG_BUSY_TIME_NON_DEFAULT;
}
