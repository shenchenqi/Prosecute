package com.so1spms.module.rpc;

import android.app.Application;
import com.so1spms.module.rpc.interfaces.RPCParam;
import com.so1spms.module.rpc.interfaces.TokenCheck;

/**
 * @author lixiaolei 2019/1/16
 * @Package com.etransfar.module.rpc
 * @Title: BaseRpcParam
 * @Description: (用一句话描述该文件做什么)
 * Copyright (c) 传化公路港物流有限公司版权所有
 * Create DateTime: 2019/1/16
 */
public abstract class BaseRpcParam implements RPCParam {
    @Override
    public abstract Application getApp();

    @Override
    public String getUUID() {
        return "";
    }

    @Override
    public abstract boolean isDebug();

    @Override
    public TokenCheck getTokenCheck() {
        return null;
    }
}
