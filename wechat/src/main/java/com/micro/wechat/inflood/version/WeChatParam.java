package com.micro.wechat.inflood.version;

import static com.micro.wechat.Const.monitorLogger;

/**
 * @Author KiLin
 * @Time 2020/4/11 16:24
 */
public abstract class WeChatParam {
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

    protected static String PACKAGE;

    protected abstract void logClass();

    public static String TOOLS_LOG_CLASS;
    public static String TOOLS_LOGGER_INTER_CLASS;
    public static String TOOLS_LOG_SDK_INIT_METHOD;
    public static String LOG_SETUP_CLASS;
    public static String LOG_SETUP_KEEP_METHOD;
    public static String LOG_MARS_CLASS;
    public static String LOG_MARS_WRITE_METHOD;
    public static String LOG_MARS_LEVEL_METHOD;
    public static String LOGIC_NATIVE_CLASS;
    public static String LOGIC_NATIVE_JAR_METHOD;
}