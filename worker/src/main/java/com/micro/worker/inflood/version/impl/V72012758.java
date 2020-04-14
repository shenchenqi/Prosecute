package com.micro.worker.inflood.version.impl;

import com.micro.worker.inflood.version.WorkerParam;

/**
 * @Author KiLin
 * @Time 2020/4/11 16:31
 */
public class V72012758 extends WorkerParam {

    public V72012758() {
        logClass();
        apiClass();
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

    @Override
    protected void apiClass() {
        API_APP_CLASS = "com.yxcorp.gifshow.KwaiApp";
        API_APP_SERVICE_METHOD = "getApiService";
        API_SERVICE_CLASS = "com.yxcorp.gifshow.retrofit.service.KwaiApiService";
        API_SERVICE_USER_PROFILE_METHOD = "userProfileV2";
        API_ENUM_CONFIG_CLASS = "com.kuaishou.gifshow.network.degrade.RequestTiming";
        API_ENUM_CONFIG_DEFAULT = "DEFAULT";
        API_ENUM_CONFIG_COLD_START = "COLD_START";
        API_ENUM_CONFIG_ON_HOME_PAGE_CREATED = "ON_HOME_PAGE_CREATED";
        API_ENUM_CONFIG_ON_FOREGROUND = "ON_FOREGROUND";
        API_ENUM_CONFIG_ON_BACKGROUND = "ON_BACKGROUND";
        API_ENUM_CONFIG_LOGIN = "LOGIN";
        API_ENUM_CONFIG_LOGOUT = "LOGOUT";
        API_ENUM_CONFIG_AFTER_STARTUP = "AFTER_STARTUP";
        API_ENUM_CONFIG_AFTER_ABTEST = "AFTER_ABTEST";
        API_ENUM_CONFIG_NETWORK_CHANGED = "NETWORK_CHANGED";
        API_ENUM_CONFIG_BUSY_TIME = "BUSY_TIME";
        API_ENUM_CONFIG_BUSY_TIME_NON_DEFAULT = "BUSY_TIME_NON_DEFAULT";
    }
}