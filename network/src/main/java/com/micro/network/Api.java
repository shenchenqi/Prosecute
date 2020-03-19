package com.micro.network;

import com.mirco.network.BuildConfig;

/**
 * created by kilin on 20-3-19 上午9:57
 */
public interface Api {
    /**
     * 测试服默认地址
     */
    String DEF_TEST_URL = "http://api.worken.cn/";
    /**
     * 正式服默认地址
     */
    String DEF_RELEASE_URL = "https://api.worken.cn/";
    /**
     * baseUrl
     */
    String BASE_URL = BuildConfig.DEBUG ? DEF_TEST_URL : DEF_RELEASE_URL;
}