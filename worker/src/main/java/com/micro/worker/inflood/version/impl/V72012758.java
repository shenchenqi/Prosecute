package com.micro.worker.inflood.version.impl;

import com.micro.worker.inflood.version.WorkerParam;

/**
 * @Author KiLin
 * @Time 2020/4/11 16:31
 */
public class V72012758 extends WorkerParam {

    public V72012758() {
        logClass();
    }

    @Override
    protected void logClass() {
        LOG_CLASS = "com.yxcorp.utility.Log";
        LOG_VERBOSE_METHOD = "a";
        LOG_VERBOSE_THROWABLE_METHOD = "a";
        LOG_DEBUG_METHOD = "b";
        LOG_DEBUG_THROWABLE_METHOD = "b";
        LOG_INFO_METHOD = "c";
        LOG_INFO_THROWABLE_METHOD = "c";
        LOG_WARN_METHOD = "d";
        LOG_WARN_THROWABLE_METHOD = "d";
        LOG_ERROR_METHOD = "e";
        LOG_ERROR_THROWABLE_METHOD = "e";
        LOG_WARN_THROWABLE_1_METHOD = "a";
        LOG_ERROR_THROWABLE_2_METHOD = "b";
        LOG_ERROR_THROWABLE_3_METHOD = "c";
    }
}