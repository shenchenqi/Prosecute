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
}
