package com.micro.tremolo.version;

import static com.micro.tremolo.TremoloModule.logger;

/**
 * created by kilin on 20-3-17 下午5:05
 */
public abstract class TremoloParam {
    public static void init(String version) {
        String impl = "com.micro.tremolo.version.impl.V" + version.replaceAll("\\.", "");
        try {
            logger.i("PARAM", "初始化参数：%s", impl);
            Class implClazz = Class.forName(impl);
            implClazz.newInstance();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}