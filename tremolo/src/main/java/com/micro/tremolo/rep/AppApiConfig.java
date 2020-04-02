package com.micro.tremolo.rep;

import com.micro.tremolo.ApiService;
import com.so1spms.module.rpc.ApiConfig;
import com.so1spms.module.rpc.RPCApiFactory;

/**
 * @author lixiaolei 2018/12/27
 * @Package com.transfar.habitat.rpc
 * @Title: HabitatApiConfig
 * @Description: (用一句话描述该文件做什么)
 * Copyright (c) 传化公路港物流有限公司版权所有
 * Create DateTime: 2018/12/27
 */
public class AppApiConfig {
    public static void configApi() {
        /*RPCApiFactory.addConfig(ApiConfig.config(AppHTTPApi.class)
                .mainBaseUrl("http://47.99.104.62:8099/")
                .testBaseUrl("http://47.99.104.62:8099/")
                .preOnlineBaseUrl("http://47.99.104.62:8099/")
                .build());*/

        RPCApiFactory.addConfig(ApiConfig.config(ApiService.class)
                .mainBaseUrl("http://skingsoft.cn/")
                .testBaseUrl("http://skingsoft.cn/")
                .preOnlineBaseUrl("http://skingsoft.cn/")
                .build());
    }
}
