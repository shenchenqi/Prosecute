package com.so1spms.module.rpc.interfaces;

import android.app.Application;

/**
 * @author lixiaolei 2018/12/24
 * @Package com.etransfar.module.rpc
 * @Title: RPCParam
 * @Description: (用一句话描述该文件做什么)
 * Copyright (c) 传化公路港物流有限公司版权所有
 * Create DateTime: 2018/12/24
 */
public interface RPCParam {
    Application getApp();
    String getUUID();
    boolean isDebug();
    TokenCheck getTokenCheck();
}
