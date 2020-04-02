package com.micro.network.available;

import android.app.Application;

import com.micro.network.available.interfaces.RPCParam;
import com.micro.network.available.interfaces.TokenCheck;

/**
 * @Author KiLin
 * @Time 2020/4/2 14:11
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