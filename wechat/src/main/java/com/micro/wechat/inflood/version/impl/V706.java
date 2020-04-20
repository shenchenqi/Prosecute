package com.micro.wechat.inflood.version.impl;

import com.micro.wechat.inflood.version.WeChatParam;

/**
 * @Author KiLin
 * @Time 2020/4/11 16:31
 */
public class V706 extends WeChatParam {

    public V706() {
        PACKAGE = "com.tencent.mm";
        logClass();
    }

    @Override
    protected void logClass() {
        TOOLS_LOG_CLASS = PACKAGE + ".sdk.platformtools.ab";
        TOOLS_LOGGER_INTER_CLASS = TOOLS_LOG_CLASS + "$a";
        TOOLS_LOG_SDK_INIT_METHOD = "a";
        LOG_SETUP_CLASS = "com.tencent.mm.xlog.app.XLogSetup";
        LOG_SETUP_KEEP_METHOD = "keep_setupXLog";
        LOG_MARS_CLASS = "com.tencent.mars.xlog.Xlog";
        LOG_MARS_WRITE_METHOD = "logWrite2";
        LOG_MARS_LEVEL_METHOD = "getLogLevel";
        LOGIC_NATIVE_CLASS = PACKAGE + ".plugin.normsg.utils.NativeLogic";
        LOGIC_NATIVE_JAR_METHOD = "a";
    }
}